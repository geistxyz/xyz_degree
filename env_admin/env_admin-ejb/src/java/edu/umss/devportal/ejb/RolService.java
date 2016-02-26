/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.ejb;

import edu.umss.devportal.entity.RolEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Roger Ayaviri
 */
@Stateless
@LocalBean
public class RolService implements RolServiceRemote, RolServiceLocal {
    
    @PersistenceContext(name = "devportal-EPU")
    EntityManager em;

    public List<RolEntity> getRolsApp(){

        List<RolEntity> rolEntitys = new ArrayList<RolEntity>();

        Query query = em.createQuery("select r from RolEntity r");
        rolEntitys =  query.getResultList();
        if (rolEntitys.size()<=0 || rolEntitys == null){

            RolEntity rolEntity1 = new RolEntity();
            rolEntity1.setName("Manager");
            em.persist(rolEntity1);
            rolEntitys.add(rolEntity1);
            RolEntity rolEntity2 = new RolEntity();
            rolEntity2.setName("Administrator");
            em.persist(rolEntity2);
            rolEntitys.add(rolEntity2);
            RolEntity rolEntity3 = new RolEntity();
            rolEntity3.setName("Team Member");
            em.persist(rolEntity3);
            rolEntitys.add(rolEntity3);
        }

        return rolEntitys;
    }
 
}
