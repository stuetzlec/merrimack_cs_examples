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

import datastructures.Node;

/**
 * An implementation of a generic singly linked list.
 *
 * @param <T> the object stored in the list.
 * @author Zach Kissel
 */
 public class LinkedList<T> implements ListInterface<T>
 {
   private Node<T> head;    // The reference to the head of the list.
   private int itemCount;   // The number of items in the list.

   /**
    * Default constructor creates an empty list.
    */
   public LinkedList()
   {
     this.head = null;
     this.itemCount = 0;
   }


  /**
   * Returns true if the  list is empty (itemCount = 0) and false
   * otherwise.
   *
   * @return true if the list is empty; otherwise, false.
   */
  public boolean isEmpty()
  {
    return (itemCount == 0);
  }

  /**
   * Returns the number of elements in the list (itemCount).
   *
   * @return a number >= 0.
   */
  public int getLength()
  {
    return this.itemCount;
  }

  /**
   * This method inserts a new piece of data into the list at position
   * {@code position}.
   *
   * @param position the position in the list to insert the element at.
   * @param entry the data to insert into the list.
   * @return true if the insertion was successful and false otherwise.
   */
  public boolean insert(int position, T entry)
  {
    Node<T> newNode = null;
    Node<T> pred = null;

    // Check for a valid insert.
    if (position < 1 || position > itemCount+1)
      return false;

    // Allocate a new node to insert.
    newNode = new Node<T>(entry);

    if (position == 1)      // Insert at front.
    {
      newNode.setNext(head);
      head = newNode;
    }
    else
    {
        pred = getNodeAt(position - 1);   // Find the predecessor.
        newNode.setNext(pred.getNext());
        pred.setNext(newNode);
    }

    itemCount++;

    return true;
  }

  /**
   * This method removes an element from the list, reducing the size of the list.
   *
   * @param position the position of the element to remove.
   * @return true if the removal was successful; otherwise, false.
   */
  public boolean remove(int position)
  {
      Node<T> pred = null;
      Node<T> toDelete = null;

      // Nothing to remove.
      if (position < 1 || position > itemCount)
        return false;

      if (position== 1)
      {
          toDelete = head;
          head = head.getNext();
      }
      else  // Find the predecessor and link things up.
      {
        pred = getNodeAt(position - 1);
        toDelete = pred.getNext();
        pred.setNext(toDelete.getNext());

        // Unlink the node.
        toDelete.setNext(null);
      }

      itemCount--;    // We have one less item.

      return true;
  }

  /**
   * This method clears the list by deleteing all of the nodes in the chain.
   */
  public void clear()
  {
    while (!isEmpty())
      remove(1);
  }

  /**
   * Determines if {@code entry} is contained in the list.
   *
   * @param entry the entry to search for.
   * @return true if the entry is in the list; otherwise, false.
   */
   public boolean contains(T entry)
   {
     Node<T> walker = head;

     // Walk the chain. If the entry is found, return true.
     for(walker = head; walker != null; walker = walker.getNext())
        if (walker.getItem().equals(entry))
          return true;

     // We've walked the entire chain and did not find the entry; return false.
     return false;
   }

  /**
   * This method gets the entry at the location if the location
   * is valid.
   *
   * @param position a position in the list.
   * @return The data stored at the position in the list.
   * @throws IndexOutOfBoundsException if the position is not valid.
   */
  public T getEntry(int position) throws IndexOutOfBoundsException
  {
    if (position < 1 || position > itemCount)
      throw new IndexOutOfBoundsException("Invalid position.");

    return getNodeAt(position).getItem();
  }

  /**
   * This method replaces the item at entry if valid and returns the
   * old entry.
   *
   * @param position the positiion in the list to change.
   * @param entry the new data to replace in the list.
   * @return the data that was replaced.
   * @throws IndexOutOfBoundsException if the position is not valid.
   */
  public T replace(int position, T entry) throws IndexOutOfBoundsException
  {
    Node<T> replaceNode = null;
    T old;

    if (position < 1 || position > itemCount)
      throw new IndexOutOfBoundsException("Invalid position.");

    replaceNode = getNodeAt(position);
    old = replaceNode.getItem();
    replaceNode.setItem(entry);

    return old;
  }

  /**
   * Returns the array form of the list data.
   * @return an array of list data ordered the same as the list.
   */
  public Object[] toArray()
  {
    Object[] array =  new Object[itemCount];

    Node<T> walker;   // Reference used to walk the list.
    int idx = 0;      // Index into the array of elements.

    
      for (walker = head; walker != null; walker = walker.getNext())
        array[idx++] = walker.getItem();
   

    return array;
  }

  /***
   * Private methods
   ***/

   /**
    * This method returns the node at location position in the chain.
    * @param position a valid position in the list.
    * @return the Node at position {@code position} in the chain.
    */
   private Node<T> getNodeAt(int position)
   {
     Node<T> walker = null;

     assert (position >= 1 && position <= itemCount);

     walker = head;   // Point at the start of the chain.
     for (int i = 1; i < position; i++)
      walker = walker.getNext();

    return walker;
   }

 }
