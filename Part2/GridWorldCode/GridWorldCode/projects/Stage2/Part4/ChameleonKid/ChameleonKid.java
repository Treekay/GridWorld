import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid; 

import java.util.ArrayList;

/**
 * ChameleonKid, change its color according to the actor ahead or behind it.
 */
public class ChameleonKid extends ChameleonCritter {
    
    /**
     * A chameleon kid gets the actors in the two locations immediately in front and behind
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        /**
         * only get the neighbor of its ahead and back
         */
        int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
        for (Location loc : getLocationsInDirections(dirs)) {
            // get the ahead location and back locaiton
            Actor a = getGrid().get(loc);
            // if there is an actor, add in its actorlist
            if (a != null) {
              actors.add(a);
            }
        }

        return actors;
    }

    /**
     * According ot the chameleon's direction and its location
     * To find its ahead and back neighbors' location
     * return the location list for the getActors method to add neighbors in if exists
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions) {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
              locs.add(neighborLoc);
            }
        }
        return locs;
    }    
}