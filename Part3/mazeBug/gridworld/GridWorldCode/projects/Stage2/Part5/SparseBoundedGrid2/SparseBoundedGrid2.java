import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


/** 
 * a bounded grid which achieved by a unbounded grid
 * using a hashmap
 */ 
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantMap;
    
    private int row;
    private int col;

    /**
     * Constructs an empty sparsebounded grid.
     */
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        // create the grid using hashmap, key is the location and value is object
        occupantMap = new HashMap<Location, E>();
        row = rows;
        col = cols;
    }

    public int getNumRows() {
        return row;
    }

    public int getNumCols() {
        return col;
    }

    // since this grid is infinite, out of the grid is invalid
    public boolean isValid(Location loc) {
        return (0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc.getCol() < getNumCols());
    }
    
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<Location>();
        // find all the location which has object
        // add to the arraylist to get the collection of actor
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }
    
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        return occupantMap.remove(loc);
    }
}