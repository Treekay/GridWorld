import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

public class RockHound extends Critter {

    /**
     * remove the rock which is in its neighbor
     */
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) { 
            if (a instanceof Rock) {
                a.removeSelfFromGrid(); 
            }
        } 
    } 
} 