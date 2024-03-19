package com.example.mymoney;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        TextView textViewAbout = findViewById(R.id.textViewAbout);
        TextView textViewPrivacyPolicy = findViewById(R.id.textViewPrivacyPolicy);
        TextView textViewRateUs = findViewById(R.id.textViewRateUs);
        TextView textViewShare = findViewById(R.id.textViewShare);
        Button buttonBackStart = findViewById(R.id.buttonBackStart);

        textViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle "About" action
                //startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
            }
        });

        textViewPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage("https://sites.google.com/view/mymoneycalc/%D7%91%D7%99%D7%AA?authuser=2");
            }
        });

        textViewRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle "Rate Us" action
                openPlayStoreListing();
            }
        });

        textViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle "Share" action
                shareApp();
            }
        });

        buttonBackStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void openPlayStoreListing() {
        String appPackageName = "com.example.MyMoney.package";
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome app: https://play.google.com/store/apps/details?id=com.yourapp.package");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
