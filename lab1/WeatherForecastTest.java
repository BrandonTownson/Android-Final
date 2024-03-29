
package com.example.brandon.lab1;

import android.content.Context;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.util.Xml;

import android.view.View;

import android.widget.ImageView;

import android.widget.ProgressBar;

import android.widget.TextView;



import org.w3c.dom.Text;

import org.xmlpull.v1.XmlPullParser;

import org.xmlpull.v1.XmlPullParserFactory;



import java.io.FileOutputStream;

import java.net.HttpURLConnection;

import java.net.MalformedURLException;

import java.net.URL;



public class WeatherForecastTest extends AppCompatActivity {



    protected static final String ACTIVITY_NAME = "WeatherForecast";

    ProgressBar progressBar;

    ImageView weatherImage;

    TextView currentTemp;

    TextView minTemp;

    TextView maxTemp;




    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.weather);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        currentTemp = (TextView)findViewById(R.id.currentTemp);

        minTemp = (TextView)findViewById(R.id.minTemp);

        maxTemp = (TextView)findViewById(R.id.maxTemp);

        weatherImage = (ImageView)findViewById(R.id.WeatherImage);
        new ForecastQuery().execute("");

    }



    public class ForecastQuery extends AsyncTask<String, Integer, String>{

        String current;

        String min;

        String max;

        String iconName;

        Bitmap pic;



        @Override

        protected String doInBackground(String ...args){

            try {

                String u = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";

                URL url = new URL(u);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000 /* milliseconds */);

                conn.setConnectTimeout(15000 /* milliseconds */);

                conn.setRequestMethod("GET");

                conn.setDoInput(true);

                // Starts the query

                conn.connect();

                XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();

                XmlPullParser parser = xmlFactoryObject.newPullParser();

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

                parser.setInput(conn.getInputStream(), null);



                int event;

                String text=null;



                try {

                    event = parser.getEventType();



                    while (event != XmlPullParser.END_DOCUMENT) {

                        String name=parser.getName();



                        switch (event){

                            case XmlPullParser.START_TAG:

                                if(name.equals("temperature")){

                                    min = parser.getAttributeValue(null,"value");

                                    publishProgress(25);

                                    max = parser.getAttributeValue(null,"max");

                                    publishProgress(50);

                                    current = parser.getAttributeValue(null,"value");

                                    publishProgress(75);

                                } else if(name.equals("weather")){

                                    iconName = parser.getAttributeValue(null, "icon");

                                } else {

                                }

                                break;



                            case XmlPullParser.TEXT:

                                break;



                            case XmlPullParser.END_TAG:

                                //originally put code here, did not work probably because parser cannot see any tags if

                                //you start at the end of the document

                                break;

                        }

                        event = parser.next();

                    }

                }

                catch (Exception e) {

                    e.printStackTrace();

                    Log.i(ACTIVITY_NAME, e.getMessage());

                }



                pic  = HttpUtils.getImage("http://openweathermap.org/img/w/" + iconName + ".png");

                FileOutputStream outputStream = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);



                pic.compress(Bitmap.CompressFormat.PNG, 80, outputStream);



                Log.i(ACTIVITY_NAME, "Process 2");

                outputStream.flush();

                Log.i(ACTIVITY_NAME, "Process 3");

                outputStream.close();



                Log.i(ACTIVITY_NAME, "Before Done");

                return "Done";



            } catch (Exception e) {

                e.printStackTrace();

                Log.i(ACTIVITY_NAME, e.getMessage());

                return "";

            }

        }



        @Override

        public void onProgressUpdate(Integer ...value){

            progressBar.setVisibility(View.VISIBLE);

            progressBar.setProgress(value[0]);

            Log.i(ACTIVITY_NAME, "onProgressUpdate");

        }



        @Override

        public void onPostExecute(String result){

            super.onPostExecute(result);

            Log.i(ACTIVITY_NAME, "onPostExecute");

            if(result.equals("Done")) {

                Log.i(ACTIVITY_NAME, "onPostExecute in if statement");

                progressBar.setVisibility(View.INVISIBLE);

                Log.i(ACTIVITY_NAME, "min: " + min);

                Log.i(ACTIVITY_NAME, "max: " + max);

                currentTemp.setText("Current Temperature: " + current);
                minTemp.setText("Min: " + min);
                maxTemp.setText("Max: " + max);
                weatherImage.setImageBitmap(pic);

            }

        }

    }





}



class HttpUtils {

    public static Bitmap getImage(URL url) {

        HttpURLConnection connection = null;

        try {

            connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                return BitmapFactory.decodeStream(connection.getInputStream());

            } else

                return null;

        } catch (Exception e) {

            return null;

        } finally {

            if (connection != null) {

                connection.disconnect();

            }

        }

    }

    public static Bitmap getImage(String urlString) {

        try {

            URL url = new URL(urlString);

            return getImage(url);

        } catch (MalformedURLException e) {

            return null;

        }

    }

}



