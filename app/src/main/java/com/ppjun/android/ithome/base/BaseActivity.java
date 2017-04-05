package com.ppjun.android.ithome.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.ppjun.android.ithome.R;
import com.ppjun.android.ithome.util.ThemeHelper;
import com.ppjun.android.ithome.weight.CardPickerDialog;

/**
 * Package :com.ppjun.android.ithome.base
 * Description :
 * Author :Rc3
 * Created at :2017/04/05 11:00.
 */

public class BaseActivity extends AppCompatActivity implements CardPickerDialog.ClickListener {
    Context mContext;
    private static final String TAG= BaseActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        Log.i(TAG, "onCreate: ");
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate: ");

       if(ThemeHelper.getModeNight(mContext)){

           onConfirm(ThemeHelper.CARD_GRAY);
           getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

           ThemeHelper.setModeNight(mContext, true);
       }
       if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ThemeUtils.getColorById(this, R.color.theme_color_primary_dark));
            ActivityManager.TaskDescription description = new ActivityManager.TaskDescription(null, null, ThemeUtils.getThemeAttrColor(this, android.R.attr.colorPrimary));
            setTaskDescription(description);
        }


    }
    @Override
    public void onConfirm(int currentTheme) {
        if (ThemeHelper.getTheme(mContext) != currentTheme) {
            ThemeHelper.setTheme(mContext, currentTheme);
            ThemeUtils.refreshUI(mContext, new ThemeUtils.ExtraRefreshable() {
                @Override
                public void refreshGlobal(Activity activity) {


                        ActivityManager.TaskDescription description = new ActivityManager.TaskDescription(null, null, ThemeUtils.getThemeAttrColor(mContext,
                                android.R.attr.colorPrimary
                        ));
                        setTaskDescription(description);
                        getWindow().setStatusBarColor(ThemeUtils.getColorById(mContext, R.color.theme_color_primary_dark));



                    int currentNightMode = getResources().getConfiguration().uiMode
                            & Configuration.UI_MODE_NIGHT_MASK;
                    if(currentNightMode==Configuration.UI_MODE_NIGHT_YES){
                        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        recreate();
                        ThemeHelper.setModeNight(mContext, false);
                    }

                }

                @Override
                public void refreshSpecificView(View view) {

                }
            });
        }
    }
}
