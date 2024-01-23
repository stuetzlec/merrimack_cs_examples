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
package datastructures.pair;

/**
 * An implementation of a simple generic ordered pair class.
 * @author Zach Kissel
 */
public class Pair<T>
{
  private T first;      // The first element of the pair.
  private T second;     // The second element of the pair.

  /**
   * The constructor initializes the pair to (first, second).
   *
   * @param first the first element of the pair.
   * @param second the second element of the pair.
   */
  public Pair(T first, T second)
  {
    this.first = first;
    this.second = second;
  }

  /**
   * Gets the first element of the pair.
   *
   * @return the first element of the pair.
   */
  public T getFirst()
  {
    return this.first;
  }

  /**
   * Gets the second element of the pair.
   *
   * @return the second element of the pair.
   */
  public T getSecond()
  {
    return this.second;
  }

  /**
   * Swaps the first and second element of the pair.
   */
  public void swap()
  {
    T temp = first;
    first = second;
    second = temp;
  }

  /**
   * Convert the pair to a string.
   *
   * @return a String representing the pair.
   */
   @Override
   public String toString()
   {
     return "(" + first + ", " + second + ")";
   }
}
