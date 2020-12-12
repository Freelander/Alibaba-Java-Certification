package com.gojun.certification.view.setting;

import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.databinding.BindingAdapter;

import com.gojun.certification.R;

/**
 * Description:
 *
 * @author Jun 12/12/20
 */
public class ThemeBindingAdapters {

    @BindingAdapter("bindThemeSeekBarListener")
    public static void bindThemeSeekBarListener(AppCompatSeekBar seekBar, ThemeViewModel viewModel) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int viewId = seekBar.getId();
                switch (viewId) {
                    case R.id.sb_reb:
                        viewModel.getSbRebValue().set(progress);
                        break;
                    case R.id.sb_green:
                        viewModel.getSbGreenValue().set(progress);
                        break;
                    case R.id.sb_blue:
                        viewModel.getSbBlueValue().set(progress);
                        break;
                    default:
                        break;
                }

                if(!fromUser){
                    return;
                }
                viewModel.sendChangeThemeColorEvent();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
