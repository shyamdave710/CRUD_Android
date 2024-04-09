package com.example.databaseexe;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class singledata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singledata);

        Button pre=(Button)findViewById(R.id.pre);
        Button nxt=(Button)findViewById(R.id.next);
        TextView un=(TextView) findViewById(R.id.un);
        TextView pwd=(TextView) findViewById(R.id.pwd);

        SQLiteDatabase db= openOrCreateDatabase("test",MODE_PRIVATE,null);

        Cursor resultset = db.rawQuery("SELECT * FROM try",null);
        String nm="";

        resultset.moveToFirst();
        un.setText(resultset.getString(0));
        pwd.setText(resultset.getString(1));

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultset.isLast())
                {
                    resultset.moveToFirst();
                    un.setText(resultset.getString(0));
                    pwd.setText(resultset.getString(1));
                }
                else {
                    resultset.moveToNext();
                    un.setText(resultset.getString(0));
                    pwd.setText(resultset.getString(1));
                }
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultset.isFirst()) {
                    resultset.moveToLast();
                    un.setText(resultset.getString(0));
                    pwd.setText(resultset.getString(1));
                }
                else{
                    resultset.moveToPrevious();
                    un.setText(resultset.getString(0));
                    pwd.setText(resultset.getString(1));
                }
            }
        });

    }
}