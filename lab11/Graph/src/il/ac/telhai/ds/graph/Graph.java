package il.ac.telhai.ds.graph;

import java.util.LinkedList;
import java.util.TreeMap;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E> {
    private class Edge {

        private V v1;
        private V v2;
        private E label;

        public Edge(V v1, V v2, E label) {
            this.v1 = v1;
            this.v2 = v2;
            this.label = label;
        }

        public V getV1() {
            return v1;
        }

        public V getV2() {
            return v2;
        }

        public E getLabel() {
            return label;
        }

        public void setV1(V v1) {
            this.v1 = v1;
        }

        public void setV2(V v2) {
            this.v2 = v2;
        }

        public void setLabel(E label) {
            this.label = label;
        }

        V getOtherEdge() {
            return getV2();
        }
    }

    private final TreeMap<V, LinkedList<Edge>> map;

    public Graph() {
        map = new TreeMap<V, LinkedList<Edge>>();
    }

    @Override
    public void add(V v) {
        if (map.get(v) == null) {
            LinkedList<Edge> ll = new LinkedList<>();
            map.put(v, ll);
        }
    }

    @Override
    public E getEdge(V u, V v) {
        if (!map.containsKey(v) || !map.containsKey(u)) return null;
        for (Edge e : map.get(u)) {
            if (e.getOtherEdge() == v)
                return e.getLabel();
        }
        return null;
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        if (map.get(u) == null) {
            Edge e = new Edge(u, v, edgeLabel);
            add(v);
            add(u);
            map.get(v).add(e);
            map.get(u).add(e);
            return edgeLabel;
        } else if (getEdge(u, v) == null) {
            Edge e = new Edge(u, v, edgeLabel);
            map.get(u).add(e);
            map.get(v).add(e);
            return edgeLabel;
        }
        return null;
    }

    @Override
    public boolean containsVertex(V v) {
        return map.containsKey(v);
    }

    @Override
    public void removeVertex(V v) {
        for (LinkedList<Edge> ll : map.values()) {
            for (Edge e : ll) {
                if (e.getOtherEdge() == v) ll.remove(e);
            }
        }
        map.remove(v);
    }

    @Override
    public E removeEdge(V u, V v) {
        for (Edge e : map.get(u)) {
            if (e.getOtherEdge() == v) {
                E l = e.getLabel();
                map.get(u).remove(e);
                map.get(v).remove(e);
                return l;
            }
        }
        return null;
    }

    @Override
    public double getWeight(V u, V v) throws RuntimeException {
        E e = getEdge(u, v) != null ? getEdge(u, v) : getEdge(v, u);
        if (e == null) return 0;
        if (e instanceof Weighted)
            return ((Weighted) e).getWeight();
        if (e instanceof java.lang.Number)
            return (double) e;
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        if (map.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (V v : map.keySet()) {
            sb.append(v.toString());
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public String toStringExtended() {
        StringBuilder sb = new StringBuilder();
        for (V v : map.keySet()) {
            sb.append(v.toString());
            sb.append(":");
            if (!map.get(v).isEmpty()) {
                for (Edge e : map.get(v)) {
                    sb.append("{");
                    sb.append(e.v1);
                    sb.append(",");
                    sb.append(e.v2);
                    sb.append("}");
                    if (e.getLabel() instanceof Weighted || e.getLabel() instanceof java.lang.Number) {
                        sb.append("(");
                        sb.append(getWeight(e.v1, e.v2) == 0 ? "" : getWeight(e.v1, e.v2));
                        sb.append(")");
                    }
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public boolean areAdjacent(V u, V v) {
        return getEdge(u, v) != null || getEdge(v, u) != null;
    }
}
