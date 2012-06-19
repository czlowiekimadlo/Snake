package pl.edu.amu.wmi.lsm.snake;

import java.util.ArrayList;



public class SnakeBody {

	private ArrayList<Field> trail = new ArrayList<Field>();
	
	private int[] toIntArray(ArrayList<Field> cvec) {
        int count = cvec.size();
        int[] rawArray = new int[count * 2];
        for (int index = 0; index < count; index++) {
        	Field c = cvec.get(index);
            rawArray[2 * index] = c.x;
            rawArray[2 * index + 1] = c.y;
        }
        return rawArray;
    }
    
    private ArrayList<Field> loadIntArray(int[] rawArray) {
        ArrayList<Field> coordArrayList = new ArrayList<Field>();

        int coordCount = rawArray.length;
        for (int index = 0; index < coordCount; index += 2) {
        	Field c = new Field(rawArray[index], rawArray[index + 1]);
            coordArrayList.add(c);
        }
        return coordArrayList;
    }
	
}
