package com.example.droidbot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {

    ListView lv;
    Button b, b1, b2, b3, b4, b5;
    TextView t1;
    static final int check = 1111;
    private static BluetoothSocket mbtSocket;

    private static InputStream mbtInputStream;

    private static OutputStream mbtOutputStream;
    String result;
    int movecode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lvVoiceReturn);
        b = (Button) findViewById(R.id.bVoice);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        t1 = (TextView) findViewById(R.id.textView1);

        Intent BTIntent = new Intent(this, BTWrapperActivity.class);
        this.startActivityForResult(BTIntent,BTWrapperActivity.REQUEST_CONNECT_BT);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                t1.setText("forward");
                result = "forward";
                sendcode();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                t1.setText("left");
                result = "left";
                sendcode();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                t1.setText("right");
                result = "right";
                sendcode();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                t1.setText("back");
                result = "back";
                sendcode();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                t1.setText("stop");
                result = "stop";
                sendcode();
            }
        });
        b.setOnClickListener(this);
    }



    private int sendBTData(int dataBytes) {

        try {


            mbtOutputStream.write(dataBytes);


        } catch (Exception e) {

            return -1;

        }

        return 0;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case BTWrapperActivity.REQUEST_CONNECT_BT:

                mbtSocket = BTWrapperActivity.getSocket();
                try {
                    mbtOutputStream=mbtSocket.getOutputStream();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        if (requestCode == check && resultCode == RESULT_OK) {



            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            lv.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, results));
            result = results.get(0).toString();
            sendcode();
        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        i.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,3);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak!!");
        startActivityForResult(i, check);
    }

    public void sendcode() {
        String direction;
        direction = result;
        if (direction.equals("forward") || direction.equals("followed")
                || direction.equals("firewood") || direction.equals("harwood")|| direction.equals("ford")) {
            movecode = 48;
            t1.setText("Forward");
            sendBTData(movecode);
        } else if (direction.equals("left") || direction.equals("yes")
                || direction.equals("laugh")) {
            movecode = 50;
            t1.setText("Left");
            sendBTData(movecode);
        } else if (direction.equals("right") || direction.equals("light")
                || direction.equals("night") || direction.equals("knight")
                || direction.equals("fight") || direction.equals("nite")) {
            movecode = 51;
            t1.setText("Right");
            sendBTData(movecode);

        } else if (direction.equals("back") || direction.equals("bike")
                || direction.equals("bag")) {
            movecode = 49;
            t1.setText("Back");
            sendBTData(movecode);
        }
        else if (direction.equals("stop") || direction.equals("start")
                || direction.equals("stalled")|| direction.equals("starbucks")) {
            movecode = 54;
            t1.setText("Stop");
            sendBTData(movecode);
        }
        else if (direction.equals("lights on")||direction.equals("lights off")) {
            movecode = 55;
            t1.setText("Lights");
            sendBTData(movecode);
        }
        else if (direction.equals("fan") || direction.equals("san")
                || direction.equals("font")||direction.equals("sign")||direction.equals("pan")) {
            movecode = 56;
            t1.setText("Fan");
            sendBTData(movecode);
        }
        else if (direction.equals("slow")) {
            movecode = 52;
            t1.setText("Speed: Slow");
            sendBTData(movecode);
        }
        else if (direction.equals("fast")||direction.equals("past")) {
            movecode = 53;
            t1.setText("Speed: Fast");
            sendBTData(movecode);
        }
    }

}

