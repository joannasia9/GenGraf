import java.util.*;
import java.util.concurrent.TimeUnit;

public class Prim {
    private boolean[] visitedVertices;
    private Deque<Edge> minSpanningTree;
    private PriorityQueue<Edge> adjacencyEdges;
    private long weight;
    private int v;
    private long time;
    private int[][] graph;


    public Prim(int[][] graph) {
        v = graph.length;
        this.graph = graph;
    }

    public void primMST(){
        weight = 0;
        visitedVertices = new boolean[v];
        minSpanningTree = new ArrayDeque<>();
        adjacencyEdges = new PriorityQueue<>(v);
        long timeStart = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());


        Random rand = new Random();
        int randV = rand.nextInt(v - 1);

        visitVertex(graph, randV);

        while (!adjacencyEdges.isEmpty() || minSpanningTree.size()!= (v - 1)) {
            Edge edge = adjacencyEdges.poll();

            if (visitedVertices[edge.endVertex]) {
                continue;
            }

            minSpanningTree.push(edge);
            weight+=edge.weight;
            visitVertex(graph, edge.endVertex);
        }

        long timeStop = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());

        time += (timeStop-timeStart);
    }

    public void showTime(int iterations){
        System.out.println("CZAS WYKONYWANIA ALGORYTMU PRIMA WYNIÓSŁ: "+ (time/iterations) + " mikrosekund");
        System.out.println("SUMA WAG: " + weight);
//        System.out.println("Na MST składają się nastepujące krawędzie: ");
//        System.out.println(mstEdges(edges()));
    }

    private String mstEdges(Iterable<Edge> edges){
        StringBuilder builder = new StringBuilder();

        for (Edge item : edges) {
            builder.append(item).append("\n");
        }

        return builder.toString();
    }

    public Iterable<Edge> edges() {
        return minSpanningTree;
    }

    private void visitVertex(int[][] graph, int v) {
        visitedVertices[v] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 0) {
                continue;
            }

            if (!visitedVertices[i]) {
                adjacencyEdges.offer(new Edge(v, i, graph[v][i]));
            }
        }
    }

}
