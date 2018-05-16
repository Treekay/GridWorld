import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

/**
 * This class runs a world that contains circle bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class CircleBugRunner {
    private CircleBugRunner() {}
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new CircleBug(3));
        world.add(new Flower());
        world.add(new Rock());
        world.show();
    }
}