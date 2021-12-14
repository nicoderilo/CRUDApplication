package com.example.crudapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView id = (TextView)findViewById(R.id.etId);
        TextView name = (TextView)findViewById(R.id.etName);
        TextView address = (TextView)findViewById(R.id.etAddress);
        Button btninsert = (Button) findViewById(R.id.btnAdd);
        Button btnupdate = (Button) findViewById(R.id.btnUpdate);
        Button btndelete = (Button) findViewById(R.id.btnDelete);
        Button btnget = (Button) findViewById(R.id.btnget);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionClass();
                try {
                    if (connection !=null){
                        String sqlinsert ="INSERT INTO UserInfo_tab VALUES ('"+ id.getText().toString() +"','"+name.getText().toString()+"','"+address.getText().toString()+"')";
                        //String sqlinsert ="INSERT INTO UserInfo_tab VALUES ("+name.getText().toString()+"','"+address.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionClass();
                try {
                    if (connection !=null){
                        String sqlinsert ="UPDATE UserInfo_tab SET Name='"+name.getText().toString()+"', Address= '"+address.getText().toString()+"' where ID = '"+ id.getText().toString() +"'";
                        //String sqlinsert ="INSERT INTO UserInfo_tab VALUES ("+name.getText().toString()+"','"+address.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionClass();
                try {
                    if (connection !=null){
                        String sqlDelete ="DELETE UserInfo_tab where ID = '"+ id.getText().toString() +"'";
                        //String sqlinsert ="INSERT INTO UserInfo_tab VALUES ("+name.getText().toString()+"','"+address.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlDelete);
                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection = connectionClass();
                try {
                    if (connection !=null){
                        String sqlDelete ="SELECT * FROM UserInfo_tab where ID = '"+ id.getText().toString() +"'";
                        //String sqlinsert ="INSERT INTO UserInfo_tab VALUES ("+name.getText().toString()+"','"+address.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlDelete);

                        while(rs.next()){
                            name.setText(rs.getString(2));
                            address.setText(rs.getString(3));
                        }
                    }
                }  catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });
    }

@SuppressLint("NewApi")
    public Connection connectionClass(){
        Connection con = null;
        String ip="10.0.0.106", port="50379", username="sa",password="01Password", databasename="CRUDAndroidDB";
    StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(tp);
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        String connectionUrl="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
        con = DriverManager.getConnection(connectionUrl);
    }
    catch (Exception exception){
        Log.e("Error",exception.getMessage());
    }
    return  con;
}
}