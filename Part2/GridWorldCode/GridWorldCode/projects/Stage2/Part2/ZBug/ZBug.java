import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>ZBug</code> traces out a square "Z" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug {

    private int steps;
    private int sideLength;
    private int count; 
    // count the total steps the bug has moved

    /**
     * Constructs a Z bug that traces a Z begin with a  given side length
     * @param length the side length
     */
    public ZBug(int length) {
        steps = 0;
        count = 0;
        sideLength = length;
        setDirection(Location.EAST);
    }

    public void act() {
        // when it finish the last side
        // it can't move or turn, it will stop
        if (count != 3*sideLength) {
            if (steps < sideLength && canMove()) {
                move();
                steps++;
                count++;
            }
            // only trace a Z
            else if (steps == sideLength) {
                changeDirection();
                steps = 0;
            }
        }
    }

    private void changeDirection() {

        // Direction is EAST
        if (getDirection() == Location.EAST) {

        /**
         * change the direction to SouthWest
         * just change the direction, it can't move
         */
            setDirection(Location.SOUTHWEST);
        }

        // Direction is SOUTHWEST
        else {
            
        /**
         * change the direction to EAST
         * just change the direction, it can't move
         */
            setDirection(Location.EAST);
        }
    }
}