/*
 *   Copyright (C) 2021 -- 2023  Zachary A. Kissel
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package datastructures.priorityqueue;

import datastructures.DoubleNode;
import datastructures.exceptions.EmptyQueueException;

/**
 * An implementation of a chained priority queue.
 * @author Zach Kissel
 */
 public class LinkedPriorityQueue<T extends Comparable<? super T>>
     implements PriorityQueueInterface<T>
{
  private DoubleNode<T> head;
  private DoubleNode<T> tail;
  private int numEntries;

  /**
   * The default construct initializes the priority queue
   * with head and tail set to null and the number of entries
   * to zero.
   */
   public LinkedPriorityQueue()
   {
     head = null;
     tail = null;
     numEntries = 0;
   }

  /**
   * Adds an entry {@code entry} to the priority queue.
   * @param entry the object to be added.
   */
   public void add(T entry)
   {
     DoubleNode<T> newNode = new DoubleNode<T>(entry);

     // If chain is empty, insert to the
     // front of the chain.
     if (head == null)
     {
       head = newNode;
       tail = newNode;
       numEntries++;
       return;
     }

     // Find the first node that is less than the entry. We start
     // from the tail to ensure that items with equal priority maintain
     // FIFO order.
     DoubleNode<T> walker = tail;
     while (walker != null && walker.getItem().compareTo(entry) < 0)
       walker = walker.getPrev();

     // The new node is the highest priority, add it to the head of
     // the chain.
     if (walker == null)
     {
       newNode.setNext(head);
       head.setPrev(newNode);
       head = newNode;
       numEntries++;
       return;
     }

     // The new node is the lowest priority, add it to the tail of the chain.
     if (walker == tail)
     {
       newNode.setPrev(tail);
       tail.setNext(newNode);
       tail = newNode;
       numEntries++;
       return;
     }

     // The new node belongs somewhere besides the head.
     newNode.setNext(walker.getNext());
     newNode.setPrev(walker);
     walker.getNext().setPrev(newNode);
     walker.setNext(newNode);
     numEntries++;
   }

   /**
    * Removes and returns the entry with the highest priority.
    * @return the entry at the head of the queue or null, if no entry
    * is in the queue.
    * @throws EmptyQueueException if the queue is empty.
    */
    public T remove() throws EmptyQueueException
    {
      DoubleNode<T> toRemove = head;

      if (head == null)
        throw new EmptyQueueException();

      head = head.getNext();

      if (head != null)
        head.setPrev(null);

      return toRemove.getItem();
    }

    /**
     * Retrieves the entry at with the highest priority from the queue.
     * @return the entry with highest priority or null if the queue is empty.
     * @throws EmptyQueueException if the queue is empty.
     */
     public T peek() throws EmptyQueueException
     {
       if (head == null)
        throw new EmptyQueueException();
       return head.getItem();
     }

     /**
      * Determines the size of the queue.
      * @return a positive integer representing the number of entries in
      * the queue.
      */
      public int getSize()
      {
        return numEntries;
      }

      /**
       * Determines if the queue is empty.
       * @return true if the queue is empty; otherwise, false.
       */
       public boolean isEmpty()
       {
         return numEntries == 0;
       }

       /**
        * Removes all entries form the priority queue.
        */
        public void clear()
        {
          head = null;
          tail = null;
          numEntries = 0;
        }
}
