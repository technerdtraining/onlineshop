package com.example.technerd.onlineshop;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    //initialise Arraylist,listview,listAdapter,context
    ArrayList<Product> arrayList=new ArrayList<>();
    ListView listView;
    ListAdapter listAdapter;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating 12 products for testing
        arrayList.add(new Product(R.drawable.styleshoes01,"FreeWalk Series", "Black Kimba", "RM45","FW01"));
        arrayList.add(new Product(R.drawable.styleshoes02,"FreeWalk Series", "Orange Ozzy", "10% Discount=RM45","FW02"));
        arrayList.add(new Product(R.drawable.styleshoes03,"FreeWalk Series","Turtle Torquise","RM45","FW03"));
        arrayList.add(new Product(R.drawable.styleshoes04,"FreeWalk Series","Punchy Purple","RM45","FW04"));
        arrayList.add(new Product(R.drawable.styleshoes05,"Lunar Series","Amber Spirit","RM45","LS01"));
        arrayList.add(new Product(R.drawable.styleshoes06,"Lunar Series","Red Heart","RM45","LS02"));
        arrayList.add(new Product(R.drawable.styleshoes07,"Lunar Series","Blue Light","RM45","LS03"));
        arrayList.add(new Product(R.drawable.styleshoes08,"Lunar Series","Yummy Cream","RM45","LS04"));
        arrayList.add(new Product(R.drawable.styleshoes09,"Hype Series","Gangsta Gold","RM45","HS01"));
        arrayList.add(new Product(R.drawable.styleshoes10,"Hype Series","Pinky Promise","RM45","HS02"));
        arrayList.add(new Product(R.drawable.styleshoes11,"Hype Series","Blue Ocean","RM45","HS03"));
        arrayList.add(new Product(R.drawable.styleshoes12,"Hype Series","Rocky Grey","RM45","HS04"));

        //initalize the list view widget
        listView= (ListView) findViewById(R.id.listView);

        //initialize listAdapter
        listAdapter= new ListAdapter(this,arrayList);
        listAdapter.notifyDataSetChanged();
        //Setting Adapter
        listView.setAdapter(listAdapter);

        //setting clicklistener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderForm(listAdapter.getItem(position));
            }
        });
    }

    public void sentEmail(Product product,String Name,String Address,String Postcode,String Quantity,String Remarks){
        //Stringbuilder to update the form that we have received
        StringBuilder sb=new StringBuilder();
        sb.append("Name: "+Name);
        sb.append('\n');
        sb.append("Address: "+Address);
        sb.append('\n');
        sb.append("Postcode: "+Postcode);
        sb.append('\n');
        sb.append("Quantity: "+Quantity);
        sb.append('\n');
        sb.append("Product Code: "+product.getCode());
        sb.append('\n');
        sb.append("Remarks: "+Remarks);

        //start intent to invoke email client
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("vnd.android.cursor.item/email");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"abc@xyz.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Order On product : " + product.getTitle());
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, sb.toString());
        startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
    }

    public void orderForm(final Product product){
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.order_form);
        dialog.setTitle("Order Form");

        //initialize widget for to be used in java class
        final EditText etName=(EditText) dialog.findViewById(R.id.etName);
        final EditText etAdress=(EditText) dialog.findViewById(R.id.etAdress);
        final EditText etPostCode=(EditText) dialog.findViewById(R.id.etPostCode);
        final EditText etQuantity=(EditText) dialog.findViewById(R.id.etQuantity);
        final EditText etRemarks=(EditText) dialog.findViewById(R.id.etRemarks);
        final CheckBox checkBox=(CheckBox) dialog.findViewById(R.id.checkBox);

        Button btnCancel=(Button) dialog.findViewById(R.id.btnCancel);
        Button btnAccept=(Button) dialog.findViewById(R.id.btnAccept);

        //set listener for button
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check for condition to enbale user to procedd
                if(TextUtils.isEmpty(etName.getText()) ||
                        TextUtils.isEmpty(etAdress.getText()) ||
                        TextUtils.isEmpty(etPostCode.getText()) ||
                        TextUtils.isEmpty(etQuantity.getText())||
                        !checkBox.isChecked())
                {
                    //set error message
                    //checking every empty textview
                    if(TextUtils.isEmpty(etName.getText())){
                        etName.setError("Please Insert Name");
                    }
                    if(TextUtils.isEmpty(etAdress.getText())){
                        etAdress.setError("Please Insert Adress");
                    }
                    if(TextUtils.isEmpty(etPostCode.getText())){
                        etPostCode.setError("Please Insert PostCode");
                    }
                    if(TextUtils.isEmpty(etQuantity.getText())){
                        etQuantity.setError("Please Insert Quantity");
                    }
                    if(!checkBox.isChecked()){
                        Toast.makeText(context, "Please Accept The Agreement",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //condition is fulfilled, proceed to the next step
                    sentEmail(product,etName.getText().toString(),
                            etAdress.getText().toString(),
                            etPostCode.getText().toString(),
                            etQuantity.getText().toString(),
                            etRemarks.getText().toString());

                }
            }
        });
        //set listener for cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //showing the dialog
        dialog.show();
    }
}
