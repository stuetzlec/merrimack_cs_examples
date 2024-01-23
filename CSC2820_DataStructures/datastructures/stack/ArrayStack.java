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

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * A basic implementation of an array based stack. To create an ever growing
 * stack, table doubling is used.
 */
public class ArrayStack<T> implements StackInterface<T>
{
  private T[] items;
  private static int CAPACITY = 100;
  private int tos;

   /**
    * The default constructor sets up an empty stack of size CAPACITY.
    */
   public ArrayStack()
   {
     this(CAPACITY);
   }

   /**
    * This constructor creates a stack with intial size sz.
    * @throws IllegalArgumentException if the size is < 0.
    */
    public ArrayStack(int sz) throws IllegalArgumentException
    {
      tos = -1;

      if (sz <= 0)
       throw new IllegalArgumentException("Size must be > 0");

      // In general you should never suppress warnings but, in this case,
      // we know that cast will be valid since all entries are null.
      @SuppressWarnings("unchecked")
      T[] temp = (T[]) new Object[sz];
      items = temp;
    }

  /**
   * This method returns true if the stack is empty and false otherwise.
   *
   * @return true if the stack is empty; otherwise, false.
   */
  public boolean isEmpty()
  {
    return (tos < 0);
  }

  /**
   * Pushes item {@code item} onto the stack. If there is no room on the
   * stack the stack size is doubled.
   */
  public void push(T item)
  {
    // If there is room, insert the element.
    if ((tos + 1) < items.length)
      items[++tos] = item;
    else
    {
      items = Arrays.copyOf(items, 2 * items.length);
      items[++tos] = item;
    }
  }

  /**
   * Pops the element on the top of the stack and returns it to the caller.
   *
   * @return the element at the top of the stack.
   * @throws EmptyStackException if the stack is empty.
   */
  public T pop() throws EmptyStackException
  {
    // If the stack is not empty, adjust to tos value and return true.
    if (!isEmpty())
    {
      tos--;
      return items[tos + 1];
    }
    else
      throw new EmptyStackException();
  }

  /**
   * Returns the element at the top of the stack without modifying the
   * stack.
   *
   * @return the element at the top of the stack.
   * @throws EmptyStackException if the stack is empty.
   */
  public T peek() throws EmptyStackException
  {
    if (isEmpty())
      throw new EmptyStackException();
    return items[tos];
  }

  /**
   * Clear the stack.
   */
   public void clear()
   {
     tos = -1;
   }

}
