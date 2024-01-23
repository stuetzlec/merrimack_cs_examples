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
 * A generic node for chaining.
 *
 * @param <T> the object stored in the list.
 * @author Zach Kissel
 */
 public class Node<T>
 {
   private T item;          // The nodes data.
   private Node<T> next;    // The next reference.

   /**
    * The default constructor. It sets the next reference to null.
    */
    public Node()
    {
      this.next = null;
    }

    /**
     * This is the overloaded constructor that sets the item
     * to itm and the next reference to null.
     *
     * @param itm the item stored in the node.
     */
     public Node(T itm)
     {
        this.item = itm;
        this.next = null;
     }

     /**
      * This is the overloaded constructor that sets the item to itm and the
      * next reference to nxt.
      *
      * @param itm the item to be stored in the node.
      * @param next the reference to the next node in the chain.
      */
     public Node(T itm, Node<T> nxt)
     {
        this.item = itm;
        this.next = nxt;
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
     public void setNext(Node<T> next)
     {
       this.next = next;
     }

     /**
      * Return the reference to the next node in the chain.
      *
      * @return the reference to the next node in the chain.
      */
     public Node<T> getNext()
     {
       return this.next;
     }
 }
