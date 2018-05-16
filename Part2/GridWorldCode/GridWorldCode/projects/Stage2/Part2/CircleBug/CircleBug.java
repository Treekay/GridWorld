import info.gridworld.actor.Bug;

/**
 * A <code>CircleBug</code> traces out a square "circle" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class CircleBug extends Bug {

    private int steps;
    private int sideLength;

    /**
     * Constructs a circle bug that traces a square begin with a  given side length
     * @param length the side length
     */
    public CircleBug(int length) {
        steps = 0;
        sideLength = length;
        // the steps that the bug move on each side
    }

    /**
     * Moves to the next location of the circle.
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        }
        // finish one side or can't move
        else {
            // only turn once
            turn();
            steps = 0;
        }
    }
}