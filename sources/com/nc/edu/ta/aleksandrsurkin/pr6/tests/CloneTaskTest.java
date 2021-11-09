package com.nc.edu.ta.aleksandrsurkin.pr6.tests;

import com.nc.edu.ta.aleksandrsurkin.pr6.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CloneTaskTest {

    @Test
    public void testClone() throws Exception {
        Task task = new Task("Title", 10);
        Task clone = (Task) task.clone();
        assertEquals("Clone must equal original", task, clone);
        assertNotSame("Clone must be different object", task, clone);
        clone.setTitle("Clone");
        assertEquals("Title", task.getTitle());
        assertEquals("Clone", clone.getTitle());
    }
}










