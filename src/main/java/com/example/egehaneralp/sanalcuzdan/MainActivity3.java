package com.example.egehaneralp.sanalcuzdan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
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
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity3 extends AppCompatActivity {
    TextView tlText,dolarText,euroText,textInfoD,textInfoE;
    EditText editText;
    RadioGroup bgrup;
    RadioButton dolarrb,eurorb,RB;
    Button satinAlButton,bozdurButton, basaDonButton,bakiyeGuncelleButton ,kurGuncelleButton, kaydetButton, cikisButton;

    ImageView imgEUR,imgUSD,imgTR, imgUSD2,imgEUR2;
    int control2;

    String edittextS,kulAd;
    int girilentutar;

    int control;
    String s1,s2;
    static float bakiyeTL,bakiyeDOLAR,bakiyeEURO, defTL,defUSD,defEUR;

    SharedPreferences sharedPre;
    SharedPreferences.Editor editor;

    double sinirlayici=0.01;



    @Override
    public void onBackPressed() {
        // Çalışmasını istediğiniz kodu buraya yazacağız
        editor.putInt("k",0);
        editor.commit();
        //finish();
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sharedPre=getPreferences(MODE_PRIVATE);
        editor=sharedPre.edit();

      //  moveTaskToBack(false);

        Bundle extras= getIntent().getExtras();
        defTL = extras.getFloat("bakiyeTL");
        defUSD =extras.getFloat("bakiyeUSD");
        defEUR =extras.getFloat("bakiyeEUR");
        kulAd = extras.getString("kullaniciadi2");

        imgEUR=findViewById(R.id.euroicon);
        imgUSD=findViewById(R.id.dolaricon);
        imgTR=findViewById(R.id.tlicon);
        imgUSD2=findViewById(R.id.dolariconX);
        imgEUR2=findViewById(R.id.euroiconX);


        imgUSD2.setImageResource(R.drawable.usd_icon);
        imgUSD2.setVisibility(View.VISIBLE);

        imgEUR2.setImageResource(R.drawable.eur_icon);
        imgEUR2.setVisibility(View.VISIBLE);

        imgUSD.setImageResource(R.drawable.usd_icon);
        imgUSD.setVisibility(View.VISIBLE);

        imgTR.setImageResource(R.drawable.tr_icon);
        imgTR.setVisibility(View.VISIBLE);

        imgEUR.setImageResource(R.drawable.eur_icon);
        imgEUR.setVisibility(View.VISIBLE);

        cikisButton=findViewById(R.id.cikisButton);
        kaydetButton = findViewById(R.id.buttonKaydet);
        basaDonButton = findViewById(R.id.basaDon);              // Yeniler
        bakiyeGuncelleButton =findViewById(R.id.bakiyeGuncelle); // Yeniler
        kurGuncelleButton =findViewById(R.id.kurGuncelle);       // YENİLER

        control2=sharedPre.getInt("k",0);


        bakiyeTL=sharedPre.getFloat("a",defTL);
        bakiyeDOLAR=sharedPre.getFloat("b",defUSD);
        bakiyeEURO=sharedPre.getFloat("c",defEUR);
        if(control2==1){
            control2=0;
            bakiyeTL=defTL;
            bakiyeDOLAR=defUSD;
            bakiyeEURO=defEUR;
        }



        tlText=findViewById(R.id.tlText);
        dolarText=findViewById(R.id.dolarText);
        euroText=findViewById(R.id.euroText);

        tlText.setText(" "+bakiyeTL+" ₺");
        dolarText.setText(" "+bakiyeDOLAR+ " $");
        euroText.setText(" "+bakiyeEURO+ " €");

        editor.putFloat("a",bakiyeTL);
        editor.putFloat("b",bakiyeDOLAR);
        editor.putFloat("c",bakiyeEURO);    //SON EKLEDİĞİM 4 SATIR*********************************
        editor.commit();

        textInfoD=findViewById(R.id.textInfoD);
        textInfoE=findViewById(R.id.textInfoE);


        //en son ekledim altı -> sayfa açıldığında güncellemesi için yaptım
        control=2;
        new ArkaPlan().execute("https://www.doviz.gen.tr/doviz_json.asp?version=1.0.4");
        //en son ekledim üstü

        s1=sharedPre.getString("s1"," ALIŞ - SATIŞ");
        s2=sharedPre.getString("s2"," ALIŞ - SATIŞ");

        textInfoD.setText(s1);
        textInfoE.setText(s2);

        editText=findViewById(R.id.editText);

        bgrup=findViewById(R.id.bgrup);

        dolarrb=findViewById(R.id.dolarrb);
        eurorb=findViewById(R.id.eurorb);

        dolarrb.setChecked(true);

        satinAlButton=findViewById(R.id.satinAlButton);
        bozdurButton=findViewById(R.id.bozdurButton);




        /*editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        /*editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });*/

        /*cikisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //bir sonraki act e yolla
                moveTaskToBack(true); // 1.act başlat
                Intent intent = new Intent(Intent.ACTION_USER_BACKGROUND);
                intent.setData(Uri.parse("package:" + getApplicationContext().getPackageName()));
                startActivity(intent);

            }
        });*/

        satinAlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((editText.getText().toString()).equals("")){

                }
                else {
                    control = 0;
                    new ArkaPlan().execute("https://www.doviz.gen.tr/doviz_json.asp?version=1.0.4");
                }
            }
        });
        bozdurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((editText.getText().toString()).equals("")){

                }
                else {
                    control = 1;
                    new ArkaPlan().execute("https://www.doviz.gen.tr/doviz_json.asp?version=1.0.4");
                }
            }
        });

        basaDonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //parayı kıyaslama simülasyonu


                /*editor.putInt("k",0);
                editor.commit();*/
                Intent i =new Intent(MainActivity3.this,MainActivity4.class);
                i.putExtra("kullaniciadi2",kulAd);
                i.putExtra("bakiyeTL", bakiyeTL);
                i.putExtra("bakiyeUSD", bakiyeDOLAR);
                i.putExtra("bakiyeEUR",bakiyeEURO);
                startActivity(i);
                finish();
            }
        });

        bakiyeGuncelleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity3.this,MainActivity2.class);
                i.putExtra("euryakala",bakiyeEURO);
                i.putExtra("usdyakala",bakiyeDOLAR);
                i.putExtra("tlyakala",bakiyeTL);
                i.putExtra("kullaniciadi",kulAd);
                editor.putInt("k",1);//1di

                editor.putFloat("b",0);
                editor.putFloat("c",0);
                editor.commit();
                startActivity(i);
                finish();

            }
        });

        kurGuncelleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control=2;
                new ArkaPlan().execute("https://www.doviz.gen.tr/doviz_json.asp?version=1.0.4");
            }
        });

        kaydetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3.this, "Kaydetme işlemi başarılı", Toast.LENGTH_SHORT).show();
                editor.putInt("k",0);
                editor.commit();
            }
        });

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

            int selectedRB= bgrup.getCheckedRadioButtonId(); //********************
            RB =findViewById(selectedRB);                   //SEÇİLMİŞ RB Yİ BELİRLEDİK

            if(control==2){
                if((editText.getText().toString().equals(""))){
                    editText.setText("0");
                }
            }

            edittextS=editText.getText().toString();         //EditText teki sayı ---editInt---
            girilentutar = Integer.parseInt(edittextS);

            /*
            if(edittextS.equals(null)){
                girilentutar=0;
            }else {
                girilentutar = Integer.parseInt(edittextS);
            }*/


            try{
                JSONObject json =new JSONObject(s);

                if(control==2){ // kur güncelle butonu
                    double a,b,c,d;
                    a=json.getDouble("dolar");
                    b=json.getDouble("dolar2");
                    c=json.getDouble("euro");
                    d=json.getDouble("euro2");

                    String str1,str2;
                    str1= " ALIŞ="+a+", SATIŞ="+b;
                    str2= " ALIŞ="+c+", SATIŞ="+d;

                    textInfoD.setText(str1);
                    textInfoE.setText(str2);

                    editor.putString("s1",str1);
                    editor.putString("s2",str2);
                    editor.commit();
                }


                if(control==0){ //SATIN AL BUTONUNA BASILDIYSA         not==== BOZDUR -> DOLAR VE EURO  (CONTROL==1)
                               //                                        not====SATIL AL -> TL (CONTROL=0)

                    if(RB==dolarrb){

                        double kur=json.getDouble("dolar2");
                        double a=kur*girilentutar;

                        if((bakiyeTL-a)<0){
                            Toast.makeText(MainActivity3.this, "İşlem İçin Yetersiz Türk Lirası", Toast.LENGTH_SHORT).show();
                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("b", bakiyeDOLAR);
                            editor.commit();
                        }
                        else {
                            bakiyeTL -= a;

                            double f=bakiyeTL%sinirlayici;
                            bakiyeTL -=f;

                            tlText.setText(" " + bakiyeTL + " ₺");
                            bakiyeDOLAR += girilentutar;
                            dolarText.setText(" " + bakiyeDOLAR + " $");

                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("b", bakiyeDOLAR);
                            editor.commit();
                        }
                    }
                    if(RB==eurorb){

                        double kur=json.getDouble("euro2");
                        double a =kur*girilentutar;

                        if((bakiyeTL-a)<0){
                            Toast.makeText(MainActivity3.this, "İşlem İçin Yetersiz Türk Lirası", Toast.LENGTH_SHORT).show();
                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("c", bakiyeEURO);
                            editor.commit();
                        }
                        else {
                            bakiyeTL -= a;

                            double f=bakiyeTL%sinirlayici;
                            bakiyeTL -=f;

                            tlText.setText(" " + bakiyeTL + " ₺");
                            bakiyeEURO += girilentutar;
                            euroText.setText(" " + bakiyeEURO + " €");

                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("c", bakiyeEURO);
                            editor.commit();
                        }
                    }

                }
                if(control==1){ //BOZDUR BUTONUNA TIKLANDIYSA
                    if(RB==dolarrb){ //doları kontrol et

                        if(girilentutar>bakiyeDOLAR){
                            Toast.makeText(MainActivity3.this, "Dolar Bakiyeniz Yetersiz", Toast.LENGTH_SHORT).show();
                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("b", bakiyeDOLAR);
                            editor.commit();
                        }
                        else {
                            double kur = json.getDouble("dolar");
                            double a = kur * girilentutar;
                            bakiyeTL += a;

                            double f=bakiyeTL%sinirlayici;
                            bakiyeTL -=f;

                            tlText.setText(" " + bakiyeTL + " ₺");
                            bakiyeDOLAR -= girilentutar;
                            dolarText.setText(" " + bakiyeDOLAR + " $");

                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("b", bakiyeDOLAR);
                            editor.commit();
                        }
                    }
                    if(RB==eurorb){//euroyu kontrol et

                        if(girilentutar>bakiyeEURO){
                            Toast.makeText(MainActivity3.this, "Euro Bakiyeniz Yetersiz", Toast.LENGTH_SHORT).show();
                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("c", bakiyeEURO);
                            editor.commit();
                        }
                        else {

                            double kur = json.getDouble("euro");
                            double a = kur * girilentutar;
                            bakiyeTL += a;

                            double f=bakiyeTL%sinirlayici;
                            bakiyeTL -=f;

                            tlText.setText(" " + bakiyeTL + " ₺");
                            bakiyeEURO -= girilentutar;
                            euroText.setText(" " + bakiyeEURO + " €");

                            editor.putFloat("a", bakiyeTL);
                            editor.putFloat("c", bakiyeEURO);
                            editor.commit();
                        }
                    }
                }
                double a,b,c,d;
                a=json.getDouble("dolar");
                b=json.getDouble("dolar2");
                c=json.getDouble("euro");
                d=json.getDouble("euro2");

                String str1,str2;
                str1= " ALIŞ="+a+", SATIŞ="+b;
                str2= " ALIŞ="+c+", SATIŞ="+d;

                textInfoD.setText(str1);
                textInfoE.setText(str2);

                editor.putString("s1",str1);
                editor.putString("s2",str2);
                editor.commit();
                editor.putFloat("a",bakiyeTL);
                editor.putFloat("b",bakiyeDOLAR);
                editor.putFloat("c",bakiyeEURO);
                editor.commit();

            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}
