/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.xplanner.connection;

import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.plugins.xplanner.config.XPlannerConfig;
import edu.umss.edu.devportal.exception.MissingParameterException;
import edu.umss.edu.devportal.exception.ServerNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.ConfigurationException;
import localhost.xplanner.soap.XPlanner.XPlanner;
import localhost.xplanner.soap.XPlanner.XPlannerServiceLocator;
import org.apache.axis.AxisProperties;
import org.apache.axis.client.Stub;

/**
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class ConnectionManager {

    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    /**
     *
     * Test if a connection to an Xplanner using givesn configuration can be established.
     *
     * @param config parameters to establish connection to XPlanner.
     * @return returns true if the connection can be established.
     * @throws ServerNotFoundException If the server cannot be found.
     * @throws MissingParameterException If a required parameter is missing.
     */
    public static boolean testConnection(Map<String, String> config)
            throws ServerNotFoundException, MissingParameterException {
        boolean succeeded = false;
        String serverUrl;

        // Check parameters
        checkParameters(config);

        // Check server existense
        serverUrl = config.get(XPlannerConfig.ServiceUrl);
        if (!checkServerAvailability(serverUrl)) {
            throw new ServerNotFoundException(XPlannerConfig.ToolName, serverUrl);
        }

        // Check if the xplanner service is running
        try {

            AxisProperties.setProperty(XPlannerWSConnection.SocketSecureFactory,
                    SSLSocketFactory.class.getName());

            XPlannerServiceLocator locator = new XPlannerServiceLocator();
            String xplannerServiceUrl = XPlannerWSConnection.appendServiceLocation(serverUrl);
            XPlanner xplanner = locator.getXPlanner(new URL(xplannerServiceUrl));

            ((Stub) xplanner).setUsername(config.get(XPlannerConfig.User));
            ((Stub) xplanner).setPassword(config.get(XPlannerConfig.Password));

            //Check user has sysadmin priviledges
            if (xplanner.isSysAdmin(config.get(XPlannerConfig.User))) {
                succeeded = true;
            }

        } catch (Exception ex) {
            logger.log(Level.INFO, "Exception raised testing xplanner connection", ex);
        }

        return succeeded;
    }

    private static void checkParameters(Map<String, String> config) throws MissingParameterException {
        //Iterate over required parameters and check them exist and not be null or empty value
        for (ParameterDescriptor param : XPlannerConfig.getRequiredParameters()) {
            String temp = config.get(param.getName());
            if (temp == null || temp.length() == 0) {
                throw new MissingParameterException(XPlannerConfig.ToolName, param.getName());
            }

            Pattern pattern = Pattern.compile(param.getCheckPattern());
            Matcher matcher = pattern.matcher(temp);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("Wrong format for " + param.getName());
            }
        }
    }

    private static boolean checkServerAvailability(String serverUrl) {
        boolean available = false;
        int responseCode;
        try {
            URL server = new URL(serverUrl);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) server.openConnection();
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpUrlConnection.connect();

            responseCode = httpUrlConnection.getResponseCode();

            logger.log(Level.INFO, "Response code of server {0} is {1}", new Object[]{serverUrl, responseCode});

            if (responseCode == 200) {
                available = true;
            }

        } catch (MalformedURLException mue) {
            logger.log(Level.INFO, mue.getMessage(), mue);
        } catch (IOException ioe) {
            logger.log(Level.INFO, ioe.getMessage(), ioe);
        }

        return available;
    }

    public static XPlannerConnection createConnection(Map<String, String> config, XPlannerConnectionType type)
            throws ServerNotFoundException, MissingParameterException {
        XPlannerConnection connection = null;
        switch (type) {
            case WebService:
                connection = new XPlannerWSConnection();
                try {
                    connection.applyConfiguration(config);
                } catch (ConfigurationException ex) {
                    throw new ServerNotFoundException(XPlannerConfig.ToolName, ex.getMessage());
                }
                break;
        }
        return connection;
    }
}
