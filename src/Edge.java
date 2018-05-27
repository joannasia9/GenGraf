public class Edge implements Comparable<Edge> {
    private final int startVertex;
    private final int endVertex;
    private final long weight;

    public Edge(int v, int w, int weight) {
        this.startVertex = v;
        this.endVertex = w;
        this.weight = weight;
    }

    public int getStartVertex() {
        return startVertex;
    }

    public int getEnd(int v){
        if (v == startVertex) {
            return endVertex;
        } else return startVertex;
    }
    public int getEndVertex(int vertex) {
        if (vertex == startVertex) {
            return endVertex;
        } else if (vertex == endVertex) {
            return startVertex;
        }
        throw new IllegalArgumentException("Vertex not matched");
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d->%d (%d)", startVertex, endVertex, weight);
    }

    @Override
    public int compareTo(Edge arg) {
        if (getWeight() < arg.getWeight()) {
            return -1;
        } else if (getWeight() > arg.getWeight()) {
            return 1;
        }
        return 0;
    }

    public long getEndV() {
        return endVertex;
    }
}
