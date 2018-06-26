package com.example.alez.newsapp;


import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EditDatePreference extends DialogPreference {

    private int mLastDay = 0;
    private int mLastMonth = 0;
    private int mLastYear = 0;
    private String mDate;
    private CharSequence mSummary;
    private DatePicker picker = null;


    public EditDatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
    }

    private static int getYear(String date) {
        String[] pieces = date.split("-");
        return (Integer.parseInt(pieces[0]));
    }

    private static int getMonth(String date) {
        String[] pieces = date.split("-");
        return (Integer.parseInt(pieces[1]));
    }

    private static int getDay(String date) {
        String[] pieces = date.split("-");
        return (Integer.parseInt(pieces[2]));
    }

    @Override
    protected View onCreateDialogView() {
        picker = new DatePicker(getContext());
        picker.setCalendarViewShown(false);

        return (picker);
    }


    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);

        picker.updateDate(mLastYear, mLastMonth, mLastDay);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            mLastYear = picker.getYear();
            mLastMonth = picker.getMonth();
            mLastDay = picker.getDayOfMonth();

            String date = String.valueOf(mLastYear) + "-"
                    + String.valueOf(mLastMonth + 1) + "-"
                    + String.valueOf(mLastDay);

            if (callChangeListener(date)) {
                persistString(date);
            }
        }
    }


    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return (a.getString(index));
    }


    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        mDate = null;

        if (restoreValue) {
            if (defaultValue == null) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String formatted = format1.format(cal.getTime());
                mDate = getPersistedString(formatted);
            } else {
                mDate = getPersistedString(defaultValue.toString());
            }
        } else {
            mDate = defaultValue.toString();
        }

        mLastYear = getYear(mDate);
        mLastMonth = getMonth(mDate) - 1;
        mLastDay = getDay(mDate);
    }

    public String getText() {
        return mDate;
    }

    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();
        mDate = text;
        persistString(text);

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }

    @Override
    public CharSequence getSummary() {
        return mSummary;
    }


    @Override
    public void setSummary(CharSequence summary) {
        if (summary == null && mSummary != null || summary != null
                && !summary.equals(mSummary)) {
            mSummary = summary;
            notifyChanged();
        }
    }
}