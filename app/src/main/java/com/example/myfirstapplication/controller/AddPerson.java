package com.example.myfirstapplication.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.datasource.FireBase;
import com.example.myfirstapplication.model.entities.Package;
import com.example.myfirstapplication.model.entities.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AddPerson extends AppCompatActivity {
    FireBase dal;
    Button btnAdd;
    Button btnClear;
    EditText name;
    EditText phone;
    EditText email;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.Phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        dal = new FireBase();
        configurebtnAdd();
        configurebtnClear();
    }

    private void configurebtnAdd() {
        address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String curAddress = getLocationFromAddress(address.getText().toString());
                    address.setText(curAddress);// code to execute when EditText loses focus
                }
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curAddress = getLocationFromAddress(address.getText().toString());
                address.setText(curAddress);// code to execute when EditText loses focus
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() && !name.getText().toString().matches("") && !phone.getText().toString().matches("") && !address.getText().toString().matches("")&& (address.getText() !=null)) {
                    Person person = new Person(name.getText().toString(), phone.getText().toString(), email.getText().toString(), address.getText().toString());
                    dal.addPerson(person);
                    Toast.makeText(AddPerson.this, "Person Added ", Toast.LENGTH_LONG).show();
                    finish();
                } else
                    Toast.makeText(AddPerson.this, "Check input ", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void configurebtnClear() {
    }



    public String getLocationFromAddress(String address) {
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocationName(address, 5);
            return addresses.get(0).getAddressLine(0);

        } catch (Exception ex) {
            Toast.makeText(this, "Error in getting location of address: " + address, Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
