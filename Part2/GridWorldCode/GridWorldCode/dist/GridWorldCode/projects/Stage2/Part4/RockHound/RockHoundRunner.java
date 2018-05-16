import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;

/**
 * This class runs a world that contains rock hound. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class RockHoundRunner {
    private RockHoundRunner() {}
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Flower());
        world.add(new Rock(Color.BLUE));
        world.add(new Rock(Color.PINK));
        world.add(new Rock(Color.RED));
        world.add(new Rock(Color.YELLOW));
        world.add(new RockHound());
        world.add(new RockHound());
        world.show();
    }
}