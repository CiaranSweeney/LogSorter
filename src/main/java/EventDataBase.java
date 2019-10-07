import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class EventDataBase {
    public void addToEventTable(EventDetails eventDetails) {
        Connection con;
        Statement stmt;
        int result;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection( "jdbc:hsqldb:hsql://localhost/mydb", "SA", "");
            stmt = con.createStatement();
            String sqlStatement = String.format("INSERT INTO eventtable VALUES ('%s',%d,'%s','%s',%b)",
                    eventDetails.getEventId(),(int)eventDetails.getEventDuration(),eventDetails.getType(),
                    eventDetails.getHost(),eventDetails.isAlert());
            System.out.print(sqlStatement);
            result = stmt.executeUpdate(sqlStatement);
            con.commit();
        }catch (Exception e) {
            e.printStackTrace(System.out);
                return;
        }
        System.out.println(result + " rows effected");
        System.out.println("Rows inserted successfully");
    }
}