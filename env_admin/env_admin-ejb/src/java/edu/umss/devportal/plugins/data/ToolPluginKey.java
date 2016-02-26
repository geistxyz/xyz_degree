/*
 *  @(#)ToolPluginManagerImp.java   05-jan-2011
 */

package edu.umss.devportal.plugins.data;

/**
 *
 * @author Alex Arenas
 */
public class ToolPluginKey implements Comparable<ToolPluginKey>{

    private String pluginName;
    private String toolId;

    public ToolPluginKey(String pluginName, String toolId) {
        this.pluginName = pluginName;
        this.toolId = toolId;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    @Override
    public int compareTo(ToolPluginKey toolPluginData) {
        int result = pluginName.compareTo(toolPluginData.getPluginName());
        if(result == 0){
            return toolId.compareTo(toolPluginData.getToolId());
        }
        return result;
    }

    @Override
    public boolean equals(Object plugin){
        return compareTo((ToolPluginKey)plugin) == 0;
    }
}
