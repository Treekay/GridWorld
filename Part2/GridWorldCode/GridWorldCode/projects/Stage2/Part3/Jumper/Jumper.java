import java.awt.Color;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

public class Jumper extends Actor {

    /**
     * Constructs a green jumper
     */
    public Jumper() {
        setColor(Color.GREEN);
    }

    /**
     * Constructs a jumper of a given color.
     * @param jumperColor the color for this jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * The Jumper jump forward two cells
     */
    public void jump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextTwo = next.getAdjacentLocation(getDirection());

        // while use the jump method directly without use canJump method
        // consume it is valid
        if (gr.isValid(nextTwo)) {
            moveTo(nextTwo);
        }
        else {
            removeSelfFromGrid();
        }
    }

    /**
     * Turns the Jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Judge if the jumper can jump in this step
     */
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextTwo = next.getAdjacentLocation(getDirection());
        /**
         * can't jump out of the grid
         */
        if (!gr.isValid(next) || !gr.isValid(nextTwo)) {
            return false;
        }
        /**
         * Can jump across the flower or the rock, but not any other actor
         * only the actor which can't move
         */
        if (!validEncounters(gr.get(next))) {
            return false;
        }
        /**
         * judge if the location it jump to is valid
         * a empty location or a flower is valid
         */
        return validNeighbor(gr.get(nextTwo));
    }

    /**
     * ok to jump two cells across empty location or a flower or a rock
     */ 
    private boolean validEncounters(Actor neighbor) {
        return (((neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Rock)));
    }

    /**
     * ok to jump two cells into empty location or a flower
     */ 
    private boolean validNeighbor(Actor neighbor) {
        return (neighbor == null) || (neighbor instanceof Flower);
    }
    /**
     * The act of the jumper
     */
    public void act() {
        if (canJump()) {
            jump();
        }
        else {
            turn();
        }
    }
}