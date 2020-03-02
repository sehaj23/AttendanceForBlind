package com.example.sqliteinsertandfetch;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class lists extends AppCompatActivity {
    List<student> students;
    SQLiteDatabase mDatabase;
    ListView listView;
    StudentAdapter adapter;
    sqlitehelper sql;
    String student_id;
    Button clear;
    int listPosition;
    AdapterView.AdapterContextMenuInfo info;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        listView = (ListView)findViewById(R.id.list);
        students = new ArrayList<>();
        sql = new sqlitehelper(this);
        clear = findViewById(R.id.clear);
       
        mDatabase = openOrCreateDatabase(sqlitehelper.DATABASENAME, MODE_PRIVATE, null);
        shownamesfromdatabase();
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql.deleteallStudent();
                shownamesfromdatabase();
            }
        });

    }

    private void shownamesfromdatabase() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM STUDENTS", null);
        if (cursor.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the current booking list
                students.add(new student(
                        cursor.getString(0),
                        cursor.getString(1)
                                                //   cursorEmployees.getString(4)

                ));
            } while (cursor.moveToNext());
        }
        //closing the cursor
        cursor.close();

        //creating the adapter object
        adapter = new StudentAdapter(this, R.layout.studentlistview, students, mDatabase);

        //adding the adapter to listview
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuinf = new MenuInflater(this);
        menuinf.inflate(R.menu.context_menu,menu);
        if (menu != null) {
            menu.setHeaderTitle("Select The Action");
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       

        switch (item.getItemId()){



           

            case R.id.edit:
                info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

                listPosition = info.position;
                student_id = adapter.getItem(listPosition).getID();//list
              

              Intent intent = new Intent(lists.this,Edit.class);
              intent.putExtra("rollno",student_id);
              startActivity(intent);
                break;
            case R.id.delete:
                info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
               int listPositionn = info.position;
                String student_idd = adapter.getItem(listPositionn).getID();//list
                sql.deleteStudent(Integer.parseInt(student_idd));
              Intent intent1 = new Intent(lists.this,lists.class);
              startActivity(intent1);
                break;


//                int listPosition = info.position;
//                listView.get(listPosition).getTitle();











        }
        return super.onContextItemSelected(item);

    }


}

