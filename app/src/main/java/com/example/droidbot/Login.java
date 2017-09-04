package com.example.droidbot;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

    EditText e1, e2;
    Button b1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        e1 = (EditText) findViewById(R.id.eT1);
        e2 = (EditText) findViewById(R.id.eT2);
        b1 = (Button) findViewById(R.id.bLogin);
        t1 = (TextView) findViewById(R.id.tV3);

        b1.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.bLogin:
                String user = e1.getText().toString();
                String pword = e2.getText().toString();
                if (user.contentEquals("droid")
                        && pword.contentEquals("bot")) {
                    Toast.makeText(getApplicationContext(),
                            "Login succeeded", Toast.LENGTH_SHORT)
                            .show();
                    Intent i = new Intent("com.example.droidbot.MainActivity");
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Discovering Devices", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    t1.setText("Login Failed!!!");
                }
                break;
        }
    }

}
