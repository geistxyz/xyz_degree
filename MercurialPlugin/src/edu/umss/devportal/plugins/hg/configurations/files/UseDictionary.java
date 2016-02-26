/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.hg.configurations.files;

import edu.umss.devportal.plugins.hg.utils.DictionarySections;

/**
 *
 * @author Arminda Yovana Soto
 */
public class UseDictionary {

    DictionarySections dictionary;

    public UseDictionary() {
        dictionary = new DictionarySections();
    }
    public void addWords(String key,String value){
        dictionary.put(key, value);
    }
    public void deleteWords(String key){
        dictionary.remove(key);
    }
    
}
