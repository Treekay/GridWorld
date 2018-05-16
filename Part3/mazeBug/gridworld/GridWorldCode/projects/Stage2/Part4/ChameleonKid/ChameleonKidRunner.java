import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon kid. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ChameleonKidRunner {
    private ChameleonKidRunner() {}

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Rock(Color.BLUE));
        world.add(new Rock(Color.PINK));
        world.add(new Rock(Color.RED));
        world.add(new Rock(Color.YELLOW));
        world.add(new ChameleonKid());
        world.add(new ChameleonKid());
        world.show();
    }
}