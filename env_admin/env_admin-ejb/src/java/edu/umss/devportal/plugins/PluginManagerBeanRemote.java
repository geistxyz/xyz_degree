/*
 *  @(#)PluginManagerBeanRemote.java   07-jan-2011
 */

package edu.umss.devportal.plugins;

import edu.umss.devportal.common.Tool;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.plugins.exceptions.PluginDescriptorNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginDoesNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginManagerNotFoundException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Alex Arenas
 * @version 1.0
 */
@Remote
public interface PluginManagerBeanRemote {

    void loadFromDatabase();

    public void loadToolPluginManager(String pluginManagerName, Class<ToolPlugin> toolPluginClass);

    public boolean configureTool(String pluginManagerName, String pluginName, Tool tool)
            throws PluginManagerNotFoundException, PluginDescriptorNotFoundException,
            PluginDoesNotFoundException;

    public List<ToolPlugin> getAllInstalledPlugins();

    public List<ToolPlugin> getInstalledPlugins(String toolPluginManager)
            throws PluginManagerNotFoundException;

    public List<ToolDescriptor> getAllInstalledToolDescriptors();

    public List<ToolDescriptor> getInstalledTooldescriptors(String toolPluginManager)
            throws PluginManagerNotFoundException;

    public List<ToolPlugin> getAllConfiguredTools();

    public List<ToolPlugin> getConfiguredToolsFor(String pluginmanagerName)
            throws PluginManagerNotFoundException;

    public List<ToolPlugin> getConfiguredTools(String pluginManagerName, String pluginName)
            throws PluginManagerNotFoundException, PluginDoesNotFoundException;

    public ToolPlugin getConfigureTool(String pluginManagerName, String pluginName, String toolname)
            throws PluginManagerNotFoundException, PluginDoesNotFoundException;
}
