import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

/**
 * This class runs a world that contains dancing bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class DancingBugRunner {
    private DancingBugRunner() {}
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        int[] array = {2, 3, 1, 4};
        world.add(new DancingBug(array));
        world.add(new Flower());
        world.add(new Rock());
        world.show();
    }
}