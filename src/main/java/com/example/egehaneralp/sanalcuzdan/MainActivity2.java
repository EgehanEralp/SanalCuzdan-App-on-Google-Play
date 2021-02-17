package com.example.egehaneralp.sanalcuzdan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText bakiyeTL;
    Button buttonIslem;

    String kullaniciAdi;
    float bakiyeTLGonder,gelentl,gelenusd,geleneur;


    SharedPreferences sharedPre;
    SharedPreferences.Editor editor;

    @Override
    public void onBackPressed() {
        // Çalışmasını istediğiniz kodu buraya yazacağız


/*
        if((bakiyeTL.getText().toString()).equals("")) {
            bakiyeTLGonder = (float)0;
        }else{
            bakiyeTLGonder = gelentl;
        }*/

        Intent i = new Intent(MainActivity2.this,MainActivity3.class);
        i.putExtra("kullaniciadi2",kullaniciAdi);
        i.putExtra("bakiyeTL", gelentl);
        i.putExtra("bakiyeUSD",gelenusd);
        i.putExtra("bakiyeEUR",geleneur);
        startActivity(i);
        finish();
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bakiyeTL=findViewById(R.id.bakiye);
        buttonIslem=findViewById(R.id.buttonIslem);

        sharedPre=getPreferences(MODE_PRIVATE);
        editor=sharedPre.edit();

        Bundle extras=getIntent().getExtras();
        kullaniciAdi = extras.getString("kullaniciadi");
        gelentl = extras.getFloat("tlyakala");
        gelenusd=extras.getFloat("usdyakala");
        geleneur=extras.getFloat("euryakala");

        /*bakiyeTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bakiyeTL.setText("");
            }
        });*/


        /*
        bakiyeTL.setActivated(true);
        bakiyeTL.setEnabled(true);
        bakiyeTL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        */




        buttonIslem.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if((bakiyeTL.getText().toString()).equals("")) {
                    bakiyeTLGonder = (float)0;
                }else{
                    bakiyeTLGonder = (float) Integer.parseInt(bakiyeTL.getText().toString());
                }


                Toast.makeText(MainActivity2.this,
                        "Cüzdanınıza "+bakiyeTLGonder+" ₺ tanımlanmıştır.", Toast.LENGTH_SHORT).show();


                Intent i = new Intent(MainActivity2.this,MainActivity3.class);
                i.putExtra("kullaniciadi2",kullaniciAdi);
                i.putExtra("bakiyeTL", bakiyeTLGonder);
                i.putExtra("bakiyeUSD",0.0);
                i.putExtra("bakiyeEUR",0.0);
                startActivity(i);
                finish();
            }
        });


    }
}
