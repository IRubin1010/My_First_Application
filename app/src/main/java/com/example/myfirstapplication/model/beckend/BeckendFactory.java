package com.example.myfirstapplication.model.beckend;
import com.google.firebase.database.FirebaseDatabase;
import javax.sql.DataSource;

public class BeckendFactory {
    public static DataSource getDatasource(){
        return (DataSource) FirebaseDatabase.getInstance();
    }
}
