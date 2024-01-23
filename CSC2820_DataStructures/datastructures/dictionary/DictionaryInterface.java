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
 * A simple interface describing the dictionary ADT.
 *
 * @param K the type of keys used.
 * @param V the type of values used.
 * @author Zach Kissel
 */
 public interface DictionaryInterface <K extends Comparable<? super K>, V>
 {
   /**
    * Adds a new entry with key {@code key} and value {@code val}.
    *
    * @param key the key associated with the entry.
    * @param val the value associated with the entry.
    * @return null if a new entry was added or the entry that was replaced.
    */
   public V add(K key, V val);

   /**
    * Removes the entry with key {@code key} from the dictionary.
    *
    * @param key the key associated with the entry to remove.
    * @return the value associated with the key or null.
    */
   public V remove(K key);

   /**
    * Gets the entry with key {@code key} from the dictionary.
    *
    * @param key the key associated with the entry to retrieve
    * @return the value associated with the key or null.
    */
   public V getValue(K key);

   /**
    * Determines if the dictionary contains an entry with key {@code key}.
    *
    * @param key the key associated with the entry to find.
    * @return true if the key was found in the dictionary; otherwise, false.
    */
   public boolean contains(K key);

   /**
    * Determines if the dictionary is empty.
    *
    * @return true if the dictionary is empty; otherwise, false.
    */
   public boolean isEmpty();

   /**
    * Gets the size of the dictionary.
    *
    * @return the number of entries currently in the dictionary.
    */
   public int getSize();

   /**
    * Removes all entries from the dictionary.
    */
   public void clear();

 }
