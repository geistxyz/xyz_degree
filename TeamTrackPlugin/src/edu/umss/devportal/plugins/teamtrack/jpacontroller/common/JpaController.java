/*
 *  @(#)JpaController.java   05-dic-2010
 */

package edu.umss.devportal.plugins.teamtrack.jpacontroller.common;

import edu.umss.devportal.plugins.teamtrack.jpacontroller.exceptions.NonexistentEntityException;
import edu.umss.devportal.plugins.teamtrack.jpacontroller.exceptions.PreexistingEntityException;
import java.util.List;

/**
 * Interface that provides the main methods to manage the persistence of the
 * tool.  Each entity controller should at least implement those.
 *
 * @author Alex Arenas
 * @version 1.0
 */
public interface JpaController<T, R> {

    /**
     * Permits make persistent a new entity.
     *
     * @param t new entity to make persistent.
     * @throws PreexistingEntityException if the entity already exist.
     * @throws Exception if happen another error.
     */
    public void create(T t) throws PreexistingEntityException, Exception;

    /**
     * Permits edit the information of an entity.
     *
     * @param t entity to edit, it should be already persisted.
     * @throws NonexistentEntityException if the entity does not exist yet.
     * @throws Exception if happens another error.
     */
    public void edit(T t) throws NonexistentEntityException, Exception;

    /**
     * Permits destroy an entity.
     *
     * @param id entity's identifier to be destroyed.
     * @throws NonexistentEntityException if the entity's identifier does not
     *          belong to a persisted entity.
     */
    public void destroy(R id) throws NonexistentEntityException;

    /**
     * Find all entities persisted.
     *
     * @return a list of all entities persisted.
     */
    public List<T> findEntities();

    /**
     * Permits to find entities in a paginated way.
     *
     * @param maxResults number of items to find.
     * @param firstResult index of the first result.
     * @return a lista that contains the items started in the
     *          <code>firstResult</code> index and contains as maximun
     *          <code>maxResults</code> elements.
     */
    public List<T> findEntities(int maxResults, int firstResult);

    /**
     * Permits to find a persisted entity with identifier <code>id</code>.
     *
     * @param id entity's identifier to find.
     * @return the persisted entity that has the identifier passed as argument.
     */
    public T find(R id);

    /**
     * @return the number of entities persisted.
     */
    public int getCount();

    /**
     * @return the table's name in the persistence unit.
     */
    public String getTableName();
}
