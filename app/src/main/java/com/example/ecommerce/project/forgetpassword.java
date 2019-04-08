package com.example.ecommerce.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class forgetpassword extends AppCompatActivity {
    TextView username ;
    Ecommerce obj ;
    Spinner spinner ;
    String question ;
    EditText answer  ;
    Button login ;
    String ques ;
    String getextra ;
    private static final String TAG = "myApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        getextra = getIntent().getExtras().getString("user_name");
        username = (TextView) findViewById(R.id.textView4) ;
        username.setText(getIntent().getExtras().getString("user_name"));
        spinner = (Spinner)findViewById(R.id.spinner3) ;
        login =  (Button)findViewById(R.id.button2);
        answer = (EditText)findViewById(R.id.editText2) ;
        obj = new Ecommerce(this) ;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                question = spinner.getSelectedItem().toString() ;
                if (question.equals("whats your favourite color") )
                    ques = "whats your favourite color" ;
                else if (question.equals("whats name of your primary school"))
                    ques = "whats name of your primary school" ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ques = "whats your favourite color" ;

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor = obj.forgetpassword(getextra,ques,answer.getText().toString()) ;

                if (cursor != null && cursor.getCount() > 0)
                {
                    Toast.makeText(forgetpassword.this, "done", Toast.LENGTH_SHORT).show();

                    userprofile.setUserid(cursor.getInt(2));

                    Intent i = new Intent(forgetpassword.this,MainActivity.class) ;

                    startActivity(i);

                }

                else
                {
                    Toast.makeText(forgetpassword.this, " answer not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }
}
