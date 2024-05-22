package com.example.taskmanagerapp;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Displaytask extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaytask);
        db = new DatabaseHelper(this); // Initialize the database helper

        Button backButton = findViewById(R.id.button4);
        Button view = findViewById(R.id.button7);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Displaytask.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                int count = 1;
                while(res.moveToNext()){

                    buffer.append(count++).append(".").append(res.getString(0)).append("\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Displaytask.this);
                builder.setCancelable(true);
                builder.setTitle("Titles List");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Displaytask.this, MainActivity.class));
            }
        });
    }
}
