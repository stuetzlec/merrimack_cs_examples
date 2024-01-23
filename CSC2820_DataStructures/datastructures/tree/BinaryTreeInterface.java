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
 * A simple generic binary tree interface.
 */
public interface BinaryTreeInterface<T> extends TreeInterface<T>
{
    /**
     * Sets the root data of the tree.
     *
     * @param item the data to place in the root.
     */
     public void setRootData(T item);

       /**
        * Sets the binary tree to a new binary tree with
        * the named left and right subtrees.
        *
        * @param item the data to store in the root.
        * @param leftSubTree the new left subtree.
        * @param rightSubTree the new right subtree.
        */
        public void setTree(T item, BinaryTreeInterface<T> leftSubTree,
            BinaryTreeInterface<T> rightSubTree);
}
