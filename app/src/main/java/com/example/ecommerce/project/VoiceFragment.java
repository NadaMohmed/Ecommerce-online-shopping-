package com.example.ecommerce.project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * create an instance of this fragment.
 */
public class VoiceFragment extends Fragment {

    EditText mytext;
    Cursor matchedemployees;
    int voicecode = 1;
    ListView mylist;
    Ecommerce myhelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_voice, container, false);
        myhelper = new Ecommerce(getActivity());
        mytext = (EditText) rootview.findViewById(R.id.editText3);
        mylist = (ListView) rootview.findViewById(R.id.listview);
        ImageButton voicebtn = (ImageButton) rootview.findViewById(R.id.imageButton);
        voicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(i, voicecode);
            }
        });
        return rootview;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == voicecode && resultCode == getActivity().RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mytext.setText(text.get(0));
            ArrayAdapter<String> employeesadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
            mylist.setAdapter(employeesadapter);

            if (userprofile.getTypeofsearch() == "product") ;
            {

            Cursor matchedemoloyee = myhelper.getallproducts(text.get(0));
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
            Cursor matchedemoloyee = myhelper.getallcategories(text.get(0));
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

    }

}