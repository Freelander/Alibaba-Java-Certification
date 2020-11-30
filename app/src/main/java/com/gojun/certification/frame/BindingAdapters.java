package com.gojun.certification.frame;

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
}
