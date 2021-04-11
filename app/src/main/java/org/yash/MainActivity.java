package org.yash;

import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    Thread backgroundThread;
    RecyclerView newsRecView;
    String title = "", description = "", pubdate = "";
    private ArrayList<News> newsArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRecView = findViewById(R.id.newsRecView);
        getData();
    }

    private void getData(){
        backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //doInBackground
                InputStream inputStream = getInputStream();
                if (null != inputStream) {
                    try {
                        initXMLParser(inputStream);
                    } catch (XmlPullParserException | IOException e) {
                        e.printStackTrace();
                    }
                }

                //onPost
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NewsAdapter newsAdapter = new NewsAdapter(newsArrayList);

                        newsRecView.setAdapter(newsAdapter);
                        newsRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                });
            }
        });
        backgroundThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backgroundThread.stop();
    }

    private void initXMLParser(InputStream inputStream) throws XmlPullParserException, IOException {
        Log.d(TAG, "initXMLParser: Initializing XML Pull Parser");
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);
        parser.next();

        parser.require(XmlPullParser.START_TAG, null, "rss");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            parser.require(XmlPullParser.START_TAG, null, "channel");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }

                if (parser.getName().equals("item")){
                    parser.require(XmlPullParser.START_TAG, null, "item");

                    while (parser.next() != XmlPullParser.END_TAG) {
                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                            continue;
                        }
                        String tagName = parser.getName();
                        switch (tagName) {
                            case "title":
                               title = getContent(parser, "title");
                                break;

                            case "description":
                               description = getContent(parser, "description");
                                break;

                            case "pubDate":
                               pubdate = getContent(parser, "pubDate");
                                break;
                            default:
                                skipTag(parser);
                                break;
                        }
                    }
                    News news = new News(title, description, pubdate);
                    newsArrayList.add(news);
                } else {
                    skipTag(parser);
                }
            }
        }
    }

    private void skipTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw  new IllegalArgumentException();
        }

        int num = 1;
        while (num != 0) {
            switch (parser.next()){
                case XmlPullParser.START_TAG:
                    num++;
                    break;
                case XmlPullParser.END_TAG:
                    num--;
                    break;
                default:
                    break;
            }
        }
    }

    private String getContent(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        String content = "";
        parser.require(XmlPullParser.START_TAG, null, tag); //description
        if (parser.next() == XmlPullParser.TEXT) {
            content = parser.getText();
            parser.next();
        }
        return content;
    }

    private InputStream getInputStream() {
        try {
            URL url = new URL("https://timesofindia.indiatimes.com/rssfeeds/913168846.cms");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
