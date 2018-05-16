package info.gridworld.maze;
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
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<Location> crossLocation = new Stack<Location>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	public ArrayList<Location> traces = new ArrayList<Location>();// traces the path bug has moved
	public boolean isBack = false; 

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		stepCount = new Integer(0);
		isEnd = false;
		hasShown = false;
		isBack = false;
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

		Location loc = this.getLocation();
		for (Location validLocation : getValid(loc)) {

			// judge whether the location the bug want to move to is the end
			if (grid.get(validLocation) instanceof Rock) {
				if (grid.get(validLocation).getColor().equals(Color.RED)) {
					isEnd = true;
				} 
				else {
					continue;
				}
			}

			// judge whether the location the bug want to move to has been traced
			boolean hasTraced = false;
			for (Location l : traces) {
				if (l.equals(validLocation)) {
					hasTraced = true;
				}
			}

			if (!hasTraced) {
				target = new Location(validLocation.getRow(), validLocation.getCol());
				next = target;
				isBack = false;
				return true;
			}
		}
		if (target == null) {
			next = crossLocation.pop();
			isBack = true;
			return true;
		}
		return false;
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
