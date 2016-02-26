/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.utils;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Arminda Yovana Soto
 */
public class DictionarySections extends Dictionary<String, String> {

    Map<String, String> dictionary;

    public DictionarySections() {
        dictionary = new HashMap<String, String>();
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isEmpty() {
        if (dictionary.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Enumeration<String> keys() {
        return (Enumeration<String>) dictionary.keySet();
    }

    @Override
    public Enumeration<String> elements() {
        return (Enumeration<String>) dictionary.values();
    }

    @Override
    public String get(Object key) {
        return dictionary.get(key);
    }

    @Override
    public String put(String key, String value) {
        return dictionary.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return dictionary.remove(key);
    }
}
