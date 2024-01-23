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

/**
 * An implementation of a queue using a circular array.
 */
public class ArrayQueue<T> implements QueueInterface<T>
{
   private int head;
   private int tail;
   private T[] items;

   /**
    * The default constructor creates a queue with 100 elements.
    */
    public ArrayQueue()
    {
      this(100);
    }

   /**
    * This constructor creates an queue with initial capacity {@code sz}.
    *
    * @throws IllegalArgumentException if sz <= 0.
    */
   public ArrayQueue(int sz) throws IllegalArgumentException
   {
     head = 0;
     tail = sz;

     if (sz <= 0)
      throw new IllegalArgumentException("Size must be > 0");

     // In general you should never suppress warnings but, in this case,
     // we know that cast will be valid since all entries are null.
     @SuppressWarnings("unchecked")
     T[] temp = (T[]) new Object[sz];
     items = temp;
   }

   /**
    * Determines if the queue is empty.
    *
    * @return true if the queue is empty; otherwise, false.
    */
   public boolean isEmpty()
   {
     return head == ((tail + 1) % items.length);
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
     return items[head];
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
     T rv;
     if (isEmpty())
      throw new EmptyQueueException();

     rv = items[head];
     items[head] = null;
     head = (head + 1) % items.length;
     return rv;
   }

   /**
    * Adds the item {@code item} to the end of the queue.
    */
   public void enqueue(T item)
   {
     ensureCapacity();    // Double the size of the array if needed.
     tail = (tail + 1) % items.length;
     items[tail] = item;
   }

   /**
    * Removes all entries from the queue.
    */
   public void clear()
   {
     head = 0;
     tail = items.length - 1;
   }

   /*****
    * Private helper methods.
    ****/
    private void ensureCapacity()
    {
      if (head == ((tail + 2) % items.length))
      {
        T[] oldItems = items;

        // Allocate new space.
        @SuppressWarnings("unchecked")
        T[] newItems = (T[]) new Object[2 * oldItems.length];
        items = newItems;

        // Put the head of the old queue at the first index
        // of the new array (essentially shifting to start the
        // head at 0)
        for (int i = 0; i < oldItems.length - 1; i++)
        {
            items[i] = oldItems[head];
            head = (head + 1) % oldItems.length;
        }
        head = 0;
        tail = oldItems.length - 2;
      }
    }
}
