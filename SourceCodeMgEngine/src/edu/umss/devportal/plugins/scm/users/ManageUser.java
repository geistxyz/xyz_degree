/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.users;

import edu.umss.devportal.plugins.scm.repository.Repository;
import edu.umss.devportal.plugins.scm.utils.FileManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *This class responsibilities is administrates the repository users
 *
 * @author Arminda Yovana Soto
 */
public class ManageUser{

    private User user;
    private List<String> datas;

    public ManageUser() {
    }


    private void setUser(String completeName, String password, String mail) {
        datas = new LinkedList<String>();

        StringTokenizer tokens = new StringTokenizer(completeName);
        while (tokens.hasMoreTokens()) {
            datas.add(tokens.nextToken());
        }
        if (datas.size() <= 3) {
            user = new User(datas.get(0), datas.get(1).concat(" ").
                    concat(datas.get(2)), password, mail);
        } else {
            user = new User(datas.get(0), datas.get(1), password, mail);
        }

    }

    public void setUser(String name, String lastNameFather, String lastNameMother, String mail) {
        user = new User(name, lastNameFather, lastNameMother, mail);
    }

    public void addUserToRepository(User user, Repository repository) {
        String hgDir = ".hg/";
        String hgrcFileName = "hgrc";
        String uiLine = "[ui]";
        
        String filePath = repository.getRootPath().getAbsolutePath().concat(hgDir).concat(hgrcFileName);
        setUser(user.getCompleteName(), user.getPassword(), user.getEmail());
        String userDatas = user.getCompleteName().concat(" ").concat(user.getEmail());
           
        String linesToWrite[] = {uiLine, userDatas};
        FileManager fileManager = new FileManager();
        fileManager.write(filePath, linesToWrite);
    }

    public void deleteUser() {
        search();
    }

    public void updateUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User getUSer() {
        return this.user;
    }

    private void search() {
        List listUsers = new ArrayList<User>();
    }
}
