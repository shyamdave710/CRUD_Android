package com.example.databaseexe;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(MainActivity.this, display.class);
        Intent intt = new Intent(MainActivity.this, singledata.class);
        Intent kaik = new Intent(MainActivity.this, display2.class);

        Intent get = getIntent();
        String user = get.getStringExtra("User");

        Button save = findViewById(R.id.save);
        Button displayBtn = findViewById(R.id.display);
        Button data = findViewById(R.id.data);
        Button upd = findViewById(R.id.updtdel);

        EditText un = findViewById(R.id.name);
        EditText pwd = findViewById(R.id.pwd);

        SQLiteDatabase db = openOrCreateDatabase("test", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS try(UserName varchar, Password varchar);");

        if (user == null) {
            //Insert Record
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(un.getText().toString().isEmpty() || pwd.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please fill all values", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        db.execSQL("INSERT INTO try VALUES ('" + un.getText() + "', '" + pwd.getText() + "');");
                        un.setText("");
                        pwd.setText("");
                        Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //Display Record
            displayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(i);
                }
            });

            //Display Single data
            data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intt);
                }
            });

            //button for to page to display in spinner control
            upd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(kaik);
                }
            });

        } else {

            save.setText("Edit");
            displayBtn.setVisibility(View.INVISIBLE);
            data.setVisibility(View.INVISIBLE);
            upd.setVisibility(View.INVISIBLE);

            Cursor c= db.rawQuery("SELECT * FROM try WHERE UserName='"+user+"'",null);
            c.moveToFirst();
            un.setText(user);
            pwd.setText(c.getString(1));

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.execSQL("UPDATE try set UserName='"+un.getText().toString()+"', Password='"+pwd.getText().toString()+"' WHERE UserName='"+user+"'");

                    Toast.makeText(MainActivity.this ,"Data Updated", Toast.LENGTH_LONG).show();
                    Intent go=new Intent(MainActivity.this, display2.class);
                    startActivity(go);
                    finish();
                }
            });
        }


    }
}