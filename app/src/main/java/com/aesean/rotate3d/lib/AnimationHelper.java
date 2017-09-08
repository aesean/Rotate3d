package com.aesean.rotate3d.lib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import com.aesean.rotate3d.R;

/**
 * AnimationHelper
 *
 * @author xl
 * @version 1.0
 * @since 06/09/2017
 */
public class AnimationHelper {
    private AnimationHelper() {
    }

    public static void setUpRotate3dAnimation(android.support.v4.app.FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.rotate_3d_enter, R.anim.rotate_3d_exit);
    }

    public static Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (nextAnim == R.anim.rotate_3d_enter) {
            return createRotate3dEnterAnimation();
        }
        if (nextAnim == R.anim.rotate_3d_exit) {
            return createRotate3dExitAnimation();
        }
        return null;
    }

    public static Animation createRotate3dExitAnimation() {
        Rotate3dAnimation animation = new Rotate3dAnimation(0, 90, true);
        animation.setDuration(300);
        animation.setFillAfter(false);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }

    public static Animation createRotate3dEnterAnimation() {
        final Rotate3dAnimation animation = new Rotate3dAnimation(270, 360, false);
        animation.setDuration(600);
        animation.setStartOffset(300);
        animation.setFillAfter(false);
        animation.setInterpolator(new DecelerateInterpolator());
        return animation;
    }

    public static void setUpRotate3dAnimator(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.animator.rotate_3d_enter, R.animator.rotate_3d_exit);
    }

    public static Animator createRotate3dExitAnimator() {
        final Rotate3dAnimator animator = new Rotate3dAnimator(0, 90, true);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animator.getTargetView().setVisibility(View.INVISIBLE);
            }
        });
        return animator;
    }

    public static Animator createRotate3dEnterAnimator() {
        final Rotate3dAnimator animator = new Rotate3dAnimator(270, 360, false);
        animator.setDuration(600);
        animator.setStartDelay(300, false);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animator.getTargetView().setVisibility(View.VISIBLE);
            }
        });
        return animator;
    }

    public static Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (nextAnim == R.animator.rotate_3d_enter) {
            return createRotate3dEnterAnimator();
        }
        if (nextAnim == R.animator.rotate_3d_exit) {
            return createRotate3dExitAnimator();
        }
        return null;
    }

}