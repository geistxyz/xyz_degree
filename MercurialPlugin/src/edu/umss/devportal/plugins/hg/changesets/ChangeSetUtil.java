/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.changesets;

import edu.umss.devportal.plugins.hg.receiver.HgActionReceiver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An util for loads the change sets to sorted map
 * for example a change set contains the follows datas:
 * 
 * "changeset:   2:13336fabeca9"
 * "tag:         tip"
 * "user:        username"
 * "date:        Sun Dec 12 23:20:36 2010 -0800"
 * "summary:     Updated:modified the file executor"
 *
 * @author Arminda Yovana Soto
 */
public class ChangeSetUtil {

    /**
     * Loads the data from input stream to sorted Map,which criteria sort is the revision number
     * of major to minor
     * @param datasFlow all lines of log file obtains with 'hg log 'command
     * @return sorted map sorted by Revision object
     */
    public static Map<Revision, Map<String, String>> chargeLog(InputStream datasFlow) {
        Map<Revision, Map<String, String>> mapAllChangesets = new TreeMap<Revision, Map<String, String>>();
        Map<String, String> mapOneChangeSet = new HashMap<String, String>();
        String line;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(datasFlow);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            Revision revision = null;
            //line sample -> changeset:  2:13336fabeca9
            //next line ->   tag:        tip
            while (!((line = bufferReader.readLine()) == null)) {
                if (!(line.isEmpty())) {
                    String[] token = line.split(": ", 2);
                    int incrementorAux = 1;
                    for (int incrementor = 0; incrementor < token.length; incrementor = incrementorAux + 1) {
                        String tokenTitle = token[incrementor];
                        if (tokenTitle.equals("changeset")) {
                            //completeNumberOfRevision sample-> 2:13336fabeca9
                            String[] completeNumberOfRevision = token[incrementorAux].split(":");
                            //the integerNumber sample -> only 2
                            String integerNumber = delFreeSpace(completeNumberOfRevision[0]);
                            //the literalNumber sample -> only 13336fabeca9
                            String literalNumber = completeNumberOfRevision[1];
                            
                            revision = new Revision(Integer.parseInt(integerNumber),
                                    literalNumber);

                            mapOneChangeSet.put(tokenTitle, revision.toString());

                        } else {
                            mapOneChangeSet.put(tokenTitle, delFreeSpace(token[incrementorAux]));
                        }
                    }//end for

                } else {
                    mapAllChangesets.put(revision, mapOneChangeSet);
                    mapOneChangeSet = new HashMap<String, String>();
                }
            }//end while
            bufferReader.close();
        } catch (IOException ex) {
            Logger.getLogger(HgActionReceiver.class.getName()).log(Level.SEVERE,
                    "The revision log cann't be readed");
        }
        return mapAllChangesets;
    }

    /**
     * Deletes recursively the free spaces
     * @param line string of data
     * @return line without spaces
     */
    public static String delFreeSpace(String line) {
        if (!(line.startsWith(" "))) {
            return line;
        } else {
            String[] split = line.split(" ", 2);
            line = split[1];
            return delFreeSpace(line);
        }
    }
}
