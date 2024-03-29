package com.example.ecommerce.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.

 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment {
    View rootview;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private final int Camera_Request = 1888;
    private ImageView mycapturedimage ;
    Intent  cameraintent ;
    ListView mylist;
    Ecommerce myhelper;


/*
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CameraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CameraFragment.
     */
  /*  // TODO: Rename and change types and number of parameters
    public static CameraFragment newInstance(String param1, String param2) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootview= inflater.inflate(R.layout.fragment_camera,container,false) ;
        myhelper = new Ecommerce(getActivity());
       mylist = (ListView)rootview.findViewById(R.id.listview) ;
      //  mycapturedimage =(ImageView)rootview.findViewById(R.id.imageView) ;
        final Button  b = (Button)rootview.findViewById(R.id.button5) ;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {/*
                 cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE) ;
                    startActivityForResult(cameraintent,Camera_Request);*/
              // new IntentIntegrator(getActivity()).initiateScan();

                Intent i = new Intent(getActivity(),barcode.class) ;
                startActivity(i);
            }
        });
        return rootview ;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Camera_Request && resultCode ==  getActivity().RESULT_OK)
        {
            ArrayAdapter<String> employeesadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
            mylist.setAdapter(employeesadapter);

           // Bitmap myimage  = (Bitmap)data.getExtras().get("data") ;
        //mycapturedimage.setImageBitmap(myimage);

        }

        else
            {

            final EditText search = (EditText) rootview.findViewById(R.id.editText14);
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
                }

                else
                    {

                        search.setText(result.getContents());
                    //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                   }
            }
            else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }
    /*  // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
