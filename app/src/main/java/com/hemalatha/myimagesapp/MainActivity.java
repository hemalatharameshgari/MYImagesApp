package com.hemalatha.myimagesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.imagename);
    }

    public void submit(View view) {
        String imgs=ed.getText().toString();
        Log.i("imageurl",imgs);
        if(imgs==""){
           Toast.makeText(this, "pls enter", Toast.LENGTH_SHORT).show();
       }
        else{
            Intent intent=new Intent(this,DetailActivity.class);
            intent.putExtra("names",imgs);
            startActivity(intent);
        }
    }
}
