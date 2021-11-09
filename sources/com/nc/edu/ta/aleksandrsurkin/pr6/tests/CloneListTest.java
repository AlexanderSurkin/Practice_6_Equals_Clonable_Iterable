package com.nc.edu.ta.aleksandrsurkin.pr6.tests;

import com.nc.edu.ta.aleksandrsurkin.pr6.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CloneListTest extends AbstractTaskListTest {

    public CloneListTest(Class<? extends AbstractTaskList> tasksClass) {
        super(tasksClass);
    }
    
    @Test
    public void testClone() throws CloneNotSupportedException { //возможно нужно будет убрать exception
        Task[] elements = { task("A"), task("B"), task("C") };
        addAll(elements);
        AbstractTaskList clone = (AbstractTaskList) tasks.clone();
        
        assertEquals(getTitle(), tasks.size(), clone.size());
        Iterator<?> ti = tasks.iterator();
        Iterator<?> ci = clone.iterator();
        while (ti.hasNext() && ci.hasNext())
            assertEquals(getTitle(), ti.next(), ci.next());
            
        assertNotSame(getTitle(), tasks, clone);
        
        clone.add(task("D"));
        assertEquals(getTitle(), clone.size() - 1, tasks.size());
        
        clone.remove(task("B"));
        assertContains(elements);
    }
}
