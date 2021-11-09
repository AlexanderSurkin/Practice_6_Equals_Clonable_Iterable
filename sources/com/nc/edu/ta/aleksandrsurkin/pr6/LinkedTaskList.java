package com.nc.edu.ta.aleksandrsurkin.pr6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class "LinkedTaskList" extends class "AbstractTaskList" and
 * realise methods of abstract class "AbstractTaskList".
 */
public class LinkedTaskList extends AbstractTaskList {

    private Node head;
    final String THEME_OF_TITLE_LINKED_LIST = "[EDUCTR][TA]";
    static private int numberOfLinkedTasksListsCounter;

    /**
     * This method is set values for new linked tasks list.
     */
    public LinkedTaskList() {
        head = null;
        numberOfLinkedTasksListsCounter++;
    }

    /**
     * This method is validate that "task" is not Null.
     * @param object input object for "Null - check".
     */
    void taskValidate(Task object) {
        if (object == null) {
            throw new NullPointerException("The <task> can not be Null");
        }
    }

    /**
     * This class is create fields for create linked tasks list.
     */
    public class Node {
        public Task task;
        public Node next;

        /**
         * This method is set input value into field "task" and set "null" into next node.
         * @param task set value into field "task"
         */
        public Node(Task task) {
            this.task = task;
            next = null;
        }
    }


    /**
     * This method is return object of Iterator.
     * @return object of Iterator.
     */
    @Override
    public Iterator<Task> iterator() {                                //мозможно дженерик убрать нужно будет
        return new LinkedListIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o.equals(this.iterator().hasNext())) {
            return true;
        }
        return false;
    }

//    @Override
//    public int getMax() {
//        return 0;
//    }

    /**
     * This class is implements interface "Iterator".
     */
    class LinkedListIterator implements Iterator{

        Node current = head;
        Node previous = null;
        Node previous2 = null;

        /**
         * This method is check that next element from Iterator is not null.
         * @return boolean value of next iteration.
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * This method returns next task from linked list.
         * @return element after iteration.
         */
        @Override
        public Task next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            if (hasNext()) {
                Task task = current.task;
                previous2 = previous;
                previous = current;
                current = current.next;
                return task;
            }
            return null;
        }

        /**
         * This method is remove element after iteration.
         */
        @Override
        public void remove() {
            if (previous2.next == current) {
                throw new IllegalStateException();
            }
            if (previous == null) {
                throw new IllegalStateException();
            }
            if (previous2 == null) {
                head = current;
            } else {
                previous2.next = current;
            }
            sizeCounter--;
        }
    }

    /**
     * This method is realise actions of method "abstract void add(Task task)" from class "LinkedTaskList",
     * adding theme to tasks title and counts the number of incoming tasks.
     * @param task input of task.
     */
    @Override
    public void add(Task task) {

        taskValidate(task);

        Node newNode = new Node(task);
        Node currentNode = head;

        String taskTitle = task.getTitle();
        if (!taskTitle.startsWith(THEME_OF_TITLE_LINKED_LIST)) {
            task.setTitle(THEME_OF_TITLE_LINKED_LIST + taskTitle);
        }

        if (head == null) {
            head = newNode;

        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        sizeCounter++;
    }

    /**
     * This method is realise actions of method "abstract void remove(Task task)" from class "LinkedTaskList",
     * removing task from tasks list if argument "task" equals task from tasks list.
     * @param task input of task.
     */
    @Override
    public void remove(Task task) {

        taskValidate(task);

        String taskTitle = task.getTitle();
        if (!taskTitle.startsWith(THEME_OF_TITLE_LINKED_LIST)) {
            task.setTitle(THEME_OF_TITLE_LINKED_LIST + taskTitle);
        }

        Node currentNode = head;
        Node previousNode = null;

        while (currentNode.next != null) {

            if (currentNode.task.equals(task) || currentNode.task == task) {
                if (currentNode == head) {
                    head = currentNode.next;
                    sizeCounter--;
                } else {
                    previousNode.next = currentNode.next;
                    sizeCounter--;
                }
            }

            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        if (currentNode.task.equals(task) || currentNode.task == task) {
            previousNode.next = null;
            sizeCounter--;
        }
    }

//    /**
//     * This method is realise actions of method "abstract Task getTask(int index)" from
//     * class "LinkedTaskList" and returning task from its index in tasks list.
//     * @param index index of task from tasks list.
//     * @return task or null.
//     */
//    @Override
//    public Task getTask(int index) {
//
//        if (index < 0) {
//            throw new IllegalArgumentException("The <time> can not be a negative, try to add a positive value");
//        }
//        if (size() < 0) {
//            throw new IllegalArgumentException("The <size()> can not be a negative, try to add a positive value");
//        }
//        if (index >= size()) {
//            throw new IllegalArgumentException("The <index> can not be more or equal than <size()>");
//        }
//
//        Node currentNode = head;
//        for (int i = 0; i < size(); i++) {
//            if (i == index) {
//                break;
//            }
//            currentNode = currentNode.next;
//        }
//        if (currentNode == null) {
//            return null;
//        } else {
//            return currentNode.task;
//        }
//    }

    /**
     * This method returns a list with tasks which time is into "from" and "to".
     * @param from start time of the time range.
     * @param to end time of the time range.
     * @return list with found tasks.
     */
    @Override
    public Task[] incoming(int from, int to) {

        if (from < 0 || to < 0) {
            throw new IllegalArgumentException ("You can't use a negative number");
        }
        if (to < from) {
            throw new IllegalArgumentException("<to> can not be less than <from>");
        }

        Task[] incTasks = new Task [sizeCounter];
        Task[] emptyMassive = new Task [0];
        int incTaskCounter = 0;
        Node currentNode = head;
        for (int i = 0; i < sizeCounter; i++) {
            if (currentNode.task.nextTimeAfter(from) > from && currentNode.task.nextTimeAfter(from) <= to) {
                     incTasks[incTaskCounter++] = currentNode.task;
                     currentNode = currentNode.next;
            }
            else {
                currentNode = currentNode.next;
            }
        }
            Task[] countedMassive = new Task [incTaskCounter];
            for (int i = 0; i < countedMassive.length; i++) {
                if (incTasks[i] != null) {
                    countedMassive[i] = incTasks[i];
                }
            }
            return countedMassive;
    }

}
