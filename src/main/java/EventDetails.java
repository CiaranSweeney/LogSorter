import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventDetails {
    private String eventId;
    private long eventDuration;
    private String type;
    private String host;
    private boolean alert;

    public void isAlertNeeded(){
        if(eventDuration < 4){
            alert = false;
        }
        else {
            alert = true;
        }
    }
}
