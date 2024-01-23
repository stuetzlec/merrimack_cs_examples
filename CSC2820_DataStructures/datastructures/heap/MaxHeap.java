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
package datastructures.heap;

import java.util.Arrays;

/**
 * Implements a basic MaxHeap using an array.
 * @author Zach Kissel
 */
 public class MaxHeap<T extends Comparable<? super T>>
     implements MaxHeapInterface<T>
{
  private T[] heap;
  private int numEntries;

  /**
   * Construct a new maximum heap that has initial capacity of
   * 25 elements.
   */
  public MaxHeap()
  {
    this(25);
  }

  /**
   * Constructs a maximum heap with initial capacity {@code capacity}.
   *
   * @param capacity a positive number greater than zero.
   * @throws IllegalArguementException if the capacity is invalid.
   */
  public MaxHeap(int capacity) throws IllegalArgumentException
  {
    if (capacity <= 0)
      throw new IllegalArgumentException(
          "Capacity must be greater than zero.");

    @SuppressWarnings("unchecked")
    T[] tmp = (T[]) new Comparable[capacity + 1];
    heap = tmp;
    numEntries = 0;
  }

  /**
   * Adds a new entry {@code entry} to the heap.
   */
  public void add(T entry)
  {
    // Insert the new entry in to the left-most empty
    // leaf of tree.
    heap[++numEntries] = entry;

    // Migrate entry to its correct spot in the heap by checking
    // to make sure that entry is actually in the correct spot.
    upheap();

    // Let's table double if we run out of space.
    if (numEntries == heap.length - 1)
      heap = Arrays.copyOf(heap, 2 * heap.length);
  }

  /**
   * Removes and returns the maximum element from the heap.
   * @return the maxiumum element in the heap or null, if the heap
   * is empty.
   */
  public T removeMax()
  {
    // Save off the heap value.
    T rv = heap[1];

    // Move the right most leaf into the root position and
    // decrease the number of entries in the heap.
    heap[1] = heap[numEntries];
    numEntries--;

    // Call heapify on the root to make sure the resulting
    // tree is a valid heap.
    heapify(1);

    // If the number of entries shrinks to 1/4 or smaller, we will
    // table half.
    if (numEntries <= Math.floor(0.25 * heap.length))
      heap = Arrays.copyOf(heap, heap.length / 2);

    return rv;
  }

  /**
   * Retrieves the maximum element from the heap without removing it.
   * @return the maximum element in the heap or null, if the heap is
   * empty.
   */
  public T getMax()
  {
    if (!isEmpty())
      return heap[1];
    return null;
  }

  /**
   * Determines if the heap is empty.
   * @return true if the heap is empty; otherwise, false.
   */
  public boolean isEmpty()
  {
    return numEntries == 0;
  }


  /**
   * Get the size of the heap.
   * @return a positive integer greater than or equal to zero.
   */
  public int getSize()
  {
    return numEntries;
  }

  /**
   * Remove all elements from the heap.
   */
  public void clear()
  {
    for (int i = 1; i <= numEntries; i++)
      heap[i] = null;
    numEntries = 0;
  }

  /*******
   * Private Methods.
   *******/

   /**
    * Make sure node heap[idx] is at the correct spot in the
    * heap. In otherwords, ensure H[idx] is the root of a
    * valid heap. If a swap occurs make sure that subtree
    * the new tree is also a heap.
    *
    * @param idx the index of the node to call heapify on.
    */
   private void heapify(int idx)
   {
     int largest = idx;
     int left = 2 * idx;
     int right = 2 * idx + 1;

     // Compare the root to the left child.
     if (left <= numEntries && heap[left].compareTo(heap[idx]) > 0)
      largest = left;

     // Compare the right child to the larger of the two nodes (parent and
     // left child).
     if (right <= numEntries && heap[right].compareTo(heap[largest]) > 0)
      largest = right;

      // If the largest node is not the root of the subtree, swap
      // the nodes and call heapify on the parent of the new root.
      if (largest != idx)
      {
        // Swap the larger key with the parent.
        T tmp = heap[largest];
        heap[largest] = heap[idx];
        heap[idx] = tmp;

        // Call heapify on the parent's parent.
        heapify(largest);
      }
   }

   /**
    * Starting at a node in heap move up the heap ensuring that each
    * parent is larger than it's two children.
    *
    * @param parent the index of the parent node to start at.
    */
   private void upheap()
   {
     int idx = numEntries;
     int parent = idx / 2;

     while (idx > 1 && heap[parent].compareTo(heap[idx]) < 0)
     {
          // Swap the parent and child.
          T tmp = heap[idx];
          heap[idx] = heap[parent];
          heap[parent] = tmp;

          // Move up to the next parent.
          idx = parent;
          parent = idx / 2;
     }
   }
}
