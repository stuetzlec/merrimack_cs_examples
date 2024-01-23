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
 * A basic binary node.
 * @author Zach Kissel
 */
public class BinaryNode<T>
{
  private BinaryNode<T> leftChild;
  private BinaryNode<T> rightChild;
  private T item;

   /**
    * The default constructor builds an empty binary node.
    */
   public BinaryNode()
   {
     leftChild = null;
     rightChild = null;
   }

   /**
    * This constructor creates a node that stores item {@code item}.
    *
    * @param item the data to store in the node.
    */
   public BinaryNode(T item)
   {
     this.item = item;
     leftChild = null;
     rightChild = null;
   }

   /**
    * This constructor creates a node with data {@code item}, left
    * subtree {@code leftChild}, and right child {@code rightChild}.
    *
    * @param item the data to store in the root.
    * @param leftChild a reference to the left child.
    * @param rightChild a reference to the right child.
    */
   public BinaryNode(T item, BinaryNode<T> leftChild, BinaryNode<T> rightChild)
   {
     this.item = item;
     this.leftChild = leftChild;
     this.rightChild = rightChild;
   }

   /**
    * Set the nodes item to {@code item}
    *
    * @param item the item to store in the node.
    */
   public void setItem(T item)
   {
     this.item = item;
   }

   /**
    * Get the item stored in the node.
    *
    * @return the item stored in the node.
    */
   public T getItem()
   {
     return item;
   }

   /**
    * Set the left child to {@code leftChild}.
    *
    * @param sets the left child this node should refer to.
    */
   public void setLeftChild(BinaryNode<T> leftChild)
   {
     this.leftChild = leftChild;
   }

   /**
    * Gets a reference to the left child.
    *
    * @param a reference to the left child of the node.
    */
   public BinaryNode<T> getLeftChild()
   {
     return leftChild;
   }

   /**
    * Set the right child to {@code rightChild}.
    *
    * @param sets the right child this node should refer to.
    */
   public void setRightChild(BinaryNode<T> rightChild)
   {
     this.rightChild = rightChild;
   }

   /**
    * Gets a reference to the right child.
    *
    * @param a reference to the right child of the node.
    */
   public BinaryNode<T> getRightChild()
   {
     return rightChild;
   }

   /**
    * Deteremine if the node is a leaf.
    *
    * @return true if the node is a leaf; otherwise, false.
    */
   public boolean isLeaf()
   {
     return (leftChild == null && rightChild == null);
   }

   /**
    * Determine the height of the node.
    *
    * @return a number > 0 representing the height of the node.
    */
    public int getHeight()
    {
      return getHeight(this);
    }

    /**
     * Determine the number of nodes associated with this node.
     *
     * @return an number > 0, that represents the number of nodes associated
     * with this node.
     */
     public int getNumberOfNodes()
     {
       int leftNumber = 0;
       int rightNumber = 0;

       if (leftChild != null)
        leftNumber = leftChild.getNumberOfNodes();
       if (rightChild != null)
        rightNumber = rightChild.getNumberOfNodes();

       return 1 + leftNumber + rightNumber;
     }

     /**
      * Copies the subtree rooted at {@code this} node.
      *
      * @return the root of a copy of the subtre rooted at {@code this} node.
      */
     public BinaryNode<T> copy()
     {
       BinaryNode<T> newRoot = new BinaryNode<T>(item);

       if (leftChild != null)
        newRoot.setLeftChild(leftChild.copy());
       if (rightChild != null)
        newRoot.setRightChild(rightChild.copy());

       return newRoot;
     }

    /*****
     * Private Methods.
     *****/

     /**
      * Recursively determines the hight of node {@code node}.
      *
      * @param node the node to determine the height of.
      * @return a number > 0 that represents the height of the node.
      */
     private int getHeight(BinaryNode<T> node)
     {
       int height = 0;
       if (node != null)
        height = 1 + Math.max(getHeight(node.getLeftChild()),
            getHeight(node.getRightChild()));
       return height;
     }

}
