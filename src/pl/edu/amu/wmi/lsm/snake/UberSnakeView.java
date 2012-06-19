package pl.edu.amu.wmi.lsm.snake;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

public class UberSnakeView extends TileView {
	
	private int mode = READY;
    public static final int PAUSE = 0;
    public static final int READY = 1;
    public static final int RUNNING = 2;
    public static final int LOSE = 3;
    
    private int direction = UP;
    private int nextDirection = UP;
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;
    
    private long score = 0;
    private long speed = 600;
    private long lastStep;
    
    private Field omNomNom;
	
	public UberSnakeView(Context context, AttributeSet attrs) {
		super(context, attrs);
        initUberSnakeView();
	}
	
	public UberSnakeView(Context context, AttributeSet attrs, int defStyle) {
    	super(context, attrs, defStyle);
    	initUberSnakeView();
    }

    private void initUberSnakeView() {
        setFocusable(true);

        Resources r = this.getContext().getResources();
        
        resetTiles(4);
    	
    }

}
