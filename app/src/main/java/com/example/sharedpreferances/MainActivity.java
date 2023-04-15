package com.example.sharedpreferances;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    String text;
    int sum = 0;
    Button count, reset, exit;
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = findViewById(R.id.button);
        reset = findViewById(R.id.button2);
        exit = findViewById(R.id.button3);
        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editText);
        read();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        String str = item.getTitle().toString();
        if(str.equals("Credits"))
        {
            write();
            Intent si = new Intent(this, Credits.class);
            startActivity(si);
        }
        return true;
    }

    public void read()
    {
        SharedPreferences settings = getSharedPreferences("Data",MODE_PRIVATE);
        sum = settings.getInt("count", 0);
        text = settings.getString("str", "");
        tv.setText(String.format("%d", sum));
        et.setText(text);
    }

    public void write()
    {
        text = String.valueOf(et.getText());
        SharedPreferences settings = getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("str", text);
        editor.putInt("count", sum);
        editor.commit();
    }

    public void addToSum(View view)
    {
        sum++;
        tv.setText(String.format("%d", sum)); // show number after update
    }

    public void resetSum(View view)
    {
        sum = 0;
        tv.setText(String.format("%d", sum)); // show number after update
    }

    public void exitAndSave(View view)
    {
        write();
        finishAffinity();
        System.exit(0);
    }
}