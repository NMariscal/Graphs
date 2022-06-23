package graphs;

import graphs.Edge;
import graphs.Graph;
import graphs.Position;
import graphs.Vertex;
import others.NAryTree.LinkedTree;

import java.util.*;


public class BreadthSearch<V, E> {

    enum Label {DISCOVERY, CROSS};

    /*private List<Edge<E>> pathToRoot(Graph<V, E> g, Position<Vertex<V>> node, LinkedTree<Vertex<V>> tree) {
        List<Edge<E>> result = new LinkedList<>();

        while(node != tree.root()) {
            Position<Vertex<V>> parent = tree.parent(node);
            Edge<E> edge = g.areAdjacent(node.getElement(), parent.getElement());
            result.add(0,edge);
            node = parent;
        }

        return result;
    }

    /**
     * Get the path between two vertex with minimun number of nodes.
     */
    /*public List<Edge<E>> getPath(Graph<V, E> graph, Vertex<V> startVertex, Vertex<V> endVertex) {
        LinkedTree <Vertex<V>> tree = new LinkedTree<>();
        HashMap<Edge<E>, Label> edgeLabels = new HashMap<>();

        Queue <Position<Vertex<V>>> queue = new LinkedList<>();
        HashSet<Vertex<V>> visitedNodes = new HashSet<>();

        visitedNodes.add(startVertex);
        tree.addRoot(startVertex);
        queue.add(tree.root());

        while (!queue.isEmpty()) {
            Position<Vertex<V>> vetexPos = queue.poll();
            Vertex <V> vertex = vetexPos.getElement();
            for (Edge<E> edge : graph.incidentEdges(vertex)) {
                if (edgeLabels.get(edge) == null) {
                    Vertex<V> nextNode = graph.opposite(vertex, edge);
                    if (!visitedNodes.contains(nextNode)) {
                        edgeLabels.put(edge,Label.DISCOVERY);
                        visitedNodes.add(nextNode);
                        Position<Vertex<V>> treeNode = tree.add(nextNode, vetexPos);
                        queue.add(treeNode);
                        if (nextNode == endVertex) {
                            return pathToRoot(graph, treeNode, tree);
                        }
                    } else {
                        edgeLabels.put(edge,Label.CROSS);
                    }
                }
            }
        }
        return null;
    }*/
}
