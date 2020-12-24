package com.example.hle1_gearbook;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/*
Main activity:
    3 buttons that allow you to: delete an item, add an item, and edit an item
    Shows your item list with all the item properties and below shows a total price
    of all your current items in your list
 */

public class MainActivity extends AppCompatActivity {

    private itemList myItemList = new itemList();
    public itemAdapter adapter;
    public TextView totalPrice;
    public int itempos; //Keeps track of last item position. Used to delete items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up all variables
        ListView item_list = findViewById(R.id.item_list);
        adapter = new itemAdapter(this, R.id.item_list, myItemList.getItems());
        totalPrice = (TextView) findViewById(R.id.totalPrice);
        totalPrice.setText(String.valueOf(myItemList.getPrice()));
        item_list.setAdapter(adapter);

        //Brings you to screen where you can add an item
        Button btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addItem = new Intent(MainActivity.this, addItem.class);
                startActivityForResult(addItem, 1);
                adapter.notifyDataSetChanged();
            }
        });

        //Gets item position on click
        item_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itempos = position;
            }
        });

        //Uses item position to delete the item
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myItemList.remove(itempos);
                totalPrice.setText(String.valueOf(myItemList.getPrice()));
                adapter.notifyDataSetChanged();
            }
        });

        //Gets item data then sends it to editItem through intent
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editItem = new Intent(MainActivity.this, editItem.class);
                editItem.putExtra("date_key", myItemList.getItem(itempos).getDate());
                editItem.putExtra("maker_key", myItemList.getItem(itempos).getMaker());
                editItem.putExtra("description_key", myItemList.getItem(itempos).getDescription());
                editItem.putExtra("price_key", myItemList.getItem(itempos).getPrice());
                editItem.putExtra("comment_key", myItemList.getItem(itempos).getComment());
                startActivityForResult(editItem, 2);
                adapter.notifyDataSetChanged();
            }
        });

    }//end onCreate

    //Handles what happens when you return from an activity
    //Request code 1 is for adding items and 2 is for editing items
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Gets item sent over by addItem and adds it to list, updates the price, then updates
        //the adapter
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                item newItem = (item)data.getSerializableExtra("itemKey");
                myItemList.addItem(newItem);
                totalPrice.setText(String.valueOf(myItemList.getPrice()));
                adapter.notifyDataSetChanged();
            }
            if (resultCode == RESULT_CANCELED) {
                ; //Pass
            }
        }
        //Gets item data sent over by editItem then changes the item to its new properties and also
        //updates price and adapter
        if (requestCode == 2) {
            if(resultCode == RESULT_OK){
                item editItem = (item)data.getSerializableExtra("editItemKey");
                item myItem = myItemList.getItem(itempos);
                myItem.setDate(editItem.getDate());
                myItem.setMaker(editItem.getMaker());
                myItem.setDescription(editItem.getDescription());
                myItem.setComment(editItem.getComment());
                myItem.setPrice(editItem.getPrice());
                totalPrice.setText(String.valueOf(myItemList.getPrice()));
                adapter.notifyDataSetChanged();
            }
            if(resultCode == RESULT_CANCELED){
                ; //Pass
            }
        }

    }//onActivityResult

}//end class
