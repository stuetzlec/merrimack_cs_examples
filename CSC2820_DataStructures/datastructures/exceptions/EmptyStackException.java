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
package datastructures.exceptions;

/**
 * A simple empty stack exception.
 * @author Zach Kissel
 */
public class EmptyStackException extends RuntimeException
{
  /**
   * Just provide the default constructor. We don't want
   * to allow the user to choose the message associated
   * with the exception.
   */
  public EmptyStackException()
  {
    super("Stack is empty.");
  }
}
