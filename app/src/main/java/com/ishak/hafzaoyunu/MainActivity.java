package com.ishak.hafzaoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Properties;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

TextView textView1,textView2,textView3;
String buttonName1,buttonName2;
int sayac=0;///kaç tane butona basıldığını gösteriyor
int score1=0,score2=0;
int siraKimde=1;//1 durumunda person1 devam edecek -1 durumunda person2 devam edecek
Button button[]=new Button[6],yedekButton1=null,yedekButton2=null;
ImageView imageView[]=new ImageView[6];
int image[]={R.drawable.sari,R.drawable.kenny,R.drawable.turuncu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button[0]=findViewById(R.id.button1);
        button[1]=findViewById(R.id.button2);
        button[2]=findViewById(R.id.button3);
        button[3]=findViewById(R.id.button4);
        button[4]=findViewById(R.id.button5);
        button[5]=findViewById(R.id.button6);
        imageView[0]=findViewById(R.id.imageView1);
        imageView[1]=findViewById(R.id.imageView2);
        imageView[2]=findViewById(R.id.imageView3);
        imageView[3]=findViewById(R.id.imageView4);
        imageView[4]=findViewById(R.id.imageView5);
        imageView[5]=findViewById(R.id.imageView6);
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        mixImage();


    }

    public void mixImage() {
        int [] yedek=new int[3];///her bir resimden kaç tane seçildiğini gösteriyor.

        Random random=new Random();
        int i=0;
        for(;;){
            if(i==6)break;///toplam 6 resim olduğu için tüm resimler eklendiğinde fonksiyon sonlanır.
            int a=random.nextInt(3);///3 tane resimden 1 tane seçecek

            yedek[a]++;
            if(yedek[a]>2)//resimlerden maximum iki tanesinin aynı olmasını sağlıyor.
                continue;

            imageView[i].setImageResource(image[a]);///belirtilen insekse rastgele resim atanıyor.
            button[i].setText("but"+a);///butonlara isim vererek daha sonra isEqual fonksiyonunda karşılaştıracam
            i++;
        }

    }

    public void clickCard(View view) throws InterruptedException {
sayac++;
        if(sayac==1){
            yedekButton1=(Button) view;
            yedekButton1.setVisibility(View.INVISIBLE);

            buttonName1=yedekButton1.getText().toString();
        }
        else if(sayac==2){
            yedekButton2=(Button) view;
            yedekButton2.setVisibility(View.INVISIBLE);
            buttonName2=yedekButton2.getText().toString();
        }
       if(sayac==2&&isEqual()==2){///eğer buton adları eşitse
          /* yedekButton1.setVisibility(View.INVISIBLE);
           yedekButton2.setVisibility(View.INVISIBLE);*/
           if(siraKimde==1){

               score1++;
               textView1.setText("person1:"+score1);
           }
           else {

               score2++;
               textView2.setText("person2:"+score2);
           }
       }


       else if(sayac==2&&isEqual()==3){

           siraKimde*=-1;
           if(siraKimde==1)
               textView3.setText("person1");
           else
               textView3.setText("person2");
           ///BURADA 1 SANİYE BEKLEME YAPMAM GEREKİR Kİ OYUNCULAR AÇILAN KARTLARI 1 SANİYE GÖREBİLSİN
           new CountDownTimer(1000, 1000) {
               @Override
               public void onTick(long l) {

               }

               @Override
               public void onFinish() {
                   yedekButton1.setVisibility(View.VISIBLE);///resimler aynı değilse butonlar tekrardan visible yapılarak resimler kapatılır.
                   yedekButton2.setVisibility(View.VISIBLE);
               }
           }.start();

       }

        if(sayac==2){///iki tane butona basıldığını gösteriyor.
            sayac=0;
        }


    }

    public int isEqual(){

        if(buttonName1.equals(buttonName2)){
            return 2;
        }
        else if(!buttonName1.equals(buttonName2)){
            return 3;
        }
        return 0;
    }

}