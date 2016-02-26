/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.reports;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsSelectionsJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsStatesJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsUsersJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TttIssuesJpaController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author July Camacho
 *
 */
public abstract class GridReport {
    private List<String> headers;
    protected  TttIssuesJpaController issuesController;
    protected TsStatesJpaController statesController;
    protected TsUsersJpaController usersController;
    protected TsSelectionsJpaController selectionController;
    public static final String WEB_SERVER_IP = "Web server ip";
    public static final String WEB_SERVER_PORT = "Web server port";
    public static String WEB_SERVER_IP_VALUE ;
    public static String WEB_SERVER_PORT_VALUE ;
    
   /**
    * 
    */
    public GridReport(){
        issuesController = new TttIssuesJpaController();
        statesController = new TsStatesJpaController();
        usersController = new TsUsersJpaController();
        selectionController = new TsSelectionsJpaController();
        headers = new ArrayList<String>();
        headers.add("State");
        headers.add("IssueID");
        headers.add("Title");
        headers.add("Owner");
        headers.add("Submiter");
        headers.add("Severity");
        headers.add("Prority");
    }
    /**
     * 
     * @return
     */
    protected  List<String> getHeaders(){
        return headers;
    }
    /**
     *
     * @param id
     * @param issueId
     * @return
     */
    protected  String getIssueLink(int id, String issueId)
    {
        String result = null;
        if(WEB_SERVER_IP_VALUE != null && WEB_SERVER_PORT_VALUE != null)
            result = String.format("<a href=\"http://%s:%s/tmtrack/tmtrack.dll?IssuePage&RecordId=%s&Template=view&TableId=1000/\" target = \"externo\">%s</a>"
                                        ,WEB_SERVER_IP_VALUE,WEB_SERVER_PORT_VALUE, id,issueId);
        return result;
    }
}
