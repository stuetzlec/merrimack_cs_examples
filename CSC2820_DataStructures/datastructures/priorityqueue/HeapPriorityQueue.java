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

import datastructures.heap.MaxHeap;
import datastructures.exceptions.EmptyQueueException;

/**
 * An implementation of a priority queue using a max heap.
 * @author Zach Kissel
 */
 public class HeapPriorityQueue<T extends Comparable<? super T>>
   implements PriorityQueueInterface<T>
{
  private MaxHeap<T> heap;

  /**
   * Constructs a new heap based priority queue.
   */
  public HeapPriorityQueue()
  {
    heap = new MaxHeap<T>();
  }

  /**
   * Adds an entry {@code entry} to the priority queue.
   * @param entry the object to be added.
   */
  public void add(T entry)
  {
    heap.add(entry);
  }

  /**
   * Removes and returns the entry with the highest priority.
   * @return the entry at the head of the queue or null, if no entry
   * is in the queue.
   * @throws EmptyQueueException if the queue is empty.
   */
  public T remove() throws EmptyQueueException
  {
    if (heap.isEmpty())
      throw new EmptyQueueException();

    return heap.removeMax();
  }

  /**
   * Retrieves the entry at with the highest priority from the queue.
   * @return the entry with highest priority or null if the queue is empty.
   * @throws EmptyQueueException if the queue is empty.
   */
  public T peek() throws EmptyQueueException
  {
    if (heap.isEmpty())
      throw new EmptyQueueException();
      
    return heap.getMax();
  }

  /**
   * Determines the size of the queue.
   * @return a positive integer representing the number of entries in
   * the queue.
   */
  public int getSize()
  {
    return heap.getSize();
  }

  /**
   * Determines if the queue is empty.
   * @return true if the queue is empty; otherwise, false.
   */
  public boolean isEmpty()
  {
    return heap.isEmpty();
  }

  /**
   * Removes all entries form the priority queue.
   */
  public void clear()
  {
    heap.clear();
  }
}
