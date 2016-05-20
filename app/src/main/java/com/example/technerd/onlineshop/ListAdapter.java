package com.example.technerd.onlineshop;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by technerd on 20/01/2016.
 */
public class ListAdapter extends ArrayAdapter<Product> {
    //Context initiallization
    Context context;

    public ListAdapter(Activity context,ArrayList<Product> products){
        super(context,R.layout.single,products);
        //Get context From the activity
        this.context=context;
    }

    @Override
    public View getView(int position,View view,ViewGroup viewGroup){
        //get product from the position selected from the list
        Product product=getItem(position);
        //initialised the object widget
        ImageView ivProduct;
        TextView tvTitle,tvDescription,tvPrice;
        //layout inflater for single.xml to be used in the adapter
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View rowView= inflater.inflate(R.layout.single,null,true);

        //initializing all the widget that to be used within the java class
        ivProduct=(ImageView) rowView.findViewById(R.id.ivProduct);
        tvDescription=(TextView) rowView.findViewById(R.id.tvDescription);
        tvTitle=(TextView) rowView.findViewById(R.id.tvTitle);
        tvPrice=(TextView) rowView.findViewById(R.id.tvPrice);

        Integer num;
        num=new Integer(10);
        tvTitle.setText(num);

        //setting the wigdet with the values from the object products
        ivProduct.setImageDrawable(ContextCompat.getDrawable(context,product.getImageID()));
        //tvTitle.setText(product.getTitle());
        tvDescription.setText(product.getDescrption());
        tvPrice.setText(product.getPrice());

        return rowView;
    }
}
