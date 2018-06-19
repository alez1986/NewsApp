package com.example.alez.newsapp;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Activity context, List<Article> articles) {
        super(context, 0, articles);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        Article currentArticle = getItem(position);

        String title = currentArticle.getTitle();
        TextView titleView = convertView.findViewById(R.id.title);
        titleView.setText(title);

        String section = currentArticle.getSection();
        TextView sectionView = convertView.findViewById(R.id.section);
        sectionView.setText(section);

        Date dateObject = currentArticle.getPublishDate();
        String formattedDate = formatDate(dateObject);
        TextView dateView = convertView.findViewById(R.id.date);
        dateView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        TextView timeView = convertView.findViewById(R.id.time);
        timeView.setText(formattedTime);

        return convertView;
    }


    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }


    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}