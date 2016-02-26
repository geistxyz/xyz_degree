/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins;

import edu.umss.devportal.common.ToolPlugin;
import edu.umss.devportal.utils.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 *
 * @author Roger Ayaviri
 */
public class ToolManager {

    public static <T extends ToolPlugin> T getToolByName(String name, String version, Class<T> className) {

        ServiceLoader<T> serviceLoader = ServiceLoader.load(className);
        Iterator<T> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (name.equalsIgnoreCase(t.getToolDescriptor().getName())
                    && version.equalsIgnoreCase(t.getToolDescriptor().getVersion())) {
                return t;
            }
        }
        return null;
    }

    public static <T extends ToolPlugin> ServiceLoader<T> getToolByType(Class<T> className) {

        ServiceLoader<T> serviceLoader = ServiceLoader.load(className);

        return serviceLoader;
    }

    public static <T extends ToolPlugin> T getToolByName(String name, String version) {

        HashMap<String, Class> hashMap = Variable.getToolTypes();

        for (Map.Entry<String, Class> attr : hashMap.entrySet()) {
            T t = (T) getToolByName(name, version, attr.getValue());
            if (t != null) {
                return t;
            }

        }

        return null;
    }

    public static <T extends ToolPlugin> List<T> getAllTool() {

        List<T> list = new ArrayList<T>();
        HashMap<String, Class> hashMap = Variable.getToolTypes();

        for (Map.Entry<String, Class> attr : hashMap.entrySet()) {
            ServiceLoader<T> serviceLoader = ServiceLoader.load(attr.getValue());
            Iterator<T> iterator = serviceLoader.iterator();
            while (iterator.hasNext()) {
                T t = iterator.next();
                list.add(t);
            }
        }
        return list;
    }

}
