package com.example.hle1_gearbook;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class editItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public String newitemDate = null;
    public EditText itemDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        final Context context = getApplicationContext(); //Used for toast to display messages

        findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        Intent intent = getIntent();


        //Gets the fields then sets it so that they contain the data (about the item) that was
        //passed over
        itemDate = (EditText) findViewById(R.id.dateView);
        EditText itemMaker = (EditText) findViewById(R.id.maker);
        EditText itemDescription = (EditText) findViewById(R.id.description);
        EditText itemPrice = (EditText) findViewById(R.id.price);
        EditText itemComment = (EditText) findViewById(R.id.comment);

        itemDate.setText(intent.getStringExtra("date_key"));
        itemMaker.setText(intent.getStringExtra("maker_key"));
        itemDescription.setText(intent.getStringExtra("description_key"));
        itemPrice.setText(String.valueOf(intent.getDoubleExtra("price_key", -1)));
        itemComment.setText(intent.getStringExtra("comment_key"));

        Button btnEditItem = (Button) findViewById(R.id.edit);
        //When confirm button at bottom is clicked fields are verifyed and then if everything is
        //proper then an item is made and sent back to main
        btnEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newitemDate = itemDate.getText().toString();
                String newitemMaker = null;
                String newitemDescription = null;
                Double newitemPrice = null;
                String newitemComment = null;

                //Used for checking a validItem
                boolean validItem = false;
                boolean validPrice;
                boolean validMaker;
                boolean validDescription;
                boolean validComment;

                //Don't create an item until all fields are proper
                while(!validItem) {
                    EditText itemMaker = (EditText) findViewById(R.id.maker);
                    EditText itemDescription = (EditText) findViewById(R.id.description);
                    EditText itemPrice = (EditText) findViewById(R.id.price);
                    EditText itemComment = (EditText) findViewById(R.id.comment);

                    //Get all values in fields
                    newitemMaker = itemMaker.getText().toString();
                    newitemDescription = itemDescription.getText().toString();
                    try {
                        newitemPrice = Double.parseDouble(itemPrice.getText().toString());
                        validPrice = true;
                    } catch (Exception e) {
                        validPrice = false;
                    }
                    newitemComment = itemComment.getText().toString();

                    //Check all constraints
                    validMaker = newitemMaker.length() < 20;
                    validDescription = newitemDescription.length() < 40;
                    validComment = newitemComment.length() < 20;

                    if (validPrice && validMaker && validDescription && validComment) {
                        validItem = true;
                    } else {
                        if(!validPrice) Toast.makeText(context, "Please enter a valid price", Toast.LENGTH_SHORT).show();
                        if(!validMaker) Toast.makeText(context, "Please enter a valid maker (up to 20 characters)", Toast.LENGTH_SHORT).show();
                        if(!validDescription) Toast.makeText(context, "Please enter a valid description (up to 40 characters)", Toast.LENGTH_SHORT).show();
                        if(!validComment) Toast.makeText(context, "Please enter a valid comment (up to 20 characters)", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }//end while loop

                //Create item and send back to main
                item editItem = new item(newitemDate, newitemMaker, newitemDescription,
                        newitemPrice, newitemComment);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("editItemKey", editItem);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

        });
    }//end onCreate

    private void showDatePicker(){
        DatePickerDialog datePicker = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH) ,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePicker.show();
    }//end showDatePicker

    @Override
    //Sets item date and updates date text to the new date
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        newitemDate = year + "-" + month + "-" + day;
        itemDate.setText(newitemDate);
    }//end onDateSet

}//end Class
