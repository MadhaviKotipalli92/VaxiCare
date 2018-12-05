package com.example.madhavi.vaxicare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewChart extends AppCompatActivity {

    int passedID;
    SQLiteDatabase database;

    void update(String date){
        String nextDate;
        String sql;
        Cursor cursor;

        sql = "SELECT datetime('"+date+"','+1 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            Log.i("Testing","nextDate is "+cursor.getString(0));
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd1)).setText(f[0]);
            ((TextView) findViewById(R.id.ad1)).setText("-");
            ((TextView) findViewById(R.id.s1)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+2 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd2)).setText(f[0]);
            ((TextView) findViewById(R.id.ad2)).setText("-");
            ((TextView) findViewById(R.id.s2)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+3 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd3)).setText(f[0]);
            ((TextView) findViewById(R.id.ad3)).setText("-");
            ((TextView) findViewById(R.id.s3)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+4 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd4)).setText(f[0]);
            ((TextView) findViewById(R.id.ad4)).setText("-");
            ((TextView) findViewById(R.id.s4)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+5 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd5)).setText(f[0]);
            ((TextView) findViewById(R.id.ad5)).setText("-");
            ((TextView) findViewById(R.id.s5)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+6 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd6)).setText(f[0]);
            ((TextView) findViewById(R.id.ad6)).setText("-");
            ((TextView) findViewById(R.id.s6)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+7 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd7)).setText(f[0]);
            ((TextView) findViewById(R.id.ad7)).setText("-");
            ((TextView) findViewById(R.id.s7)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+8 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd8)).setText(f[0]);
            ((TextView) findViewById(R.id.ad8)).setText("-");
            ((TextView) findViewById(R.id.s8)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+9 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd9)).setText(f[0]);
            ((TextView) findViewById(R.id.ad9)).setText("-");
            ((TextView) findViewById(R.id.s9)).setText("-");
            cursor.close();
        }

        sql = "SELECT datetime('"+date+"','+10 months')";
        cursor = database.rawQuery(sql,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            nextDate = cursor.getString(0);
            String f[] = nextDate.split(" ");
            ((TextView) findViewById(R.id.sd10)).setText(f[0]);
            ((TextView) findViewById(R.id.ad10)).setText("-");
            ((TextView) findViewById(R.id.s10)).setText("-");
            cursor.close();
        }

    }


    public void makeTable(){
        String sql = "SELECT dateOfBirth FROM babyData WHERE id="+passedID;
        Cursor cursor = database.rawQuery(sql,null);
        String date="";
        if(cursor!=null){
            cursor.moveToFirst();
            date+=cursor.getString(0);
        }
        Log.i("Testing","makeTable date is "+date);
        cursor.close();
        update(date);


    }


    public void generateChart(View v){

        EditText childID = (EditText) findViewById(R.id.editText);

        TextView heading = (TextView) findViewById(R.id.chidID);
        int id;
        try {
            id = Integer.parseInt(childID.getText().toString());
            Cursor cursor = database.rawQuery("SELECT id FROM babyData WHERE id="+id,null);
            //cursor.moveToFirst();
            if(cursor.getCount()<=0){
                Log.i("Testing","no id");
                Toast.makeText(this.getApplicationContext(),"Enter a valid id",Toast.LENGTH_SHORT).show();
                cursor.close();
            }
            else {
                Log.i("Testing","id exists");
                Cursor c = database.rawQuery("SELECT name FROM babyData WHERE id=" + childID.getText().toString(), null);
                c.moveToFirst();
                heading.setText("Child ID: " + childID.getText().toString() + " Name:" + c.getString(0));
                c.close();
                passedID = Integer.parseInt(childID.getText().toString());
                TableLayout t = (TableLayout) findViewById(R.id.tableLayout);
                makeTable();
                t.setVisibility(View.VISIBLE);
                cursor.close();
            }
        }catch (Exception e){
            Toast.makeText(this.getApplicationContext(),"Enter an ID",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passedID = getIntent().getIntExtra("open ID",0);
        setContentView(R.layout.activity_view_chart);
        database = MainActivity.cdvsDatabase;
        if(passedID!=0){
            TextView heading = (TextView) findViewById(R.id.chidID);
            Cursor c = database.rawQuery("SELECT name FROM babyData WHERE id="+passedID,null);
            c.moveToFirst();
            heading.setText("Child ID: "+passedID+" Name:"+c.getString(0));
            c.close();
            TableLayout t = (TableLayout) findViewById(R.id.tableLayout);
            makeTable();
            t.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this.getApplicationContext(), VaccinationScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
