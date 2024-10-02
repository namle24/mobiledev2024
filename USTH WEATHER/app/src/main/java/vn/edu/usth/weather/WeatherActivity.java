package vn.edu.usth.weather;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

        AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {

            @Override
            protected void onPreExecute() {
                // Preparation before background task (like showing a progress bar, etc.)
                Toast.makeText(WeatherActivity.this, "Starting network request...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                // Simulate network delay using sleep()
                try {
                    Thread.sleep(3000); // Simulating 3 seconds network request delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null; // Simulate returning a null Bitmap as we're not loading an actual image here
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                // This could update a progress bar, but here it's left as a placeholder
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                // Task completion: show a toast message
                Toast.makeText(WeatherActivity.this, "Network request completed!", Toast.LENGTH_SHORT).show();
            }
        };

        task.execute("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");
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

        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
// This method is executed in main thread
                String content = msg.getData().getString("server_response");
                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
// this method is run in a worker thread
                try {
// wait for 5 seconds to simulate a long network access
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putString("server_response", "some sample json here");
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        t.start();

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
