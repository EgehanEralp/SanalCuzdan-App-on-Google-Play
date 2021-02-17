package com.example.egehaneralp.sanalcuzdan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {

    EditText ad;
    String kulAd;
    Button button;
    TextView tv1,tv2;

    SharedPreferences sharedPre;
    SharedPreferences.Editor editor;

    static String control; // ilk girişte 0 olacak




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);


        sharedPre=getPreferences(MODE_PRIVATE);
        editor=sharedPre.edit();

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        ad = findViewById(R.id.ad);
        button=findViewById(R.id.button);


        /*Bundle extras=getIntent().getExtras();
        control= extras.getString("control");
        editor.putString("cont",control);
        editor.commit();*/

        control=sharedPre.getString("cont","0");

        if(!control.equals("0")){ //ilk defa girmiyorsa 3.act e yolla

            Intent i= new Intent(MainActivity1.this,MainActivity3.class);
            i.putExtra("kullaniciadi",kulAd);
            startActivity(i);
            finish();

        }

        //ad.setText(String.valueOf(control));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kulAd= ad.getText().toString(); //ismi aldım

                Toast.makeText(getApplicationContext(), "Hoşgeldin "+kulAd , Toast.LENGTH_LONG).show();

                editor.putString("cont","1");
                editor.commit();

                Intent i= new Intent(MainActivity1.this,MainActivity2.class);
                i.putExtra("kullaniciadi",kulAd);
                i.putExtra("tlyakala",0.0);
                i.putExtra("usdyakala",0.0);
                i.putExtra("euryakala",0.0);
                startActivity(i);
                finish();




            }
        });








    }
}
