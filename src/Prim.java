import java.util.*;

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
        long timeStart = System.currentTimeMillis();
        visitedVertices = new boolean[v];
        minSpanningTree = new ArrayDeque<>();
        adjacencyEdges = new PriorityQueue<>(v);


        Random rand = new Random();
        int randV = rand.nextInt(v - 1);

        visitVertex(graph, randV);

        while (!adjacencyEdges.isEmpty() || minSpanningTree.size()!= (v - 1)) {
            Edge edge = adjacencyEdges.poll();
            int startVertex = edge.getStartVertex();
            int endVertex = edge.getEndVertex(startVertex);

            if (visitedVertices[startVertex] && visitedVertices[endVertex]) {
                continue;
            }

            minSpanningTree.push(edge);

            weight+=edge.getWeight();

            if (!visitedVertices[startVertex]) {
                visitVertex(graph, startVertex);
            }
            if (!visitedVertices[endVertex]) {
                visitVertex(graph, endVertex);
            }
        }

        long timeStop = System.currentTimeMillis();

        time += (timeStop-timeStart);
    }

    public void showTime(int iterations){
        System.out.println("CZAS WYKONYWANIA ALGORYTMU PRIMA WYNIÓSŁ: "+ (time/iterations) + " milisekund");
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

        ArrayList<Edge> adjacencyList = new ArrayList<>();
        for(int i=0;i<graph.length;i++){
            if(graph[v][i]>0) adjacencyList.add(new Edge(v,i,graph[v][i]));
        }

        for (Edge edge : adjacencyList) {
            if (!visitedVertices[edge.getEndVertex(v)]) {
                adjacencyEdges.offer(edge); // dodaj krawędź do kolejki
            }
        }
    }

}
