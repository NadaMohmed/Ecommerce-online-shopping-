package com.example.ecommerce.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    String user_name ;
    int user_id ;
    EditText username ;
    EditText password ;
    Button login ;
     Ecommerce obj ;
     boolean check  ;
     String usernamee ;
     String passwordd ;

    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         username = (EditText) findViewById(R.id.editText4);
         password = (EditText) findViewById(R.id.editText5);
         login = (Button) findViewById(R.id.button);
           obj = new Ecommerce(this);
           check = false ;
        saveLoginCheckBox = (CheckBox)findViewById(R.id.checkBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true)
        {
            username.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }




          //to make line under textview
        TextView textView = (TextView)findViewById(R.id.text) ;
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // saveLoginCheckBox.setVisibility(View.VISIBLE);
                // shared preferance
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(username.getWindowToken(), 0);

                usernamee = username.getText().toString();
                passwordd = password.getText().toString();

                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", usernamee);
                    loginPrefsEditor.putString("password", passwordd);
                    loginPrefsEditor.commit();
                   // saveLoginCheckBox.setVisibility(View.INVISIBLE);
                }
                else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }



                if (!(username.getText().toString().equals("") && password.getText().toString().equals("") ))
                {
                    Cursor cursor = obj.login(username.getText().toString(), password.getText().toString());
                    if (cursor != null && cursor.getCount() > 0) {



                      // check =true ;
                       // user_name = cursor.getString(0) ;
                        user_id = cursor.getInt(2) ;
                        Toast.makeText(login.this, cursor.getString(0) + " " + cursor.getString(1) + " " +cursor.getInt(2) + "  " +cursor.getString(3) + "  " +cursor.getString(4) , Toast.LENGTH_LONG).show();
                         Intent i = new Intent(login.this,MainActivity.class) ;
                         startActivity(i);
                         //Intent intet = new Intent(login.this,productDetailsActivity.class) ;
                         //intet.putExtra("userid" ,user_id) ;

                        userprofile.setUserid(user_id);


                    }
                    else
                    {
                        Toast.makeText(login.this, "Wrong user name or password", Toast.LENGTH_LONG).show();
                    }

                }

                else
                {
                    Toast.makeText(login.this, "please fill all elements", Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    public void click(View v)

    {
       int id = v.getId() ;
       if (id==R.id.text)

       {
           Intent intent = new Intent (this ,signup.class) ;
           startActivity(intent);

       }


    }

    public void forgetpassword (View v)

    {
      //  password.setVisibility(View.INVISIBLE);

        int id = v.getId() ;
        if (id==R.id.textView3)
        {

            if (!(username.getText().toString().equals(""))) {
                Cursor cursor = obj.checkusername(username.getText().toString());
                if (cursor != null && cursor.getCount() > 0) {
                    Intent i = new Intent(login.this, forgetpassword.class);
                    i.putExtra("user_name", username.getText().toString());
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(login.this, "please enter valid username", Toast.LENGTH_LONG).show();
                }

            }

            else if (username.getText().toString().equals(""))
                {

                Toast.makeText(login.this, "please fill username", Toast.LENGTH_LONG).show();
                }
        }

    }
}
