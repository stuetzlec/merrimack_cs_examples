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
package datastructures.tree;

/**
 * A generic search tree interface. Make sure T implements the Comparable
 * interface on itself or its superclass.
 * @author Zach Kissel
 */
 public interface SearchTreeInterface<T extends Comparable<? super T>> extends
  TreeInterface<T>
 {
   /**
    * Searches the tree for a specific item {@code item}
    *
    * @return true if {@code item} is found in the tree; otherwise, false.
    */
    public boolean contains(T item);

    /**
     * Retrieves a specific item in the tree.
     *
     * @param item the item to be found.
     * @return either the object found in the tree or null if not found.
     */
     public T getEntry(T item);

     /**
      * Adds an entry to the tree.
      *
      * @param item the item to add to the tree.
      * @return null if {@code item} was added to the tree did not contain the
      * item or the existing entry that matched item is returned (it is replaced
      * by {@code item}).
      */
      public T add(T item);

      /**
       * Removes an item from the tree.
       *
       * @param item the item to remove from the tree.
       */
       public void remove(T item);
 }
