package com.example.ecommerce.project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class TextFragment extends Fragment {
    EditText mytext;
    Cursor matchedemployees;
    ListView mylist;
    Ecommerce myhelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_text, container, false);
        mytext = (EditText) rootview.findViewById(R.id.editText6) ;
        mylist = (ListView)rootview.findViewById(R.id.listview) ;
        myhelper = new Ecommerce(getActivity()) ;
        Button search = (Button)rootview.findViewById(R.id.button4) ;
        Button getproductapi = (Button)rootview.findViewById(R.id.button12) ;
       final ArrayAdapter<String> employeesadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        mylist.setAdapter(employeesadapter);
        // get product from api

         getproductapi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(getActivity(),productapi.class) ;
                 startActivity(i);
             }
         });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (userprofile.getTypeofsearch() == "product") ;
                {

                    Cursor matchedemoloyee = myhelper.getallproducts(mytext.getText().toString());
                    if (matchedemoloyee != null && matchedemoloyee.getCount() > 0) {
                        while (!matchedemoloyee.isAfterLast()) {
                            employeesadapter.add(matchedemoloyee.getString(0));
                            matchedemoloyee.moveToNext();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "no matched products", Toast.LENGTH_SHORT).show();
                    }

                }

                if (userprofile.getTypeofsearch() == "category")

                {
                    Cursor matchedemoloyee = myhelper.getallcategories(mytext.getText().toString());
                    if (matchedemoloyee != null && matchedemoloyee.getCount() > 0) {
                        while (!matchedemoloyee.isAfterLast()) {
                            employeesadapter.add(matchedemoloyee.getString(0));
                            matchedemoloyee.moveToNext();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "no matched categories", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });


        return rootview ;
    }


}
