package com.example.taskmanagerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewDetail extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        db = new DatabaseHelper(this);
        Button backButton = findViewById(R.id.button5);
        Button view = findViewById(R.id.button8);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(ViewDetail.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                int count = 1;
                while(res.moveToNext()){
                    buffer.append(count++).append(".Title:").append(res.getString(0)).append("\n");
                    buffer.append("Description :"+res.getString(1)+"\n");
                    buffer.append("DueDate :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewDetail.this);
                builder.setCancelable(true);
                builder.setTitle("Task Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewDetail.this, MainActivity.class));
            }
        });
    }
}
