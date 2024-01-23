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
 * A basic generic queue (FIFO) interface.
 */
public interface QueueInterface<T>
{
  /**
   * Determines if the queue is empty.
   *
   * @return true if the queue is empty; otherwise, false.
   */
  public boolean isEmpty();

  /**
   * Gets the element at the front of the queue without
   * modifying the queue.
   *
   * @return the element at the front of the queue.
   * @throws EmptyQueueException if the queue is empty.
   */
  public T getFront() throws EmptyQueueException;

  /**
   * Removes the element from the front of the queue and returns
   * it to the caller.
   *
   * @return the element at the front of the queue.
   * @throws EmptyQueueException if the queue is empty.
   */
  public T dequeue() throws EmptyQueueException;

  /**
   * Adds the item {@code item} to the end of the queue.
   */
  public void enqueue(T item);

  /**
   * Removes all entries from the queue.
   */
  public void clear();
}
