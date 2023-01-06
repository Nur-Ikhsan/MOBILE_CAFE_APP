package praktik.pmobile.responsikodea.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000; // waktu splash screen (dalam milisecond)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menampilkan splash screen
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(mainIntent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}

