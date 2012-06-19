package pl.edu.amu.wmi.lsm.snake;

import java.util.ArrayList;



public class SnakeBody {

	public ArrayList<Field> trail = new ArrayList<Field>();
	
	public SnakeBody() {
		this.reset();
	}
	
	public void reset() {
		trail.clear();
		trail.add(new Field(7, 7));
		trail.add(new Field(6, 7));
		trail.add(new Field(5, 7));
		trail.add(new Field(4, 7));
		trail.add(new Field(3, 7));
		trail.add(new Field(2, 7));
	}
	
	public int[] toIntArray() {
        int count = trail.size();
        int[] rawArray = new int[count * 2];
        for (int index = 0; index < count; index++) {
        	Field c = trail.get(index);
            rawArray[2 * index] = c.x;
            rawArray[2 * index + 1] = c.y;
        }
        return rawArray;
    }
    
    public void loadIntArray(int[] rawArray) {

        int coordCount = rawArray.length;
        for (int index = 0; index < coordCount; index += 2) {
        	Field c = new Field(rawArray[index], rawArray[index + 1]);
        	trail.add(c);
        }
    }
    
    public void move(Field f, boolean grow) {
    	trail.add(0, f);
    	if (!grow) {
    		trail.remove(trail.size() - 1);
    	}
    }
    
    public boolean collision(int x, int y) {
    	Field f = new Field(x, y);
    	for (Field c : this.trail) {
            if (c.equals(f)) return true;
        }
    	return false;
    }
    public boolean collision(Field f) {
    	for (Field c : this.trail) {
            if (c.equals(f)) return true;
        }
    	return false;
    }
	
}
