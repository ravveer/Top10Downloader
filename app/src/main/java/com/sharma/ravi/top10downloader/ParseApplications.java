package com.sharma.ravi.top10downloader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Ravi Sharma Local on 12/25/2016.
 */

public class ParseApplications {

    private static final String TAG = "ParseApplications";
    private ArrayList<FeedEntry> applications;

    public ParseApplications() {
        this.applications = new ArrayList<>();

    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }

    //the method that actually does the xml parsing
    public boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d(TAG, "parse: Starting tag for " + tagName);
                        if ("entry".equalsIgnoreCase(tagName)) { //string "entry" can never be null, so it's protecting us from null pointer exception
                            inEntry = true; //only the entry tag in our xml contains the top 10 downloaded apps
                            currentRecord = new FeedEntry(); //data will be stored in  this instance of FeedEntry class we created earlier
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText(); //this will keep on putting data part of every tag in xml into textValue field.
                        break;
                    //we will only be using the textData when we want, by checking at the end tag that inEntry was true or not
                    case XmlPullParser.END_TAG:
//                        Log.d(TAG, "parse: Ending Tag for " + tagName);
                        if (inEntry) {
                            //when we get to the end of the entry tag, we'll have everything we want in currentRecord and make sure to put something in our array list
                            if ("entry".equalsIgnoreCase(tagName)) {
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if ("name".equalsIgnoreCase(tagName)) {
                                currentRecord.setName(textValue);
                            } else if ("artist".equalsIgnoreCase(tagName)) {
                                currentRecord.setArtist(textValue);
                            } else if ("releaseDate".equalsIgnoreCase(tagName)) {
                                currentRecord.setReleaseDate(textValue);
                            } else if ("summary".equalsIgnoreCase(tagName)) {
                                currentRecord.setSummary(textValue);
                            } else if ("image".equalsIgnoreCase(tagName)) {
                                currentRecord.setImageURL(textValue);
                            }
                        }
                        break;
                    default:
                        //nothing left to do
                }
                eventType = xpp.next();
            }
//            for (FeedEntry app : applications) {
//                Log.d(TAG, "*************************");
//                Log.d(TAG, app.toString());
//            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;

    }
}
