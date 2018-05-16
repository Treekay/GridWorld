import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> traces out a "Spiral" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class SpiralBug extends Bug {

    private int steps;
    private int sideLength;

    /**
     * Constructs a spiral bug that traces a sipral with a  given side length
     * @param length the side length
     */
    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the spiral.
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        }
        else {
            //turn twice when it finish each side or can't move
            //and start a new side, sideLength add one
            turn();
            turn();
            steps = 0;
            sideLength += 1;
        }
    }
}