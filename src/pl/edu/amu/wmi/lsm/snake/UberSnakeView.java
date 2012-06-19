package pl.edu.amu.wmi.lsm.snake;

import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

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
    
    private static final int RED_STAR = 1;
    private static final int YELLOW_STAR = 2;
    private static final int GREEN_STAR = 3;
    
    private long score = 0;
    private long speed = 600;
    private long lastStep;
    
    private Field omNomNom;
    private SnakeBody ssssnake;
    
    private static final Random RNG = new Random();
    
    private RefreshHandler redrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            UberSnakeView.this.update();
            UberSnakeView.this.invalidate();
        }

        public void sleep(long delayMillis) {
        	this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };
	
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
        Log.v("INFO", "snake view init");
        resetTiles(4);
        loadTile(RED_STAR, r.getDrawable(R.drawable.redstar));
        loadTile(YELLOW_STAR, r.getDrawable(R.drawable.yellowstar));
        loadTile(GREEN_STAR, r.getDrawable(R.drawable.greenstar));
        this.ssssnake = new SnakeBody();
        this.omNomNom = new Field(10,7);
        this.resetGame();
    }
    
    public void resetGame() {
    	this.ssssnake.reset();
    	this.omNomNom.x = 10;
    	this.omNomNom.y = 7;
    	this.score = 0;
    	this.speed = 600;
    	this.nextDirection = RIGHT;
    }
    
    public void update() {
        if (mode == RUNNING) {
            long now = System.currentTimeMillis();

            if (now - lastStep > speed) {
                clearTiles();
                this.updateBoard();
                this.drawSnake();
                this.drawDot();
                lastStep = now;
            }
            redrawHandler.sleep(speed);
        }
        
    }
    
    private void updateBoard() {
    	Field head = this.ssssnake.trail.get(0);
    	Field next;
    	
    	this.direction = this.nextDirection;
    	
    	switch (this.direction) {
        case LEFT: {
            next = new Field(head.x - 1, head.y);
            break;
        }
        case RIGHT: {
        	next = new Field(head.x + 1, head.y);
            break;
        }
        case UP: {
        	next = new Field(head.x, head.y - 1);
            break;
        }
        case DOWN: {
        	next = new Field(head.x, head.y + 1);
            break;
        }
        default: {
        	next = new Field(0,0);
        }
        }
    	
    	
    	if ((next.x < 0) || (next.y < 0) || (next.x > mXTileCount - 1)
                || (next.y > mYTileCount - 1) || this.ssssnake.collision(next)) {
            setMode(LOSE);
            Log.v("INFO", "lost");
            return;
        }
    	
    	if (this.omNomNom.equals(next)) {
    		this.rollDot();
    		this.ssssnake.move(next, true);
    		if (this.speed > 200) this.speed -= 10;
    		this.updateScore();
    	}
    	else {
    		this.ssssnake.move(next, false);
    	}
    }
    
    private void rollDot() {
        boolean found = false;
        while (!found) {
            int newX = 1 + RNG.nextInt(mXTileCount - 2);
            int newY = 1 + RNG.nextInt(mYTileCount - 2);
            this.omNomNom.x = newX;
            this.omNomNom.y = newY;

            found = !this.ssssnake.collision(this.omNomNom);
        }
    }
    
    private void drawDot() {
    	setTile(YELLOW_STAR, this.omNomNom.x, this.omNomNom.y);
    }
    
    private void drawSnake() {
        for (Field c : this.ssssnake.trail) {
            setTile(RED_STAR, c.x, c.y);
        }
    }
    
    public void setMode(int newMode) {
        int oldMode = mode;
        mode = newMode;

        if (newMode == RUNNING & oldMode != RUNNING) {
            update();
            return;
        }
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.drawSnake();
        this.drawDot();
        this.setMode(RUNNING);
    }
    
    public void goLeft() {
    	if (mode != RUNNING) {
    		return;
    	}
    	
    	if (this.nextDirection == UP || this.nextDirection == DOWN) {
    		this.nextDirection = LEFT;
    	}
    	else {
    		this.nextDirection = DOWN;
    	}
    }
    
    public void goRight() {
    	if (mode != RUNNING) {
    		return;
    	}
    	
    	if (this.nextDirection == UP || this.nextDirection == DOWN) {
    		this.nextDirection = RIGHT;
    	}
    	else {
    		this.nextDirection = UP;
    	}
    }
    
    private void updateScore() {
    	this.score++;
    }

}
