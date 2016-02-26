///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package edu.umss.devportal.plugins.dummy;
//
//import edu.umss.devportal.common.BasicProjectImpl;
//import edu.umss.devportal.common.Project;
//import edu.umss.devportal.common.ToolDescriptor;
//import edu.umss.devportal.common.ToolVersion;
//import edu.umss.edu.devportal.exception.NoToolServerConnectionException;
//import javax.management.InstanceNotFoundException;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Raul Lopez
// * @version 1.0
// */
//public class DummyProjectTrackerTest {
//
//    DummyProjectTracker instance;
//
//    public DummyProjectTrackerTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    @Before
//    public void setUp() {
//        instance = new DummyProjectTracker();
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getToolDescriptor method, of class DummyProjectTracker.
//     */
//    @Test
//    public void testGetToolDescriptor() {
//        ToolDescriptor result = instance.getToolDescriptor();
//        ToolVersion expToolVersion = new ToolVersion(DummyProjectTracker.MajorVersion, DummyProjectTracker.MinorVersion);
//
//        assertEquals(DummyProjectTracker.ToolName, result.getName());
//        assertEquals(DummyProjectTracker.ToolDescription, result.getDescription());
//        assertEquals(expToolVersion.toString(), result.getVersion());
//
//    }
//
//    /**
//     * Test of createProject method, of class DummyProjectTracker.
//     */
//    @Test
//    public void testCreateProject() throws Exception {
//        Project project = new BasicProjectImpl("Test Project",
//                "Project used to test createProject method ", "0");
//        int before = instance.getProjectList().size();
//
//        instance.createProject(project);
//
//        int after = instance.getProjectList().size();
//
//        assertEquals(before+1, after);
//
//        boolean found = false;
//
//        for(Project p : instance.getProjectList() )
//        {
//            if( (p.getName().equals(project.getName())) &&
//                    (p.getDescription().equals(project.getDescription())))
//            {
//                found = true;
//                break;
//            }
//        }
//
//        if(!found)
//        {
//            fail("The project has not been created");
//        }
//    }
//
//    @Test(expected=NullPointerException.class)
//    public void testCreateNullProject() throws Exception
//    {
//        instance.createProject(null);
//    }
//
//    /**
//     * Test of getProjectList method, of class DummyProjectTracker.
//     */
//    @Test
//    public void testGetProjectList() throws Exception {
//
//        String projectName = "Test Project";
//        String projectDesc = "Test project desc";
//        Project project = new BasicProjectImpl(projectName, projectDesc, "0");
//
//        assertEquals(0, instance.getProjectList().size());
//
//        String projectId = instance.createProject(project);
//        assertEquals(1, instance.getProjectList().size());
//
//        instance.createProject(project);
//        assertEquals(2, instance.getProjectList().size());
//
//        instance.removeProject(projectId);
//        assertEquals(1, instance.getProjectList().size());
//        //result = instance.getProjectList();
//    }
//
//
//    @Test
//    public void testRemoveProject() throws Exception
//    {
//        Project project = new BasicProjectImpl("ProjectName", "Project Description", "0");
//        String projectId = instance.createProject(project);
//        boolean found = false;
//        instance.removeProject(projectId);
//
//        for(Project p : instance.getProjectList())
//        {
//            if(p.getName().equals(project.getName()) && p.getDescription().equals(project.getDescription()))
//            {
//                found = true;
//                break;
//            }
//        }
//
//        if(found)
//            fail("Project has not been removed");
//
//        try
//        {
//            instance.removeProject(projectId);
//            fail("Project has not been removed previously");
//        }catch (InstanceNotFoundException ex)
//        {
//        }
//
//    }
//
//    @Test(expected=IllegalArgumentException.class)
//    public void testRemoveProjectNullProjectId() throws InstanceNotFoundException, NoToolServerConnectionException
//    {
//        instance.removeProject(null);
//    }
//
//    @Test(expected=IllegalArgumentException.class)
//    public void testRemoveProjectWrongNumberProjectId() throws InstanceNotFoundException, NoToolServerConnectionException
//    {
//        instance.removeProject("asdf");
//    }
//
//    @Test(expected=InstanceNotFoundException.class)
//    public void testRemoveProjectInstanceDoesNotExist() throws InstanceNotFoundException, NoToolServerConnectionException
//    {
//        Integer projectId = 12312412;
//        instance.removeProject(projectId.toString());
//    }
//
//}