package com.example.ecommerce.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String searchby ;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // TextView txt = (TextView)findViewById(R.id.textView9) ;
        switch (item.getItemId())
        {
            case R.id.action_search:
               // txt.setText("search button clicked");
                return true;
            case R.id.item1 :
                {
                    userprofile.setTypeofsearch("category");
              //  searchby= "category" ;
                    Intent i= new Intent(MainActivity.this,fragmentactivity.class) ;

                    startActivity(i);
                return true ;
            }
            case R.id.item2 : {
                userprofile.setTypeofsearch("product");
               // searchby = "product";
                Intent i= new Intent(MainActivity.this,fragmentactivity.class) ;
              //  i.putExtra("catname",searchby) ;
                startActivity(i);
                return true;
            }
            case R.id.shoopingcart:
            {
                Intent i = new Intent(MainActivity.this,mycart.class) ;
                startActivity(i);
            }

        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ListView  list =(ListView)findViewById(R.id.listview) ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
         final List<Integer> arr = new ArrayList<Integer>();
        Ecommerce obj = new Ecommerce(this) ;
        Cursor cursor =obj.fetchallcateories() ;

        if (cursor != null && cursor.getCount()>0)
        {
            while (!cursor.isAfterLast()) {
                adapter.add(cursor.getString(1));
                Toast.makeText(this, "catname" + cursor.getString(1), Toast.LENGTH_SHORT).show();
                arr.add(cursor.getInt(0));
                cursor.moveToNext();

            }
        }
        else
            Toast.makeText(this, "there is no category" , Toast.LENGTH_SHORT).show();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(MainActivity.this ,productActivity.class) ;
                intent.putExtra("catid",arr.get(i));
                startActivity(intent);
                arr.remove(i) ;

            }
        });

    }


}
