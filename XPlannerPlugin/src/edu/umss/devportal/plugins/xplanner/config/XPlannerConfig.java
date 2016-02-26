/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.config;

import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolDescriptorImpl;
import edu.umss.devportal.common.ToolVersion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class contains Xplanner's plug-in configuration necessary information
 *
 * @author Raul Lopez
 */
public class XPlannerConfig {
    private static ToolDescriptor toolDescriptor;
    private static final ArrayList<ParameterDescriptor> requiredParameters;
    public static final int VersionMajor = 1;
    public static final int VersionMinor = 0;
    public static final String ToolName = "Xplanner Tool";
    public static final String ToolDescription = "Xplanner Project tracker connnection tool";

    // Required parameters information
    public static final String ServiceUrl = "serviceURL";
    public static final String ServiceUrlPattern = "((https?):((//)|(\\\\\\\\))[\\w\\d:#%/;$()~_?\\-=\\\\\\.&]*)";
    public static final String ServiceUrlDesc = "Xplanner SOAP service URL";
    public static final String ServiceUrlDefault = "http://localhost:9080/xplanner/";

    public static final String User = "user";
    public static final String UserPattern = "[\\w|\\d|\\.|-]+";
    public static final String UserDesc = "Xplanner user with SysAdmin priviledges";
    public static final String UserDefault = "sysadmin";

    public static final String Password = "password";
    public static final String PasswordPattern = "[\\w|\\d|\\W]*";
    public static final String PasswordDesc = "Xplanner user's password";
    public static final String PasswordDefault = "admin";

    /**
     * XPlannerConfig constructor
     */
    static {

        // Initialize tool descriptor
        ToolVersion toolVersion = new ToolVersion(VersionMajor, VersionMinor);

        toolDescriptor = new ToolDescriptorImpl(ToolName,
                    ToolDescription,
                    toolVersion);

        // Initialize required parameters list    
        requiredParameters = new ArrayList<ParameterDescriptor>(3);
        requiredParameters.add(new ParameterDescriptor(User, UserDefault, UserDesc, UserPattern));
        requiredParameters.add(new ParameterDescriptor(Password, PasswordDefault, PasswordDesc, PasswordPattern));
        requiredParameters.add(new ParameterDescriptor(ServiceUrl, ServiceUrlDefault, ServiceUrlDesc, ServiceUrlPattern));
    }

    /**
     * @return Tool descriptor of XPlanner plug-in
     */
    public static ToolDescriptor getToolDescriptor() {
        return toolDescriptor;
    }

    /**
     * @return Required parameters for XPlanner plug-in
     */
    public static List<ParameterDescriptor> getRequiredParameters() {
        return requiredParameters;
    }
}
