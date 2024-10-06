package vn.edu.usth.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForecastFragment extends Fragment {

    private ImageView logoImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        // Find the ImageView for the logo
        logoImageView = view.findViewById(R.id.logo_image_view);

        // Execute AsyncTask to download the USTH logo and set it to the ImageView
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(getContext(), "Downloading USTH logo...", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Bitmap doInBackground(String... urls) {
                String urlDisplay = urls[0];
                Bitmap logo = null;

                try {
                    // Initialize URL
                    URL url = new URL(urlDisplay);

                    // Make a request to the server
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);

                    // Allow reading response code and response data
                    connection.connect();

                    // Receive response
                    int response = connection.getResponseCode();
                    Log.i("USTHWeather", "The response is: " + response);

                    // Process image response
                    if (response == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();
                        logo = BitmapFactory.decodeStream(is);
                    } else {
                        Log.e("ForecastFragment", "Failed to download image: " + response);
                    }

                    connection.disconnect();
                } catch (Exception e) {
                    Log.e("ForecastFragment", "Error: " + e.getMessage());
                }

                return logo;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                // Update the ImageView with the downloaded bitmap
                if (result != null) {
                    logoImageView.setImageBitmap(result);
                    Toast.makeText(getContext(), "USTH logo downloaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to download USTH logo", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");

        return view;
    }
}

