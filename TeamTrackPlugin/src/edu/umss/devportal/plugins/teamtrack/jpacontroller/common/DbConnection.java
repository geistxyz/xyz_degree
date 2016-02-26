/*
 *  @(#)DbConnection.java   09-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller.common;

import edu.umss.devportal.plugins.teamtrack.TeamTrackTool;
import edu.umss.edu.devportal.exception.MissingParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.naming.ConfigurationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alex Arenas
 */
public class DbConnection {

    private static final Logger logger = Logger.getLogger(DbConnection.class.getName());

    private EntityManagerFactory emf = null;    

    // database configuration
    private Map<String, String> configuration;

    // unique instance of this class.
    private static DbConnection instance;

    /**
     * Default constructor.
     */
    private DbConnection(){
    }

    /**
     * @return the unique instance of this class.
     */
    public static DbConnection getInstance(){
        if(instance == null) instance = new DbConnection();
        return instance;
    }

    /**
     * Permits create a default entity manager.
     *
     * @return the default entity manager.
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Permits apply the configuration to connect to database.
     *
     * @param config configuration object that contains all (key, value) pairs
     *          to configure the database connection.
     * @throws ConfigurationException if the configuration has errors.
     */
    public void applyConfiguration(Map<String, String> config)
            throws MissingParameterException, ConfigurationException{

        try{
            checkConfiguration(config);
        }catch(MissingParameterException e){
            throw new ConfigurationException(e.getMessage());
        }
        
        this.configuration = new HashMap<String, String>();
        String url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s",
                config.get(TeamTrackTool.DATABASE_HOST_KEY),
                config.get(TeamTrackTool.DATABASE_PORT_KEY),
                config.get(TeamTrackTool.DATABASE_NAME_KEY));

        configuration.put("javax.persistence.jdbc.driver",
                "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        configuration.put("javax.persistence.jdbc.url", url);
        configuration.put("javax.persistence.jdbc.user",
                config.get(TeamTrackTool.USER_NAME_KEY));
        configuration.put("javax.persistence.jdbc.password",
                config.get(TeamTrackTool.USER_PASSWORD_KEY));

        emf = Persistence.createEntityManagerFactory("TeamTrackPluginPU", configuration);
    }

    /**
     * check all configuration.
     *
     * @param config configation object that contains all properties.
     * @throws ConfigurationException if the co figuration has errors.
     */
    public static void checkConfiguration(Map<String, String> config)
            throws MissingParameterException {

        if (config == null)
            throw new NullPointerException("The configuration object cannot be null");

        checkConfiguration(config, TeamTrackTool.DATABASE_HOST_KEY, false);
        checkConfiguration(config, TeamTrackTool.DATABASE_PORT_KEY, false);
        checkConfiguration(config, TeamTrackTool.DATABASE_NAME_KEY, false);
        checkConfiguration(config, TeamTrackTool.USER_NAME_KEY, false);
        checkConfiguration(config, TeamTrackTool.USER_PASSWORD_KEY, true);
    }

    /**
     * Checks a configuration property.
     *
     * @param config configuration object that contains all configuration.
     * @param propName configuration property name.
     * @param canBeNull true if the value of property can be null, false in
     *                  other case
     * @throws ConfigurationException if the configuration property is not correct.
     */
    private static void checkConfiguration(Map<String, String> config,
            String propName, boolean canBeNull) throws MissingParameterException {

        if (!config.containsKey(propName)) {
            throw new MissingParameterException("TeamTrack Tool", propName);
        } else {
            if (!canBeNull && config.get(propName) == null) {
                String msg = "The property " + propName + " cannot be null";
                logger.warning(msg);
                throw new MissingParameterException("TeamTrack Tool", propName);
            }
        }
    }
}
