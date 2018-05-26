package com.androidtutorialshub.loginregister.activities;

/**
 * Created by rohit on 10/2/2017.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import static android.R.attr.onClick;
import static android.R.id.edit;
import static android.os.Build.VERSION_CODES.M;
import static com.androidtutorialshub.loginregister.R.id.textInputEditTextEmail;

public class Loginsuccess extends AppCompatActivity {
    EditText editDate, editSource, editDeparture, editDestination, editArrival, editType, editSeats, editFare, editNumber , editID;
    Button btnAddRide , btnViewRides , btnUpdateRide , btnDelete;
    DatabaseeHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginn);

            myDb = new DatabaseeHelper(this);

        editDate = (EditText) findViewById(R.id.editText_date);
        editSource = (EditText) findViewById(R.id.editText_source);
        editDeparture = (EditText) findViewById(R.id.editText_departure);
        editDestination = (EditText) findViewById(R.id.editText_destination);
        editArrival = (EditText) findViewById(R.id.editText_arrival);
        editType = (EditText) findViewById(R.id.editText_type_car);
        editNumber = (EditText) findViewById(R.id.editText_number);
        editSeats = (EditText) findViewById(R.id.editText_seats);
        editFare = (EditText) findViewById(R.id.editText_fare);
        editNumber = (EditText) findViewById(R.id.editText_number);
        btnAddRide = (Button)findViewById(R.id.button_add);
        btnViewRides=(Button)findViewById(R.id.button_view);
        editID=(EditText) findViewById(R.id.editText_ID);
        btnUpdateRide=(Button)findViewById(R.id.button_update);
        btnDelete=(Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void AddData() {
        btnAddRide.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(editDate.getText().toString(),
                                editSource.getText().toString(),
                                editDeparture.getText().toString(),
                                editDestination.getText().toString(),
                                editArrival.getText().toString(),
                                editType.getText().toString(),
                                editSeats.getText().toString(),
                                editFare.getText().toString(),
                                editNumber.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(Loginsuccess.this, "Ride  Added", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Loginsuccess.this, "Ride not Added", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }
    public void UpdateData() {
        btnUpdateRide.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editID.getText().toString(),editDate.getText().toString(),
                                editSource.getText().toString(),
                                editDeparture.getText().toString(),
                                editDestination.getText().toString(),
                                editArrival.getText().toString(),
                                editType.getText().toString(),
                                editSeats.getText().toString(),
                                editFare.getText().toString(),
                                editNumber.getText().toString()
                               );
                        if(isUpdate == true)
                            Toast.makeText(Loginsuccess.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Loginsuccess.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll() {
        btnViewRides.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllRides();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID  :"+ res.getString(0)+"\n");
                            buffer.append("Date of Journey :"+ res.getString(1)+"\n");
                            buffer.append("Source :"+ res.getString(2)+"\n");
                            buffer.append("Departure :"+ res.getString(3)+"\n");
                            buffer.append("Destination :"+ res.getString(4)+"\n");
                            buffer.append("Arrival :"+ res.getString(5)+"\n");
                            buffer.append("Type of Car:"+ res.getString(6)+"\n");
                            buffer.append("Number of Seats :"+ res.getString(7)+"\n");
                            buffer.append("Fare  :"+ res.getString(8)+"\n");
                            buffer.append("Phone number :"+ res.getString(9)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Loginsuccess.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Loginsuccess.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}



