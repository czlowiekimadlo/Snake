package pl.edu.amu.wmi.lsm.snake;

public class Field {
	public int x;
    public int y;

    public Field(int newX, int newY) {
        this.setCoordinates(newX, newY);
    }

    public boolean equals(Field other) {
        if (x == other.x && y == other.y) {
            return true;
        }
        return false;
    }
    
    public void setCoordinates(int newX, int newY) {
    	x = newX;
        y = newY;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
