// assiant class for the SparseBoundedGrid
public class OccupantInCol {

    private Object occupant;
    // store the object in this location

    private int col;
    // the colNum of this object in the row
    
    public OccupantInCol(Object o, int numCol){
        occupant = o;
        col = numCol;
    }
    
    public Object getOccupant() {
        return occupant;
    }
    
    public void setOccupant(Object o) {
        occupant = o;
    }
    
    public int getColNum() {
        return col;
    }
    
    public void setColNum(int num) {
        col = num;
    }
}