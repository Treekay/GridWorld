import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * QuickCrab, which can move two locations in a step
 */
public class QuickCrab extends CrabCritter {

    /**
     * return a list of empty locations
     * two locations to the right and two locations to the left
     */
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();

        /** 
         * Adds a valid and empty two away location in direction left or right to locs. 
         */ 
        judgeTwoLocation(locs, getDirection() + Location.LEFT);
        judgeTwoLocation(locs, getDirection() + Location.RIGHT);

        if (locs.size() == 0) {
            // randomly choose one location to move form the locationlist
            return super.getMoveLocations();
        }

        return locs;
    }

    private void judgeTwoLocation(ArrayList<Location> locs, int direction) {
        Grid g = getGrid();

        Location temp = getLocation().getAdjacentLocation(direction);
        if (g.isValid(temp) && g.get(temp) == null) {
            Location loc = temp.getAdjacentLocation(direction);
            /*
             * the location must be valid and empty. 
             */
            if(g.isValid(loc) && g.get(loc)== null) {
                locs.add(loc);
            }
        }
    }
}