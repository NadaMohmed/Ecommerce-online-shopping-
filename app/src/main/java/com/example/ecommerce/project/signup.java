package com.example.ecommerce.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Properties;


public class signup extends AppCompatActivity {
 String question ;
 String ques ;
 //String answer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        final Button signup= (Button)findViewById(R.id.button3) ;
        final Ecommerce obj = new Ecommerce(this) ;

        //final EditText name = (EditText)findViewById(R.id.editText10)  ;
        final EditText usernam = (EditText)findViewById(R.id.editText11)  ;
        final EditText password = (EditText)findViewById(R.id.editText12)  ;
        final RadioButton Male = (RadioButton)findViewById(R.id.radioButton9)  ;
        final RadioButton Female = (RadioButton)findViewById(R.id.radioButton10)  ;
       // final DatePicker birthdate = (DatePicker)findViewById(R.id.datePicker2) ;
        final Button calenderbtn = (Button)findViewById(R.id.calenderbtn) ;
        final TextView calendertxt = (TextView)findViewById(R.id.calendertxt) ;
        final String date = getIntent().getStringExtra("date") ;
        calendertxt.setText(date);

      //  final EditText job = (EditText)findViewById(R.id.editText13)  ;
        final Spinner spinner = (Spinner)findViewById(R.id.spinner) ;
        final EditText answer = (EditText)findViewById(R.id.editText) ;
        final EditText email = (EditText)findViewById(R.id.editText7) ;
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

            }
        });

        calenderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(signup.this,calenderActivity.class)  ;
                startActivity(intent);

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  gender = "" ;
                //birthdate
                String datee = date ;
                if (!(/*name.getText().toString().equals("") &&*/ usernam.getText().toString().equals("") && password.getText().toString().equals("")/* && job.getText().toString().equals("") */))
                {
                    if (Male.isChecked())
                    {
                        gender  = "Male"  ;
                    }
                     if (Female.isChecked())
                    {
                        gender  = "Female"  ;
                    }
                    if (gender.equals(""))
                    {
                        Toast.makeText(signup.this, "please choose gender", Toast.LENGTH_SHORT).show();
                    }

                    obj.signup(usernam.getText().toString(),password.getText().toString(),gender,datee,ques , answer.getText().toString(),email.getText().toString());
                    Toast.makeText(signup.this, "done ♥", Toast.LENGTH_LONG).show();

                   GMailSender sm= new GMailSender(signup.this, "nadamohamedfcis1997@gmail.com", "online shopping", " you have registered succesfully");

                    //Executing sendmail to send email
                    sm.execute();
                    Intent i = new Intent(signup.this,login.class) ;
                    startActivity(i);
                }
                else
                {

                    Toast.makeText(signup.this, "please fill all elemnts", Toast.LENGTH_LONG).show();
                }





            }
        });




    }
}
