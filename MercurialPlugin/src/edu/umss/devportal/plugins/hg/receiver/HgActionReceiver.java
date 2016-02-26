/*
 * HgActionReceiver.java
 */
package edu.umss.devportal.plugins.hg.receiver;

import edu.umss.devportal.common.User;
import edu.umss.devportal.plugins.hg.changesets.ChangeSet;
import edu.umss.devportal.plugins.hg.changesets.ChangeSetManager;
import edu.umss.devportal.plugins.hg.changesets.ChangeSetUtil;
import edu.umss.devportal.plugins.hg.changesets.Revision;
import edu.umss.devportal.plugins.hg.configurations.files.HgrcManager;
import edu.umss.devportal.plugins.scm.commands.processor.CommandProcessor;
import edu.umss.devportal.plugins.scm.services.SCMCommonsActions;
import edu.umss.devportal.plugins.scm.utils.StringUtilities;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *Receives the commons actions and resolve this actions
 * @author Arminda Yovana Soto
 */
public class HgActionReceiver implements SCMCommonsActions {

    private CommandProcessor processor;
//the literals modificar ponerlo dentro de un diccionarity
    private final String SPACE = StringUtilities.SPACE.getValue();
    private final String SEP = StringUtilities.FILE_SEPARATOR.getValue();
    private File file;
//the commnads
    private final String COMMAND_INIT = "hg init";
    private final String COMMAND_LOG = "hg log";

    /**
     * Constructor initialize the local variables
     * @param projectName the repository name
     * @param repositoryPath the repository path
     * @param processor process the command
     */
    public HgActionReceiver(CommandProcessor processor) {
        this.processor = processor;
    }

    /**
     * Creates repository in Mercurial system
     */
    public void createRepository(String repositoryName, String repositoryPath) {

        File repoPath = new File(repositoryPath);

        processor.process(COMMAND_INIT.concat(SPACE).concat(repositoryName), repoPath);
        System.out.println("Hg create repository");
    }

    /**
     * Show the revisions of change sets
     * @param path the repository path
     * @return listChangeSets
     */
    public List<ChangeSet> showRevisions(String path) {

        processor.process(COMMAND_LOG.concat(SPACE), new File(path));

        Map<Revision, Map<String, String>> chargeLog = ChangeSetUtil.chargeLog(processor.getInputStream());

        ChangeSetManager changeManager = new ChangeSetManager(chargeLog);

        return changeManager.getListChangeSets();
    }

    /**
     * Authentificate user using Hgrc file
     * @param user
     */
    public void authentificateUser(User user) {
    }

    /**
     * Assign permission to who can will be read,write,
     * @param user
     */
    public void assignPermiss(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
