package com.hrc.administrator.alphabitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private int alpha=100;
    private MyView myView;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        alpha=progress;
        setTitle("alpha:"+alpha);
        myView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class MyView extends View{

        private Bitmap bitmap;

        public MyView(Context context){
            super(context);
            setBackgroundColor(Color.WHITE);
            InputStream is=getResources().openRawResource(R.raw.panda);
            bitmap= BitmapFactory.decodeStream(is);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint=new Paint();
            paint.setAlpha(alpha);
            canvas.drawBitmap(bitmap,new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),new RectF(10,10,310,235),paint);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        myView=new MyView(this);
        myView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,260));
        SeekBar seekBar=new SeekBar(this);
        seekBar.setMax(255);
        seekBar.setProgress(alpha);
        seekBar.setOnSeekBarChangeListener(this);
        linearLayout.addView(myView);
        linearLayout.addView(seekBar);
        linearLayout.setBackgroundColor(Color.WHITE);
        setContentView(linearLayout);
        setTitle("alpha:"+alpha);
    }
}
