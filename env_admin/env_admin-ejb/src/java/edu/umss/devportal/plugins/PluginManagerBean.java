/*
 *  @(#)PluginManagerBean.java   07-jan-2011
 */

package edu.umss.devportal.plugins;

import edu.umss.devportal.common.Tool;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.plugins.exceptions.PluginDescriptorNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginDoesNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginManagerNotFoundException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Alex Arenas
 * @version  1.0
 */
@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class PluginManagerBean implements PluginManagerBeanRemote {

    private PluginManager pluginManager;

    @PostConstruct
    @Override
    public void loadFromDatabase() {
        pluginManager = new PluginManagerImp();
        //TODO Load information from database and load plugins in plugin manager
        //Load plugins and load in the plugin manager
        // pluginManager.loadToolPluginManager("projectTracker", ProjectTracker.class); ...
        //Load tools and configure in plugin manager
        //pluginManager.configureTool("projectTracker", "XplannerPlugin", tool);
    }

    @Override
    public void loadToolPluginManager(String pluginManagerName, Class<ToolPlugin> toolPluginClass){
        pluginManager.loadToolPluginManager(pluginManagerName, toolPluginClass);
    }

    @Override
    public boolean configureTool(String pluginManagerName, String pluginName, Tool tool)
            throws PluginManagerNotFoundException, PluginDescriptorNotFoundException,
            PluginDoesNotFoundException{
        return pluginManager.configureTool(pluginManagerName, pluginName, tool);
    }

    @Override
    public List<ToolPlugin> getAllInstalledPlugins(){
        return pluginManager.getAllInstalledPlugins();
    }

    @Override
    public List<ToolPlugin> getInstalledPlugins(String toolPluginManager)
            throws PluginManagerNotFoundException{
        return pluginManager.getInstalledPlugins(toolPluginManager);
    }

    @Override
    public List<ToolDescriptor> getAllInstalledToolDescriptors(){
        return pluginManager.getAllInstalledToolDescriptors();
    }

    @Override
    public List<ToolDescriptor> getInstalledTooldescriptors(String toolPluginManager)
            throws PluginManagerNotFoundException{
        return pluginManager.getInstalledToolDescripors(toolPluginManager);
    }

    @Override
    public List<ToolPlugin> getAllConfiguredTools(){
        return pluginManager.getAllConfiguredTools();
    }

    @Override
    public List<ToolPlugin> getConfiguredToolsFor(String pluginmanagerName)
            throws PluginManagerNotFoundException{
        return pluginManager.getConfiguredTools(pluginmanagerName);
    }

    @Override
    public List<ToolPlugin> getConfiguredTools(String pluginManagerName, String pluginName) 
            throws PluginManagerNotFoundException, PluginDoesNotFoundException{
        return pluginManager.getConfiguredTools(pluginManagerName, pluginName);
    }

    @Override
    public ToolPlugin getConfigureTool(String pluginManagerName, String pluginName, String toolname)
            throws PluginManagerNotFoundException, PluginDoesNotFoundException{
        return pluginManager.getCconfiguredTool(pluginManagerName, pluginName, toolname);
    }    
}
