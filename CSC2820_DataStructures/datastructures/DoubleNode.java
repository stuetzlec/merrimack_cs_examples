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
package datastructures;

/**
 * A generic DoubleNode for chaining.
 *
 * @param <T> the object that is stored in the node.
 * @author Zach Kissel
 */
 public class DoubleNode<T>
 {
   private T item;          // The DoubleNodes data.
   private DoubleNode<T> next;    // Reference to next node
   private DoubleNode<T> prev;    // Reference to previous node.

   /**
    * The default constructor. It sets the next reference to null.
    */
    public DoubleNode()
    {
      this.next = null;
    }

    /**
     * This is the overloaded constructor that sets the item
     * to itm and the next and prev reference to null.
     *
     * @param itm the item stored in the DoubleNode.
     */
     public DoubleNode(T itm)
     {
        this.item = itm;
        this.next = null;
        this.prev = null;
     }

     /**
      * This is the overloaded constructor that sets the item to itm and the
      * next reference to nxt.
      *
      * @param itm the item to be stored in the node.
      * @param nxt the reference to the next node in the chain.
      * @param prev the reference to the prev node in the chain.
      */
     public DoubleNode(T itm, DoubleNode<T> nxt, DoubleNode<T> prev)
     {
        this.item = itm;
        this.next = nxt;
        this.prev = prev;
     }

     /**
      * Set the item field of the node to item.
      *
      * @param item the item to store in the node.
      */
     public void setItem(T item)
     {
       this.item = item;
     }

     /**
      * Get the value stored in the item field of the node.
      *
      * @return the item stored in the node.
      */
     public T getItem()
     {
       return item;
     }

     /**
      * Set the value of the next reference.
      *
      * @param next the reference to the next node in the chain.
      */
     public void setNext(DoubleNode<T> next)
     {
       this.next = next;
     }

     /**
      * Return the reference to the next node in the chain.
      *
      * @return the reference to the next node in the chain.
      */
     public DoubleNode<T> getNext()
     {
       return this.next;
     }

     /**
      * Set the value of the previous reference.
      *
      * @param prev the reference to the previous node in the chain.
      */
     public void setPrev(DoubleNode<T> prev)
     {
       this.prev = prev;
     }

     /**
      * Return the reference to the previous node in the chain.
      *
      * @return the reference to the previous node in the chain.
      */
     public DoubleNode<T> getPrev()
     {
       return this.prev;
     }
 }
