/*
 *  @(#)ToolPluginManagerImp.java   21-nov-2010
 */

package edu.umss.devportal.plugins;

import edu.umss.devportal.common.Tool;
import edu.umss.devportal.common.ToolDescriptor;
import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.plugins.data.ToolPluginKey;
import edu.umss.devportal.plugins.exceptions.PluginDescriptorNotFoundException;
import edu.umss.devportal.plugins.exceptions.PluginDoesNotFoundException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ToolPluginManager default implementation.
 *
 * @author Alex Arenas
 * @version  1.0
 * @see ToolPluginManager
 */
public class ToolPluginManagerImp<T extends ToolPlugin> implements ToolPluginManager<T>{

    private static final Logger logger = Logger.getLogger(ToolPluginManager.class.toString());

    private ServiceLoader<T> serviceLoader;
    private Map<ToolPluginKey, T> configuredTools;

    /**
     * Default constructor.
     */
    public ToolPluginManagerImp(Class<T> pluginClass){
        configuredTools = new HashMap<ToolPluginKey, T>();
        serviceLoader = ServiceLoader.load(pluginClass);
    }

    /**
     * @see ToolPluginManager#installedPlugins()
     */
    @Override
    public List<T> getInstalledPlugins() {
        logger.info("Get all installed plugins");

        List<T> list = new ArrayList<T>();
        for (T obj : serviceLoader) {
            if(obj != null)
                list.add(obj);
        }
        return list;
    }

    /**
     * @see ToolPluginManager#installedToolDescriptors()
     */
    @Override
    public List<ToolDescriptor> getInstalledToolDescriptors() throws NullPointerException{
        logger.info("Get all installed tool descriptors");

        List<T> pluginList = new ArrayList<T>();
        List<ToolDescriptor> descriptorList = new ArrayList<ToolDescriptor>();

        for (ToolPlugin toolPlugin : pluginList) {
            descriptorList.add(toolPlugin.getToolDescriptor());
        }

        return descriptorList;
    }

    /**
     * @see ToolPluginManager#loadPlugin(java.lang.String)
     */
    @Override
    public boolean configureTool(String pluginName, Tool tool) throws PluginDescriptorNotFoundException, PluginDoesNotFoundException{
        logger.log(Level.INFO, "Load plugin: {0} for tool " + tool.getName(), pluginName);

        if(pluginName == null)
            throw new NullPointerException("The param name is null");

        T plugin = null;
        for (T toolPlugin : getInstalledPlugins()) {
            try{
                if(toolPlugin.getToolDescriptor().getName().equals(pluginName))
                    plugin = toolPlugin;
            }catch(NullPointerException e){
                logger.log(Level.WARNING, "Does not exist plugin descriptor for " + toolPlugin, e);
                throw new PluginDescriptorNotFoundException();
            }
        }

        if(plugin == null) throw new PluginDoesNotFoundException();

        logger.info("Plugin loaded sucessfull");
        ToolPluginKey key = new ToolPluginKey(pluginName, tool.getName());
        if(!configuredTools.containsKey(key)){
            T clonePlugin = plugin; //TODO clone the plugin
            //TODO add tool information to plugin
            configuredTools.put(key, clonePlugin);
            return true;
        }
        return false;
    }

    /**
     * @see ToolPluginManager#loadedPlugins()
     */
    @Override
    public List<T> getAllConfiguredTools() {
        return new ArrayList<T>(configuredTools.values());
    }

    @Override
    public List<T> getConfiguredTools(String pluginName) throws PluginDoesNotFoundException{
        List<T> answerList = new ArrayList<T>();

        for (ToolPluginKey key : configuredTools.keySet()) {
            if(key.getPluginName().equals(pluginName))
                answerList.add(configuredTools.get(key));
        }

        return answerList;
    }

    @Override
    public T getConfiguredTool(String pluginName, String toolName) throws PluginDoesNotFoundException{
        ToolPluginKey key = new ToolPluginKey(pluginName, toolName);
        if(!configuredTools.containsKey(key))
            throw new PluginDoesNotFoundException();

        return configuredTools.get(key);
    }

    /**
     * @see Iterator#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        return configuredTools.values().iterator();
    }
}
