/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.dummy;

import edu.umss.devportal.common.reports.MagicNumberData;
import edu.umss.devportal.common.reports.MagicNumbers;
import edu.umss.devportal.common.reports.MagicNumbersStructure;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author july camacho
 */
public class MagicNumbersImpl implements MagicNumbers{

    public MagicNumbersStructure getMagicNumbers(String projectId, List<MagicNumberData> severities) {
         MagicNumbersStructure magicNumbersStructure = new MagicNumbersSructureImp();

        List<MagicNumberData> criteria = new ArrayList<MagicNumberData>();
        List<MagicNumberData> status = new ArrayList<MagicNumberData>();
        List<MagicNumberData> magicNumber = new ArrayList<MagicNumberData>();
        List<MagicNumberData> devMagic = new ArrayList<MagicNumberData>();
        List<MagicNumberData> fulfilledSeverity = new ArrayList<MagicNumberData>();

        criteria.add(severities.get(0));
        criteria.add(severities.get(1));
        criteria.add(severities.get(2));
        criteria.add(severities.get(3));

        MagicNumberData status1 = new MagicNumberDataImp();
        status1.setKey("Status1");
        status1.setValue(80);

        MagicNumberData status2 = new MagicNumberDataImp();
        status2.setKey("Status2");
        status2.setValue(90);

        MagicNumberData status3 = new MagicNumberDataImp();
        status3.setKey("Status3");
        status3.setValue(71);

        MagicNumberData status4 = new MagicNumberDataImp();
        status4.setKey("Status4");
        status4.setValue(75);

        status.add(status1);
        status.add(status2);
        status.add(status3);
        status.add(status4);

        MagicNumberData mn1 = new MagicNumberDataImp();
        mn1.setKey("MagicNumber1");
        mn1.setValue(3);

        MagicNumberData mn2 = new MagicNumberDataImp();
        mn2.setKey("MagicNumber2");
        mn2.setValue(4);

        MagicNumberData mn3 = new MagicNumberDataImp();
        mn3.setKey("MagicNumber3");
        mn3.setValue(0);

        MagicNumberData mn4 = new MagicNumberDataImp();
        mn4.setKey("MagicNumber4");
        mn4.setValue(0);

        magicNumber.add(mn1);
        magicNumber.add(mn2);
        magicNumber.add(mn3);
        magicNumber.add(mn4);

        MagicNumberData dm1 = new MagicNumberDataImp();
        dm1.setKey("DevMagic1");
        dm1.setValue(3);

        MagicNumberData dm2 = new MagicNumberDataImp();
        dm2.setKey("DevMagic2");
        dm2.setValue(4);

        MagicNumberData dm3 = new MagicNumberDataImp();
        dm3.setKey("DevMagic3");
        dm3.setValue(0);

        MagicNumberData dm4 = new MagicNumberDataImp();
        dm4.setKey("DevMagic4");
        dm4.setValue(0);

        devMagic.add(dm1);
        devMagic.add(dm2);
        devMagic.add(dm3);
        devMagic.add(dm4);

        MagicNumberData fulfilledSev1 = new MagicNumberDataImp();
        fulfilledSev1.setKey("fulfilledSev1");
        fulfilledSev1.setValue(Integer.parseInt(criteria.get(0).getValue().toString()) <= Integer.parseInt(status.get(0).getValue().toString()));

        MagicNumberData fulfilledSev2 = new MagicNumberDataImp();
        fulfilledSev2.setKey("fulfilledSev2");
        fulfilledSev2.setValue(Integer.parseInt(criteria.get(1).getValue().toString()) <= Integer.parseInt(status.get(1).getValue().toString()));

        MagicNumberData fulfilledSev3 = new MagicNumberDataImp();
        fulfilledSev3.setKey("fulfilledSev3");
        fulfilledSev3.setValue(Integer.parseInt(criteria.get(2).getValue().toString()) <= Integer.parseInt(status.get(2).getValue().toString()));

        MagicNumberData fulfilledSev4 = new MagicNumberDataImp();
        fulfilledSev4.setKey("fulfilledSev4");
        fulfilledSev4.setValue(Integer.parseInt(criteria.get(3).getValue().toString()) <= Integer.parseInt(status.get(3).getValue().toString()));

        fulfilledSeverity.add(fulfilledSev1);
        fulfilledSeverity.add(fulfilledSev2);
        fulfilledSeverity.add(fulfilledSev3);
        fulfilledSeverity.add(fulfilledSev4);

        magicNumbersStructure.setCriteriaList(criteria);
        magicNumbersStructure.setStatusList(status);
        magicNumbersStructure.setMagicNumbersList(magicNumber);
        magicNumbersStructure.setDevMagicList(devMagic);
        magicNumbersStructure.setFulfilledSeverityList(fulfilledSeverity);

        return magicNumbersStructure;
    }

    public List<MagicNumberData> getSeverities(String sevName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<MagicNumberData> getSeverities() {
         MagicNumberData sev1 = new MagicNumberDataImp();
        sev1.setKey("Sev1");
        sev1.setValue(50);

        MagicNumberData sev2 = new MagicNumberDataImp();
        sev2.setKey("Sev2");
        sev2.setValue(50);

        MagicNumberData sev3 = new MagicNumberDataImp();
        sev3.setKey("Sev3");
        sev3.setValue(50);

        MagicNumberData sev4 = new MagicNumberDataImp();
        sev4.setKey("Sev4");
        sev4.setValue(50);

        List<MagicNumberData> sevList = new ArrayList();
        sevList.add(sev1);
        sevList.add(sev2);
        sevList.add(sev3);
        sevList.add(sev4);

        return sevList;
    }

}
