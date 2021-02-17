package com.example.egehaneralp.sanalcuzdan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity4 extends AppCompatActivity {

    EditText editText;
    TextView tlT,dolarT,euroT,poundT;
    Button button,geriButton;
    RadioButton tlrb,dolarrb,eurorb;
    RadioGroup bgrup;
    RadioButton rB;
    int girilensayi,a;
    String stext,kulAd;

    double sinirlayici=0.01;

    ImageView imgUSD,imgEUR,imgTL;

    int kontrol=0;
    float defTL,defUSD,defEUR;

    @Override
    public void onBackPressed() {
        // Çalışmasını istediğiniz kodu buraya yazacağız
        Intent i=new Intent(MainActivity4.this,MainActivity3.class);

        i.putExtra("kullaniciadi2",kulAd);
        i.putExtra("bakiyeTL", defTL);
        i.putExtra("bakiyeUSD",defUSD);
        i.putExtra("bakiyeEUR",defEUR);

        startActivity(i);
        finish();


        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Bundle extras= getIntent().getExtras();
        defTL = extras.getFloat("bakiyeTL");
        defUSD=extras.getFloat("bakiyeUSD");
        defEUR=extras.getFloat("bakiyeEUR");
        kulAd = extras.getString("kullaniciadi2");


        imgEUR=findViewById(R.id.euroicon);
        imgUSD=findViewById(R.id.dolaricon);
        imgTL=findViewById(R.id.tlicon);

        imgTL.setImageResource(R.drawable.tr_icon);
        imgTL.setVisibility(View.VISIBLE);

        imgUSD.setImageResource(R.drawable.usd_icon);
        imgUSD.setVisibility(View.VISIBLE);

        imgEUR.setImageResource(R.drawable.eur_icon);
        imgEUR.setVisibility(View.VISIBLE);

        geriButton=findViewById(R.id.geriButton);
        tlT=findViewById(R.id.tlT);
        dolarT=findViewById(R.id.dolarT);
        euroT=findViewById(R.id.euroT);
        //poundT=findViewById(R.id.poundT);

        bgrup=findViewById(R.id.bgrup);
        tlrb=findViewById(R.id.tlrb);
        dolarrb=findViewById(R.id.dolarrb);
        eurorb=findViewById(R.id.eurorb);

        tlrb.setChecked(true);


        editText=findViewById(R.id.editText);

        geriButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity4.this,MainActivity3.class);

                i.putExtra("kullaniciadi2",kulAd);
                i.putExtra("bakiyeTL", defTL);
                i.putExtra("bakiyeUSD",defUSD);
                i.putExtra("bakiyeEUR",defEUR);

                startActivity(i);
                finish();
            }
        });



        bgrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                new ArkaPlan().execute("https://www.doviz.gen.tr/doviz_json.asp?version=1.0.4");
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!((editText.getText().toString()).equals(""))) {
                    new ArkaPlan().execute("https://www.doviz.gen.tr/doviz_json.asp?version=1.0.4");
                }
                else{
                    editText.setText("0");
                    new ArkaPlan().execute("https://www.doviz.gen.tr/doviz_json.asp?version=1.0.4");
                }
            }
        });



        /*button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ArkaPlan().execute("https://api.exchangeratesapi.io/latest?base=TRY");

            }


            editText.getText().toString().equals(null)


            ACTION_USER_BACKGROUND


        });*/

    }

    class ArkaPlan extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection;
            BufferedReader buf;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is = connection.getInputStream();
                buf = new BufferedReader(new InputStreamReader(is));

                String satir, dosya ="";

                while ((satir = buf.readLine()) != null) {
                    //Log.d("satir", satir);
                    dosya += satir;  // WHİLE BİTTİGİNDE SUNUCUDAKİ TÜM SATİRLARI ELDE ETMİŞ OLUCAĞIM

                }
                return dosya;

            } catch (Exception e) {
                e.printStackTrace();

            }

            return "sorun";
        }

        @Override
        protected void onPostExecute(String s) {


                stext = editText.getText().toString();
                girilensayi = Integer.parseInt(stext);
                tlT.setText(stext);
                int selectedId = bgrup.getCheckedRadioButtonId();
                rB = findViewById(selectedId);

                double dolarkuru,eurokuru;

                try {
                    JSONObject json2 = new JSONObject(s);

                    /*String rates = json.getString("rates");

                    JSONObject json2 = new JSONObject(rates);*/



                    if (rB == tlrb) {
                        tlT.setText("  " + girilensayi +" ₺");

                        double a = json2.getDouble("dolar"); //5.71 çekti
                        double ca=(1.0)/a;
                        double dolar=ca*girilensayi;
                        //dolar -= dolar%sinirlayici;
                        String s2= String.format("%.2f $", dolar);
                        dolarT.setText("  " + s2);

                        double b = json2.getDouble("euro");
                        b=(1.0)/b;
                        double euro=b*girilensayi;
                        //euro -= euro%sinirlayici;
                        String s3 = String.format("%.2f €", euro);
                        euroT.setText("  " + s3);

                        //double c=json2.getDouble("GBP");
                        //poundT.setText("GBP = "+(c*girilensayi));
                    } else if (rB == dolarrb) {     //KURDAKİ SAYILARI DOLAR KURUNA BÖL SONRA *girilensayi

                        double kur = json2.getDouble("dolar");
                        double tl=(kur * girilensayi);
                        //tl -= tl%sinirlayici;
                        String s1=String.format("%.2f ₺", tl);
                        tlT.setText("  " + s1);

                        dolarT.setText("  " + girilensayi +" $");

                        double b = json2.getDouble("euro");
                        double euro=((kur / b) * girilensayi);
                        //euro -= euro%sinirlayici;
                        String s2=String.format("%.2f €", euro);
                        euroT.setText("  " + s2);

                        //double c=json2.getDouble("GBP");
                        //poundT.setText("GBP = "+((c/kur)*girilensayi));
                    } else if (rB == eurorb) {

                        double kur = json2.getDouble("euro");
                        double tl=(kur * girilensayi);
                        //tl -= tl%sinirlayici;
                        String s1=String.format("%.2f ₺", tl);
                        tlT.setText("  " + s1);

                        double a = json2.getDouble("dolar");
                        double dolar=((kur / a) * girilensayi);
                        //dolar -= dolar%sinirlayici;
                        String s2=String.format("%.2f $", dolar);
                        dolarT.setText("  " + s2);

                        euroT.setText("  " + girilensayi +" €");

                        //double c=json2.getDouble("GBP");
                        //poundT.setText("GBP   ="+((c/kur)*girilensayi));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }




        }
    }

}
