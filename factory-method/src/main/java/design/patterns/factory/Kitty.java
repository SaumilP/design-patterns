package design.patterns.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Specialised Character Class
 */
public class Kitty extends Character {
    private static final Logger log = LoggerFactory.getLogger(Kitty.class);

    @Override public void interactWith(Obstacle obstacle) {
        log.debug("Kitty has encountered a {}", obstacle.action() );
        System.out.println("Kitty has encountered a "+ obstacle.action() );
    }
}
