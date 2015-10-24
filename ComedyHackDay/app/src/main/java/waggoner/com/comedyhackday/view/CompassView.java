package waggoner.com.comedyhackday.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import waggoner.com.comedyhackday.R;


/**
 * Compass View that handles changing and animating rotation
 */
public class CompassView extends View {
    private Drawable mDrawable;

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
        animateDegree(toDegree, 1000);
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
        animateDegree(toDegree, duration, 0f);
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

        if (a.hasValue(R.styleable.CompassView_drawable)) {
            mDrawable = a.getDrawable(
                    R.styleable.CompassView_drawable);
            mDrawable.setCallback(this);
        }
        a.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        canvas.rotate(mCurrentDegree, (float) Math.floor(contentWidth/2.f), (float) Math.floor(contentHeight/2.f));

        if (mDrawable != null) {
            mDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mDrawable.draw(canvas);
        }
    }
}
