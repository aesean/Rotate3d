package com.aesean.rotate3d;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;

import com.aesean.rotate3d.lib.AnimationHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String TAG_LEFT_TOP_0 = "TAG_LEFT_TOP_0";
    private static final String TAG_LEFT_TOP_1 = "TAG_LEFT_TOP_1";
    private static final String TAG_RIGHT_TOP_0 = "TAG_RIGHT_TOP_0";
    private static final String TAG_RIGHT_TOP_1 = "TAG_RIGHT_TOP_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragmentV4(R.id.fragment_container_0, TAG_LEFT_TOP_0, TAG_LEFT_TOP_1);
        showFragmentV11(R.id.fragment_container_1, TAG_RIGHT_TOP_0, TAG_RIGHT_TOP_1);

        findViewById(R.id.fragment_container_0).setOnClickListener(new View.OnClickListener() {
            boolean show0 = false;

            @Override
            public void onClick(View view) {
                if (show0) {
                    showFragmentV4(R.id.fragment_container_0, TAG_LEFT_TOP_0, TAG_LEFT_TOP_1);
                } else {
                    showFragmentV4(R.id.fragment_container_0, TAG_LEFT_TOP_1, TAG_LEFT_TOP_0);
                }
                show0 = !show0;
            }
        });
        findViewById(R.id.fragment_container_1).setOnClickListener(new View.OnClickListener() {
            boolean show0 = false;

            @Override
            public void onClick(View view) {
                if (show0) {
                    showFragmentV11(R.id.fragment_container_1, TAG_RIGHT_TOP_0, TAG_RIGHT_TOP_1);
                } else {
                    showFragmentV11(R.id.fragment_container_1, TAG_RIGHT_TOP_1, TAG_RIGHT_TOP_0);
                }
                show0 = !show0;
            }
        });
        findViewById(R.id.fragment_container_2).setOnClickListener(new View.OnClickListener() {
            boolean show0 = false;

            @Override
            public void onClick(View view) {
                final View view0 = findViewById(R.id.left_bottom_0);
                final View view1 = findViewById(R.id.left_bottom_1);
                final Animation rotate3dEnterAnimation = AnimationHelper.createRotate3dEnterAnimation();
                final Animation rotate3dExitAnimation = AnimationHelper.createRotate3dExitAnimation();
                if (show0) {
                    view0.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.INVISIBLE);
                    view0.startAnimation(rotate3dEnterAnimation);
                    view1.startAnimation(rotate3dExitAnimation);
                } else {
                    view0.setVisibility(View.INVISIBLE);
                    view1.setVisibility(View.VISIBLE);
                    view0.startAnimation(rotate3dExitAnimation);
                    view1.startAnimation(rotate3dEnterAnimation);
                }
                show0 = !show0;
            }
        });
        findViewById(R.id.fragment_container_3).setOnClickListener(new View.OnClickListener() {
            boolean show0 = false;

            @Override
            public void onClick(View view) {
                View view0 = findViewById(R.id.right_bottom_0);
                View view1 = findViewById(R.id.right_bottom_1);
                Animator rotate3dEnterAnimator = AnimationHelper.createRotate3dEnterAnimator();
                Animator rotate3dExitAnimator = AnimationHelper.createRotate3dExitAnimator();
                if (show0) {
                    rotate3dEnterAnimator.setTarget(view1);
                    rotate3dEnterAnimator.start();
                    rotate3dExitAnimator.setTarget(view0);
                    rotate3dExitAnimator.start();
                } else {
                    rotate3dEnterAnimator.setTarget(view0);
                    rotate3dEnterAnimator.start();
                    rotate3dExitAnimator.setTarget(view1);
                    rotate3dExitAnimator.start();
                }
                show0 = !show0;
            }
        });
    }

    private void showFragmentV4(@IdRes int containerId, String showTag, String hideTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        AnimationHelper.setUpRotate3dAnimation(transaction);
        Fragment showFragment = fragmentManager.findFragmentByTag(showTag);
        if (showFragment == null) {
            showFragment = SampleFragmentV4.newInstance("SampleFragmentV4\n" + showTag);
            transaction.add(containerId, showFragment, showTag);
        } else {
            transaction.show(showFragment);
        }

        Fragment hideFragment = fragmentManager.findFragmentByTag(hideTag);
        if (hideFragment != null) {
            transaction.hide(hideFragment);
        }
        transaction.commit();
    }

    private void showFragmentV11(@IdRes int containerId, String showTag, String hideTag) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        AnimationHelper.setUpRotate3dAnimator(transaction);
        android.app.Fragment showFragment = fragmentManager.findFragmentByTag(showTag);
        if (showFragment == null) {
            showFragment = SampleFragmentV11.newInstance("SampleFragmentV11\n" + showTag);
            transaction.add(containerId, showFragment, showTag);
        } else {
            transaction.show(showFragment);
        }

        android.app.Fragment hideFragment = fragmentManager.findFragmentByTag(hideTag);
        if (hideFragment != null) {
            transaction.hide(hideFragment);
        }
        transaction.commit();
    }
}
