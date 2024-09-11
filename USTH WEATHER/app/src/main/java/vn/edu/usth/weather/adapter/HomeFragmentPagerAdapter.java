package vn.edu.usth.weather.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import vn.edu.usth.weather.ForecastFragment;
import vn.edu.usth.weather.ParisFragment;
import vn.edu.usth.weather.ToulouseFragment;
import vn.edu.usth.weather.WeatherFragment;
import vn.edu.usth.weather.HaNoiFragment;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String[] titles = new String[] { "Hanoi", "Paris", "Toulouse" };

    public HomeFragmentPagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HaNoiFragment();
            case 1:
                return new ParisFragment();
            case 2:
                return new ToulouseFragment();
            default:
                return new HaNoiFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch (position) {
            case 0:
                title = "Ha Noi";
                break;
            case 1:
                title = "Paris";
                break;
            case 2:
                title = "Toulouse";
                break;
        }
        return title;
    }
}
