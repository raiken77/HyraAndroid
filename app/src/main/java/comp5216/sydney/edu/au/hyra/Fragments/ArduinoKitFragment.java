package comp5216.sydney.edu.au.hyra.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import comp5216.sydney.edu.au.hyra.R;

/**
 * Created by apple on 10/16/15.
 */
public class ArduinoKitFragment extends Fragment {

    EditText features;
    EditText microcontroller;
    EditText clockSpeed;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.arduino_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        features = (EditText)getActivity().findViewById(R.id.featuresValue);
        microcontroller = (EditText)getActivity().findViewById(R.id.microcontrollerValue);
        clockSpeed = (EditText)getActivity().findViewById(R.id.clockSpeedVal);
    }

    public String getFeatures() {
        return features.getText().toString();
    }

    public String getMicrocontroller() {
        return microcontroller.getText().toString();
    }

    public String getClockSpeed() {
        return clockSpeed.getText().toString();
    }
}
