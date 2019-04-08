package com.example.ecommerce.project;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder>{


    public myAdapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
       obj = new Ecommerce(context) ;
      // intent = new Intent(context,editCart.class) ;
    }

    private List<Listitem> listitems ;
    private Context context ;
    private Ecommerce obj ;
    //private Intent intent ;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.recyclerlist,parent,false) ;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Listitem listitem = listitems.get(position);
        holder.proname.setText(listitem.getProdname());
        holder.proquantity.setText(listitem.getProdquantity());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             // to delete cart from database
                obj.deletecart(listitem.getCart_id());
                Toast.makeText(context, "object deleted ", Toast.LENGTH_SHORT).show();
               //obj.deletecart(listitem.getCart_id());
                listitems.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listitems.size());

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // call another activity to edit quantity
                Intent i = new Intent(context,editCart.class) ;
                i.putExtra("proname",listitem.getProdname()) ;
                i.putExtra("proquantity",listitem.getProdquantity());
                i.putExtra("cartid",listitem.getCart_id());
                i.putExtra("pos",position) ;
                ((mycart)context).startActivity(i);
                ;
               // context.startActivity( new Intent(context,editCart.class));


            }
        });

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView proname  ;
        public TextView proquantity  ;
        public Button edit  ;
        public Button delete ;


        public ViewHolder(View itemView) {
            super(itemView);
            proname =(TextView) itemView.findViewById(R.id.textView18) ;
            proquantity =(TextView) itemView.findViewById(R.id.textView19) ;
            edit = (Button)itemView.findViewById(R.id.button7) ;
            delete = (Button)itemView.findViewById(R.id.button8) ;


        }
    }
}
