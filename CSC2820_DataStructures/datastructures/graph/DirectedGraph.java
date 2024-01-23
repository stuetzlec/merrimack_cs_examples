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

import datastructures.dictionary.LinkedDictionary;

/**
 * Implementation of a directed graph as an adjacency list.
 * @author Zach Kissel
 */
 public class DirectedGraph<T extends Comparable<? super T>> implements GraphInterface<T>
 {
    private LinkedDictionary<T, Vertex<T>> vertices;
    private int edgeCount;

    /**
     * Construct a new empty digraph.
     */
     public DirectedGraph()
     {
       vertices = new LinkedDictionary<>();
       edgeCount = 0;
     }

     /**
      * Adds a vertex with label {@code label} to the graph.
      *
      * @param label a unique lable for the vertex in the graph.
      * @return true if the vertex is added; otherwise, false.
      */
     public boolean addVertex(T label)
     {
       if (!vertices.contains(label))
        return vertices.add(label, new Vertex<T>(label)) == null;
       return true;
     }

     /**
      * Adds an edge from verted {@code begin} to vertex {@code end} with
      * weight {@code weight}.
      *
      * @param begin the label of the origin vertex.
      * @param end the label of the destination vertex.
      * @param weight the weight of the edge.
      * @return true if the edge is added; otherwise, false.
      */
     public boolean addEdge(T begin, T end, double weight)
     {
       Vertex<T> beginV = vertices.getValue(begin);
       Vertex<T> endV = vertices.getValue(end);

       // If we can't find one of the end points, we can't have an edge.
       if (beginV == null || endV == null)
        return false;

       // Connect the edge.
       if (beginV.connect(endV, weight))
       {
         edgeCount++;
         return true;
       }

       return false;
     }

     /**
      * Adds an edge from verted {@code begin} to vertex {@code end} with
      * weight {@code weight}.
      *
      * @param begin the label of the origin vertex.
      * @param end the label of the destination vertex.
      * @param weight the weight of the edge.
      * @return true if the edge is added; otherwise, false.
      */
     public boolean addEdge(T begin, T end)
     {
       return addEdge(begin, end, 0);
     }

     /**
      * Determines if there is an edge from {@code begin} to {@code end}.
      *
      * @return true if there is an edge from {@code begin} to {@code end};
      * otherwise, false.
      */
     public boolean hasEdge(T begin, T end)
     {
        Vertex<T> beginV;

        if (vertices.isEmpty())
          return false;

        beginV = vertices.getValue(begin);

        if (beginV != null)
          return beginV.hasEdge(end);
        return false;
     }

     /**
      * Determines if the graph is empty.
      *
      * @return true if the graph is empty; otherwise, false.
      */
     public boolean isEmpty()
     {
       return vertices.isEmpty();
     }

     /**
      * Get the number of vertices in the graph.
      *
      * @return the number of vertices in the graph (greater than or
      * equal to zero).
      */
     public int getNumberOfVertices()
     {
       return vertices.getSize();
     }

     /**
      * Get the number of edges in the graph.
      *
      * @return the number of edges in the graph (greater than or
      * equal to zero).
      */
     public int getNumberOfEdges()
     {
       return edgeCount;
     }

     /**
      * Remove all vertices and edges from the graph.
      */
     public void clear()
     {
       vertices.clear();
       edgeCount = 0;
     }
 }
