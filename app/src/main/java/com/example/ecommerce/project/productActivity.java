package com.example.ecommerce.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class productActivity extends AppCompatActivity {
    List<Integer> arr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

       final ListView list = (ListView)findViewById(R.id.listview) ;
      final   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        int catid = getIntent().getExtras().getInt("catid") ;
        final Ecommerce obj = new Ecommerce(this) ;

        Cursor cursor =obj.getproducts(catid) ;

        arr = new ArrayList<Integer>();
        if (cursor != null && cursor.getCount()>0)
        {
            while (!cursor.isAfterLast()) {


                adapter.add(cursor.getString(0));
                //adapter.add(String.valueOf( cursor.getInt(3)));
                arr.add(cursor.getInt(3));
                cursor.moveToNext();

            }

        }
        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if (  arr.isEmpty())
                {
                Toast.makeText(productActivity.this, arr.get(i),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(productActivity.this,productDetailsActivity.class) ;
                    intent.putExtra("product_id", arr.get(i)) ;
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(productActivity.this, "empty",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                Intent intent = new Intent(productActivity.this,productDetailsActivity.class) ;
                intent.putExtra("product_id", arr.get(i)) ;
                startActivity(intent);
                arr.remove(i) ;

            }
        });


    }
}
