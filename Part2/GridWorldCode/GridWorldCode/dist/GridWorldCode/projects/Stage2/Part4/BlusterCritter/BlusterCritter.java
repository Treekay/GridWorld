import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

/**
 * BlusterCritter, lighten or darken according to the num of its neighbor
 */
public class BlusterCritter extends Critter {

    private int courage;

    /**
     * Construct a bluster critter
     */
    public BlusterCritter(int c) {
        super();
        courage = c;
    }

    /**
     * A bluster critter gets the actors within two steps of its current location
     * return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();

        Location loc = getLocation(); 
        // get the location of this critter

        for (int row = loc.getRow() - 2; row <= loc.getRow() + 2; row++) {
            for (int col = loc.getCol() - 2; col <= loc.getCol() + 2; col++) {

                Location current = new Location(row, col);
                if (getGrid().isValid(current)) {
                    Actor a = getGrid().get(current);
                    // add all the acotr within its two steps of its current location
                    if (a != null && a != this) {
                        actors.add(a);
                    }
                }
            }
        }
        return actors;
    }

    /** 
     * Processes the actors. Count all the actors within 2 locations of this critter
     * If there are fewer than courage critters in these locations
     * this BlusterCritter lightens, otherwise it darkens.
     * @param actors the actors to be processed
     */ 
    public void processActors(ArrayList<Actor> actors) {
        int count = 0;
        for(Actor a: actors) {
            if(a instanceof Critter) {
                count++;
            }
            int red = getColor().getRed();
            int green = getColor().getGreen();
            int blue = getColor().getBlue();
            if(count < courage) {
                //if the actor in its neighbor less than courage
                // it will be lighten
                if (red < 255) {
                    red++;
                }
                if (green < 255) {
                    green++;
                }
                if (blue < 255) {
                    blue++;
                }
            }
            else {
                //if the actor in its neighbor more than courage
                //it will be darken
                if (red > 0) {
                    red--;
                }
                if (green > 0) {
                    green--;
                }
                if (blue > 0) {
                    blue--;
                }
            }
            setColor(new Color(red, green, blue));
        }
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}