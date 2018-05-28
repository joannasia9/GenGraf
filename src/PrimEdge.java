public class PrimEdge implements Comparable<PrimEdge> {
    int end;
    int cost;

    public PrimEdge(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }

    public int compareTo(PrimEdge edge) {
        return this.cost - edge.cost;
    }
}
