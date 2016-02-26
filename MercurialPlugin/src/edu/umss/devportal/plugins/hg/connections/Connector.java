/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.connections;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arminda Yovana Soto
 */
public class Connector {
    URL url;
    public Connector(URL url) {
        this.url = url;
    }

    public URLConnection connect(String site) {
       URLConnection connection = null;
        try {
            url = new URL(site);
            connection = url.openConnection(); 
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return connection;
    }
}
