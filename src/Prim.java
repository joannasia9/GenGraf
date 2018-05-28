import java.util.*;
import java.util.concurrent.TimeUnit;

public class Prim {
    private boolean[] visitedVertices;
    private Deque<PrimEdge> minSpanningTree;
    private PriorityQueue<PrimEdge> adjacencyEdges;
    private long weight;
    private long time;
    private PrimVertex[] vertices;


    public Prim(PrimVertex[] vertices) {
        this.vertices = vertices;
    }

    public void primMST(){
        weight = 0;
        visitedVertices = new boolean[vertices.length];
        minSpanningTree = new ArrayDeque<>();
        adjacencyEdges = new PriorityQueue<>(vertices.length);
        long timeStart = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());


        Random rand = new Random();
        int randV = rand.nextInt(vertices.length - 1);

        visitedVertices[randV] = true;

        for (PrimEdge edge : vertices[randV].neighbors) {
            adjacencyEdges.offer(edge);
        }

        while (!adjacencyEdges.isEmpty()) {
            PrimEdge edge = adjacencyEdges.remove();

            if (visitedVertices[edge.end]) {
                continue;
            }

            visitedVertices[edge.end] = true;
            minSpanningTree.push(edge);
            weight+=edge.cost;

            for (PrimEdge visitedEdge : vertices[edge.end].neighbors) {
                adjacencyEdges.offer(visitedEdge);
            }
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

    /*public Iterable<Edge> edges() {
        return minSpanningTree;
    }*/

}
