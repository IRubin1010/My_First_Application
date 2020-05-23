package com.example.myfirstapplication.model.datasource;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.myfirstapplication.model.entities.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.myfirstapplication.model.entities.Package;

public class FireBase{

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference packagesRef = database.getReference("Packages");
    public static DatabaseReference personsRef = database.getReference("Persons");
    Intent i;

    public void addPackage(final Package p) {
//        Gson gson = new Gson();
//        String pasJson = gson.toJson(p);
//        packagesRef.setValue(pasJson);
//        String key = packagesRef.push().getKey();
        packagesRef.child(p.getKeyId()).setValue(p);
    }

    public void addPerson(Person person) {
//        Gson gson = new Gson();
//        String pasJson = gson.toJson(person);
//        //  myRef.setValue(pasJson);
        //  String key = myRef.push().getKey();
        personsRef.child(person.getTel()).setValue(person);
    }

    public String makeKeyId() {
        return packagesRef.push().getKey();
    }
}









