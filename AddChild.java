package com.example.madhavi.vaxicare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddChild extends AppCompatActivity {

    EditText date;

    public void submitDetails(View v){

        try {
            SQLiteDatabase cdvsDatabase = MainActivity.cdvsDatabase;
            Cursor c = cdvsDatabase.rawQuery("SELECT max(id) from babyData;", null);

            int id = 0;

            if (c == null) {
                Log.i("Testing", "c is null");
            } else {
                c.moveToFirst();
                id = c.getInt(0);
                Log.i("Testing", "c is not null" + id + " " + c.isNull(0));
            }

            id++;


            String sql = "insert into babyData (id, name, dateOfBirth) values(?,?,?)";
            SQLiteStatement statement = cdvsDatabase.compileStatement(sql);
            statement.bindString(1, Integer.toString(id));
            if(((TextView) findViewById(R.id.babyName)).getText().toString().matches(""))
                Toast.makeText(this.getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
            else {
                statement.bindString(2, ((TextView) findViewById(R.id.babyName)).getText().toString());
                if(((TextView) findViewById(R.id.dateEditText)).getText().toString().matches("")){
                    Toast.makeText(this.getApplicationContext(),"Enter Date",Toast.LENGTH_SHORT).show();
                }
                else {
                    String date = ((TextView) findViewById(R.id.dateEditText)).getText().toString();

                    statement.bindString(3, date);

                    statement.execute();

                    c.close();

                    Intent toNext = new Intent(this.getApplicationContext(), ViewChart.class);
                    toNext.putExtra("open ID", id);
                    startActivity(toNext);
                }
            }
        }catch (Exception e){
            Toast.makeText(this.getApplicationContext(),"Enter data",Toast.LENGTH_SHORT);
        }
    }

    public void showDatePicker(final View v){

        Calendar c = Calendar.getInstance();

        Log.i("Reached","Yes");


        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String day=Integer.toString(i2);
                String month=Integer.toString(i1);
                if(i1<10){
                    month="0"+month;
                }
                if(i2<10){
                    day="0"+day;
                }

                date.setText(i+"-"+month+"-"+day);
                v.clearFocus();
                findViewById(R.id.babyName).clearFocus();
                findViewById(R.id.Submit).requestFocus();

            }
        };

        new DatePickerDialog(this,onDateSetListener,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();




    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        final TextView dateEditText = (TextView)findViewById(R.id.dateEditText);
        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b==true){
                    showDatePicker(dateEditText);
                }
            }
        });
        date = (EditText) findViewById(R.id.dateEditText);
    }
}
