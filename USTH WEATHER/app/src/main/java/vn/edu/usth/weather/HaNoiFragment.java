package vn.edu.usth.weather;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HaNoiFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hanoi_weather, container, false);
    }

    public static HaNoiFragment newInstance() {
        HaNoiFragment frag_layout1 = new HaNoiFragment();
        return frag_layout1;
    }
}