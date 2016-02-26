/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.changesets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Manages the all change sets list
 * @author Arminda Yovana Soto
 */
public class ChangeSetManager {

    Map<Revision, Map<String, String>> log;
    ChangeSet changeSet;
    List<ChangeSet> listChangeSets;

    /**
     * Initialize the map of change sets and loads every ChangeSet object to list of change sets
     * @param log map of all change sets
     */
    public ChangeSetManager(Map log) {
        this.log = log;
        listChangeSets = new ArrayList<ChangeSet>();
        addListChangeSet();
    }

    /**
     * Recovery a change set of the sorted map,creates a ChangeSet object
     * and adds this to list of change sets
     */
    private void addListChangeSet() {
        Collection<Map<String, String>> values = log.values();
        for (Revision revision : log.keySet()) {
            for (Map<String, String> map : values) {
                if (revision.toString().equals(map.get("changeset"))) {
                    changeSet = new ChangeSet(revision, map.get("tag"),map.get("user"),
                                                        map.get("date"),map.get("summary"));
                    listChangeSets.add(changeSet);
                }
            }
        }//end first for
    }

    /**
     * Show the list of change sets objects
     */
    public void showLits() {
        Iterator<ChangeSet> iterator = listChangeSets.iterator();
        while (iterator.hasNext()) {
            ChangeSet changeS = iterator.next();
            System.out.println("changeset:" + changeS.getRevision());
            System.out.println("tag:" + changeS.getTag());
            System.out.println("user:" + changeS.getUserName());
            System.out.println("date:" + changeS.getDate());
            System.out.println("description:" + changeS.getSummary());
        }
    }
    
    /**
     * Returns the list of all change sets
     * @return list of change sets
     */
    public List<ChangeSet> getListChangeSets(){
        return listChangeSets;
    }
}
