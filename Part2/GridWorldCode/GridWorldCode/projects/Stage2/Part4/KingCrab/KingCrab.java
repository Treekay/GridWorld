import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * KingCrab, remove the actor further away from it.
 */
public class KingCrab extends CrabCritter {
    
    /* 
    * moved the actor a location further away from this KingCrab
    * If it is not valid, removed the actor from the grid
    */ 
    public void processActors(ArrayList<Actor> actors) { 
        for (Actor a : actors) { 
            if (!moveFurtherAway(a)) { 
                a.removeSelfFromGrid(); 
            } 
        } 
    } 

    /*
     * if exist a location further away, return true
     * If not a location further away, return false.
     */
    private boolean moveFurtherAway(Actor a) {
        ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(a.getLocation());

        for(Location loc:locs) {
            Location loc1 = getLocation();
            /**
             * Computes the rounded integer distance between two given locations.
             */
            if((int)Math.floor(Math.sqrt(Math.pow(loc1.getRow() - loc.getRow(),2) + Math.pow(loc1.getCol() - loc.getCol(),2)) + .5) > 1) {
                a.moveTo(loc);
                return true;
            }
        }
        return false; 
    } 
}
