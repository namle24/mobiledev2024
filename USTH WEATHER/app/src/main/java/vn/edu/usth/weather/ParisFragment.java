package vn.edu.usth.weather;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ParisFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.paris_weather, container, false);
    }

    public static ParisFragment newInstance() {
        ParisFragment frag_layout2 = new ParisFragment();
        return frag_layout2;
    }
}