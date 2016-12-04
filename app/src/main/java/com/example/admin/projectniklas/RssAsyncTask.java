package com.example.admin.projectniklas;


import android.os.AsyncTask;

import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class RssAsyncTask extends AsyncTask<String, Void, ArrayList<Feed>> {

    private OnPostResultListener listener;

    public RssAsyncTask(OnPostResultListener listener){this.listener = listener; }


    @Override
    protected ArrayList<Feed> doInBackground(String... strings) {
        ArrayList<Feed> feeds = new ArrayList<>();
        for (String s : strings)
            try {
                InputStream iStream = new URL(s).openConnection().getInputStream();
                Feed feed = EarlParser.parseOrThrow(iStream, 0);
                feeds.add(feed);

            } catch (IOException | DataFormatException | XmlPullParserException e) {
                return null;
            }
        return feeds;
    }

    public interface OnPostResultListener{
        void onPostExecute(ArrayList<Feed> feeds);
    }

    @Override
    protected void onPostExecute(ArrayList<Feed> feeds) {
        super.onPostExecute(feeds);
        listener.onPostExecute(feeds);
    }
}
