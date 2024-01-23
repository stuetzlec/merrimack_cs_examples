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

/**
 * An interface for a maximum heap.
 * @author Zach Kissel
 */
 public interface MaxHeapInterface <T extends Comparable<? super T>>
 {
    /**
     * Adds a new entry {@code entry} to the heap.
     */
    public void add(T entry);

    /**
     * Removes and returns the maximum element from the heap.
     * @return the maxiumum element in the heap or null, if the heap
     * is empty.
     */
    public T removeMax();

    /**
     * Retrieves the maximum element from the heap without removing it.
     * @return the maximum element in the heap or null, if the heap is
     * empty.
     */
    public T getMax();

    /**
     * Determines if the heap is empty.
     * @return true if the heap is empty; otherwise, false.
     */
    public boolean isEmpty();

    /**
     * Get the size of the heap.
     * @return a positive integer greater than or equal to zero.
     */
    public int getSize();

    /**
     * Remove all elements from the heap.
     */
    public void clear();
 }
