package com.example.pam2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener  {

    ImageView _imageView;
    ImageView _imageView1;
    Context context = this;
    String name = "plan00";
    String fname = "ref00";
    TextView textView;
    boolean showElements = false;
    boolean showText = false;
    Button _tvButton;
    Button _WazaButton;
    Button _KomodButton;
    Button _info;
    TextView tv;
    float x;
    float y;
    float x_down = 0;
    float y_down = 0;
    float x_up = 0;
    float y_up = 0;
    String sDown;
    String sMove;
    String sUp;
    String MyCommnet = "";
    int actualFrame = 0;

    int oldDistance = 0;
    int velocity = 10;
    String strPlate = "Waza Kniazia Dzmitro\n 100% gwarancja \n cena : bezcenna";
    String strTV = "SHARP 22148\n Ekran: 40 duimov\n 1920*1080 ";
    String strKom = "Komod szlachecki\n drewno naturalne\n 100*50*200 (cm) ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        _tvButton = (Button) findViewById(R.id.buttonTV);
        ChangeButtonTvCoordinatAndSize(_tvButton, 300 , 200 , 300 , 720);
        _WazaButton = (Button) findViewById(R.id.buttonWaza);
        ChangeButtonTvCoordinatAndSize(_WazaButton, 150 , 150 , 830 , 590);
        _KomodButton = (Button) findViewById(R.id.buttonKom);
        ChangeButtonTvCoordinatAndSize(_KomodButton, 300 , 200 , 800 , 690);


        _info = (Button) findViewById(R.id.buttonInfo);
        _info.setVisibility(View.GONE);
        _imageView = (ImageView) findViewById(R.id.imageView);
        _imageView1 = (ImageView) findViewById(R.id.imageView1);
        _imageView.setLongClickable(true);
        _imageView1.setLongClickable(true);
        _imageView.setImageDrawable(getResources().getDrawable(getImageId(context, name + Integer.toString(actualFrame))));
        _imageView1.setImageDrawable(getResources().getDrawable(getImageId(context, fname + Integer.toString(actualFrame))));
        tv = (TextView) findViewById(R.id.textView);
        _imageView.setOnTouchListener(this);
        _imageView1.setOnTouchListener(this);
        tv.setText(MyCommnet);

        addListenerOnButtonTv();
        addListenerOnButtonPlate();
        addListenerOnButtonInfo();
        addListenerOnButtonKomod();
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                x_down = x;
                y_down = y;
                sDown = "Down: " + x + "," + y;
                sMove = "";
                sUp = "";
              /*  if(showElements == true)
                {
                    setUnVisibleBut();
                    showElements = false;
                }*/
                _imageView1.setImageDrawable(getResources().getDrawable(getImageId(context, fname + Integer.toString(actualFrame))));
                _imageView.setImageDrawable(getResources().getDrawable(getImageId(context, name + Integer.toString(actualFrame))));

                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;
                MyCommnet = "Actual frame: " + actualFrame;
                setUnVisibleBut();

                animation(x, x_down);
                _info.setVisibility(View.GONE);
                showText = false;
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                x_up = x;
                y_up = y;
                sMove = "";
                sUp = "Up: " + x + "," + y;
                controlButtonTV();
                controlButtonWaza();
                controlButtonKom();
                setVisibleBut();
                showElements = true;
                _imageView1.setImageDrawable(getResources().getDrawable(getImageId(context, fname + Integer.toString(actualFrame ))));
                _imageView.setImageDrawable(getResources().getDrawable(getImageId(context, name + Integer.toString(actualFrame))));

               // whatKindOfSwipe();
                oldDistance = 0;
                break;
        }
      //  tv.setText(sDown + "\n" + sMove + "\n" + sUp + " " + MyCommnet);
        tv.setText(MyCommnet);
        return true;
    }

    public void animation(float x, float x_down){
        int actualDistance = (int)(x - x_down);
        if(actualDistance > oldDistance + velocity && actualFrame <= 95)
        {
            actualFrame = actualFrame + 1;
            _imageView.setImageDrawable(getResources().getDrawable(getImageId(context, name + Integer.toString(actualFrame))));
            _imageView1.setImageDrawable(getResources().getDrawable(getImageId(context, fname + Integer.toString(actualFrame))));
            oldDistance = oldDistance + velocity;
        }
        else if(actualDistance < oldDistance - 10 && actualFrame >= 1)
        {
            actualFrame = actualFrame - 1;
            _imageView.setImageDrawable(getResources().getDrawable(getImageId(context, name + Integer.toString(actualFrame))));
            _imageView1.setImageDrawable(getResources().getDrawable(getImageId(context, fname + Integer.toString(actualFrame))));
            oldDistance = oldDistance - velocity;
        }
    }
    public void setVisibleBut(){
        _tvButton.setVisibility(View.VISIBLE);
        _WazaButton.setVisibility(View.VISIBLE);
        _KomodButton.setVisibility(View.VISIBLE);
    }
    public void setUnVisibleBut(){
        _tvButton.setVisibility(View.GONE);
        _WazaButton.setVisibility(View.GONE);
        _KomodButton.setVisibility(View.GONE);
    }
    public void addListenerOnButtonInfo(){
        _info.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _info.setVisibility(View.GONE);
                setVisibleBut();
            }
        });
    }
    public void addListenerOnButtonTv(){
        _tvButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _info.setVisibility(View.VISIBLE);
                _info.setText(strTV);
                setUnVisibleBut();
            }
        });
    }


    public void addListenerOnButtonPlate(){
        _WazaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _info.setVisibility(View.VISIBLE);
                _info.setText(strPlate);
               setUnVisibleBut();
            }
        });
    }
    public void addListenerOnButtonKomod(){
        _KomodButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                _info.setVisibility(View.VISIBLE);
                _info.setText(strKom);
                setUnVisibleBut();
            }
        });
    }


    public void whatKindOfSwipe()
    {
        float xDistance = Math.abs(x_up - x_down);
        float yDistance = Math.abs(y_up - y_down);
        /*if(xDistance > 200 && yDistance <= 200) {
            if (x_up < x_down) // swipe left
                MyCommnet = "SWIPE LEFT";
            else
                MyCommnet = "SWIPE RIGHT";
        }
        else
            MyCommnet = "No swipeLeft: x:" + xDistance + " y: " +   yDistance;*/
    }

    public static int getImageId(Context context, String imageName) {
        int result = context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
        return result;
    }
    public void controlButtonWaza() {
        int temp = actualFrame;

        if (temp < 8) {
            ChangeButtonTvCoordinatAndSize(_WazaButton, 150 , 150 , 840 , 590);
            return;
        }
        if (temp >= 8 && temp < 13) {
            ChangeButtonTvCoordinatAndSize(_WazaButton, 150 , 150 , 865 , 575);
            return;
        }
        if (temp >= 13 && temp < 20) {
            ChangeButtonTvCoordinatAndSize(_WazaButton, 150 , 150 , 930 , 540);
            return;
        }

        if (temp >= 20 && temp < 85) {
            ChangeButtonTvCoordinatAndSize(_WazaButton, 0 , 0 , 0 , 0);
            return;
        }

        if (temp >= 85 && temp < 90) {
            ChangeButtonTvCoordinatAndSize(_WazaButton, 150 , 150 , 930 , 560);
            return;
        }
        if (temp >= 90 && temp <= 96) {
            ChangeButtonTvCoordinatAndSize(_WazaButton, 150 , 150 , 910 , 580);
            return;
        }
    }
    public void controlButtonKom() {
        int temp = actualFrame;

        if (temp < 8) {
            ChangeButtonTvCoordinatAndSize(_KomodButton, 300 , 200 , 800 , 690);
            return;
        }
        if (temp >= 8 && temp < 13) {
            ChangeButtonTvCoordinatAndSize(_KomodButton, 300 , 200 , 830 , 675);
            return;
        }
        if (temp >= 13 && temp < 20) {
            ChangeButtonTvCoordinatAndSize(_KomodButton, 300 , 200 , 850 , 660);
            return;
        }

        if (temp >= 20 && temp < 85) {
            ChangeButtonTvCoordinatAndSize(_KomodButton, 0 , 0 , 0 , 0);
            return;
        }

        if (temp >= 85 && temp < 90) {
            ChangeButtonTvCoordinatAndSize(_KomodButton, 300 , 200 , 860 , 675);
            return;
        }
        if (temp >= 90 && temp <= 96) {
            ChangeButtonTvCoordinatAndSize(_KomodButton, 300 , 200 , 840 , 690);
            return;
        }
    }
    public void controlButtonTV()
    {
        int temp = actualFrame;

        if(temp < 8) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250 , 180 , 330 , 730);
            return;
        }
        if(temp >= 8 && temp < 20) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250 +  temp*3, 180 +  temp*3, 320 , 685 );
            return;
        }
        if(temp >= 20 && temp < 31) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250 +  (int)(temp*4.5), 180 +  (int)(temp*4.5), 310 , 600  );
            return;
        }

        if(temp >= 31 && temp < 40) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250 +  (int)(temp*6), 180 +  (int)(temp*6), 240, 490);
            return;
        }
        if(temp >= 40 && temp < 55) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250 +  (int)(temp*6), 180 +  (int)(temp*6), 175, 410);
            return;
        }
        if(temp >= 55 && temp < 60) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250 +  (int)(temp*5), 180 +  (int)(temp*4), 190, 480);
            return;
        }
        if(temp >= 60 && temp < 70) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250 + (int)(temp*2.5) , 180 +  (int)(temp*1.5), 235, 570);
            return;
        }
        if(temp >= 70 && temp < 85) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 280 , 220 , 280, 640);
            return;
        }
        if(temp >= 85 && temp <= 96) {

            ChangeButtonTvCoordinatAndSize(_tvButton, 250  , 180, 285, 680);
            return;
        }
    }




    public void ChangeButtonTvCoordinatAndSize(Button but, int width, int height, int X, int Y)
    {
        but.setLayoutParams(new ConstraintLayout.LayoutParams(width,height));
        but.setX(X);
        but.setY(Y);
    }
}


