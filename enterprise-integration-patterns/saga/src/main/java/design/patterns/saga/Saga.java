package design.patterns.saga;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Orchestrated Saga: execute steps; on failure, run compensations in reverse order.
 *
 * This is intentionally small and in-memory. In production you'd persist saga state and use timeouts/retries.
 */
public final class Saga {
    public record Result(boolean success, List<String> log) {}

    public static Builder builder(String name) {
        return new Builder(name);
    }

    private final String name;
    private final List<Step> steps;

    private Saga(String name, List<Step> steps) {
        this.name = name;
        this.steps = steps;
    }

    public String name() {
        return name;
    }

    public Result run() {
        List<String> log = new ArrayList<>();
        List<Step> completed = new ArrayList<>();
        log.add("saga.start " + name);

        for (Step step : steps) {
            log.add("step.start " + step.name);
            try {
                step.action.run();
                completed.add(step);
                log.add("step.ok " + step.name);
            } catch (RuntimeException e) {
                log.add("step.fail " + step.name + " error=" + e.getClass().getSimpleName());
                compensate(completed, log);
                log.add("saga.fail " + name);
                return new Result(false, List.copyOf(log));
            }
        }

        log.add("saga.ok " + name);
        return new Result(true, List.copyOf(log));
    }

    private static void compensate(List<Step> completed, List<String> log) {
        for (int i = completed.size() - 1; i >= 0; i--) {
            Step step = completed.get(i);
            if (step.compensation == null) {
                continue;
            }
            log.add("comp.start " + step.name);
            try {
                step.compensation.run();
                log.add("comp.ok " + step.name);
            } catch (RuntimeException e) {
                log.add("comp.fail " + step.name + " error=" + e.getClass().getSimpleName());
            }
        }
    }

    private static final class Step {
        private final String name;
        private final Runnable action;
        private final Runnable compensation;

        private Step(String name, Runnable action, Runnable compensation) {
            this.name = name;
            this.action = action;
            this.compensation = compensation;
        }
    }

    public static final class Builder {
        private final String name;
        private final List<Step> steps = new ArrayList<>();

        private Builder(String name) {
            this.name = Objects.requireNonNull(name, "name");
            if (name.isBlank()) {
                throw new IllegalArgumentException("name must not be blank");
            }
        }

        public Builder step(String name, Runnable action, Runnable compensation) {
            Objects.requireNonNull(name, "name");
            Objects.requireNonNull(action, "action");
            steps.add(new Step(name, action, compensation));
            return this;
        }

        public Builder step(String name, Runnable action) {
            return step(name, action, null);
        }

        public Saga build() {
            if (steps.isEmpty()) {
                throw new IllegalStateException("Saga must have at least one step");
            }
            return new Saga(name, List.copyOf(steps));
        }
    }
}

