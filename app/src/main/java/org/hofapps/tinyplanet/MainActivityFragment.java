package org.hofapps.tinyplanet;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private PlanetChangeCallBack mPlanetChangeCallBacks;
    private CoordinatorLayout coordinatorLayout;

    private static final int ARRAY_MIN_POS = 0;
    private static final int ARRAY_MAX_POS = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SeekBar.OnSeekBarChangeListener listener = getSeekBarListener();

        SeekBar sizeSeekBar = (SeekBar) view.findViewById(R.id.size_seekBar);
        sizeSeekBar.setOnSeekBarChangeListener(listener);

        SeekBar scaleSeekBar = (SeekBar) view.findViewById(R.id.scale_seekBar);
        scaleSeekBar.setOnSeekBarChangeListener(listener);

        SeekBar angleSeekBar = (SeekBar) view.findViewById(R.id.angle_seekBar);
        angleSeekBar.setOnSeekBarChangeListener(listener);

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout);

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                View coordinatorLayoutView = findViewById(R.id.slidingLayout);
                Snackbar.make(coordinatorLayout, "snackbar test", Snackbar.LENGTH_LONG).show();
//                Snackbar.make(view, "Hello Snackbar", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mPlanetChangeCallBacks = (PlanetChangeCallBack) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement SeekBarCallBacks.");
        }
    }

    @Override
    public void onDetach() {

        super.onDetach();
        mPlanetChangeCallBacks = null;

    }


    private SeekBar.OnSeekBarChangeListener getSeekBarListener() {

        SeekBar.OnSeekBarChangeListener l = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int id = seekBar.getId();

                int value;

                if (id == R.id.size_seekBar) {

                    value = getSeekBarValue(R.array.size_seekbar_values, i);
                    mPlanetChangeCallBacks.onSizeChange(value);
                }
                else if (id == R.id.scale_seekBar) {

                    value = getSeekBarValue(R.array.scale_seekbar_values, i);
                    mPlanetChangeCallBacks.onScaleChange(value);

                }
                else if (id == R.id.angle_seekBar) {

                    value = getSeekBarValue(R.array.angle_seekbar_values, i);
                    mPlanetChangeCallBacks.onAngleChange(value);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        return l;

    }

    private int getSeekBarValue(int seekBarId, int pos) {

        int value;

        int[] array = getResources().getIntArray(seekBarId);

        value = (int) (pos * (array[ARRAY_MAX_POS] - array[ARRAY_MIN_POS])) / 100;

        return value;

    }

//    private SeekBar.OnSeekBarChangeListener getScaleSeekBarListener() {
//
//        SeekBar.OnSeekBarChangeListener l = new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//
//                int sId = seekBar.getId();
//                int scaleId = R.id.scale_seekBar;
//
//                mPlanetChangeCallBacks.onScaleChange(i);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
////                mPlanetChangeCallBacks.onSizeChange();
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
////                mPlanetChangeCallBacks.onSizeChange();
//
//            }
//        };
//
//        return l;
//
//    }


    public static interface PlanetChangeCallBack {

        void onSizeChange(int size);
        void onScaleChange(int scale);
        void onAngleChange(int angle);
    }




}
