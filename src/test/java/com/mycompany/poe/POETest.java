/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author User
 */
public class POETest {
    
     private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        // Setting up the tasks using the provided test data
        task1 = new Task("Login Feature", 0, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        task2 = new Task("Add Task Feature", 1, "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");
    }

    @Test
    public void testCheckTaskDescription() {
        assertTrue(task1.checkTaskDescription(), "Task description should be valid");
        assertTrue(task2.checkTaskDescription(), "Task description should be valid");
    }

    @Test
    public void testCreateTaskID() {
        assertEquals("LO:0:SON", task1.createTaskID());
        assertEquals("AD:1:ITH", task2.createTaskID());
    }

    
    
      @Test
    public void testPrintTaskDetails() {
          String expectedDetails1 = "Task Status: To Do\n" +
                "Developer Details: Robyn Harrison\n" +
                "Task Number: 0\n" +
                "Task Name: Login Feature\n" +
                "Task Description: Create Login to authenticate users\n" +
                "Task ID: LO:0:SON\n" +
                "Task Duration: 8 hours";
        String expectedDetails2 = "Task Status: Doing\n" +
                "Developer Details: Mike Smith\n" +
                "Task Number: 1\n" +
                "Task Name: Add Task Feature\n" +
                "Task Description: Create Add Task feature to add task users\n" +
                "Task ID: AD:1:ITH\n" +
                "Task Duration: 10 hours";

        assertEquals(expectedDetails1, task1.printTaskDetails());
        assertEquals(expectedDetails2, task2.printTaskDetails());
    }

    @Test
    public void testGetTaskDuration() {
        assertEquals(8, task1.getTaskDuration());
        assertEquals(10, task2.getTaskDuration());
    }

    @Test
    public void testReturnTotalHours() {
        Task[] tasks = { task1, task2 };
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getTaskDuration();
        }
        assertEquals(18, totalHours);
    }
}
