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
package datastructures.queue;

import datastructures.exceptions.EmptyQueueException;
import datastructures.Node;

/**
 * A basic implementation of a linked queue.
 * @author Zach Kissel
 */
public class LinkedQueue<T> implements QueueInterface<T>
{
  private Node<T> head;     // The reference to the head of the queue.
  private Node<T> tail;     // The reference to the tail of the queue.

  /**
   * The constructor sets the head and tail reference to null.
   */
   public LinkedQueue()
   {
     head = null;
     tail = null;
   }

   /**
    * Determines if the queue is empty.
    *
    * @return true if the queue is empty; otherwise, false.
    */
    public boolean isEmpty()
    {
      return (head == null) && (tail == null);
    }

    /**
     * Gets the element at the front of the queue without
     * modifying the queue.
     *
     * @return the element at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public T getFront() throws EmptyQueueException
    {
      if (isEmpty())
        throw new EmptyQueueException();

      return head.getItem();
    }

    /**
     * Removes the element from the front of the queue and returns
     * it to the caller.
     *
     * @return the element at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public T dequeue() throws EmptyQueueException
    {
      if (isEmpty())
        throw new EmptyQueueException();

      Node<T> front = head;
      head = head.getNext();
      front.setNext(null);

      // Don't leave a dangling reference to the tail.
      if (head == null)
        tail = null;

      return front.getItem();
    }

    /**
     * Adds the item {@code item} to the end of the queue.
     */
    public void enqueue(T item)
    {
      Node<T> newEntry = new Node<T>(item);

      // If the queue is empty the head and the tail both need
      // to be updated.
      if (isEmpty())
      {
        head = newEntry;
        tail = newEntry;
      }
      else // Only need to add to the end of the chain.
      {
        tail.setNext(newEntry);
        tail = newEntry;
      }

    }

    /**
     * Removes all entries from the queue.
     */
    public void clear()
    {
      head = null;
      tail = null;
    }


}
