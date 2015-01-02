package design.patterns.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Specialized Character Class
 */
public class KungFuGuy extends Character {
    private static final Logger log = LoggerFactory.getLogger(KungFuGuy.class);

    @Override public void interactWith(Obstacle obstacle) {
        log.debug("KungFuGuy now battles a {}", obstacle.action() );
        System.out.println("KungFuGuy now battles a "+ obstacle.action() );
    }
}
