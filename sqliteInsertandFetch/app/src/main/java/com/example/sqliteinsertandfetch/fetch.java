package com.example.sqliteinsertandfetch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class fetch extends AppCompatActivity {
    SQLiteDatabase mDatabase;
    TextView rollno,username;
    sqlitehelper sql;
    List<student> Student;
    Button next,present,absent;
    int x = 1;
    static int count=0;
    SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        mDatabase = openOrCreateDatabase(sqlitehelper.DATABASENAME, MODE_PRIVATE, null);
    rollno = (TextView) findViewById(R.id.rollno);
    username =(TextView)findViewById(R.id.username);
        sharedPreferences = getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);


    present = (Button)findViewById(R.id.present);
    absent = (Button)findViewById(R.id.absent);
sql = new sqlitehelper(this);
        fetchnames();


    present.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {


            String totalstudents = sharedPreferences.getString("totalstudent",null);

   //         Toast.makeText(fetch.this,totalstudents,Toast.LENGTH_LONG).show();
                        if(count == Integer.parseInt(totalstudents)){
                Toast.makeText(fetch.this,"ALL STUDENTS ATTENDANCE TAKEN",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(fetch.this,report.class);
                            startActivity(intent);
                }else {
                            String roll = rollno.getText().toString();
                            sql.markpresentattendance(Integer.parseInt(roll));
                            fetchnames();
                            count++;
                        }



        }
    });
    absent.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View v) {

            String totalstudents = sharedPreferences.getString("totalstudent",null);
            if(count == Integer.parseInt(totalstudents)){
                Toast.makeText(fetch.this,"ALL STUDENTS ATTENDANCE TAKEN",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(fetch.this,report.class);
                startActivity(intent);
            }else{
                String roll = rollno.getText().toString();
                sql.markabsentattendance(Integer.parseInt(roll));
                fetchnames();
                count++;
            }

        }
    });



    }
    public void fetchnames() {

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM STUDENTS where ID =" + x, null);
        if (cursor.moveToFirst()) {


            String id = cursor.getString(0);

            String name = cursor.getString(1);
            username.setText(name);
            rollno.setText(String.valueOf(id));
            x++;


        }



        cursor.close();
        }



    }







