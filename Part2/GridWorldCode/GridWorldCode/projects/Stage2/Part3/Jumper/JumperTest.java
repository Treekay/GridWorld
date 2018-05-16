import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class JumperTest {
    private int five = 5;
    private int three = 3;
    private int two = 2;
    private int one = 1;

    @Before
    public void setUp() throws Exception {

    }
    
    @Test
    public void testJump() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();

        world.add(new Location(five, five), jumper);
        // direction in North

        jumper.jump();
        assertEquals(new Location(three, five), jumper.getLocation());
        // test if the jumper jump forward two cells
    }
    
    @Test
    public void testTurn() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();

        world.add(new Location(five, five), jumper);
        // direction in North
        
        jumper.turn();
        assertEquals(45, jumper.getDirection());
        // test if the jumper turn 4five degrees
    }
    
    @Test
    public void testCanJumpOutGrid() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();

        world.add(new Location(five, 0), jumper);
        // direction in North
        
        // turn to west and test if it can judge out the grid
        // if it can't, test success
        jumper.setDirection(-90);
        assertEquals(false, jumper.canJump());

        // move the jumper one cell away from the side of the grid
        // test if it can judge out the grid
        // if it can't, test success
        jumper.moveTo(new Location(five, one));
        assertEquals(false, jumper.canJump());
    }

    @Test
    public void testCanJumpTo() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        world.add(new Location(five, one), jumper);
        jumper.setDirection(90);

        // test if it can jump on a rock
        // if it can't, test success
        Rock rock = new Rock();
        world.add(new Location(five, three), rock);
        assertEquals(false, jumper.canJump());

        // test if it can jump on a flower
        // if it can, test success
        world.remove(new Location(five, three));
        Flower flower = new Flower();
        world.add(new Location(five, three), flower);
        assertEquals(true, jumper.canJump());

        // test if it can jump on a bug
        // if it can't, test success
        world.remove(new Location(five, three));
        Bug bug = new Bug();
        world.add(new Location(five, three), bug);
        assertEquals(false, jumper.canJump());

        // test if it can jump on a critter
        // if it can't, test success
        world.remove(new Location(five, three));
        Critter critter = new Critter();
        world.add(new Location(five, three), critter);
        assertEquals(false, jumper.canJump());

        // test if it can jump to a jumper
        // if it can't, test success
        world.remove(new Location(five, three));
        Jumper j = new Jumper();
        world.add(new Location(five, two), j);
        assertEquals(false, jumper.canJump());
    }

    @Test
    public void testCanJumpAcross() {
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        world.add(new Location(five, one), jumper);
        jumper.setDirection(90);

        // test if it can jump across a rock
        // if it can, test success
        Rock rock = new Rock();
        world.add(new Location(five, two), rock);
        assertEquals(true, jumper.canJump());

        // test if it can jump across a flower
        // if it can, test success
        world.remove(new Location(five, two));
        Flower flower = new Flower();
        world.add(new Location(five, two), flower);
        assertEquals(true, jumper.canJump());

        // test if it can jump across a bug
        // if it can't, test success
        world.remove(new Location(five, two));
        Bug bug = new Bug();
        world.add(new Location(five, two), bug);
        assertEquals(false, jumper.canJump());

        // test if it can jump across a critter
        // if it can't, test success
        world.remove(new Location(five, two));
        Critter critter = new Critter();
        world.add(new Location(five, two), critter);
        assertEquals(false, jumper.canJump());

        // test if it can jump across a jumper
        // if it can't, test success
        world.remove(new Location(five, two));
        Jumper j = new Jumper();
        world.add(new Location(five, two), j);
        assertEquals(false, jumper.canJump());
    }
}