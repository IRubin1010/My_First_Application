package com.example.myfirstapplication.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.datasource.FireBase;
import com.example.myfirstapplication.model.entities.CurrentAddress;
import com.example.myfirstapplication.model.entities.Package;
import com.example.myfirstapplication.model.entities.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class AddPackage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name;
    EditText phone;
    EditText email;
    Spinner packageType;
    Spinner fragile;
    Spinner weight;
    Button btnOrder;
    Button btnClear;
    Person person;
    FireBase dal;
    CurrentAddress currentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package);

        person = new Person();
        dal = new FireBase();

        name = findViewById(R.id.name);
        phone = findViewById(R.id.Phone);
        email = findViewById(R.id.email);

        packageType = findViewById(R.id.packageTypeSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.packageTypeArray, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        packageType.setAdapter(adapter1);

        fragile = findViewById(R.id.fragileSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.fragileArray, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fragile.setAdapter(adapter2);

        weight = findViewById(R.id.weightSpinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.weightArray, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight.setAdapter(adapter3);

        packageType.setOnItemSelectedListener(this);
        fragile.setOnItemSelectedListener(this);
        weight.setOnItemSelectedListener(this);


        btnOrder = findViewById(R.id.btnOrder);
        btnClear = findViewById(R.id.btnClear);

        updateCurrentAddress();

        configurebtnOrder();
        configurebtnClear();
    }

    private void configurebtnOrder() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPerson(phone.getText().toString());
            }
        });
    }

    private void configurebtnClear() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    public void getPerson(final String phoneNumber) {

        FireBase.personsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() && !name.getText().toString().matches("") && !phone.getText().toString().matches("") && !name.getText().toString().matches("")) {
                    if (dataSnapshot.hasChild(phoneNumber)) {
                        person = dataSnapshot.child(phoneNumber).getValue(Person.class);
                        Package p = new Package(currentAddress, person, packageType.getItemAtPosition(packageType.getSelectedItemPosition()).toString(), fragile.getItemAtPosition(fragile.getSelectedItemPosition()).toString(), weight.getItemAtPosition(weight.getSelectedItemPosition()).toString(), dal.makeKeyId());
                        dal.addPackage(p);
                        Toast.makeText(AddPackage.this, "Package Added ", Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Toast.makeText(AddPackage.this, "Mail not found ", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else
                    Toast.makeText(AddPackage.this, "Check input ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void updateCurrentAddress() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /*---------- Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
//            Toast.makeText(
//                    getBaseContext(),
//                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
//                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            String latitude = "Latitude: " + loc.getLatitude();


            /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
            String street = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            String s = country + "\n" + state + "\n" + city + "\n" + street + "\n" + postalCode;
            Toast.makeText(
                    getBaseContext(),
                    "Current address is:\n" + s, Toast.LENGTH_SHORT).show();
            currentAddress = new CurrentAddress(country, state, city, street, postalCode);

        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }


    //---------------------------------------------------------------------------------------------------------------------
    public void getGeoByAddress(String address) {
        Address result = getLocationFromAddress(address);
        double lng = result.getLongitude();
        double lat = result.getLatitude();
    }


    public Address getLocationFromAddress(String address) {
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocationName(address, 5);
            return addresses.get(0);

        } catch (Exception ex) {
            Toast.makeText(this, "Error in getting location of address: " + address, Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
