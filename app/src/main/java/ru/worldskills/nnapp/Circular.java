package ru.worldskills.nnapp;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

public class Circular implements Transformation {

    public Circular(){
    }


    public Bitmap transform(Bitmap sourse){
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(sourse, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(sourse.getWidth(),sourse.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawCircle(sourse.getWidth() / 2, sourse.getHeight() / 2,sourse.getWidth() / 2,paint);

        if (sourse != output){
            sourse.recycle();
        }
        return output;
    }


    public String key(){
        return "circle";
    }

}
