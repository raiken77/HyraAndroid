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
public class CalculatorsFragment extends Fragment {

    EditText brand;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.calculator_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        brand = (EditText)getActivity().findViewById(R.id.brandValue);
    }

    public String getBrand()
    {
        return brand.getText().toString();
    }
}
