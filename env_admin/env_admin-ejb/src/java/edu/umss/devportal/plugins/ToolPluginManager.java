/*
 *  @(#)ToolPluginManager.java   21-nov-2010
 */

package edu.umss.devportal.plugins;

import edu.umss.devportal.common.Tool;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.plugins.exceptions.PluginDescriptorNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginDoesNotFoundException;
import java.util.List;

/**
 * <p>Class that permits manage the installed and loaded plugins of a plugin type.
 * This class provides the necesary functionality to load a plugins and get all
 * installed and loaded plugins.</p>
 *
 * <p>A <b>installed plugin</b> is a plugins that was implemented and deployed 
 * with the system, but not necesarily used with the syste.  (it dependes of
 * the system configuration)</p>
 *
 * <p>A <b>loaded plugin</b> is a plugin that furthermore be installed with the
 * system, it was loaded for be used in a given configuration.</p>
 *
 * @author Alex Arenas
 * @version 1.0
 */
public interface ToolPluginManager<T extends ToolPlugin> extends Iterable<T>{

    /**
     * Method that permits get all installed plugins implementations of this
     * plugin type.
     *
     * @return all installed plugins.
     */
    public List<T> getInstalledPlugins();

    /**
     * Permits get all installed plugins's descriptors of this plugin 
     * implementation.
     *
     * @return all plugin descriptors of installed plugins.
     */
    public List<ToolDescriptor> getInstalledToolDescriptors();

    /**
     * Permits load a plugins and make available for the system.
     *
     * @param pluginName plugin's name
     * @param toolId tool identifier for which the plugin should be configured.
     * @return true if the plugin was loaded sucesffull, false if not (generally
     *          becouse it was already loaded before.
     * @throws PluginDescriptorNotFoundException if some plugin descriptor is not
     *          found.
     * @throws PluginDoesNotFoundException if the plugin <code>name</code> is
     *         not installed in the system.
     */
    public boolean configureTool(String pluginName, Tool tool)
            throws PluginDescriptorNotFoundException, PluginDoesNotFoundException;

    /**
     * Permtis get all configured plugins of the current plugin type.
     *
     * @return all loaded plugins.
     */
    public List<T> getAllConfiguredTools();

    public List<T> getConfiguredTools(String pluginName) throws PluginDoesNotFoundException;

    public T getConfiguredTool(String pluginName, String toolName) throws PluginDoesNotFoundException;
}
