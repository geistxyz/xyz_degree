
import edu.umss.devportal.plugins.teamtrack.TeamTrackTool;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex Arenas
 */
public class Main {

    public static void main(String[] args) {
        TeamTrackTool tool = new TeamTrackTool();
        try{
            Map<String, String> map = new HashMap<String, String>();
            map.put(TeamTrackTool.DATABASE_HOST_KEY, "ALEX-BCF5EA0DD3");
            map.put(TeamTrackTool.DATABASE_NAME_KEY, "teamtrack");
            map.put(TeamTrackTool.DATABASE_PORT_KEY, "1433");
            map.put(TeamTrackTool.USER_NAME_KEY, "sa");
            map.put(TeamTrackTool.USER_PASSWORD_KEY, ".sa");
            boolean test = tool.testConnection(map);

             if(test)
                System.out.println("The connection is available");
             else
                 System.out.println("The connection is not available");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
