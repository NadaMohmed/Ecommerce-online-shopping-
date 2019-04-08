package com.example.ecommerce.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.*;

public class productDetailsActivity extends AppCompatActivity {
    float productprice;
    public static ArrayList<ProductPrice> pr=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        final int proid = getIntent().getExtras().getInt("product_id");
//       final int userid = getIntent().getExtras().getInt("userid") ;

        final TextView proname = (TextView) findViewById(R.id.textView9);
        final TextView proprice = (TextView) findViewById(R.id.textView10);
        final TextView pronquantity = (TextView) findViewById(R.id.textView11);
        final Button addtocart = (Button) findViewById(R.id.button6);
        final EditText quantity = (EditText) findViewById(R.id.editText8);


        final Ecommerce obj = new Ecommerce(this);
        final Cursor cursor = obj.getProductsDetails(proid);

        if (cursor != null && cursor.getCount() > 0) {
            proname.setText(cursor.getString(0));
            proprice.setText(String.valueOf(cursor.getFloat(1)));
            pronquantity.setText(String.valueOf(cursor.getInt(2)));
            productprice = cursor.getFloat(1);
        }
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = Integer.parseInt(quantity.getText().toString());
                if (result > cursor.getInt(2) || result <= 0) {
                    Toast.makeText(productDetailsActivity.this, "there is no avaliable quantity ", Toast.LENGTH_SHORT).show();

                } else {
                    Log.i("PPP", userprofile.getUserid() + "   " + proid + "   " + result + "  " + cursor.getString(0));

                    boolean i = obj.addtocart(userprofile.getUserid(), proid, result, cursor.getString(0),cursor.getFloat(1));
                    if (i == true) {
                        pr.add(new ProductPrice(productprice,result));
                        Toast.makeText(productDetailsActivity.this, "done", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(productDetailsActivity.this, "?????????????", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

}
