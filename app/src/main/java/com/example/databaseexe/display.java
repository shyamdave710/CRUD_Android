package com.example.databaseexe;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView test=(TextView)findViewById(R.id.txt);
        ListView lst=(ListView) findViewById(R.id.lst);
        SQLiteDatabase db= openOrCreateDatabase("test",MODE_PRIVATE,null);



        Cursor resultset = db.rawQuery("SELECT * FROM try",null);
        String nm="";
        if (resultset.moveToFirst()) {
            do {

                nm +="Name : " +resultset.getString(0)+"Password : "+ resultset.getString(1)+",";

            } while (resultset.moveToNext());
            // moving our cursor to next.
        }


        String[] un= nm.split(",");
        ArrayAdapter<String> adt= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,un);
        lst.setAdapter(adt);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}