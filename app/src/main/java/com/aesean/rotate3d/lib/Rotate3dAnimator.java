package com.aesean.rotate3d.lib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.View;

public class Rotate3dAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {
    private static double K = Math.sqrt(2.0f);

    private static final int TYPE_SCALE = 0;
    private static final int TYPE_PX = 1;

    private View mTargetView;

    private final float mFromDegrees;
    private final float mToDegrees;
    private float mCenterX;
    private float mCenterY;
    private float mDepthZ;
    private int mType = TYPE_PX;
    private boolean mNeedInit = true;
    private final boolean mReverse;
    private boolean mException = true;

    private boolean mVisibleBeforeStart = false;

    public Rotate3dAnimator(float fromDegrees, float toDegrees, boolean reverse) {
        this(fromDegrees, toDegrees, 0.5f, 0.5f, 0.5f, reverse, TYPE_SCALE);
    }

    public Rotate3dAnimator(float fromDegrees, float toDegrees,
                            float centerX, float centerY, float depthZ,
                            boolean reverse, int type) {
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mReverse = reverse;
        this.mCenterX = centerX;
        this.mCenterY = centerY;
        this.mDepthZ = depthZ;
        this.mType = type;
        addUpdateListener(this);
        addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (!mVisibleBeforeStart) {
                    mTargetView.setVisibility(View.VISIBLE);
                }
                if (mNeedInit) {
                    if (mType == TYPE_SCALE) {
                        mCenterX = mCenterX * mTargetView.getWidth();
                        mCenterY = mCenterY * mTargetView.getHeight();
                    }
                    mTargetView.setPivotX(mCenterX);
                    mTargetView.setPivotY(mCenterY);
                    mNeedInit = false;
                }
                removeListener(this);
            }
        });
        setFloatValuesSafe(0f, 1f);
    }

    private void setFloatValuesSafe(float... values) {
        mException = false;
        setFloatValues(values);
        mException = true;
    }

    @Override
    public void setFloatValues(float... values) {
        if (mException) {
            throw new IllegalAccessError("Disable call. ");
        }
        super.setFloatValues(values);
    }

    public void setStartDelay(long startDelay, boolean visibleBeforeStart) {
        super.setStartDelay(startDelay);
        mVisibleBeforeStart = visibleBeforeStart;
    }

    View getTargetView() {
        return mTargetView;
    }

    @Override
    public void setTarget(@Nullable Object target) {
        super.setTarget(target);
        if (target == null) {
            throw new NullPointerException("Target can't be null.");
        }
        mTargetView = (View) target;
        if (!mVisibleBeforeStart) {
            mTargetView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float progress = (float) animation.getAnimatedValue();
        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * progress);
        double value = mDepthZ * K;
        if (mReverse) {
            // progress 0 - 1
            // exit 1 -> value
            mTargetView.setScaleX((float) (1 - (1 - value) * progress));
            mTargetView.setScaleY((float) (1 - (1 - value) * progress));
        } else {
            // progress 0 - 1
            // enter value -> 1
            mTargetView.setScaleX((float) (value + (1 - value) * progress));
            mTargetView.setScaleY((float) (value + (1 - value) * progress));
        }
        mTargetView.setRotationY(degrees);
    }
}
