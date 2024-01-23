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
package datastructures.dictionary;

/**
 * Represents a basic key-value entry.
 *
 * @param K the key type.
 * @param V the value type.
 *
 * @author Zach Kissel
 */
 public class Entry<K, V>
 {
   private K key;
   private V val;
   private Entry<K, V> next;


   /**
    * Constructs an empty node.
    * @param key the key associated with the entry.
    * @param val the value associated with the entry.
    */
   public Entry()
   {
     this(null, null, null);
   }

   /**
    * Constructs a new entry node with key {@code key} and value {@code val}.
    */
   public Entry(K key, V val)
   {
     this(key, val, null);
   }

   /**
    * Constructs a new entry node with key {@code key} and value {@code val}
    * and next reference {@code next}.
    * @param key the key associated with the entry.
    * @param val the value associated with the entry.
    * @param next the reference to the next node in the chain.
    */
   public Entry(K key, V val, Entry<K, V> next)
   {
     this.key = key;
     this.val = val;
     this.next = next;
   }

   /**
    * Gets the key associated with this node.
    *
    * @return the key associated with the node.
    */
   public K getKey()
   {
     return this.key;
   }

   /**
    * Gets the value associated with this node.
    *
    * @return the value associated with the node.
    */
    public V getValue()
    {
      return this.val;
    }

      /**
       * Set the value to {@code val}.
       */
      public void setValue(V val)
      {
        this.val = val;
      }

      /**
       * Set the value of the next reference.
       *
       * @param next the reference to the next node in the chain.
       */
      public void setNext(Entry<K, V> next)
      {
        this.next = next;
      }

      /**
       * Return the reference to the next node in the chain.
       *
       * @return the reference to the next node in the chain.
       */
      public Entry<K, V> getNext()
      {
        return this.next;
      }

 }
