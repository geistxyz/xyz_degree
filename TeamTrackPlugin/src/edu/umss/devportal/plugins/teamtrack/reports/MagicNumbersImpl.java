/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.reports;

import edu.umss.devportal.common.reports.MagicNumberData;
import edu.umss.devportal.common.reports.MagicNumbers;
import edu.umss.devportal.common.reports.MagicNumbersStructure;
import java.util.List;

/**
 *
 * @author July Camacho
 */
public class MagicNumbersImpl implements MagicNumbers {

    /**
     *
     * @param projectId
     * @param severities
     * @return
     */
    public MagicNumbersStructure getMagicNumbers(String projectId, List<MagicNumberData> severities) {

        MagicNumbersStructure magicNumberStructure = new MagicNumbersSructureImp();
        MagicNumbersReport magicNumbersReport = new MagicNumbersReport();
        magicNumbersReport.initialize(projectId,severities);
        magicNumbersReport.processCriteria(magicNumberStructure);
        magicNumbersReport.processStatus(magicNumberStructure);
        magicNumbersReport.processMagicNumbers(magicNumberStructure);
        magicNumbersReport.processDevMagic(magicNumberStructure);
        magicNumbersReport.processFullFilledSeverity(magicNumberStructure);
        return magicNumberStructure;
    }
    /**
     *
     * @param sevName
     * @return
     */
    public List<MagicNumberData> getSeverities(String sevName) {

        MagicNumbersReport magicNumberReport = new MagicNumbersReport();
        return magicNumberReport.getSeverities(sevName);
    }
    /**
     * 
     * @return
     */
    public List<MagicNumberData> getSeverities() {

        MagicNumbersReport magicNumberReport = new MagicNumbersReport();
        return magicNumberReport.getSeverities();
    }

}
