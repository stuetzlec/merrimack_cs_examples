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
package datastructures.list;

/**
 * A basic generic list interface for the list collection.
 *
 * @param <T> the object stored in the list.
 */
public interface ListInterface<T>
{
  /**
   * Tests if the list is empty.
   * @return true if the list is empty; otherwise, false.
   */
  public boolean isEmpty();

  /**
   * Returns the length of the list.
   * @return a positive integer representing the length of the list.
   */
  public int getLength();

  /**
   * Inserts element entry at position position.
   * @param poistion a number between 1 and list length inclusive.
   * @param entry the element to insert at {@code position} in the list.
   * @return true if successful; otherwise, false.
   */
  public boolean insert(int position, T entry);

  /**
   * Removes the element at position position.
   * @param poistion a number between 1 and list length inclusive.
   * @return true if successful; otherwise, false is returned.
   */
  public boolean remove(int position);

  /**
   * Removes all items from the list.
   */
  public void clear();

  /**
   * Determines if {@code entry} is contained in the list.
   * @param entry the entry to search for.
   * @return true if the entry is in the list; otherwise, false.
   */
   public boolean contains(T entry);

  /**
   * Gets the item at entry position, provided the position is valid.
   * @param position a number between 1 and list length inclusive.
   * @return the item found at {@code position}.
   * @throws IndexOutOfBoundsException if the position is unknown
   */
  public T getEntry(int position) throws IndexOutOfBoundsException;

  /**
   * Replaces the item at position position with entry entry. The
   * replaced entry is returned.
   * @param poistion a number between 1 and list length inclusive.
   * @param entry to replace the entry at {@code position} with.
   * @return the item that was replaced.
   * @throws IndexOutOfBoundsException if the position is unknown
   */
  public T replace(int position, T entry) throws IndexOutOfBoundsException;

  /**
   * Returns the array form of the list data.
   * @return an array of list data ordered the same as the list.
   */
  public Object[] toArray();
}
