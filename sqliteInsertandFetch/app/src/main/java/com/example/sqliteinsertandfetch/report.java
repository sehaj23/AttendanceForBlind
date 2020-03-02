package com.example.sqliteinsertandfetch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class report extends AppCompatActivity {
    TextView total, present, absent, datee;
    sqlitehelper sql;
    SharedPreferences sharedPreferences;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        total = (TextView) findViewById(R.id.total);
        present = (TextView) findViewById(R.id.present);
        absent = (TextView) findViewById(R.id.absent);
        datee = (TextView) findViewById(R.id.date);
        sql = new sqlitehelper(this);
        int presentstudent = sql.getpresent();
        int absentstudent = sql.getabsent();
        int totalstudent = sql.totalstudent();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        Date currentTime = Calendar.getInstance().getTime();


        String oldtime = sdf.format(currentTime);

          Toast.makeText(report.this,oldtime,Toast.LENGTH_LONG).show();

        sharedPreferences = report.this.getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lasttime", oldtime);
        editor.commit();


        present.setText(String.valueOf(presentstudent));
        absent.setText(String.valueOf(absentstudent));
        total.setText(String.valueOf(totalstudent));
        datee.setText(date);


    }
}

