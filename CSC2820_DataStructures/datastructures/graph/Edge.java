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
 * Represents an edge in a graph.
 * @author Zach Kissel
 */
 public class Edge<T>
 {
    private VertexInterface<T> vertex;
    private double weight;

    /**
     * Sets the edge between this vertex and {@cod end} with
     * weight {@code weight}.
     *
     * @param end the other end of the edge.
     * @param weight the weight of the edge.
     */
    public Edge(VertexInterface<T> end, double weight)
    {
      this.vertex = end;
      this.weight = weight;
    }

    /**
     * Sets the edge between this vertex and end.
     *
     * @param end the other end of the edge.
     */
    public Edge (VertexInterface<T> end)
    {
      this(end, 0);
    }

    /**
     * Get the vertex associated with this edge.
     *
     * @return a vertex associated with the edge.
     */
    public VertexInterface<T> getEndVertex()
    {
      return vertex;
    }

    /**
     * Get the weight associated with the edge.
     *
     * @return the weight associated iwth the edge.
     */
    public double getWeight()
    {
      return weight;
    }
 }
