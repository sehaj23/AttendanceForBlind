package com.example.sqliteinsertandfetch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button submit,next,allnames,report;
    sqlitehelper sql;
    Date date1,date2;
    SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= (EditText)findViewById(R.id.name1);
        submit = (Button)findViewById(R.id.submit);
        allnames = (Button)findViewById(R.id.all);
        report=(Button)findViewById(R.id.report);


        next=(Button)findViewById(R.id.next);
        sql = new sqlitehelper(this);
        int totalstudent = sql.totalstudent();
        sharedPreferences = MainActivity.this.getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("totalstudent", String.valueOf(totalstudent));
        editor.commit();



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                sql.addstudent(new student(null,Name));
                Toast.makeText(MainActivity.this,"USERNAME INSERTED",Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String lasttime = sharedPreferences.getString("lasttime", null);

                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");

                Date currentTime = Calendar.getInstance().getTime();
                //String currentime = sdf.format(currentTime);
//                try {
//                    date1 = format.parse(lasttime);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    date2 = format.parse(String.valueOf(currentTime));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                long mills = date1.getTime() - date2.getTime();
//                int hours = (int) (mills / (1000 * 60 * 60));
              //  if (hours >=20){
                //    Toast.makeText(MainActivity.this,"ATTENDANCE ALREADY TAKEN",Toast.LENGTH_LONG).show();
                //}else{

                    Intent intent = new Intent(MainActivity.this, fetch.class);
                    startActivity(intent);
                }
           // }
        });
        allnames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,lists.class);
                startActivity(intent);
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,report.class);
                startActivity(intent);
            }
        });








    }
    @RequiresApi(api = Build.VERSION_CODES.N)


    private boolean checktimings(String time, String endtime) {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }
}

