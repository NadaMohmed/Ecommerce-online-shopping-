package com.example.ecommerce.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class editCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cart);

        final String proname = getIntent().getExtras().getString("proname");
        final String proquantity = getIntent().getExtras().getString("proquantity");
        final int cartid = getIntent().getExtras().getInt("cartid");
        final int pos = getIntent().getExtras().getInt("proquantity");
        final TextView pronametxt = (TextView)findViewById(R.id.textView20) ;
        final EditText proquantitytxt = (EditText)findViewById(R.id.editText9);
        pronametxt.setText(proname);
        proquantitytxt.setText(proquantity);
        Button btn = (Button)findViewById(R.id.button10) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(editCart.this,mycart.class) ;
                i.putExtra("quantity",proquantitytxt.getText().toString()) ;
                i.putExtra("cartid",cartid) ;
                i.putExtra("position",pos) ;
                setResult(RESULT_OK,i);
                boolean isEdited=new Ecommerce(view.getContext()).updateCart(cartid,Integer.parseInt(proquantitytxt.getText().toString()));
                if (isEdited== true)
                {
                    Toast.makeText(view.getContext(), "item updated", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(view.getContext(), "item not updated", Toast.LENGTH_SHORT).show();

                }
                finish();

            }
        });

    }
}
