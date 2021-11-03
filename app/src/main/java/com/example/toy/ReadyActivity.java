package com.example.toy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ReadyActivity extends AppCompatActivity {

    public String[] name = new String[5];

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        button = (Button) findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNum();

                Intent intent = new Intent(ReadyActivity.this, MainLanderActivity.class);
                Random rand = new Random();
                intent.putExtra("winner", rand.nextInt(5)+1);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    public void setNum() {
        name[0] = (((EditText)findViewById(R.id.etName1)).getText().toString());
        name[1] = (((EditText)findViewById(R.id.etName2)).getText().toString());
        name[2] = (((EditText)findViewById(R.id.etName3)).getText().toString());
        name[3] = (((EditText)findViewById(R.id.etName4)).getText().toString());
        name[4] = (((EditText)findViewById(R.id.etName5)).getText().toString());
    }
}