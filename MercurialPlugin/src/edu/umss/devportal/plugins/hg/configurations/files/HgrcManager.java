/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.configurations.files;

import java.util.Map;

/**
 *
 * @author Arminda Yovana Soto
 */
public class HgrcManager {

    private HgrcFile hgrc;
    private final String SECTIONPATH = "paths";
    private final String SECTIONAUTH = "auth";
    private final String SECTIONWEB = "web";

    public HgrcManager(HgrcFile hgrc) {
        this.hgrc = hgrc;
    }

    public void auth(Map<String, String> values) {
        for (String key : values.keySet()) {
            for (String value : values.values()) {

                hgrc.addValuesToSection(SECTIONAUTH, key, value);

            }
        }
    }

    public void path(Map<String, String> values) {
        for (String key : values.keySet()) {
            for (String value : values.values()) {

                hgrc.addValuesToSection(SECTIONPATH, key, value);

            }
        }
    }

    public void web(Map<String, String> values) {
        for (String key : values.keySet()) {
            for (String value : values.values()) {

                hgrc.addValuesToSection(SECTIONWEB, key, value);

            }
        }
    }
    
}
