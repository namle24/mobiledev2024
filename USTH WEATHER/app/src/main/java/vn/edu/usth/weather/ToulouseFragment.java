package vn.edu.usth.weather;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ToulouseFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.toulouse_weather, container, false);
    }

    public static ToulouseFragment newInstance() {
        ToulouseFragment frag_layout3 = new ToulouseFragment();
        return frag_layout3;
    }
}