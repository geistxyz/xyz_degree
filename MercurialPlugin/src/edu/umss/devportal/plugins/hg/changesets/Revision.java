/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.changesets;

/**
 * Comparable class, Manages the number of revision of the change set
 * @author Arminda Yovana Soto
 */
public class Revision implements Comparable<Object> {

    public static final Revision TIP = new Revision("tip");
    private final String literalRevision;
    private final int integerRevision;

    /**
     *Constructor set a literal and integer revision's numbers
     * @param literalRevision only literal number of revision after ':' character
     */
    protected Revision(String literalRevision) {
        this(-1, literalRevision);
    }

    /**
     *Constructor Initialize the revision numbers
     * @param literalRevision the literal revision after ':' character,its format is hexadecimal
     * @param integerRevision the integer revision before ':' character, its format is integer
     */
    public Revision(int integerRevision, String literalRevision) {
        this.literalRevision = literalRevision;
        this.integerRevision = integerRevision;
    }

    /**
     * Returns the literal revision
     * @return literalRevision in hexadecimal format
     */
    public String getLiteralRevision() {
        return literalRevision;
    }

    /**
     * Returns the integer number of revision
     * @return integerRevision a integer number
     */
    public int getIntegerRevision() {
        return integerRevision;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Revision) {
            Revision r = (Revision) obj;
            return r.integerRevision == integerRevision
                    || r.literalRevision.equals(literalRevision);
        }
        return false;
    }

    /**
     * Overrides the object hash code method
     * @return literalRevision a number hash coded
     */
    @Override
    public int hashCode() {
        return literalRevision.hashCode();
    }

    /**
     * Overrides the Object to String method, parse to String type
     * @return the complete number of revision, literal and integer number joined
     */
    @Override
    public String toString() {
        return integerRevision + ":" + literalRevision;
    }

    /**
     * Parse to Revision a string complete revision number
     * @param revisionNumber a String sample 2:345sdrd
     * @return a instance of this class
     */
    public static Revision transform(String revisionNumber) {
        int i = revisionNumber.indexOf(':');
        return new Revision(Integer.parseInt(revisionNumber.substring(0, i)),
                revisionNumber.substring(i + 1));
    }

    /**
     *Compare by integer number of revision
     * @param numRevision integer number
     * @return a integer 
     */
    public int compareTo(Object o) {
        Revision temp = (Revision) o;
        return (temp.integerRevision - this.integerRevision);
    }
}
