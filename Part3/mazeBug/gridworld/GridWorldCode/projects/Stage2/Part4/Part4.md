act, getActors, processActors, getMoveLocations, selectMoveLocation, makeMove.  
// @file: framework/info/gridworld/actor/Critter.java  
// @line: 38, 56, 71, 88, 104, 125  
```
public void act()

public ArrayList<Actor> getActors()

public void processActors(ArrayList<Actor> actors)

public ArrayList<Location> getMoveLocations()

public Location selectMoveLocation(ArrayList<Location> locs)

public void makeMove(Location loc)

```

getActors, processActors, getMoveLocations, selectMoveLocation, makeMove.  
// @file: framework/info/gridworld/actor/Critter.java  
// @line: 56, 71, 88, 104, 125  
```
public ArrayList<Actor> getActors()

public void processActors(ArrayList<Actor> actors)

public ArrayList<Location> getMoveLocations()

public Location selectMoveLocation(ArrayList<Location> locs)

public void makeMove(Location loc)

```


Yes, if the subclass look elsewhere for actors to process. The answer can also be seen in the annotation    
// @file: framework/info/gridworld/actor/Critter.java  
// @line: 49~59  
```
/**
 * Gets the actors for processing. Implemented to return the actors that
 * occupy neighboring grid locations. Override this method in subclasses to
 * look elsewhere for actors to process.<br />
 * Postcondition: The state of all actors is unchanged.
 * @return a list of actors that this critter wishes to process.
 */
public ArrayList<Actor> getActors()
{
        return getGrid().getNeighbors(getLocation());
}
```

It could eat(remove) actors neighbor to it if the actors are not Rock or Critter, or override the processActors method to process actors in different ways, like changing actors' direction, changing actors' color and so on.  
// @file: framework/info/gridworld/actor/Critter.java  
// @line: 63~65  
```
* Implemented to "eat" (i.e. remove) selected actors
* that are not rocks or critters. Override this method in subclasses to
* process actors in a different way. 
```

getMoveLocations, selectMoveLocation, makeMove. First, the act method calls the getMoveLocations method to get an ArrayList<Location>, after receiving the list of locations, calls the selectMoveLocation to choose a locaiton and restores in a variable, then pass the locaiton to the makeMove method and move the actor to the new location.   
// @file: framework/info/gridworld/actor/Critter.java  
// @line: 44~46   
```
ArrayList<Location> moveLocs = getMoveLocations();
Location loc = selectMoveLocation(moveLocs);
makeMove(loc);  
```

The Critter class is inherited from Actor, the Actor class has a default constructor. In Java, it will create a default constructor if a class doesn't have a constructor. and the Critter default constructor call super() which provided by Java will will call the Actor() default constructor.  
// @file: framework/info/gridworld/actor/Critter.java  
// @line: 31  
```
public class Critter extends Actor
```

Because the act method calls getActors, processActors, getMoveLocations, selectMoveLocation and makeMove. And the ChameleonCritter override the processActors and makeMove methods.  
// @file: projects/critters/ChameleonCritter.java  
// @line: 36, 50  
```
public void processActors(ArrayList<Actor> actors)

public void makeMove(Location loc)
```

Because the makeMove method of ChameleonCritter first change the Direction and then act the same as the Critter, so it calls super.makeMove to act the same action.  
// @file: projects/critter/ChameleonCritter.java  
// @line: 50~54  
```
public void makeMove(Location loc)
{
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
}
```

Add the action to drop flower in the makeMove method, first define a variable to restore the old location, and if the ChameleonCritter move to a new location, put a flower in the old location.  


Because it processes the same list of actors as Critter do, so it needn't to override the getActors method.  

The Actor class.  
// @file: framework/info/gridworld/actor/Actor.java  
// @line: 102  
```
public Location getLocation()
```


use the getGrid method.  
// @file: framework/info/gridworld/actor/Actor.java  
// @line: 92~95  
```
public Grid<Actor> getGrid()
{
    return grid;
}
```


Because the CrabCritter processes the processActors method to eat all the neighbors, which the same as Critter, so it needn't to override the processActors method.  

It uses the getActors method to find the list of neighbors, and uses the processActors to eat other actors.  
No, it can only eat the actor in front of , right-front of, and left-front of itself.  
// @file: projects/critters/CrabCritter.java  
// @line: 48  
```
    { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
```

It uses the getLocationInDirections to get valid adjacent locations.  
// @file: projects/critters/CrabCritter.java  
// @line: 98~99  
```
 * @return a set of valid locations that are neighbors of the current
 * location in the given directions
```

(4,3), (4,4), (4,5)  
// @file: projects/critters/CrabCritter.java  
// @line: 44~57  
```
 public ArrayList<Actor> getActors()
{
    ArrayList<Actor> actors = new ArrayList<Actor>();
    int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    for (Location loc : getLocationsInDirections(dirs))
    {
            Actor a = getGrid().get(loc);
            if (a != null)
                    actors.add(a);
    }

    return actors;
}
```

The similarities are that they don't turn in the direction that they are moving and they both randomly choose a location as a new location.  
The differences are that a CrabCritter will only move to its left or right, but a Critter can move to its eight neighboring adjacent locations.  
// @file: projects/citters/CrabCritter.java  
// @line: 66 
```
    { Location.LEFT, Location.RIGHT };
```


When the parameter loc in makeMove equals to its location, it turns instead of moving.  
// @file: projects/critters/CrabCritters.java  
// @line: 79~88  
```
if (loc.equals(getLocation()))
{
    double r = Math.random();
    int angle;
    if (r < 0.5)
        angle = Location.LEFT;
    else
        angle = Location.RIGHT;
    setDirection(getDirection() + angle);
}
```

Because a Critter can't eat Rock or Critter, the CrabCritter extends from the Critter class, so it can eat other Critter also CrabCritter.  
// @file: framework/info/gridworld/actor/Critter.java  
// @line: 75~76  
```
    if (!(a instanceof Rock) && !(a instanceof Critter))
        a.removeSelfFromGrid();
```

