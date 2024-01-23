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
 * A generic array based list implementation.
 *
 * @param <T> the object stored in the list.
 * @author Zach Kissel
 */
public class ArrayList<T> implements ListInterface<T>
{
  private T[] items;      // The list data.
  private static final int DEFAULT_CAPACITY = 100;     // The capacity created by def. const.
  private int itemCount;      // Number of elements in the list.

  /**
   * Constructs an empty list with capacity 100.
   */
   public ArrayList()
   {
     // Call the other constructor. Remember, this, is a refernce to
     // this instance of the class.
     this(DEFAULT_CAPACITY);
   }

   /**
    * Allocates a list that can hold sz entries.
    * @param sz an integer > 0.
    */
   public ArrayList(int sz) throws IllegalArgumentException
   {
     itemCount = 0;

     if (sz <= 0)
      throw new IllegalArgumentException("Size must be > 0");

     // In general you should never suppress warnings but, in this case,
     // we know that cast will be valid since all entries are null.
     @SuppressWarnings("unchecked")
     T[] temp = (T[]) new Object[sz];
     items = temp;
   }


  /**
   * Tests if the list is empty.
   * @return true if the list is empty; otherwise, false.
   */
   public boolean isEmpty()
   {
     return (itemCount == 0);
   }

  /**
   * Returns the length of the list.
   * @return a positive integer representing the length of the list.
   */
  public int getLength()
  {
    return itemCount;
  }

  /**
   * Inserts element entry at position position.
   * @param poistion a number between 1 and list length inclusive.
   * @param entry the element to insert at {@code position} in the list.
   * @return true if successful; otherwise, false.
   */
  public boolean insert(int position, T entry)
  {
    int idx = position - 1;

    // See if we have space.
    if (((itemCount + 1) >= items.length) || (position < 1) ||
       (position > (itemCount + 2)))
      return false;

    // Shift all elements  right one position and update itemCount.
    for (int i = itemCount; i >= idx; i--)
      items[i + 1] = items[i];
    itemCount++;

    // Insert the element at the given index;
    items[idx] = entry;
    return true;
  }

  /**
   * Removes the element at position position.
   * @param poistion a number between 1 and list length inclusive.
   * @return true if successful; otherwise, false is returned.
   */
  public boolean remove(int position)
  {
    // Nothing to remove.
    if (position < 1 || position > itemCount + 1)
      return false;

    // Shift everything left one.
    for (int i = position - 1; i <= itemCount; i++)
      items[i] = items[i + 1];
    itemCount--;
    return true;
  }

  /**
   * Removes all items from the list.
   */
  public void clear()
  {
    itemCount = 0;
  }

  /**
   * Determines if {@code entry} is contained in the list.
   * @param entry the entry to search for.
   * @return true if the entry is in the list; otherwise, false.
   */
   public boolean contains(T entry)
   {
     for (T item : items)
      if (item.equals(entry))
        return true;
     return false;
   }

  /**
   * Gets the item at entry position, provided the position is valid.
   * @param position a number between 1 and list length inclusive.
   * @return the item found at {@code position}.
   * @throws IndexOutOfBoundsException if the position is invalid.
   */
  public T getEntry(int position) throws IndexOutOfBoundsException
  {
    if (position >= 1 && position <= itemCount + 1)
      return items[position - 1];
    else
      throw new IndexOutOfBoundsException("Given position is out of bounds.");
  }

  /**
   * Replaces the item at position position with entry entry. The
   * replaced entry is returned.
   * @param poistion a number between 1 and list length inclusive.
   * @param entry to repelace the entry at {@code position} with.
   * @return the item that was replaced.
   */
  public T replace(int position, T entry) throws IndexOutOfBoundsException
  {
    T tmp;
    if (position >= 1 && position <= itemCount + 1)
    {
      tmp = items[position - 1];
      items[position - 1] = entry;
      return tmp;
    }
    else
      throw new IndexOutOfBoundsException("Given position is out of bounds.");
  }

  /**
   * Returns the array form of the list data.
   * @return an array of list data ordered the same as the list.
   */
  public Object[] toArray()
  {
    Object[] res = new Object[itemCount];

    int idx = 0;  // The index into res.

    for (T item : items)
      res[idx++] = item;

    return res;
  }
}
