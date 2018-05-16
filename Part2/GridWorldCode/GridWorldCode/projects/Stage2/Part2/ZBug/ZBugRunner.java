import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

/**
 * This class runs a world that contains z bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ZBugRunner {
    private ZBugRunner() {}
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new ZBug(4));
        world.add(new Flower());
        world.add(new Rock());
        world.show();
    }
}