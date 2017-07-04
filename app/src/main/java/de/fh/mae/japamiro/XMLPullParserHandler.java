package de.fh.mae.japamiro;

/**
 * Created by shaidex on 03.07.2017.
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLPullParserHandler {

    List<Weather> weatherList;

    private Weather weather;
    private String text;

    public XMLPullParserHandler() { weatherList = new ArrayList<Weather>(); }

    public List<Weather> getWeatherObj() { return weatherList; }

    public List<Weather> parse( InputStream is ) {

        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try {
             factory = XmlPullParserFactory.newInstance();
             factory.setNamespaceAware( true );

             parser = factory.newPullParser();
             parser.setInput( is, null );

             int eventType = parser.getEventType();
             while ( eventType != XmlPullParser.END_DOCUMENT ) {
                    String tagname = parser.getName();

                    switch ( eventType ) {

                        case XmlPullParser.START_TAG :

                         if ( tagname.equalsIgnoreCase( "weather" ) ) { weather = new Weather(); }
                            break;

                        case XmlPullParser.TEXT :

                            text = parser.getText();
                            break;

                        case XmlPullParser.END_TAG :

                         if ( tagname.equalsIgnoreCase( "weather" ) ) {
                             weatherList.add( weather );
                         } else if ( tagname.equalsIgnoreCase( "zeit" ) ) {
                             weather.setZeit( text );
                         } else if ( tagname.equalsIgnoreCase( "luftdruck" ) ) {
                             weather.setLuftdruck( Integer.parseInt( text ) );
                         } else if ( tagname.equalsIgnoreCase( "temperatur" ) ) {
                             weather.setTemperatur( Double.parseDouble( text ) );
                         } else if ( tagname.equalsIgnoreCase( "regen" ) ) {
                             weather.setRegen( Double.parseDouble( text ) );
                         }

                        default :
                            break;
                    }
                    eventType = parser.next();
             }
        } catch ( Exception e ) { e.printStackTrace(); }

        return weatherList;
    }

}
