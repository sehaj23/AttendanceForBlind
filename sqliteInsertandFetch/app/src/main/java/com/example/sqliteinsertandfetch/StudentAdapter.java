package com.example.sqliteinsertandfetch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<student> {
    Context mCtx;
    int listLayoutRes;
    List<student> students;
    SQLiteDatabase mDatabase;


    public StudentAdapter(Context mCtx, int listLayoutRes, List<student> students, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, students);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.students = students;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.studentlistview, null);
         student Student = students.get(position);
        TextView textViewName = view.findViewById(R.id.name1);
        TextView textViewroll = view.findViewById(R.id.roll);
        textViewName.setText(Student.getUSERNAME());
        textViewroll.setText(Student.getID());

        return view;


    }


}
