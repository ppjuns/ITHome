package com.ppjun.android.ithome.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Package :com.ppjun.android.ithome.util
 * Description :
 * Author :Rc3
 * Created at :2017/04/04 17:08.
 */

public class ThemeHelper {
    public static final int CARD_SAKURA = 0x1;
    public static final int CARD_HOPE = 0x2;
    public static final int CARD_STORM = 0x3;
    public static final int CARD_WOOD = 0x4;
    public static final int CARD_LIGHT = 0x5;
    public static final int CARD_THUNDER = 0x6;
    public static final int CARD_SAND = 0x7;
    public static final int CARD_FIREY = 0x8;
    public static final int CARD_GRAY = 0x9;
    private static final String CURRENT_THEME = "theme_current";
    private static final String MODE_NIGHT = "MODE_NIGHT";
    private static final String LAST_COLOR_THEME = "LAST_COLOR_THEME";

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("multipe_theme", Context.MODE_PRIVATE);
    }

    public static void setTheme(Context context, int themeId) {
        getSharePreference(context).edit().putInt(CURRENT_THEME, themeId)
                .apply();

    }

    //夜间模式
    public static SharedPreferences getModeNightSPF(Context context) {
        return context.getSharedPreferences(MODE_NIGHT, Context.MODE_PRIVATE);
    }

    public static void setModeNight(Context context, boolean isNight) {
        getModeNightSPF(context).edit().putBoolean(MODE_NIGHT, isNight)
                .apply();

    }

    public static boolean getModeNight(Context context) {
        return getModeNightSPF(context).getBoolean(MODE_NIGHT, false);

    }

    //彩色主题
    public static SharedPreferences getLastThemeColor(Context context) {
        return context.getSharedPreferences(LAST_COLOR_THEME, Context.MODE_PRIVATE);
    }

    public static void setLastColor(Context context, int color) {
        getLastThemeColor(context).edit().putInt(LAST_COLOR_THEME, color)
                .apply();

    }

    public static int getLastColor(Context context) {
        return getLastThemeColor(context).getInt(LAST_COLOR_THEME, ThemeHelper.CARD_SAKURA);

    }

    public static int getTheme(Context context) {
        return getSharePreference(context).getInt(CURRENT_THEME, CARD_SAKURA);
    }

    public static boolean isDefaultTheme(Context context) {
        return getTheme(context) == CARD_SAKURA;
    }

    public static String getName(int themeId) {
        switch (themeId) {
            case CARD_SAKURA:

                return "THE SAKURA";
        }
        return "THE RETURN";
    }
}
