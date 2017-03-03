package funniehunnie.magnussimulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Funniehunnie on 3/2/2017.
 */

public class RectPlayer implements GameObject {

    private Rect rectangle;
    private int color;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private AnimationManager animManager;

    public Rect getRectangle() {
        return rectangle;
    }

    public RectPlayer(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_front1);
        Bitmap walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_front2);
        Bitmap walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_front3);
        Bitmap walk3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_left1);
        Bitmap walk4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_left2);
        Bitmap walk5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_left3);
        Bitmap walk6 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_right1);
        Bitmap walk7 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_right2);
        Bitmap walk8 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yellow_right3);


        idle = new Animation(new Bitmap[]{idleImg, walk1, walk2}, 2);
//        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);
        walkRight = new Animation(new Bitmap[]{walk6, walk7, walk8}, 0.5f);
//        Matrix m = new Matrix();
//        m.preScale(-1, 1);
//        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
//        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

//        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);
        walkLeft = new Animation(new Bitmap[]{walk3, walk4, walk5}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});
    }

    @Override
    public void draw(Canvas canvas) {
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rectangle, paint);
        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animManager.update();
    }

    public void update(Point point) {
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);

        int state = 0;
        if(rectangle.left - oldLeft > 5)
            state = 1;
        else if(rectangle.left - oldLeft < -5)
            state = 2;

        animManager.playAnim(state);
        animManager.update();
    }
}