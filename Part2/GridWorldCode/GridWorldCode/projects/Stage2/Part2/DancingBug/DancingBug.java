import info.gridworld.actor.Bug;
import java.util.Arrays;

/**
 * A <code>DancingBug</code>
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug {

    //store the index of the dancing array
    private int index;
    private int[] dancingArray;

    /**
     * Constructs a dancing bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug(int[] array) {
        dancingArray = Arrays.copyOf(array, array.length);
        index = 0;
    }
  
    /**
     * Moves to the next location.
     */
    public void act() {
        // before each move it will "dance"(turn some times)
        dance();
        super.act();
    }

    private void dance() {
        // get the index value of the array
        // and turn the corresponding times
        for (int i = 0; i < dancingArray[index]; i++) {
            turn();
        }
        index++;
        // the dancing method loop using the array
        index %= dancingArray.length;
    }
}