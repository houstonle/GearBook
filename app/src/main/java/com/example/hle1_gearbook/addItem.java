package com.example.hle1_gearbook;


import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;

public class addItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public String newitemDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        final Context context = getApplicationContext(); //Used for toast to display messages

        findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        //Gets fields and if proper then creates items and send it over
        Button btnAddItem = (Button) findViewById(R.id.add);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newitemMaker = null;
                String newitemDescription = null;
                Double newitemPrice = null;
                String newitemComment = " ";

                //Used for checking if fields are valid
                boolean validItem = false;
                boolean validDate;
                boolean validPrice;
                boolean validMaker;
                boolean validDescription;
                boolean validComment;


                //Won't allow user to add an item until all fields are proper
                while(!validItem) {
                    EditText itemMaker = (EditText) findViewById(R.id.maker);
                    EditText itemDescription = (EditText) findViewById(R.id.description);
                    EditText itemPrice = (EditText) findViewById(R.id.price);
                    EditText itemComment = (EditText) findViewById(R.id.comment);

                    //Gets values from fields
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
                    validDate = newitemDate != null;

                    if (validPrice && validMaker && validDescription && validComment && validDate) {
                        validItem = true;
                    } else {
                        if(!validPrice) Toast.makeText(context, "Please enter a valid price", Toast.LENGTH_SHORT).show();
                        if(!validMaker) Toast.makeText(context, "Please enter a valid maker (up to 20 characters)", Toast.LENGTH_SHORT).show();
                        if(!validDescription) Toast.makeText(context, "Please enter a valid description (up to 40 characters)", Toast.LENGTH_SHORT).show();
                        if(!validComment) Toast.makeText(context, "Please enter a valid comment (up to 20 characters)", Toast.LENGTH_SHORT).show();
                        if(!validDate) Toast.makeText(context, "Please enter in a date", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }//end while loop

                //Create new item and send to main activity
                item newItem = new item(newitemDate, newitemMaker, newitemDescription,
                        newitemPrice, newitemComment);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("itemKey", newItem);
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
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        newitemDate = year + "-" + month + "-" + day;
    }//end onDateSet

}//end Class
