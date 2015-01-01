package design.pattern.decorator;

import design.pattern.decorator.armors.Boot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by PATEL1 on 1/1/15.
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Player player = new Player();
        player.equipArmor( new Boot() );

        Enemy enemy = new Enemy();
        enemy.setPoisoned(true);

        // missing game engine related code
    }
}
