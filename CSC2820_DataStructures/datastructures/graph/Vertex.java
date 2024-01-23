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

import datastructures.Node;

/**
 * A simple vertex.
 * @author Zach Kissel
 */
 public class Vertex<T> implements VertexInterface<T>
 {
   private T label;
   private boolean visited;
   private Node<Edge<T>> edgeList;
   private int numEdges;

   /**
    * Creates a new vertex with label {@code label}.
    */
   public Vertex(T label)
   {
     this.label = label;
     edgeList = null;
     visited = false;
     numEdges = 0;
   }

   /**
    * Gets the vertex's label.
    *
    * @return the label associated with the vertex.
    */
   public T getLabel()
   {
     return this.label;
   }

   /**
    * Connects this vertex to {@code end} with an edge.
    *
    * @param end the vertex to connect to.
    * @return true if the edge is added; otherwise, false.
    */
   public boolean connect(VertexInterface<T> end)
   {
     return connect(end, 0);
   }

   /**
    * Connects this vertex to {@code end} with an edge of weight {@code weight}.
    *
    * @param end the vertex to connect to.
    * @param weight the weight of the edge.
    * @return true if the edge is added; otherwise, false.
    */
   public boolean connect(VertexInterface<T> end, double weight)
   {
     // Walk the edge chain checking to see if the vertex is
     // in the list. If it is, return false; otherwise, add a new edge
     // to the list.
     for (Node<Edge<T>> walker = edgeList; walker != null; walker = walker.getNext())
      if (walker.getItem().getEndVertex().equals(end))
        return false;

     // No such edge was found, add it.
     Node<Edge<T>> newEdge = new Node<Edge<T>>(new Edge<T>(end, weight), edgeList);
     edgeList = newEdge;

     // Increment the number of edges.
     numEdges++;

     return true;
   }

   /**
    * Determines if this vertex has at least one neighbor.
    *
    * @return true if the this vertex has a neighbor; otherwise, false.
    */
   public boolean hasNeighbor()
   {
     return edgeList != null;
   }

   /**
    * Determines if there is an edge from this vertex to {@code end}.
    *
    * @param endLabel the label of the vertex we may be connected to.
    */
    public boolean hasEdge(T endL)
    {
      for (Node<Edge<T>> walker = edgeList; walker != null; walker = walker.getNext())
        if (walker.getItem().getEndVertex().getLabel().equals(endL))
          return true;
      return false;
    }

   /**
    * Determine if two vertices are equal.
    *
    * @return true if the vertices are equal; otherwise, false.
    */
    @Override
    public boolean equals(Object obj)
    {
      if (obj != null && (getClass() == obj.getClass()))
      {
        @SuppressWarnings("unchecked")
        Vertex<T> other = (Vertex<T>) obj;
        return this.label.equals(other.getLabel());
      }
      return false;
    }

    /**
     * Marks the vertex as visited.
     */
     public void setVisited()
     {
       this.visited = true;
     }

     /**
      * Marks the vertex as unvisited.
      */
      public void setUnvisited()
      {
        this.visited = false;
      }

      /**
       * Determines if the node has been visited.
       *
       * @return true if the node has been visited; otherwise, false.
       */
       public boolean isVisited()
       {
         return this.visited;
       }

       /**
        * Gets a copy of the neighbor vertices labels.
        *
        * @return an array of Objects representing the neighboring vertices
        * labels.
        */
        public Object[] getNeighbors()
        {
          Object[] neighbors = new Object[numEdges];
          int idx  = 0;

          for (Node<Edge<T>> walker = edgeList; walker != null;
              walker = walker.getNext())
              neighbors[idx++] = walker.getItem().getEndVertex().getLabel();

          return neighbors;
        }
 }
