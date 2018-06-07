import java.util.*;
import java.util.concurrent.TimeUnit;

public class Prim {
    private boolean[] visitedVertices;
    private Deque<PrimEdge> minSpanningTree;
    private PriorityQueue<PrimEdge> adjacencyEdges;
    private long weight;
    private long time;
    private PrimVertex[] vertices;
    private OnFinishedListener onFinishedListener;


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
        String t = "Czas wykonania algorytmu PRIMA wyniósł: "+ (time/iterations) + " mikrosekund\n";
        String w = "Suma wag: " + weight;
        System.out.println("CZAS WYKONYWANIA ALGORYTMU PRIMA WYNIÓSŁ: "+ (time/iterations) + " mikrosekund");
        System.out.println("SUMA WAG: " + weight);
        onFinishedListener.onFinished(t,w);
        System.out.println("Na MST składają się nastepujące krawędzie: ");
        System.out.println(showMST(minSpanningTree));
    }

    private String showMST(Deque<PrimEdge> tree){
        StringBuilder builder = new StringBuilder();
        while(!tree.isEmpty()){
            PrimEdge edge = tree.pop();
            builder.append(edge.start).append(" -> ")
                    .append(edge.end).append(": ")
                    .append(edge.cost)
                    .append("\n");
        }

        return builder.toString();
    }

    public void setOnFinishedListener(OnFinishedListener onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }
}
