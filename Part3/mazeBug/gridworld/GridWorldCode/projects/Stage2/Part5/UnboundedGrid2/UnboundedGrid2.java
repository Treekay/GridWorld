import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    // using a two-dimesion array to achieve the grid

    private int length;
    // colNum and rowNum are the same

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2() {
        length = 16;
        occupantArray = new Object[length][length];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    // since this unbounded grid broaden by double the length(row and col)
    // the part that row < 0 and col < 0 isn't including in the grid
    // so the row < 0 and the col < 0 is invalid
    public boolean isValid(Location loc)
    {
        return (loc.getRow() >= 0 && loc.getCol() >= 0);
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < length; c++) {
                // If there's an object at this location
                // put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    locs.add(loc);
                }
            }
        }
        
        return locs;
    }

    public E get(Location loc) {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        // find the location which isn't included in the grid (before the grid broaden)
        if (loc.getRow() >= length || loc.getCol() >= length) {
            return null;
        } 
        else {
            return (E) occupantArray[loc.getRow()][loc.getCol()];   
        }
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        
        if (loc.getRow() >= length || loc.getCol() >= length) {
            resizeGrid(loc);
        }
        
        // resize the Grid and then put the obj to the loc
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        
        if (loc.getRow() >= length || loc.getCol() >= length) {
            return null;
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    
    /**
     *  When put an actor outside the grid,
     *  double both array bounds until they are large enough.
     *  construct a new square array with those bounds, 
     *  and place the existing occupants into the new array.
     */
    private void resizeGrid(Location loc) {
        /**
         * init the length to new.
         */
        int newLength = length;
        while (loc.getRow() >= newLength || loc.getCol() >= newLength) {
            newLength *= 2;
        }
        
        /**
         * init the array to new.
         */
        Object[][] newArray = new Object[newLength][newLength];
        
        /**
         * copy the object in the oldgrid to the newgrid
         */
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < length; c++) {
                newArray[r][c] = occupantArray[r][c];
            }
        }
        
        length = newLength;
        occupantArray = newArray;
    }
}