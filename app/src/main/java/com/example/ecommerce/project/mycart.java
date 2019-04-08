package com.example.ecommerce.project;

import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class mycart extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private RecyclerView.Adapter  adapter;
    private List <Listitem> listitems ;
    List<Integer> arr ;
    List<Float> productprices ;
    List<Integer> productquantity ;
    List<String> productname ;
    int cartid = 0;
    String quantity = null;
    private  Ecommerce obj ;
    Button showBtnOrder ;
    Button buy ;
    float count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
         obj = new Ecommerce(this) ;
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        buy = (Button)findViewById(R.id.button13) ;
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = "" ;
                StringBuilder pro = new StringBuilder()  ;

                productname = new ArrayList<String>() ;
                Cursor proname = obj.gettocart(userprofile.getUserid()) ;

                if (proname != null && proname.getCount()>0)
                {
                    while (!proname.isAfterLast()) {
                        //get product name
                        productname.add(proname.getString(0)) ;
                        proname.moveToNext() ;

                    }
                }

                for (int i=0 ; i< productname.size();i++)
                {
                    pro.append(",") ;
                    pro.append( productname.get(i)) ;
                }
                str = pro.toString() ;
               Log.i("name" ,str) ;
                Cursor cursor = obj.getuseremail(userprofile.getUserid()) ;
     String email = cursor.getString(0) ;
     GMailSender sm= new GMailSender(mycart.this, email, "online shopping", " you have ordered " + pro.toString()+ "and price" );
                sm.execute();

            }
        });


        showBtnOrder = findViewById(R.id.button11);
        //getfrom intent


        showBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txt = (TextView)findViewById(R.id.textView22) ;

                productprices = new ArrayList<Float>() ;
                productquantity  = new ArrayList<Integer>() ;

                Cursor cursor = obj.gettocart(userprofile.getUserid()) ;

                if (cursor != null && cursor.getCount()>0)
                {
                    while (!cursor.isAfterLast()) {
                       //get product prices
                        productprices.add(cursor.getFloat(4)) ;
                        productquantity.add(cursor.getInt(1)) ;
                        cursor.moveToNext() ;

                    }
                }

                for (int i=0 ;i< productquantity.size();i++)
                {
                    count+=(productprices.get(i)*productquantity.get(i));
                   // count+=(productDetailsActivity.pr.get(i).getPrice()*productDetailsActivity.pr.get(i).getQuantity());
                    //Toast.makeText(mycart.this,count+"",Toast.LENGTH_LONG).show();
                    Log.i("P",count+"");
                    txt.setText(String.valueOf(count));


//                    listitems.get(i).
                }
                count = 0 ;

            }
        });

        Button btn= (Button)findViewById(R.id.button9) ;

btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(mycart.this,MapsActivity.class) ;
        startActivity(intent);
    }
});
    }

    @Override
    protected void onResume() {
        super.onResume();
        listitems = new ArrayList<>() ;
        Cursor cursor = obj.gettocart(userprofile.getUserid()) ;
        Listitem listitem;
        arr = new ArrayList<Integer>();


        if (cursor != null && cursor.getCount()>0)
        {
            while (!cursor.isAfterLast()) {
                listitem = new Listitem(cursor.getString(0),String.valueOf(cursor.getInt(1)),cursor.getInt(3)) ;
                listitems.add(listitem) ;
                arr.add(cursor.getInt(2)) ;
               // productprices.add(cursor.getFloat(4)) ;
                cursor.moveToNext() ;

            }
        }
        adapter = new myAdapter(listitems,this) ;
        recyclerView.setAdapter(adapter);

    }


}
