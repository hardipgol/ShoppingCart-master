package com.shoppingcart.tutoral;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.danimahardhika.android.helpers.core.ColorHelper;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.shoppingcart.R;
import com.shoppingcart.netUtils.MyPreferences;

/*
 * Wallpaper Board
 *
 * Copyright (c) 2017 Dani Mahardhika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class TapIntroHelper {

    /* todo dashboard tutoral */
    public static void showDashboardIntro(@NonNull final Context context, @ColorInt final int color) {
        try {
            final MyPreferences myPreferences = new MyPreferences(context);
            if (myPreferences.getTutoralPreferences(MyPreferences.dashboard_tutorial).equals("")) {
                final AppCompatActivity activity = (AppCompatActivity) context;
                final Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int primary = ColorHelper.getTitleTextColor(color);
                            int secondary = ColorHelper.setColorAlpha(primary, 0.7f);
                            TapTargetSequence tapTargetSequence = new TapTargetSequence(activity);
                            tapTargetSequence.continueOnCancel(true);
                            Typeface title = TypefaceHelper.getMedium(context);
                            Typeface description = TypefaceHelper.getRegular(context);

                            if (toolbar != null) {
                                TapTarget tapTarget_nav = TapTarget.forToolbarNavigationIcon(toolbar,
                                        context.getResources().getString(R.string.tap_intro_dashboard_navigation),
                                        context.getResources().getString(R.string.tap_intro_dashboard_navigation_desc))
                                        .titleTextColorInt(primary)
                                        .descriptionTextColorInt(secondary)
                                        .targetCircleColorInt(primary)
                                        .drawShadow(true);
                                if (title != null) {
                                    tapTarget_nav.textTypeface(title);
                                }

                                if (description != null) {
                                    tapTarget_nav.textTypeface(description);
                                }
                                tapTargetSequence.target(tapTarget_nav);

                                TapTarget tapTarget = TapTarget.forToolbarMenuItem(toolbar, R.id.shopping_cart,
                                        context.getResources().getString(R.string.tap_intro_dashboard_notifaction_apply),
                                        context.getResources().getString(R.string.tap_intro_dashboard_notifaction_apply_desc))
                                        .titleTextColorInt(primary)
                                        .descriptionTextColorInt(secondary)
                                        .targetCircleColorInt(primary)
                                        .outerCircleColorInt(color)
                                        .drawShadow(true);

                                if (title != null) {
                                    tapTarget.textTypeface(title);
                                }

                                if (description != null) {
                                    tapTarget.textTypeface(description);
                                }
                                tapTargetSequence.target(tapTarget);

                            }



                            tapTargetSequence.listener(new TapTargetSequence.Listener() {
                                @Override
                                public void onSequenceFinish() {
                                    myPreferences.setTutoralPreferences(MyPreferences.dashboard_tutorial, "1");
                                }

                                @Override
                                public void onSequenceStep(TapTarget tapTarget, boolean b) {

                                }

                                @Override
                                public void onSequenceCanceled(TapTarget tapTarget) {

                                }
                            });
                            tapTargetSequence.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
