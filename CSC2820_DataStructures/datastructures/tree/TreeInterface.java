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

import datastructures.exceptions.EmptyTreeException;

/**
 * Interface for a generic tree structure.
 */
public interface TreeInterface<T>
{
  /**
   * Gets the root data from the tree.
   *
   * @return the data stored at the root.
   * @throws EmptyTreeException if the tree is empty.
   */
   public T getRootData() throws EmptyTreeException;

   /**
    * Gets the height of the tree.
    *
    * @return the height of the tree (integer > 0).
    */
    public int getHeight();

    /**
     * Gets the number of nodes in the tree.
     *
     * @return an integer >= 0.
     */
     public int getNumberOfNodes();

     /**
      * Determines if the tree is empty.
      *
      * @return true if the tree is empty; otherwise, false.
      */
      public boolean isEmpty();

      /**
       * Removes all entries from the tree.
       */
       public void clear();
}
