import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * SparseGridNode linked list of OccupantInCol version
 * BoundedGrid, a rectangular grid with a finite number of rows and columns.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    private ArrayList<LinkedList<OccupantInCol>> occupantList = new ArrayList<LinkedList<OccupantInCol>>();
    /**
     * a grid can be seen as several rows in a array
     * and the elements in the array are linkedlist
     * so the occupantIncol class is a location which store the colNum and the object
     */

    private int row;
    private int col;
    
    public SparseBoundedGrid() {
        row = 16;
        col = 16;
        // init the rows and cols as 16
        for (int i = 0; i < row; i++) {
            // add one row one time, one row is one linkedlist.
            occupantList.add(new LinkedList<OccupantInCol>());
        }
    }

    /**
     * init the rows and cols of the grid
     * this method can set the rowNum and colNum freedomly
     */
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        row = rows;
        col = cols;
        for (int i = 0; i < rows; i++) {
            // an occupantIncol will point to the next auto
            occupantList.add(new LinkedList<OccupantInCol>());
        }
    }

    public int getNumCols() {
        return col;
    }

    public int getNumRows() {
        return row;
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        
        /** 
         * scan the row 
         * find the object's col to get the location
         */ 
        for (int r = 0; r < getNumRows(); r++) {
            // list is one row, which store in the arraylist
            LinkedList<OccupantInCol> list = occupantList.get(r);

            if (list != null) {
                // occupantincol is a col location of the row
                for (OccupantInCol o : list) {
                    Location loc = new Location(r, o.getColNum());
                    locs.add(loc);
                }
            }
        }
        return locs;
    }

    public boolean isValid(Location loc) {
        // since this is a infinite grid, out of the grid is invalid.
        return 0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }

        /**
         * use remove to remove the obj 
         */
        E oldOccupant = remove(loc);
        /**
         * Add the new object to the grid.
         */
        LinkedList<OccupantInCol> rowNum = occupantList.get(loc.getRow());
        rowNum.add(new OccupantInCol(obj, loc.getCol()));

        return oldOccupant;
    }
    
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        
        /**
         * judge if the colNum exist
         */
        if (occupantList.get(loc.getRow()) != null) {
            /**
             * return the object in the location
             */
            for (OccupantInCol o : occupantList.get(loc.getRow())) {
                if (o.getColNum() == loc.getCol()) {
                    return (E)o.getOccupant();
                }
            }
        }
        return null;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        
        E oldOccupant = get(loc);
        
        if (oldOccupant == null) {
            return null;
        }
        
        int index =-1;
        LinkedList<OccupantInCol> rowNum = occupantList.get(loc.getRow());
        /**
         * one row is a list, there are many OccupantInCol
         * find out which col equals to loc.getCol
         */
        for (OccupantInCol o : rowNum) {
            if (o.getColNum() == loc.getCol()) {
                index = rowNum.indexOf(o);
                break;
            }
        }
        
        /**
         * If find the object in the grid
         * Remove the object from the grid
         */
        if (index != -1) {
            rowNum.remove(index);
        }
        
        return oldOccupant;
    }
    
}