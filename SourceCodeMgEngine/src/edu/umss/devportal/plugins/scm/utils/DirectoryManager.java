/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Manages the files and returns string not files
 * @author Arminda Yovana Soto
 */
public class DirectoryManager {

    private static final File TMP_ROOT = new File(System.getProperty("java.io.tmpdir"));
    private static long fileSuffix;

    public static String getSystemTempDirectory() {
        return TMP_ROOT.getName();
    }

    /**
     * @return a newly created temp directory which is located inside the default temp directory
     */
    public static File createNewTempDirectory() {
        File tmp = new File(getSystemTempDirectory());
        File newTemp = null;
        while (!(newTemp = new File(tmp, "hgTemp_" + fileSuffix)).mkdir()) {
            fileSuffix++;
        }
        return newTemp;
    }

    /**
     * Search a directory if it exist return it, else create a new directory in the indicated path
     * @param source the root of the new directory
     * @return localFile the new directory
     */
    public static String createDirectory(String source) {
        File localFile = null;
        File sourc=new File(source);
        sourc.mkdir();
        localFile = new File(sourc.getAbsolutePath());
        return localFile.getAbsolutePath();
    }

    /**
     *Returns a file of indicated
     * @param parent file parent path
     * @param source name of file
     * @return a specific file
     */
    public static File getFile(File parent, String source) {
        File response = null;
        String root = parent.getAbsolutePath();
        File tempRoot = new File(root.concat(StringUtilities.FILE_SEPARATOR.getValue()).concat(source));
        if (tempRoot.exists()) {
            response = tempRoot;
        }
        return response;
    }

    /**
     * Search the file in the rootPath indicated
     * @param rootPath absolute path of file
     * @return if find returns this file else return null value
     */
    public static String search(String rootPath) {
        File file = new File(rootPath);
        String response = null;
        if (file.exists()) {
            response = file.getAbsolutePath();

        }
        return response;
    }

    /**
     * Deletes a directory recursive form, before verifies if it exist, if this exist then is deleted and return a
     * successfully message, else returns a file not exist message
     * @param source the root path of directory
     * @param recursive if true deletes all files into directory
     * @return true if deletes process are execute successfully
     */
    public static boolean delete(File source, boolean recursive) {
        if (source == null || !source.exists()) {
            return true;
        }
        if (recursive) {
            if (source.isDirectory()) {
                for (File file : source.listFiles()) {
                    boolean ok = delete(file, true);
                    if (!ok) {
                        return false;
                    }
                    Logger.getLogger(DirectoryManager.class.getName()).log(Level.INFO,
                            "repository deleted");
                }
            }
        }
        boolean result = source.delete();
        if (!result && !source.isDirectory()) {

            Logger.getLogger(DirectoryManager.class.getName()).log(Level.SEVERE,
                    "Directory not found");
        }
        return result;
    }
}
