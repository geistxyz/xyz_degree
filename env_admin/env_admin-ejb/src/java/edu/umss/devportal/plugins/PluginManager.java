/*
 *  @(#)PluginManager.java   21-nov-2010
 */

package edu.umss.devportal.plugins;

import edu.umss.devportal.common.Tool;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.plugins.exceptions.PluginDescriptorNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginDoesNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginManagerNotFoundException;
import java.util.List;

/**
 * Class that permits manage all system plugins.
 *
 * First an or many ToolPluginManager(s) should be loaded, and then using the name
 * assigned, all plugins can be manage, using the next two plugin types.
 *
 * <p>A <b>installed plugin</b> is a plugins that was implemented and deployed with the
 * system, but not necesarily used with the syste.  (it dependes of the system
 * configuration)</p>
 *
 * <p>A <b>loaded plugin</b> is a plugin that furthermore be installed with the
 * system, it was loaded for be used in a given configuration.</p>
 *
 * @author Alex Arenas
 * @version 1.0
 */
public interface PluginManager {

    /**
     * Permits load a ToolPluginManager.  This is necesary before call other
     * methods of the implementation of this interface due to this object is
     * the plugin manager of a plugin type.
     *
     * @param <T> Plugin type.
     * @param name ToolPluginManager's name that will be used from now on.
     * @param toolPluginClass class (interface) that represents the plugin.
     *          it should extend <code>ToolPlugin</code>.
     */
    public <T extends ToolPlugin> void loadToolPluginManager(
            String pluginManagerName, Class<T> toolPluginClass);

    /**
     * Permits load a plugin in a given ToolPluginManager.  The ToolPluginManager
     * needs to be loaded previously.
     *
     * @param toolPluginManager ToolPluginManager's name.
     * @param pluginName plugin's name
     * @return true if the plugin was loaded, false if it already existed.
     * @throws PluginManagerNotFoundException if the ToolPluginManager was not
     *          loaded previously.
     * @throws PluginDescriptorNotFoundException if the PluginDescriptor of a
     *          plugin implementations does not exist.
     * @throws PluginDoesNotFoundException if the plugin was not find.
     */
    public boolean configureTool(String pluginManagerName, String pluginName,
            Tool tool) throws PluginManagerNotFoundException,
            PluginDescriptorNotFoundException, PluginDoesNotFoundException;

    /**
     * Gets all installed plugins of all ToolPluginManager's.
     *
     * @return a list of all installed plugins.
     */
    public List<ToolPlugin> getAllInstalledPlugins();

    public List<ToolPlugin> getInstalledPlugins(String pluginManagerName)
            throws PluginManagerNotFoundException;

    /**
     * @return a list of all installed ToolDescriptors of all ToolPluginManager's.
     */
    public List<ToolDescriptor> getAllInstalledToolDescriptors();

    public List<ToolDescriptor> getInstalledToolDescripors(String pluginManagerName)
            throws PluginManagerNotFoundException;

    /**
     * @return a list of all loaded plugins of all ToolPluginManager's.  This
     *          plugins are the availables plugins of a given configuration.
     */
    public List<ToolPlugin> getAllConfiguredTools();

    /**
     * Permits get a list of all ToolPlugins managed by a ToolPluginManager
     * which name is passed as argument.
     *
     * @param toolPluginManager toolPluginManager's name.  This name is give in
     *          the moment that a new ToolPluginManager is loaded.
     * @return a list of all installed plugins of the ToolPluginManager gave.
     * @throws PluginManagerNotFoundException if the ToolPluginManager is not
     *          loaded.
     */
    public List<ToolPlugin> getConfiguredTools(String pluginManagerName)
            throws PluginManagerNotFoundException;

    public List<ToolPlugin> getConfiguredTools(String pluginManagerName, String pluginName)
            throws PluginManagerNotFoundException, PluginDoesNotFoundException;

    public ToolPlugin getCconfiguredTool(String pluginManagerName, String pluginName, String toolName)
            throws PluginManagerNotFoundException, PluginDoesNotFoundException;    

}
