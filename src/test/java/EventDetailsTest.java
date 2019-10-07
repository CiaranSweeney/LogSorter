import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class EventDetailsTest {
    private EventDetails eventDetails;

    @Test
    public void isAlertNeededGreaterThan3(){
        eventDetails = new EventDetails("test",4,"test","test-host",false);
        eventDetails.isAlertNeeded();
        assertTrue(eventDetails.isAlert());
    }

    @Test
    public void isAlertNeededTestLessThan3(){
        eventDetails = new EventDetails("test",3,"test","test-host",false);
        eventDetails.isAlertNeeded();
        assertFalse(eventDetails.isAlert());
    }
}
