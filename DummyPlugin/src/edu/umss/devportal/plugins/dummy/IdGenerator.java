/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.dummy;

/**
 *
 * @author Raul Lopez
 * @version 1.0
 */
public class IdGenerator {
    static final public  Integer DefaultSeed = 1;
    private final String NegativeSeedStringMessage =
            "IdGenerator seed must be a positive number";
    private Integer value;

    public IdGenerator(Integer seed)
    {
        if(seed < 0)
            throw new IllegalArgumentException(NegativeSeedStringMessage);
        value = seed;
    }

    public Integer GenerateId()
    {
        Integer temp = value;
        value = temp + 1;
        return temp;
    }

    public IdGenerator()
    {
        this(DefaultSeed);
    }

}
