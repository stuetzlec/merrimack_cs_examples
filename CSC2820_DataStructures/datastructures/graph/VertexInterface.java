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
 * An interface that describes a graph vertex.
 * @author Zach Kissel
 */
 public interface VertexInterface<T>
 {
   /**
    * Gets the vertex's label.
    *
    * @return the label associated with the vertex.
    */
   public T getLabel();

   /**
    * Connects this vertex to {@code end} with an edge.
    *
    * @param end the vertex to connect to.
    * @return true if the edge is added; otherwise, false.
    */
   public boolean connect(VertexInterface<T> end);

   /**
    * Connects this vertex to {@code end} with an edge of weight {@code weight}.
    *
    * @param end the vertex to connect to.
    * @param weight the weight of the edge.
    * @return true if the edge is added; otherwise, false.
    */
   public boolean connect(VertexInterface<T> end, double weight);

   /**
    * Determines if this vertex has at least one neighbor.
    *
    * @return true if the this vertex has a neighbor; otherwise, false.
    */
   public boolean hasNeighbor();

   /**
    * Determines if there is an edge from this vertex to {@code end}.
    *
    * @param endL the label of the vertex we may be connected to.
    */
    public boolean hasEdge(T endL);

    /**
     * Marks the vertex as visited.
     */
     public void setVisited();

     /**
      * Marks the vertex as unvisited.
      */
      public void setUnvisited();

      /**
       * Determines if the node has been visited.
       *
       * @return true if the node has been visited; otherwise, false.
       */
       public boolean isVisited();

       /**
        * Gets a copy of the neighbor vertices labels.
        *
        * @return an array of Objects representing the neighboring vertices
        * labels.
        */
        public Object[] getNeighbors();
 }
