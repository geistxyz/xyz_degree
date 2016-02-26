/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

import java.util.List;

/**
 *
 * @author July Camacho
 */
public interface MagicNumbers {
    public MagicNumbersStructure getMagicNumbers(String projectId, List<MagicNumberData> severities);
    public List<MagicNumberData> getSeverities(String sevName);
    public List<MagicNumberData> getSeverities();
}
