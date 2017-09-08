package com.aesean.rotate3d;

import android.animation.Animator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aesean.rotate3d.lib.AnimationHelper;

/**
 * SampleFragmentV11
 *
 * @author xl
 * @version 1.0
 * @since 08/09/2017
 */
public class SampleFragmentV11 extends Fragment {

    @ColorRes
    private static final int[] COLOR = new int[]{R.color.cyan500, R.color.purple500};

    private static final String KEY_TEXT = "KEY_TEXT";
    private static final String KEY_COLOR = "KEY_COLOR";
    private static int sIndex = -1;
    private String mText = "Rotate_3D";
    @ColorInt
    private int mColor;

    public static Fragment newInstance(String text) {
        Fragment fragment = new SampleFragmentV11();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @ColorInt
    private int getColor() {
        int i = ++sIndex;
        if (i >= COLOR.length) {
            i = sIndex = 0;
        }
        return ResourcesCompat.getColor(getResources(), COLOR[i], getActivity().getTheme());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mText = savedInstanceState.getString(KEY_TEXT);
        } else {
            Bundle bundle = getArguments();
            if (bundle != null) {
                mText = bundle.getString(KEY_TEXT);
            }
            mColor = getColor();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TEXT, mText);
        outState.putInt(KEY_COLOR, mColor);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(mText);
        view.setBackgroundColor(mColor);
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        Animator animator = AnimationHelper.onCreateAnimator(transit, enter, nextAnim);
        if (animator == null) {
            return super.onCreateAnimator(transit, enter, nextAnim);
        } else {
            return animator;
        }
    }
}
