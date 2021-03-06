package com.gojun.certification.widget.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 圆角图片
 * @author Porster
 * @version   
 * 2.0, 2016-10-25 下午3:15:02
 */
public class GlideRoundedTransform extends BitmapTransformation{

	private  float radius = 0f;

    public GlideRoundedTransform(Context context) {
        this(context, 5);
    }

    public GlideRoundedTransform(Context context, int dp) {
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    public float getRadius() {
        return radius;
    }

    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private  Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

//    @Override public String getId() {
//        return getClass().getName() + Math.round(radius);
//    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

}
