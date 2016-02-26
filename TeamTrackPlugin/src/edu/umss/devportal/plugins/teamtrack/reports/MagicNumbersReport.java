/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.teamtrack.reports;
import edu.umss.devportal.common.reports.MagicNumbersStructure;
import edu.umss.devportal.common.reports.MagicNumberData;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsFieldsJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsSelectionsJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TsStatesJpaController;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.TttIssuesJpaController;
import edu.umss.devportal.plugins.teamtrack.model.TsSelections;
import edu.umss.devportal.plugins.teamtrack.model.TttIssues;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author July Camacho
 */
public class MagicNumbersReport {

    private TttIssuesJpaController issuesController;
    private TsFieldsJpaController fieldsController;
    private TsSelectionsJpaController selectionController;
    private TsStatesJpaController statesController;
    private List<MagicNumberData> totalClosedPrs;
    private List<MagicNumberData> totalPrsFound;
    private List<MagicNumberData> criteria;
    private int projectId;
    protected static final Logger logger = Logger.getLogger(MagicNumbersReport.class.getName());
    /**
     * Default constructor
     */
    public MagicNumbersReport(){

        totalClosedPrs = new ArrayList<MagicNumberData>();
        totalPrsFound = new ArrayList<MagicNumberData>();
        criteria = new ArrayList<MagicNumberData>();
        issuesController = new TttIssuesJpaController();
        fieldsController = new TsFieldsJpaController();
        selectionController = new TsSelectionsJpaController();
        statesController = new TsStatesJpaController();
    }
    /**
     *
     * @param projectId
     * @param severities
     */
    public void initialize(String projectId,List<MagicNumberData> severities){
        this.projectId = Integer.parseInt(projectId);
       /* for(MagicNumberData data: severities){
            MagicNumberData temp = new MagicNumberDataImp();
            temp.setKey(data.getKey());
            temp.setValue(Integer.parseInt((String)data.getValue()));
            criteria.add(temp);
        }*/
        criteria = severities;
        fillTotalPrsFound(totalPrsFound);
        fillTotalClosedPrs(totalClosedPrs);
    }
    /**
     *
     * @param totalPrsFound
     */
     private void fillTotalPrsFound(List<MagicNumberData> totalPrsFound) {
         int selectionId;
         try{
             int severityId = fieldsController.getSeverityId();
             List<TttIssues> issues;
             int totalFound;
             for(MagicNumberData data : criteria){
                 selectionId = selectionController.getSelectionId(data.getKey(),severityId);
                 issues = issuesController.getIssuesBySeverity(selectionId);
                 totalFound = issues.size();
                 for(TttIssues issue : issues){
                     if(issue.getTsProjectid()!= projectId)
                         totalFound--;
                 }
                 MagicNumberData magicData = new MagicNumberDataImp();
                 magicData.setKey(data.getKey());
                 magicData.setValue(totalFound);
                 totalPrsFound.add(magicData);
             }
         }
         catch(Exception e){
             logger.log(Level.SEVERE, e.getMessage());
         }
    }
    /**
     *
     * @param totalClosedPrs
     */
    private void fillTotalClosedPrs(List<MagicNumberData> totalClosedPrs) {
        int selectionId;
        try{
            int severityId = fieldsController.getSeverityId();
            List<TttIssues> issues;
            int totalClosed;
            for(MagicNumberData data : criteria){
                selectionId = selectionController.getSelectionId(data.getKey(),severityId);
                issues = issuesController.getIssuesBySeverity(selectionId);
                totalClosed = issues.size();
                for(TttIssues issue : issues){
                    if(issue.getTsClosedate()== null || issue.getTsProjectid()!= projectId)
                        totalClosed--;
                 }
                 MagicNumberData magicData = new MagicNumberDataImp();
                 magicData.setKey(data.getKey());
                 magicData.setValue(totalClosed);
                 totalClosedPrs.add(magicData);
             }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    /**
     *
     * @param structure
     */
    public void processCriteria(MagicNumbersStructure structure){
        structure.setCriteriaList(criteria);
    }
    /**
     *
     * @param structure
     */
    public void processStatus(MagicNumbersStructure structure){
        List<MagicNumberData> statusArray = new ArrayList<MagicNumberData>();
        double status;
        for(MagicNumberData totalClosed : totalClosedPrs){
            for(MagicNumberData totalFound : totalPrsFound){
                if(totalClosed.getKey().equals(totalFound.getKey())){
                    status = ((double)((Integer)totalClosed.getValue())/(double)((Integer)totalFound.getValue())) * 100;
                    MagicNumberData statusData = new MagicNumberDataImp();
                    statusData.setKey(totalClosed.getKey());
                    statusData.setValue((int)status);
                    statusArray.add(statusData);
                    break;
                }
            }
        }
        structure.setStatusList(statusArray);
    }
    /**
     *
     * @param structure
     */
    public void processMagicNumbers(MagicNumbersStructure structure){
         List<MagicNumberData> magicNumbers = new ArrayList<MagicNumberData>();
        double criteriaNumber,required,product,magic;
        int size = criteria.size();
        for(int i = 0 ; i < size ; i++){
            if(criteria.get(i).getKey().equals(totalClosedPrs.get(i).getKey()) &&
               criteria.get(i).getKey().equals(totalPrsFound.get(i).getKey())){
                criteriaNumber = (double)((Integer)criteria.get(i).getValue());
                required = criteriaNumber / 100;
                product = required * (double)((Integer)totalPrsFound.get(i).getValue());
                if(criteriaNumber < 100)
                    magic = Math.ceil(product) - (double)((Integer)totalClosedPrs.get(i).getValue());
                else
                    magic = product -(double)((Integer)totalClosedPrs.get(i).getValue());
                MagicNumberData magicData = new MagicNumberDataImp();
                magicData.setKey(criteria.get(i).getKey());
                magicData.setValue((int)magic);
                magicNumbers.add(magicData);
            }
        }
        structure.setMagicNumbersList(magicNumbers);
    }
    /**
     *
     * @param structure
     */
    public void processDevMagic(MagicNumbersStructure structure){
        List<MagicNumberData> devMagic = new ArrayList<MagicNumberData>();
        List<MagicNumberData> totalResolved = new ArrayList<MagicNumberData>();
        try{
            int stateId = statesController.getStateId("Resolved");
            int severityId = fieldsController.getSeverityId();
            int selectionId;
            int resolved;
            List<TttIssues> issues;
            for(MagicNumberData data : criteria){
                selectionId = selectionController.getSelectionId(data.getKey(),severityId);
                issues = issuesController.getIssuesBySeverity(selectionId);
                resolved = issues.size();
                for(TttIssues issue : issues){
                    if(issue.getTsState() != stateId || issue.getTsProjectid()!= projectId)
                        resolved--;
                 }
                 MagicNumberData magicData = new MagicNumberDataImp();
                 magicData.setKey(data.getKey());
                 magicData.setValue(resolved);
                 totalResolved.add(magicData);
             }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        int size = criteria.size();
        List<MagicNumberData> magicNumbers = structure.getMagicNumbersList();
        int devMagicNumber ;
        for(int i = 0 ; i< size; i++){
            if(magicNumbers.get(i).getKey().equals(totalResolved.get(i).getKey())){
                devMagicNumber = (Integer)magicNumbers.get(i).getValue() - (Integer)totalResolved.get(i).getValue();
                MagicNumberData devMagicData= new MagicNumberDataImp();
                devMagicData.setKey(magicNumbers.get(i).getKey());
                devMagicData.setValue(devMagicNumber);
                devMagic.add(devMagicData);
            }
        }
        structure.setDevMagicList(devMagic);
    }
    /**
     *
     * @param structure
     */
    public void processFullFilledSeverity(MagicNumbersStructure structure){
        List<MagicNumberData> filledSeverity = new ArrayList<MagicNumberData>();
        List<MagicNumberData> status = structure.getStatusList();
        int size = status.size();
        for(int i = 0 ; i < size; i++){

            if(status.get(i).getKey().equals(criteria.get(i).getKey())){
                MagicNumberData filledSeverityData = new MagicNumberDataImp();
                filledSeverityData.setKey(status.get(i).getKey());
                if((Integer)status.get(i).getValue() >= (Integer)criteria.get(i).getValue())
                    filledSeverityData.setValue(Boolean.TRUE);
                else
                    filledSeverityData.setValue(Boolean.FALSE);
                filledSeverity.add(filledSeverityData);
             }
        }
         structure.setFulfilledSeverityList(filledSeverity);
    }
    /**
     *
     * @return
     */
    public List<MagicNumberData> getSeverities(){
        if(criteria.size() > 0)
            return criteria;
        List<MagicNumberData> severities = new ArrayList<MagicNumberData>();
        try{
        int severityId = fieldsController.getSeverityId();
        List<TsSelections> selections = selectionController.getSelectionsByFielId(severityId);
        for(TsSelections selection : selections){
            MagicNumberData data = new MagicNumberDataImp();
            data.setKey(selection.getTsName());
            data.setValue(0);
            severities.add(data);
        }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        return severities;

    }
    /**
     *
     * @param sevName
     * @return
     */
    public List<MagicNumberData> getSeverities(String sevName){
        if(criteria.size() > 0)
            return criteria;
         List<MagicNumberData> severities = new ArrayList<MagicNumberData>();
        try{
        int severityId = fieldsController.getSeverityId(sevName);
        List<TsSelections> selections = selectionController.getSelectionsByFielId(severityId);
        for(TsSelections selection : selections){
            MagicNumberData data = new MagicNumberDataImp();
            data.setKey(selection.getTsName());
            data.setValue(0);
            severities.add(data);
        }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
        return severities;
    }

}
