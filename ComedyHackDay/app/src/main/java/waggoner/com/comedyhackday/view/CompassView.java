package waggoner.com.comedyhackday.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import waggoner.com.comedyhackday.R;


/**
 * Compass View that handles changing and animating rotation
 */
public class CompassView extends ImageView {

    private float mCurrentDegree = 0;

    public CompassView(Context context) {
        super(context);
        init(null, 0);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void animateDegree(float toDegree) {
        animateDegree(toDegree, 2500);
    }


    public void dueNorth(float severity) {
        if (mCurrentDegree > 180) {
            animateDegree(360f, 1000, severity);
        } else {
            animateDegree(0f, 1000, severity);
        }
    }

    public void dueSouth(float severity) {
        animateDegree(180f, 1000, severity);
    }

    public void animateDegree(float toDegree, int duration) {
        animateDegree(toDegree, duration, 2f);
    }

    public void animateDegree(float toDegree, int duration, float severity) {
        if (toDegree > 360f) {
            toDegree = toDegree % 360f;
        }

        ObjectAnimator oa;
        oa = ObjectAnimator.ofFloat(this, "degree", mCurrentDegree, toDegree);
        oa.setDuration(duration);
        oa.setInterpolator(new AnticipateOvershootInterpolator(severity, 4f));
        oa.start();
        mCurrentDegree = toDegree;
    }

    public void setDegree(float degree) {
        mCurrentDegree = degree;
        invalidate();
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CompassView, defStyle, 0);
        mCurrentDegree = a.getFloat(R.styleable.CompassView_degree, 0.f);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        canvas.rotate(mCurrentDegree, (float) Math.floor(contentWidth/2.f), (float) Math.floor(contentHeight/2.f));

        super.onDraw(canvas);
    }

    public float getDegree() {
        return mCurrentDegree;
    }

}
