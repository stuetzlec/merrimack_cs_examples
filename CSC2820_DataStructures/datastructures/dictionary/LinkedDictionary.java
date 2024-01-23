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
 * A linked dictionary is a dictionary that uses a hash table with separate
 * chaining.
 * @param K the type of the keys.
 * @param V the type of the values.
 *
 * @author Zach Kissel
 */
 public class LinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>
 {
   private Entry<K, V>[] table;
   private int numEntries;    // Number of entries in the dictionary.

   /**
    * Creates a new dictionary with default numEntries of 11.
    * Note: for optimal use, the numEntries of the dictionary should be a prime
    * number.
    */
    public LinkedDictionary()
    {
      this(11);
    }

   /**
    * Creates a new dictionary with numEntries sz.
    */
  @SuppressWarnings("unchecked")
   public LinkedDictionary(int sz) throws IllegalArgumentException
   {
     if (sz <= 0)
      throw new IllegalArgumentException();

     this.table = new Entry[sz];
     this.numEntries = 0;
   }

   /**
    * Adds a new entry with key {@code key} and value {@code val}.
    *
    * @param key the key associated with the entry.
    * @param val the value associated with the entry.
    * @return null if a new entry was added or the entry that was replaced.
    */
   public V add(K key, V val)
   {
     Entry<K, V> entry;
     V tmp;

     // If the key is already found in the table, replace the value
     // with the new value.
     entry = findInChain(table[getHashIndex(key)], key);
     if (entry != null)
     {
      tmp = entry.getValue();
      entry.setValue(val);
      return tmp;
     }

     // At this point we did not find the key so we must add our
     // new entry to the head of the appropriate chain.
     entry = new Entry<K, V>(key, val, table[getHashIndex(key)]);
     table[getHashIndex(key)] = entry;
     this.numEntries++;
     return null;
   }

   /**
    * Removes the entry with key {@code key} from the dictionary.
    *
    * @param key the key associated with the entry to remove.
    * @return the value associated with the key or null.
    */
   public V remove(K key)
   {
     Entry<K, V> entry;
     Entry<K, V> tmp;
     Entry<K, V> prev = null;

     for (entry = table[getHashIndex(key)]; entry != null;
                entry = entry.getNext()) {
          if (entry.getKey().equals(key))
          {
              tmp = entry;
              if (prev != null)
                  prev.setNext(entry.getNext());
              else
                  table[getHashIndex(key)] = table[getHashIndex(key)].getNext();
              this.numEntries--;
              return tmp.getValue();
          }
          prev = entry;
     }
     return null;
   }

   /**
    * Gets the entry with key {@code key} from the dictionary.
    *
    * @param key the key associated with the entry to retrieve
    * @return the value associated with the key or null.
    */
   public V getValue(K key)
   {
     Entry<K, V> entry =  findInChain(table[getHashIndex(key)], key);

     if (entry != null)
        return entry.getValue();
     return null;
   }

   /**
    * Determines if the dictionary contains an entry with key {@code key}.
    *
    * @param key the key associated with the entry to find.
    * @return true if the key was found in the dictionary; otherwise, false.
    */
   public boolean contains(K key)
   {

     return findInChain(table[getHashIndex(key)], key) != null;
   }

   /**
    * Determines if the dictionary is empty.
    *
    * @return true if the dictionary is empty; otherwise, false.
    */
   public boolean isEmpty()
   {
     return this.numEntries == 0;
   }

   /**
    * Gets the number of entries of the dictionary.
    *
    * @return the number of entries currently in the dictionary.
    */
   public int getSize()
   {
     return this.numEntries;
   }

   /**
    * Removes all entries from the dictionary.
    */
   public void clear()
   {
     for (int i = 0; i < table.length; i++)
      table[i] = null;
     numEntries = 0;
   }

   /********
    * Private Methods
    ********/

    /**
     * Finds a location for the key {@code key}.
     *
     * @param key the key to insert.
     * @return an index in the table, a number between 0 and numEntries - 1 inclusive
     */
    private int getHashIndex(K key)
    {
      int idx = key.hashCode() % table.length;

      if (idx < 0)
        idx += table.length;

      return idx;
    }

    /**
     * Finds an entry in the chain and returns it to the caller.
     *
     * @param head the head node of the chain.
     * @param key the key to search the chain for.
     * @return the node that mathces the key {@code key} or null.
     */
    private Entry<K, V> findInChain(Entry<K, V> head, K key)
    {
      Entry<K, V> walker;

      for(walker = head; walker != null; walker = walker.getNext())
        if (walker.getKey().equals(key))
          return walker;
      return null;
    }


 }
