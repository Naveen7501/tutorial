package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText username,password,repassword;
Button btnsignup;
TextView signupcondition,haveanaccount,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.edtusername);
        password=findViewById(R.id.edtpassword);
        repassword=findViewById(R.id.edtrepassword);
        btnsignup=findViewById(R.id.btnsignup);
        signupcondition=findViewById(R.id.txtsignupcondt);
        haveanaccount=findViewById(R.id.txthaveaccount);
        login=findViewById(R.id.txtlogin);

        DBHelper db=new DBHelper(this);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass.equals(repass))
                    {
                        Boolean checkuser=db.checkusername(user,pass);
                        if(checkuser==false) {
                            Boolean insert = db.insertdata(user, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Login_Activity.class);
                startActivity(intent);
            }
        });

    }
}