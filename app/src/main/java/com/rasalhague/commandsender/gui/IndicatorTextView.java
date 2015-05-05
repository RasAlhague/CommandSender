package com.rasalhague.commandsender.gui;

import android.app.Activity;
import android.widget.TextView;

public class IndicatorTextView
{
    private final TextView indicatorTextView;
    private final Activity activity;

    public IndicatorTextView(TextView indicatorTextView, Activity activity)
    {
        this.indicatorTextView = indicatorTextView;
        this.activity = activity;
    }

    public void setText(final CharSequence text)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                indicatorTextView.setText(text);
            }
        });
    }
}
