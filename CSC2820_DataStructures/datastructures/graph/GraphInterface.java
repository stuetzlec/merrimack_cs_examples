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
package datastructures.graph;

/**
 * The basic interface for a graph.
 * @author Zach Kissel
 */
public interface GraphInterface<T>
{
  /**
   * Adds a vertex with label {@code label} to the graph.
   *
   * @param label a unique lable for the vertex in the graph.
   * @return true if the vertex is added; otherwise, false.
   */
  public boolean addVertex(T label);

  /**
   * Adds an edge from verted {@code begin} to vertex {@code end} with
   * weight {@code weight}.
   *
   * @param begin the label of the origin vertex.
   * @param end the label of the destination vertex.
   * @param weight the weight of the edge.
   * @return true if the edge is added; otherwise, false.
   */
  public boolean addEdge(T begin, T end, double weight);

  /**
   * Adds an edge from verted {@code begin} to vertex {@code end} with
   * weight {@code weight}.
   *
   * @param begin the label of the origin vertex.
   * @param end the label of the destination vertex.
   * @param weight the weight of the edge.
   * @return true if the edge is added; otherwise, false.
   */
  public boolean addEdge(T begin, T end);

  /**
   * Determines if there is an edge from {@code begin} to {@code end}.
   *
   * @return true if there is an edge from {@code begin} to {@code end};
   * otherwise, false.
   */
  public boolean hasEdge(T begin, T end);

  /**
   * Determines if the graph is empty.
   *
   * @return true if the graph is empty; otherwise, false.
   */
  public boolean isEmpty();

  /**
   * Get the number of vertices in the graph.
   *
   * @return the number of vertices in the graph (greater than or
   * equal to zero).
   */
  public int getNumberOfVertices();

  /**
   * Get the number of edges in the graph.
   *
   * @return the number of edges in the graph (greater than or
   * equal to zero).
   */
  public int getNumberOfEdges();

  /**
   * Remove all vertices and edges from the graph.
   */
  public void clear();
}
