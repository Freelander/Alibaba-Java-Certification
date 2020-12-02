package com.gojun.certification.frame;

import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.gojun.certification.widget.LightingView;

/**
 * Description:
 *
 * @author Jun 11/30/20
 */
public class BindingAdapters {

    @BindingAdapter("bindLightingColor")
    public static void bindLightingColor(LightingView view, int color) {
        view.setColor(color);
    }

    @BindingAdapter("bindViewVisibility")
    public static void bindViewVisibility(View view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("bindText")
    public static void bindText(TextView textView, CharSequence text) {
        if (text != null) {
            textView.setText(text);
        }
    }
}
