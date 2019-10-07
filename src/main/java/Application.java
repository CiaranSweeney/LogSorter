

public class Application {
    public static void main(String args[]){
        Service service = new Service();
        service.parseFile(args[0]);
        service.addAllEventsToDataBase();

    }
}
