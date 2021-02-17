package com.theo.videogameqizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView version;
    private TextView name;
    private TextView mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        String versionApp = "0";
        String nameApp = null;
        String mailApp = null;
        Intent about = getIntent();
        version = findViewById(R.id.aboutVersionTextView);
        name = findViewById(R.id.aboutNameTextView);
        mail = findViewById(R.id.aboutMailTextView);
        String[] nameDev = about.getStringArrayExtra("name");
        String[] mailDev = about.getStringArrayExtra("mail");
        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            versionApp = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        version.setText("v."+versionApp);
        for (String n : nameDev) {
            if (nameApp == null){
                nameApp = n;
            }else {
                nameApp = nameApp
                        + System.getProperty("line.separator")
                        + n;
            }
        }
        name.setText(nameApp);
        for (String m : mailDev) {
            if (mailApp == null){
                mailApp = m;
            }else {
                mailApp = mailApp
                        + System.getProperty("line.separator")
                        + m;
            }
        }
        mail.setText(mailApp);
    }
}