import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBugEX extends Bug {
    public Location next;
    public Location last;
    public boolean isEnd = false;
    public Stack<Location> crossLocation = new Stack<Location>();
    public Integer stepCount = 0;
    boolean hasShown = false;//final message has been shown

    public ArrayList<Location> traces = new ArrayList<Location>();// traces the path bug has moved
    public boolean isBack = false;
    private int[] predict = {1, 1, 1, 1};

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public MazeBugEX() {
        setColor(Color.GREEN);
        last = new Location(0, 0);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();

        if (traces.size() == 0) {
            traces.add(this.getLocation());
            last = getLocation();
        }

        if (isEnd == true) {
        //to show step count when reach the goal        
            if (hasShown == false) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } 
        else if (willMove) {
            move();
            //increase step count when move 
            stepCount++;
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();
        
        int[] validDirection = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
        for (int direction : validDirection) {
            Location target = loc.getAdjacentLocation(direction);
            if (grid.isValid(target)) {
                valid.add(target);
            }
        }

        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    @Override
    public boolean canMove() {
        Grid<Actor> grid = getGrid();
        Location target = null;

        // store the location for selecting which has a bigger direction trendency
        ArrayList<Location> nextMove = new ArrayList<Location>();

        Location loc = this.getLocation();
        for (Location validLocation : getValid(loc)) {
            // the validLocation is the location the bug want to move to

            // judge whether the location the bug want to move to is the end
            if (grid.get(validLocation) instanceof Rock) {
                if (grid.get(validLocation).getColor().equals(Color.RED)) {
                    isEnd = true;
                    return false;
                } 
                else {
                    continue;
                }
            }

            /**
             * find the validLocation from the traces in which the bug has trace
             * judge whether the location the bug want to move to has been traced
             * if has, the bug is backing
             * if not, 
             */
            boolean hasTraced = false;
            for (Location l : traces) {
                if (l.equals(validLocation)) {
                    hasTraced = true;
                    break;
                }
            }

            if (!hasTraced) {
                nextMove.add(validLocation);
            }
        }
        /**
         * randomly choose a best way and move to
         */
        if (nextMove.size() != 0) {
            Location direction = null;
            int sum = 0;
            for (Location l : nextMove) {          
                sum += predict[loc.getDirectionToward(l) / 90];
            }
            for (Location l : nextMove) {          
                if (getRandom(sum) < predict[loc.getDirectionToward(l) / 90]) {
                    direction = l;
                }
            }
            next = direction;
            // index 0, 1, 2, 3 refer to NORTH, EAST, SOUTH, WEST in the predict array
            predict[loc.getDirectionToward(direction) / 90]++;
            isBack = false;
            return true;
        }
        /**
         *  the bug is moving back
         */
        else {
            next = crossLocation.pop();
            predict[next.getDirectionToward(loc) / 90]--;
            isBack = true;
            return true;
        }
    }

    /**
     * get a random num
     */
    public int getRandom(int sum) {
        return (int)(Math.random()*sum);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> grid = getGrid();
        if (grid == null) {
            return;
        }
        Location loc = getLocation();
        if (grid.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            if (last != null && !isBack) {
                crossLocation.push(last);
            }
            moveTo(next);
            traces.add(next);
            last = next;
        } 
        else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(grid, loc);
    }
}
