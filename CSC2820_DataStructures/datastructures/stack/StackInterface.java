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
package datastructures.stack;

import datastructures.exceptions.EmptyStackException;

/**
 * A basic stack interface.
 */
public interface StackInterface<T>
{
  /**
   * Returns true if the stack is empty and false otherwise.
   *
   * @return true if stack is empty; otherwise false.
   */
  public boolean isEmpty();

  /**
   * Pushes an element onto the top of the stack.
   *
   * @param newEntry the entry to add to the top of the stack.
   */
  public void push(T newEntry);

  /**
   * Removes the top element from the stack and returns it.
   *
   * @return the top element of the stack.
   * @throws EmptyStackException if called on an empty stack.
   */
  public T pop() throws EmptyStackException;

  /**
   * Returns a copy of the top element of the stack.
   *
   * @return the top elment on the stack.
   * @throws EmptyStackException if called on an empty stack.
   */
  public T peek() throws EmptyStackException;

  /**
   * Removes all entries from the stack.
   */
  public void clear();
}
