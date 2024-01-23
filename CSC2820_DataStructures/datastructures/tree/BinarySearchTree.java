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
 * This class implments a generic Binary Search Tree (BST).
 *
 * @author Zach Kissel
 */
 public class BinarySearchTree<T extends Comparable<? super T>>
     extends BinaryTree<T> implements SearchTreeInterface<T>
  {
    /**
     * Builds an empty BST.
     */
    public BinarySearchTree()
    {
      super();
    }

    /**
     * Constructs a binary search tree that contains {@code item} stored
     * in the root.
     *
     * @param item the item to be stored in the root of the tree.
     */
     public BinarySearchTree(T item)
     {
       super();
       setRootNode(new BinaryNode<T>(item));
     }

     /**
      * Sets the binary tree to a new binary tree with
      * the named left and right subtrees.
      *
      * @param item the data to store in the root.
      * @param leftSubTree the new left subtree.
      * @param rightSubTree the new right subtree.
      *
      */
      public void setTree(T item, BinaryTreeInterface<T> leftSubTree,
          BinaryTreeInterface<T> rightSubTree)
      {
        // This method doesn't make sense for a binary search tree.
        throw new UnsupportedOperationException();
      }

      /**
       * Searches the tree for a specific item {@code item}
       *
       * @return true if {@code item} is found in the tree; otherwise, false.
       */
       public boolean contains(T item)
       {
         return getEntry(item) != null;
       }

       /**
        * Retrieves a specific item in the tree.
        *
        * @param item the item to be found.
        * @return either the object found in the tree or null if not found.
        */
        public T getEntry(T item)
        {
          return findItem(getRootNode(), item);
        }

        /**
         * Adds an entry to the tree.
         *
         * @param item the item to add to the tree.
         * @return null if {@code item} was added to the tree did not contain the
         * item or the existing entry that matched item is returned (it is replaced
         * by {@code item}).
         */
         public T add(T item)
         {
           if (isEmpty())
            setRootNode(new BinaryNode<T>(item));
           else
            return addItem(getRootNode(), item);
           return null;
         }

         /**
          * Removes an item from the tree.
          *
          * @param item the item to remove from the tree.
          */
          public void remove(T item)
          {
            BinaryNode<T> newRoot = removeItem(getRootNode(), item);
            setRootNode(newRoot);
          }

          /******
           * Private methods.
           *******/

           /**
            * This method finds the entry in the tree if it exists. If
            * the entry does not exist, null is returned.
            *
            * @param subTree the root of the subtree to search in.
            * @param item the item to look for.
            * @return the item or null if the item is not found.
            */
           private T findItem(BinaryNode<T> subTree, T item)
           {
             if (subTree != null)
             {
               if (subTree.getItem().equals(item))
                return subTree.getItem();
               else if (subTree.getItem().compareTo(item) < 0)
                return findItem(subTree.getLeftChild(), item);
               else
                return findItem(subTree.getRightChild(), item);
             }

             return null;
           }

           /**
            * Adds a new item to the binary tree.
            *
            * @param subTree the root of the subtree to add the item to.
            * @param item the item to add to the tree.
            * @return the item that was replaced or null if no item was
            * replaced.
            */
           private T addItem(BinaryNode<T> subTree, T item)
           {
             int comparison = subTree.getItem().compareTo(item);
             T result = null;

             if (comparison == 0) // Items are equal, replace the item.
             {
                result = subTree.getItem();
                subTree.setItem(item);
             }
             else if (comparison > 0) // Subtree root data is > than item.
             {
               if (subTree.getLeftChild() != null)
                result = addItem(subTree.getLeftChild(), item);
               else
                subTree.setLeftChild(new BinaryNode<T>(item));
             }
             else // Subtree root data is < than item.
             {
               if (subTree.getRightChild() != null)
                result = addItem(subTree.getRightChild(), item);
               else
                subTree.setRightChild(new BinaryNode<T>(item));
             }

             return result;
           }

           /**
            * Removes an item {@code item} from the tree.
            *
            * @param subTree the root of the subtree to remove the item from.
            * @param item the item to remove.
            * @return the new root of the binary tree.
            */
            private BinaryNode<T> removeItem(BinaryNode<T> subTree, T item)
            {
              int comparison = -1;

              if (subTree != null)
              {
                comparison = subTree.getItem().compareTo(item);

                if (comparison == 0) // They are equal.
                {
                  subTree = removeFromRoot(subTree);
                }
                else if (comparison > 0) // item is less than subTree.
                  subTree.setLeftChild(removeItem(subTree.getLeftChild(), item));
                else // Item is greater than subTree.
                  subTree.setRightChild(removeItem(subTree.getRightChild(), item));
              }

              return subTree;
            }

            /**
             * Removes a node from the root of the tree or subtree.
             *
             * @param subTree the root of the subtree.
             * @return the new root of the subtree.
             */
            private BinaryNode<T> removeFromRoot(BinaryNode<T> subTree)
            {
              // Handle the case that the subTree has a left and right subtree,
              // the most complicated case. Replace the node with its successor
              // (the right most node of the left child) and hook up the new
              // right child (the right subtree without the successor).
              if (subTree.getLeftChild() != null && subTree.getRightChild() != null)
              {
                BinaryNode<T> successor = findMin(subTree.getRightChild());

                subTree.setItem(successor.getItem());
                subTree.setRightChild(removeMin(subTree.getRightChild()));
              }

              // Root has at most one child.
              else if (subTree.getRightChild() != null)
                subTree = subTree.getRightChild();
              else
                subTree = subTree.getLeftChild();

              // At this point if subTree is a leaf, it is null.
              return subTree;
            }

            /**
             * Finds the smallest node in a subtree by walking the right spine.
             *
             * @param subTree the root of the subtree to find the largest value in.
             * @return the largest node in the subtree.
             */
            private BinaryNode<T> findMin(BinaryNode<T> subTree)
            {
              while (subTree.getLeftChild() != null)
                subTree = findMin(subTree.getLeftChild());
              return subTree;
            }

            /**
             * Removes the smallest node from the subtree rooted at {@code root}.
             *
             * @param subTree the root of the subtree to remove the node from.
             * @return the new root of the subtree.
             */
            private BinaryNode<T> removeMin(BinaryNode<T> subTree)
            {
              if (subTree.getLeftChild() != null)
                subTree.setLeftChild(removeMin(subTree.getLeftChild()));
              else
                subTree = subTree.getRightChild();
              return subTree;
            }


  }
