package com.example.databaseexe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class display2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);


        TextView lbl=(TextView)findViewById(R.id.lbl);
        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        SQLiteDatabase sql= openOrCreateDatabase("test",MODE_PRIVATE,null);
        Button updt=(Button) findViewById(R.id.updt);
        Button del=(Button) findViewById(R.id.del);
        Intent go=new Intent(display2.this, MainActivity.class);

        getdata();

        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=spinner.getSelectedItem().toString();
                go.putExtra("User",user);
                startActivity(go);
                finish();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unm=spinner.getSelectedItem().toString();
                sql.execSQL("DELETE FROM try WHERE UserName='"+unm+"'");
                Toast.makeText(display2.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                getdata();

            }
        });

    }
    void getdata(){

        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        SQLiteDatabase sql= openOrCreateDatabase("test",MODE_PRIVATE,null);
        Cursor sel=sql.rawQuery("SELECT * FROM try",null);

        String nm="";
        if (sel.moveToFirst()) {
            do {

                nm += sel.getString(0)+",";

            } while (sel.moveToNext());
            // moving our cursor to next.
        }

        String[] data=nm.split(",");
        ArrayAdapter<String> adt=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data);

        spinner.setAdapter(adt);
    }
}