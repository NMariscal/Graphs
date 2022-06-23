package graphs;

import java.util.*;


// grafo sin direccion en los arcos(ELEdge)
public class ELGraph<V, E> implements Graph<V, E>{
    private final Set<ELVertex<V>> vertexList = new HashSet<>();
    private final Set<ELEdge<V, E>> edgeList = new HashSet<>();

    @Override
    public Collection<? extends Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public Collection<? extends Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Collection<? extends Edge<E>> incidentEdges(Vertex<V> v) {
        // sacamos los arcos del vertice dado
        HashSet<Edge<E>> incidentEdges = new HashSet<>();
        for(ELEdge<V, E> edge : edgeList){
            if (edge.getStartVertex() == v){
                incidentEdges.add(edge);
            }
            if (edge.getEndVertex() == v){
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        ELEdge<V, E> edge = checkEdge(e);
        if (edge.getStartVertex() == v){
            return edge.endVertex;
        }else if (edge.getEndVertex() == v){
            return edge.startVertex;
        }else{
            throw new RuntimeException("This vertex is not in the edge.");
        }
    }


    // Return both nodes of the edge
    @Override
    public List<Vertex<V>> endVertices(Edge<E> edge) {
        ArrayList<Vertex<V>> list = new ArrayList<>();
        ELEdge<V, E> e = checkEdge(edge);
        if (e.getEndVertex() != null){
            list.add(e.getEndVertex());
        }
        if (e.getStartVertex() != null){
            list.add(e.getStartVertex());
        }
        return list;
    }

    @Override
    public Edge<E> areAdjacent(Vertex<V> v1, Vertex<V> v2) {
        // Son adyacentes si comparten arco
        ELVertex<V> vertex1 = checkVertex(v1);
        ELVertex<V> vertex2 = checkVertex(v2);
        for (ELEdge<V, E> edge : edgeList){
            if((edge.getStartVertex() == vertex1 && edge.getEndVertex() == vertex2) || (edge.getEndVertex() == vertex1 && edge.getStartVertex() == vertex2)){
                return edge;
            }
        }
        return null;
    }

    //
    @Override
    public V replace(Vertex<V> vertex, V vertexValue) {
        ELVertex<V> v = checkVertex(vertex);
        V aux = vertex.getElement();
        v.setVertexValue(vertexValue);
        return aux;
    }

    @Override
    public E replace(Edge<E> edge, E edgeValue) {
        ELEdge<V, E> e = checkEdge(edge);
        E aux = edge.getElement();
        e.setEdge(edgeValue);
        return aux;
    }

    @Override
    public Vertex<V> insertVertex(V value) {
        ELVertex<V> newVertex = new ELVertex<>(value, this);
        vertexList.add(newVertex);
        return newVertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeValue) {
        if (!vertexList.contains(v1)){
            throw  new RuntimeException("The vertex v1 does not belong to this graph.");
        }
        if (!vertexList.contains(v2)){
            throw  new RuntimeException("The vertex v2 does not belong to this graph.");
        }
        ELEdge<V, E> newEdge = new ELEdge<>(edgeValue,this, v1, v2);
        // Para actualizar los arcos es necesario eliminarlo primero.
        if (edgeList.contains(edgeValue)){
            edgeList.remove(edgeValue);
        }
        edgeList.add(newEdge);
        return newEdge;
    }

    @Override
    public V removeVertex(Vertex<V> vertex) {
        ELVertex<V> v = checkVertex(vertex);
        vertexList.remove(v);
        // CUIDADO !! No puedes iterar y eliminar a la vez
        ArrayList<ELEdge<V,E>> remove = new ArrayList<>();
        for (ELEdge<V, E> edge : edgeList){
            if (edge.getStartVertex() == v || edge.getEndVertex() == v){
                remove.add(edge);
            }
        }
        for (ELEdge<V,E> e : remove){
            edgeList.remove(e);
        }
        return v.getElement();
    }

    @Override
    public E removeEdge(Edge<E> edge) {
        ELEdge<V, E> e = checkEdge(edge);
        E aux = edge.getElement();
        edgeList.remove(e);
        return aux;
    }

    private ELEdge<V, E> checkEdge(Edge<E> edge) {
        if (edge instanceof ELEdge){
            ELEdge<V, E> e = (ELEdge<V, E>)edge;
            if (e.getGraph() == this)
                return e;
        }
        throw new RuntimeException("The edge is not in the graph");
    }

    private ELVertex<V> checkVertex(Vertex<V> vertex) {
        if (vertex instanceof ELVertex){
            ELVertex<V> v = (ELVertex<V>)vertex;
            if (v.getGraph() == this)
                return v;
        }
        throw new RuntimeException("The vertex is not in the graph");
    }

    class ELVertex<V> implements Vertex<V>{
        private V vertexValue;
        private final Graph<V,E> graph;

        @Override
        public V getElement() {
            return vertexValue;
        }

        public ELVertex(V vertexValue, Graph<V, E> graph) {
            this.vertexValue = vertexValue;
            this.graph = graph;
        }

        public V getVertexValue() {
            return vertexValue;
        }

        public void setVertexValue(V vertexValue) {
            this.vertexValue = vertexValue;
        }

        public Graph<V, E> getGraph() {
            return graph;
        }
    }
    class ELEdge<V, E> implements Edge<E> {
        private E edge;
        private final Graph<V, E> graph;
        private final Vertex<V> startVertex;
        private final Vertex<V> endVertex;

        @Override
        public E getElement() {
            return edge;
        }

        public ELEdge(E edge, Graph<V, E> graph, Vertex<V> startVertex, Vertex<V> endVertex) {
            this.edge = edge;
            this.graph = graph;
            this.startVertex = startVertex;
            this.endVertex = endVertex;
        }

        public E getEdge() {
            return edge;
        }

        public void setEdge(E edge) {
            this.edge = edge;
        }

        public Graph<V, E> getGraph() {
            return graph;
        }

        public Vertex<V> getStartVertex() {
            return startVertex;
        }

        public Vertex<V> getEndVertex() {
            return endVertex;
        }
    }


}
