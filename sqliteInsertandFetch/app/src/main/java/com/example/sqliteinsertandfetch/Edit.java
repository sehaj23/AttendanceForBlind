package com.example.sqliteinsertandfetch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    TextView rollno;
    EditText username;
    sqlitehelper sql;
    SQLiteDatabase mDatabase;
    Button update;
    String x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        rollno = (TextView)findViewById(R.id.roll);
        username = (EditText)findViewById(R.id.name);
        update = (Button)findViewById(R.id.update);
        sql = new sqlitehelper(this);
        mDatabase = openOrCreateDatabase(sqlitehelper.DATABASENAME, MODE_PRIVATE, null);
        Intent intent = getIntent();
        x =  intent.getStringExtra("rollno");

        fetchnames();



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (username.getText().toString().equals("")){
                        Toast.makeText(Edit.this,"USERNAME CANNOT BE NULL",Toast.LENGTH_LONG).show();
                    }else{
                        sql.updatestudent(Integer.parseInt(x),username.getText().toString());
                        Intent intent1 = new Intent(Edit.this,lists.class);
                        startActivity(intent1);
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



        }



        cursor.close();
    }



}

