public class PrimEdge implements Comparable<PrimEdge> {
    int start;
    int end;
    int cost;

    public PrimEdge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public int compareTo(PrimEdge edge) {
        return this.cost - edge.cost;
    }
}
