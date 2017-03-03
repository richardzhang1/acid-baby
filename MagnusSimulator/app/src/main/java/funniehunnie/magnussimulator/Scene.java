package funniehunnie.magnussimulator;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Funniehunnie on 3/2/2017.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
