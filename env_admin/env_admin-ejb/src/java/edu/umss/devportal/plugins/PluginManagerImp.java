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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex Arenas
 * @version 1.0
 * @see PluginManager
 */
public class PluginManagerImp implements PluginManager{

    private Map<String, ToolPluginManager<? extends ToolPlugin>> pluginManagerMap;

    /**
     * Default constructor.
     */
    public PluginManagerImp(){
        pluginManagerMap = new HashMap<String, ToolPluginManager<? extends ToolPlugin>>();
    }

    /**
     * @see PluginManager#loadToolPluginManager
     */
    @Override
    public <T extends ToolPlugin> void loadToolPluginManager
            (String pluginManagerName, Class<T> toolPluginClass){
        ToolPluginManager<T> pManager = new ToolPluginManagerImp<T>(toolPluginClass);
        pluginManagerMap.put(pluginManagerName, pManager);
    }

    /**
     * @see PluginManager#getAllInstalledPlugins()
     */
    @Override
    public List<ToolPlugin> getAllInstalledPlugins(){
        List<ToolPlugin> installedPlugins = new ArrayList<ToolPlugin>();
        for (ToolPluginManager<? extends ToolPlugin> toolPluginManager : pluginManagerMap.values()) {
            for (ToolPlugin toolPlugin : toolPluginManager.getInstalledPlugins()) {
                installedPlugins.add(toolPlugin);
            }
        }
        return installedPlugins;
    }

    @Override
    public List<ToolPlugin> getInstalledPlugins(String pluginManagerName)
            throws PluginManagerNotFoundException{
        if(!pluginManagerMap.containsKey(pluginManagerName))
            throw new PluginManagerNotFoundException();

        ToolPluginManager tpm = pluginManagerMap.get(pluginManagerName);
        return tpm.getInstalledPlugins();
    }

    /**
     * @see PluginManager#getAllInstalledToolDescriptors()
     */
    @Override
    public List<ToolDescriptor> getAllInstalledToolDescriptors(){
        List<ToolDescriptor> toolDescriptors = new ArrayList<ToolDescriptor>();
        for (ToolPlugin toolPlugin : getAllInstalledPlugins()) {
            toolDescriptors.add(toolPlugin.getToolDescriptor());
        }
        return toolDescriptors;
    }

    @Override
    public List<ToolDescriptor> getInstalledToolDescripors(String pluginManagerName)
            throws PluginManagerNotFoundException{
        List<ToolDescriptor> toolDescriptors = new ArrayList<ToolDescriptor>();
        for (ToolPlugin plugin : getInstalledPlugins(pluginManagerName)) {
            toolDescriptors.add(plugin.getToolDescriptor());
        }

        return toolDescriptors;
    }

    /**
     * @see PluginManager#getAllConfiguredTools()
     */
    @Override
    public List<ToolPlugin> getAllConfiguredTools(){
        List<ToolPlugin> configuredPlugins = new ArrayList<ToolPlugin>();
        for (ToolPluginManager<? extends ToolPlugin> toolPluginManager : pluginManagerMap.values()) {
            for (ToolPlugin toolPlugin : toolPluginManager.getAllConfiguredTools()) {
                configuredPlugins.add(toolPlugin);
            }
        }
        return configuredPlugins;
    }

    /**
     * @see PluginManager#getConfiguredTools(java.lang.String)
     */
    @Override
    public List<ToolPlugin> getConfiguredTools(String pluginManagerName) throws PluginManagerNotFoundException{
        if(!pluginManagerMap.containsKey(pluginManagerName))
            throw new PluginManagerNotFoundException();

        ToolPluginManager tpm = pluginManagerMap.get(pluginManagerName);
        return tpm.getAllConfiguredTools();
    }

    @Override
    public List<ToolPlugin> getConfiguredTools(String pluginManagerName, String pluginName)
            throws PluginManagerNotFoundException, PluginDoesNotFoundException {
        if(!pluginManagerMap.containsKey(pluginManagerName))
            throw new PluginManagerNotFoundException();

        ToolPluginManager tpm = pluginManagerMap.get(pluginManagerName);
        return tpm.getConfiguredTools(pluginName);
    }

    @Override
    public ToolPlugin getCconfiguredTool(String pluginManagerName, String pluginName, String toolName)
            throws PluginManagerNotFoundException, PluginDoesNotFoundException {
        if(!pluginManagerMap.containsKey(pluginManagerName))
            throw new PluginManagerNotFoundException();

        ToolPluginManager tpm = pluginManagerMap.get(pluginManagerName);
        return tpm.getConfiguredTool(pluginName, toolName);
    }

    /**
     * @see PluginManager#configureTool(java.lang.String, java.lang.String)
     */
    @Override
    public boolean configureTool(String pluginManagerName, String pluginName, Tool tool) throws PluginManagerNotFoundException, PluginDescriptorNotFoundException, PluginDoesNotFoundException{
        if(!pluginManagerMap.containsKey(pluginManagerName))
            throw new PluginManagerNotFoundException();

        ToolPluginManager pManager = pluginManagerMap.get(pluginManagerName);
        return pManager.configureTool(pluginName, tool);
    }
}
