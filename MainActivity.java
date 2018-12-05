package com.example.madhavi.vaxicare;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static SQLiteDatabase cdvsDatabase;

    public void vaccinationClick(View view){

        Intent vaccinationIntent = new Intent(this, VaccinationScreen.class);
        startActivity(vaccinationIntent);

    }

    public void anganwadiClicked(View view){

        Intent vaccinationIntent = new Intent(this, AnganwadiScreen.class);
        startActivity(vaccinationIntent);

    }

    public void helpClicked(View view){
        /*
        Intent vaccinationIntent = new Intent(this, HelpScreen.class);
        startActivity(vaccinationIntent);
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cdvsDatabase = this.openOrCreateDatabase("CDVSDatabase", MODE_PRIVATE, null);

        cdvsDatabase.execSQL("create table if not exists babyData (id int(3) primary key, name varchar, dateOfBirth date)");




    }
}
