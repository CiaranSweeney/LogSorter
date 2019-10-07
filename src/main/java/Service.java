import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {
    private ArrayList<EventDetails> eventDetailsList = new ArrayList();
    private EventDataBase eventDataBass = new EventDataBase();

    @SuppressWarnings("unchecked")
    public void parseFile(String filePath){
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jp = jsonFactory.createJsonParser(fileInputStream);
            jp.setCodec(new ObjectMapper());
            jp.nextToken();
            EventDetails eventDetails;
            EventDetails checkEventDetails;
            while (jp.hasCurrentToken()) {
                EventData eventData = jp.readValueAs(EventData.class);
                eventDetails = new EventDetails(eventData.getId(),
                        eventData.getTimestamp(), eventData.getType(), eventData.getHost(), false);

                checkEventDetails = containsEventId(eventDetailsList, eventDetails.getEventId());
                if(checkEventDetails != null){
                    checkEventDetails.setEventDuration(workOutDuration(checkEventDetails,eventDetails));
                    checkEventDetails.isAlertNeeded();
                }
                else {
                    eventDetailsList.add(eventDetails);
                }
                jp.nextToken();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAllEventsToDataBase(){
        for(EventDetails eventDetails: eventDetailsList){
            eventDataBass.addToEventTable(eventDetails);
        }
    }

    private EventDetails containsEventId(final List<EventDetails> list, final String eventId){
        return list.stream().filter(eventDetails -> eventDetails
                .getEventId().equals(eventId)).findFirst().orElse(null);
    }

    private long workOutDuration(final EventDetails eventDetailsX, final EventDetails eventDetailsY){
        long sum;
        if(eventDetailsX.getEventDuration() < eventDetailsY.getEventDuration()){
            sum = eventDetailsY.getEventDuration() - eventDetailsX.getEventDuration();
        }

        else {
            sum = eventDetailsX.getEventDuration() -eventDetailsY.getEventDuration();
        }
        return sum;
    }

}
