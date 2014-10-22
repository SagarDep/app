package com.stackerz.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by limedv0 on 17/10/2014.
 */
public class Login extends Activity implements View.OnClickListener{

    public Button connect;
    public String username, password, endpoint;
    public EditText userInput, passInput, serverInput;
    public SharedPreferences shPref;
    public Editor toEdit;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_connect);
        getInit();
    }

    public void getInit(){
        connect = (Button)findViewById(R.id.connectButtonLogin);
        userInput = (EditText)findViewById(R.id.userName);
        serverInput = (EditText)findViewById(R.id.server);
        passInput = (EditText)findViewById(R.id.password);
        connect.setOnClickListener(this);

    }

    public void sharedPreferences() {
        shPref = getSharedPreferences("Login Credentials", MODE_PRIVATE);
        toEdit = shPref.edit();
        toEdit.putString("Username", username);
        toEdit.putString("Password", password);
        toEdit.putString("Endpoint", endpoint);
        toEdit.commit();
    }
    @Override
    public void onClick(View v){
        v.getId();
        v.setBackgroundColor(Color.RED);
        username = userInput.getText().toString();
        password = passInput.getText().toString();
        endpoint = serverInput.getText().toString();
        sharedPreferences();
        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction()
        //        .replace(R.id.container, Stackerz.PlaceholderFragment.newInstance(0))
        //        .commit();


    }

}
