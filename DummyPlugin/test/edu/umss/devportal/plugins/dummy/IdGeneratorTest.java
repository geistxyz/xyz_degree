///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package edu.umss.devportal.plugins.dummy;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Raul Lopez
// * @version 1.0
// */
//public class IdGeneratorTest {
//
//    public IdGeneratorTest() {
//    }
//
//    /**
//     * Test the default constructor
//     */
//    @Test
//    public void TestDefaultConstructor()
//    {
//        IdGenerator instance = new IdGenerator();
//        assertEquals("Id Generator does not use the default seed value in its default constructor",
//                IdGenerator.DefaultSeed,
//                instance.GenerateId() );
//    }
//
//    @Test
//    public void TestConstructor()
//    {
//        Integer seed = 3;
//        IdGenerator instance = new IdGenerator(seed);
//        assertEquals(seed, instance.GenerateId());
//
//        Integer otherSeed = 1349;
//
//        IdGenerator otherInstance = new IdGenerator(otherSeed);
//        assertEquals(otherSeed, otherInstance.GenerateId());
//
//    }
//
//    @Test( expected=IllegalArgumentException.class )
//    public void TestConstructorNegativeArgument()
//    {
//        IdGenerator instance = new IdGenerator(-2);
//    }
//
//
//
//    /**
//     * Test of GenerateId method, of class IdGenerator.
//     */
//    @Test
//    public void testGenerateId() {
//        IdGenerator instance = new IdGenerator(4);
//        Integer expResult = 4;
//
//        for(Integer index = expResult; index < 200; index++)
//        {
//            Integer result = instance.GenerateId();
//            assertEquals(index, result);
//        }
//
//    }
//
//}