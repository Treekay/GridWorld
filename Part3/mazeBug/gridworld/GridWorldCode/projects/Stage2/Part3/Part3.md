```
loc1.getRow();
```
// @file: framework/info/gridworld/grid/Location.java  
// @line: 106~113  
```
/**
  * Gets the row coordinate.
  * @return the row of this location
  */
public int getRow()
{
    return row;
}
```

false.  
// @file: framework/info/gridworld/grid/Location.java  
// @line: 211  
```
    return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
```

(4,4)  
// @file: framework/info/gridworld/grid/Location.java  
// @line: 168  
```
    return new Location(getRow() + dr, getCol() + dc);
```

135 degrees, Southeast   
// @file: framework/info/gridworld/grid/Location.java  
// @line: 178~195  
```
public int getDirectionToward(Location target)
{
    int dx = target.getCol() - getCol();
    int dy = target.getRow() - getRow();
    // y axis points opposite to mathematical orientation
    int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

    // mathematical angle is counterclockwise from x-axis,
    // compass angle is clockwise from y-axis
    int compassAngle = RIGHT - angle;
    // prepare for truncating division by 45 degrees
    compassAngle += HALF_RIGHT / 2;
    // wrap negative angles
    if (compassAngle < 0)
            compassAngle += FULL_CIRCLE;
    // round to nearest multiple of 45
    return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
}
```

It indicates the direction of the adjacent neighbor to find. It returns the adjacent location in the compass direction that is closest to the direction given in the parameter list.   
// @file: framework/info/gridworld/grid/Location.java  
// @line: 133~137  
```
int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
if (adjustedDirection < 0)
    adjustedDirection += FULL_CIRCLE;

adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
```

```
gr.getOccupiedLocations().size() //obtain a count of the objects in a grid
gr.getNumRows()*gr.getNumCols() - gr.getOccupiedLocations().size() // obtain a count of the empty locations in a bounded grid
```
// @file: framework/info/gridworld/grid/Grid.java  
// @line: 81~85  
```
/**
 * Gets the locations in this grid that contain objects.
 * @return an array list of all occupied locations in this grid
 */
ArrayList<Location> getOccupiedLocations();
```

```
gr.isValid(new Location(10,10));
```
// @file: framework/info/gridworld/grid/Grid.java  
// @line: 43~50  
```
/**
 * Checks whether a location is valid in this grid. <br />
 * Precondition: <code>loc</code> is not <code>null</code>
 * @param loc the location to check
 * @return <code>true</code> if <code>loc</code> is valid in this grid,
 * <code>false</code> otherwise
 */
boolean isValid(Location loc);
```

Grid is an interface. An interface specifies which methods another class must implement. The implementations can be found in the AbstractGrid and the BoundedGrid and UnboundedGrid classes.  
// @file: /framework/info/gridworld/grid/Grid.java  
// @line: 29  
```
public interface Grid<E>
```
// @file: /framework/info/gridworld/gird/AbstarctGrid.java  
// @line: 26  
```
public abstract class AbstractGrid<E> implements Grid<E>
```
// @file: /framework/info/gridworld/gird/Bounded.java  
// @line: 29  
```
public class BoundedGrid<E> extends AbstractGrid<E>
```
// @file: /framework/info/gridworld/gird/UnbounderGrid.java  
// @line: 31  
```
public class UnboundedGrid<E> extends AbstractGrid<E>
```

No, accessing elements with the [] notation maybe easier than using different method calls, but Arraylist does not require the user to size the list before filling it while an array needs.

color, direction and location.  
// @file: framework/info/gridworld/actor/Actor.java  
// @line: 32~34  
```
private Location location;
private int direction;
private Color color;
```

Blue, North.  
// @file: framework/info/gridworld/actor/Actor.java  
// @line: 41~42  
```
    color = Color.BLUE;
    direction = Location.NORTH;
```

An interface doesn't have instance variables or implement methods, but an actor has both state and behavior such as color, direction and location.  
// @file: framework/info/gridworld/actor/Actor.java  
// @line: 31~34  
```
    private Grid<Actor> grid;
    private Location location;
    private int direction;
    private Color color;
```

1.No, if the actor has been in the grid, it can't put itself into the grid again, it will throw a IllegalStateException.  
2.No, if the actor has been removed from the gird, it can't be removed again or it will throw a IllegalStateException.  
3.Yes, an actor can be placed into a grid, remove itself and then put itself back.  

use the turn method twice or use the setDirection method like: 
```
    setDirection(getDirection() + 90);
```

use the turn method twice or use the setDirection method like: 
```
    setDirection(getDirection() + 90);
```

// @file: framework/info/gridworld/actor/Bug.java  
// @line: 98~99  
```
if (!gr.isValid(next))
    return false;
```

// @file: framework/info/gridworld/actor/Bug.java  
// @line: 100~101  
```
    Actor neighbor = gr.get(next);
    return (neighbor == null) || (neighbor instanceof Flower);
```

isValid and get.   
To ensure that the next location is a valid location in the grid and to 
look at the object in that location to ensure that it is empty or contains an actor that can be replaced by the bug.   
// @file: framework/info/gridworld/actor/Bug.java  
// @line: 98~100  
```
    if (!gr.isValid(next))
            return false;
    Actor neighbor = gr.get(next);
}
```


getAdjacentLocation. This method is called by the bug with the bug’s current direction to find its next possible location.   
// @file: framework/info/gridworld/actor/Bug.java  
// @line: 97  
```
    getLocationLocation next = loc.getAdjacentLocation(getDirection());
```

getLocation, getDirection, getGrid    
// @file: framework/info/gridworld/actor/Actor.java  
// @line: 69, 92, 102  
```
public int getDirection()

public Grid<Actor> getGrid()

public Location getLocation()
```


The bug will remove itself from the grid.   
// @file: framework/info/gridworld/actor/Bug.java  
// @line: 78~81  
```
if (gr.isValid(next))
    moveTo(next);
else
    removeSelfFromGrid();
```

Yes, the variable loc is needed. The variable loc stores the bug’s location before it moves. After the bug moved to the new location, we can't get the original location by the getLocation method, the loc is used to insert a flower in the bug’s old location after the bug has moved to its new location.   
// @file: framework/info/gridworld/actor/Bug.java  
// @line: 83  
```
    flower.putSelfInGrid(gr, loc);
```

When it is just dropped, before the flower wilts, it is the same color.  
// @file: framework/info/gridworld/actor/Bug.java  
// @line: 82 
```
Flower flower = new Flower(getColor());
```

No, a bug removes itself from the grid by the removeSelfFromGrid method, but the removeSelfFromGrid method doesn't including placing a flower into its previous location.  
// @file: framework/info/gridworld/actor/Actor.java  
// @line: 133~146    
```
public void removeSelfFromGrid()
{
    if (grid == null)
            throw new IllegalStateException(
                            "This actor is not contained in a grid.");
    if (grid.get(location) != this)
            throw new IllegalStateException(
                            "The grid contains a different actor at location "
                                            + location + ".");

    grid.remove(location);
    grid = null;
    location = null;
}
```


// @file: framework/info/gridworld/actor/Bug.java  
// @line: 82~83  
```
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);

```