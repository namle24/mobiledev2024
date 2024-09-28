package vn.edu.usth.weather;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.edu.usth.weather.adapter.HomeFragmentPagerAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mTablayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);

        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);

        mTablayout.setupWithViewPager(mViewPager);


        try (InputStream inputStream = getResources().openRawResource(R.raw.music_1);
             OutputStream outputStream = new FileOutputStream(new File(getFilesDir(), "rickroll.mp3"))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error copying media file", e);
        }

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(new File(getFilesDir(), "rickroll.mp3").getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "Error playing media file", e);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu with actions
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks
        int id = item.getItemId();

        if (id == R.id.refresh) {
            // Show a toast when Refresh is clicked
            Toast.makeText(this, "Refresh clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.setting) {
            // Start PrefActivity when Settings is clicked
            startActivity(new Intent(this, PrefActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy called");
    }
}
