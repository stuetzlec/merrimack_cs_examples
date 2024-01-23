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
import datastructures.Node;

/**
 * This class implements a basic Linked Stack.
 * @author Zach Kissel
 */
public class LinkedStack<T> implements StackInterface<T>
{

  private Node<T> tos;    /* Represents the top of the stack. */

   /**
    * The default constructor sets the top of stack (tos) to null.
    */
   public LinkedStack()
   {
     tos = null;
   }

  /**
   * This method returns true if the stack is empty and false otherwise.
   *
   * @return true if the stack is empty; otherwise, false.
   */
  public boolean isEmpty()
  {
    return (tos == null);
  }

  /**
   * Pushes and item {@code item} onto the stack.
   */
  public void push(T item)
  {
    Node<T> newNode = new Node<T>(item, tos);
    tos = newNode;
  }

  /**
   * Pops the top element of the stack.
   *
   * @return the element popped off the stack.
   * @throws EmptyStackException if the stack is empty.
   */
  public T pop() throws EmptyStackException
  {
    Node<T> toPop = tos;

    // If the stack is not empty, adjust to tos value and return true.
    if (!isEmpty())
    {
      tos = tos.getNext();
      toPop.setNext(null);
      return toPop.getItem();
    }
    else
      throw new EmptyStackException();
  }

  /**
   * Peeks at the value of the top of stack and returns that element to
   * the caller. There is no change to the stack itself.
   *
   * @return the element at the top of the stack.
   * @throws EmptyStackException if the stack is empty.
   **/
  public T peek() throws EmptyStackException
  {
    if (isEmpty())
      throw new EmptyStackException();
    return tos.getItem();
  }

  /**
   * Removes all entries from the stack.
   */
  public void clear()
  {
    // Easiest way to clear the stack is to just
    // pop all of the entries.
    try
    {
      while(!isEmpty())
        pop();
    }
    catch (EmptyStackException ex)
    {
      // Should never happen unless isEmpty() has an implementation
      // problem.
      System.err.println("LinkedStack.isEmpty() has an implementation problem.");
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
