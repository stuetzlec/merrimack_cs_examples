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
 * A basic binary tree class.
 */
public class BinaryTree<T> implements BinaryTreeInterface<T>
{
  private BinaryNode<T> root;

  /**
   * Builds an empty binary tree.
   */
   public BinaryTree()
   {
     this.root = null;
   }

   /**
    * Builds a binary tree with root data {@code item}
    */
    public BinaryTree(T item)
    {
      this.root = new BinaryNode<T>(item);
    }

    /**
     * Builds a binary tree that contains root data {@code item},
     * the left subtree {@code leftSubTree}, and right
     * subtree {@code rightSubTree}.
     *
     * @param item the data to store in the root.
     * @param leftSubTree the new left subtree.
     * @param rightSubTree the new right subtree.
     */
     public BinaryTree(T item, BinaryTree<T> leftSubTree,
         BinaryTree<T> rightSubTree)
     {
       initializeTree(item, leftSubTree, rightSubTree);
     }

    /**
     * Sets the binary tree to a new binary tree with
     * the named left and right subtrees.
     *
     * @param item the data to store in the root.
     * @param leftSubTree the new left subtree.
     * @param rightSubTree the new right subtree.
     */
     public void setTree(T item, BinaryTreeInterface<T> leftSubTree,
         BinaryTreeInterface<T> rightSubTree)
     {
       initializeTree(item, (BinaryTree<T>)leftSubTree,
          (BinaryTree<T>)rightSubTree);
     }

   /**
    * Gets the root data from the tree.
    *
    * @return the data stored at the root.
    * @throws EmptyTreeException if the tree is empty.
    */
    public T getRootData() throws EmptyTreeException
    {
      if (isEmpty())
        throw new EmptyTreeException();
      return this.root.getItem();
    }

    /**
     * Sets the root data of the tree.
     *
     * @param item the data to place in the root.
     */
     public void setRootData(T item)
     {
       this.root.setItem(item);
     }

    /**
     * Gets the height of the tree.
     *
     * @return the height of the tree (integer > 0).
     */
     public int getHeight()
     {
       if (root != null)
        return root.getHeight();
      return 0;
     }

     /**
      * Gets the number of nodes in the tree.
      *
      * @return an integer >= 0.
      */
      public int getNumberOfNodes()
      {
        if (root != null)
          return root.getNumberOfNodes();
        return 0;
      }

      /**
       * Determines if the tree is empty.
       *
       * @return true if the tree is empty; otherwise, false.
       */
       public boolean isEmpty()
       {
         return root == null;
       }

       /**
        * Removes all entries from the tree.
        */
        public void clear()
        {
          root = null;
        }

      /**
       * Sets the root node to {@code root}.
       *
       * @param root the new root node.
       */
      public void setRootNode(BinaryNode<T> root)
      {
        this.root = root;
      }

      /**
       * Gets the root node from the tree.
       *
       * @return the root node of the tree.
       */
       public BinaryNode<T> getRootNode()
       {
         return this.root;
       }

     /*****************
      * Private Methods
      ****************/

     /**
      * Initializes a new tree with the copy of the data found in
      * the left and right subtrees.
      *
      * @param item the data to store in the root.
      * @param leftSubTree the new left subtree.
      * @param rightSubTree the new right subtree.
      */
      public void initializeTree(T item, BinaryTree<T> leftSubTree,
          BinaryTree<T> rightSubTree)
      {
        root = new BinaryNode<T>(item);

        // Check to see if the left subtree has children.
        if (leftSubTree != null && !leftSubTree.isEmpty())
          root.setLeftChild(leftSubTree.root);

        // Check if the right subtree has children.
        if (rightSubTree != null && !rightSubTree.isEmpty())
        {
            // If the left and right trees are distinct objects
          if (rightSubTree != leftSubTree)
            root.setRightChild(rightSubTree.root);
          else // If not, copy the data.
            root.setRightChild(rightSubTree.root.copy());
        }

        if (leftSubTree != null && leftSubTree != this)
          leftSubTree.clear();

        if (rightSubTree != null && rightSubTree != this)
          rightSubTree.clear();
      }
}
