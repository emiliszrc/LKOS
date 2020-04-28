package com.example.lkos;

import com.example.Controllers.DataController;
import com.example.Models.Object;
import com.example.Models.Trip;
import com.google.gson.Gson;


import org.joda.time.DateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>stop it please
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    /*
    @Test
    public void parseTripTest() throws JSONException {
        DataController testable = new DataController();
        Gson gson = new Gson();
        Trip testTrip = new Trip (13,"Ajaja", DateTime.parse("2020-04-13T00:00:00"),
                DateTime.parse("2020-04-22T00:00:00"), "2", "Ajajajajajajaja", 520);
        String JSON = gson.toJson(testTrip);
        Trip result= testable.parseTrip(JSON);
        assertEquals(testTrip.getCapacity(), result.getCapacity());
        assertEquals(testTrip.getDescription(), result.getDescription());
        assertEquals(testTrip.getEndDate(), result.getEndDate());
        assertEquals(testTrip.getStartDate(), result.getStartDate());
        assertEquals(testTrip.getTripTitle(), result.getTripTitle());
        assertEquals(testTrip.getType(), result.getType());
        assertEquals(testTrip.getTripId(), result.getTripId());
    }

     */
    @Test
    public void parseObjectTest() throws JSONException {
        DataController testable = new DataController();
        Gson gson = new Gson();
        Object testObject = new Object(13, 2, "Hotel Runmis","3420  Fieldcrest Road" );
        String JSON = gson.toJson(testObject);
        Object result = testable.parseObject(JSON);
        assertEquals(testObject.getObjectId(), result.getObjectId());
        assertEquals(testObject.getObjectAddress(), result.getObjectAddress());
        assertEquals(testObject.getObjectTitle(), result.getObjectTitle());
        assertEquals(testObject.getObjectType(), result.getObjectType());
    }
}
