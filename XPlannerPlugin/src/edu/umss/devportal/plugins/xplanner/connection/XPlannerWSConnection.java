/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.xplanner.connection;

import com.technoetic.xplanner.soap.domain.DomainData;
import edu.umss.devportal.common.BasicProjectImpl;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.LineDataStructure;
import edu.umss.devportal.common.Project;
import edu.umss.devportal.common.User;
import edu.umss.devportal.common.BasicUserImpl;
import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.plugins.xplanner.XplannerTool;
import edu.umss.devportal.plugins.xplanner.config.XPlannerConfig;
import edu.umss.devportal.plugins.xplanner.reports.impl.BurnDownProject;
import edu.umss.devportal.plugins.xplanner.reports.structure.ReportProject;
import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.ConfigurationException;
import localhost.xplanner.soap.XPlanner.XPlanner;
import localhost.xplanner.soap.XPlanner.XPlannerServiceLocator;
import org.apache.axis.AxisProperties;
import org.apache.axis.client.Stub;
import org.xplanner.soap.PersonData;
import org.xplanner.soap.ProjectData;
import org.xplanner.soap.RoleData;

/**
 *
 * @author raul lopez
 */
public class XPlannerWSConnection implements XPlannerConnection {

    private static final Logger logger = Logger.getLogger(XplannerTool.class.getName());
    private XPlanner service;
    public static final String SoapServiceLocation = "soap/XPlanner";
    public static final String SocketSecureFactory = "axis.socketSecureFactory";
    // private XPProject reportStructure;

    /***
     * Performs an Xplanner addition task
     * @param <T> Instance type to be added to Xplanner.     *
     */
    interface XPlannerCreateOperation<T extends DomainData> {

        String Execute(T t) throws RemoteException;
    }
    private HashMap<String, XPlannerCreateOperation> xplannerCreateOps = new HashMap<String, XPlannerCreateOperation>();

    public XPlannerWSConnection() {
    }

    public XPlanner getService() {
        return service;
    }

    private void checkConfiguration(Map<String, String> config)
            throws ConfigurationException {
        //Iterate over required parameters and check them exist and not be null or empty value
        for (ParameterDescriptor param : XPlannerConfig.getRequiredParameters()) {
            String temp = config.get(param.getName());
            if (temp == null || temp.length() == 0) {
                throw new ConfigurationException("The param " + param.getName()
                        + "must be present and it cannot be empty");
            }
        }
    }

    public void applyConfiguration(Map<String, String> config)
            throws ConfigurationException {

        XPlannerServiceLocator serviceLocator;

        //Check arguments
        checkConfiguration(config);

        try {

            AxisProperties.setProperty(SocketSecureFactory, SSLSocketFactory.class.getName());

            // Create serviceLocator
            serviceLocator = new XPlannerServiceLocator();

            // build service URL
            String serviceUrl = appendServiceLocation(config.get(XPlannerConfig.ServiceUrl));

            service = serviceLocator.getXPlanner(new URL(serviceUrl));

            // Provide credentials
            ((Stub) service).setUsername(config.get(XPlannerConfig.User));
            ((Stub) service).setPassword(config.get(XPlannerConfig.Password));

        } catch (Exception e) {
            service = null;
            logger.log(Level.SEVERE, "Failed starting up the xplanner connection.", e);
            throw new ConfigurationException(e.getMessage());
        }
    }

    protected static String appendServiceLocation(String serverUrl) {
        StringBuilder serviceUrl = new StringBuilder(serverUrl);
        if (!serverUrl.endsWith("/")) {
            serviceUrl.append("/");
        }
        serviceUrl.append(SoapServiceLocation);
        return serviceUrl.toString();
    }

    /***
     *
     * Converts an object into its appropriate DomainData object instance.
     *
     * @param object Object that will be converted into an XPlanner Domain Data Object.
     * @return An appropriate instance of DomainData.
     * @throws IllegalArgumentException if the given object is null or it is not managed by the system.
     */
    private DomainData makeDomainDataObject(Object object) throws IllegalArgumentException {
        DomainData data = null;
        // Build domain object
        if (object instanceof Project) {
            Project project = (Project) object;
            data = new ProjectData(0, null, project.getDescription(), project.getName());
            logger.log(Level.INFO,
                    "Creating an ProjectData object with name:{0} description: {1}",
                    new Object[]{project.getName(), project.getDescription()});
        } else if (object instanceof User) {
            User user = (User) object;
            PersonData personData = new PersonData();

            personData.setName(user.getName());
            //personData.setEmail(user.getEmail());

            personData.setUserId(user.getLogin());

            data = personData;

        } else {
            throw new IllegalArgumentException(
                    "Object is null or does not match any of managed domain objects");
        }
        return data;
    }

    /**
     * Adds an object into XPlanner.
     *
     * @param object Object that will be inserted into XPlanner.
     * @return The object id converted into an string object
     * @throws IllegalArgumentException if the given object is null or it is not managed by the system.
     */
    @Override
    public String addObject(Object object) throws NoToolServerConnectionException, IllegalArgumentException {
        String objectId = "";
        XPlannerCreateOperation op = null;
        DomainData data = null;

        if (service == null) {
            throw new NoToolServerConnectionException(XPlannerConfig.ToolName, "There is no connection to XPlanner server");
        }

        data = makeDomainDataObject(object);

        // Choose execute method
        if (object instanceof Project) {
            op = xplannerCreateOps.get(Project.class.getName());
            if (op == null) {
                op = new XPlannerCreateOperation<ProjectData>() {

                    public String Execute(ProjectData t) throws RemoteException {
                        logger.log(Level.INFO, "calling to XPlanner.addProject");
                        return Integer.toString(service.addProject(t).getId());
                    }
                };
                xplannerCreateOps.put(Project.class.getName(), op);
            }
        }
        if (object instanceof User) {
            op = xplannerCreateOps.get(User.class.getName());
            if (op == null) {
                op = new XPlannerCreateOperation<PersonData>() {

                    public String Execute(PersonData t) throws RemoteException {
                        logger.log(Level.INFO, "calling to XPlanner.addPerson");
                        return Integer.toString(service.addPerson(t).getId());
                    }
                };
            }
        }

        if (op != null && data != null) {
            try {
                objectId = op.Execute(data);
            } catch (RemoteException ex) {
                logger.log(Level.WARNING, "Error calling Xplanner service", ex);
            }
        }
        return objectId;
    }

    public String createProject(Project project) throws NoToolServerConnectionException, Exception {
        return addObject(project);
    }

    @Override
    public void removeObject(String objectId, Class clazz) throws NoToolServerConnectionException {
        Integer id;
        String methodName = "";

        // Check connection
        if (service == null) {
            throw new NoToolServerConnectionException(XPlannerConfig.ToolName, "There is no connection to XPlanner server");
        }

        // Choose method to call
        if (clazz.getName().equals(edu.umss.devportal.common.User.class.getName())) {
            methodName = "removePerson";
        } else if (clazz.getName().equals(edu.umss.devportal.common.Project.class.getName())) {
            methodName = "removeProject";
        }

        try {
            id = Integer.parseInt(objectId);
            // Find interested method
            Method method = service.getClass().getMethod(methodName, int.class);

            // Call interested method
            method.invoke(service, id);
        } catch (SecurityException ex) {
            logger.log(Level.FINE, "SecurityException invoking method " + methodName, ex);
        } catch (NoSuchMethodException ex) {
            logger.log(Level.FINE, "Method " + methodName + " was not found at Xplanner class", ex);
        } catch (InvocationTargetException ex) {
            logger.log(Level.FINE, "", ex);
        } catch (NumberFormatException ex) {
            logger.log(Level.INFO, "Failed parsing objectId to integer", ex);
            StringBuilder sb = new StringBuilder("Given either objectId is null or it does not contain a parsable integer: ");
            if (objectId != null) {
                sb.append(objectId);
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            logger.log(Level.FINE, "IllegalArgumentException calling Xplanner." + methodName + " method", ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.FINE, "IllegalAccessException calling Xplanner." + methodName + " method", ex);
        }
    }

    public List<Project> getProjectList() {
        List<Project> projects = new ArrayList<Project>();

        if (service != null) {
            try {
                ProjectData[] projectsRawData = service.getProjects();
                for (ProjectData projectData : projectsRawData) {
                    BasicProjectImpl project = new BasicProjectImpl();
                    project.setId(Integer.toString(projectData.getId()));
                    project.setName(projectData.getName());
                    project.setDescription(projectData.getDescription());
                    projects.add(project);
                }
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, "Can't recover the Project list. ", ex);
            }
        }

        return projects;
    }

    public List<User> getUserList() {
        List<User> users = new ArrayList<User>();

        if (service != null) {
            try {
                PersonData[] peopleRawData = service.getPeople();
                for (PersonData personData : peopleRawData) {
                    BasicUserImpl user = new BasicUserImpl();
                    user.setName(personData.getName());
                    user.setLogin(personData.getUserId());
                    users.add(user);
                }
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, "Can't recover the User list. ", ex);
            }
        } else {
            Map<String, String> config = new HashMap<String, String>();
            config.put("serviceURL", "http://localhost:9080/xplanner/");
            config.put("user", "sysadmin");
            config.put("password", "admin");
            try {
                applyConfiguration(config);

                try {
                    PersonData[] peopleRawData = service.getPeople();
                    for (PersonData personData : peopleRawData) {
                        BasicUserImpl user = new BasicUserImpl();
                        user.setName(personData.getName());
                        user.setLogin(personData.getUserId());
                        users.add(user);
                    }
                } catch (RemoteException ex) {
                    logger.log(Level.SEVERE, "Can't recover the User list. ", ex);
                }
            } catch (ConfigurationException ex) {
                Logger.getLogger(XPlannerWSConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return users;
    }

    /**
     * @see XPlannerConnection#associateUserToProject(java.lang.String, java.lang.String, edu.umss.devportal.common.ProjectRole)
     */
    public void associateUserToProject(String projectId, String userId, ProjectRole projectRole)
            throws NoToolServerConnectionException {
        if (service == null) {
            throw new NoToolServerConnectionException(XPlannerConfig.ToolName, "There is no connection to XPlanner server");
        }

        RoleData[] roles;
        String roleStr = "none";
        int roleId = 0;

        switch (projectRole) {
            case TeamMember:
                roleStr = "editor";
                break;
            case Manager:
                roleStr = "admin";
                break;
        }

        try {
            roles = service.getRoles();

            for (RoleData role : roles) {
                if (role.getName().equals(roleStr)) {
                    roleId = role.getId();
                }
            }

            if (roleId != 0) {
                service.addRoleProject(Integer.parseInt(projectId), Integer.parseInt(userId), roleId);
            }

        } catch (RemoteException ex) {
            logger.log(Level.SEVERE, "Can't recover the User list. ", ex);
        }

    }

    @Override
    public ReportProject getReportProject(int projectId) throws NoToolServerConnectionException {
        if (service == null) {
            throw new NoToolServerConnectionException(XPlannerConfig.ToolName, "There is no connection to XPlanner server");
        }

        ReportProject project = null;

        try {
            project = new ReportProject(service, service.getProject(projectId));
        } catch (RemoteException ex) {
            Logger.getLogger(XPlannerWSConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return project;
    }
}
