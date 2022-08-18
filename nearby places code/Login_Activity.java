package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.DataFormatException;

public class Login_Activity extends AppCompatActivity {
Button btnlogsignup;
EditText loginusername,loginuserpassword;
DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginusername=findViewById(R.id.edtloginusername);
        loginuserpassword=findViewById(R.id.edtloginpassword);
        btnlogsignup=findViewById(R.id.btnloginsignup);
db=new DBHelper(this);
        btnlogsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=loginusername.getText().toString();
                String pass=loginuserpassword.getText().toString();


                if(user.equals("") || pass.equals("") )
                {
                    Toast.makeText(Login_Activity.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass=db.checkusernamepassword(user,pass);
                    if(checkuserpass==true)
                    {
                        Toast.makeText(Login_Activity.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Login_Activity.this,MapsActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Login_Activity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}