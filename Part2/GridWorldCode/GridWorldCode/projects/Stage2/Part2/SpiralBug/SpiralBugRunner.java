import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

/**
 * This class runs a world that contains spiral bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class SpiralBugRunner {
    private SpiralBugRunner() {}
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new SpiralBug(3));
        world.add(new Flower());
        world.add(new Rock());
        world.show();
    }
}