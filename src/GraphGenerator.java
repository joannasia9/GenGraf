import java.text.DecimalFormat;
import java.util.*;

public class GraphGenerator {
    private Random rand;
    private int MAX_RANGE_WEIGHT = 233;
    private int[][] graph;
    private int addedEdges = 0;

    public int[][] getGraph() {
        return graph;
    }

    public GraphGenerator() {
        this.rand = new Random();
    }

    public void generateGraph(int selection, int vertices, int edges) {
        switch (selection) {
            case 0:
                this.graph = createSpecifiedGraph(vertices, edges);
                break;
            case 1:
                this.graph = createCompleteGraph(vertices);
                break;
        }
    }

    private int[][] createSpecifiedGraph(int vertices, int edges) {

        int[][] g = new int[vertices][vertices];
        for(int i = 0; i<vertices; i++){
            for(int j=0;j<vertices;j++) g[i][j] =0;
        }
        //generate connections i && i+1
        for (int i = 0; i < vertices; i++) {
            int weight = rand.nextInt(MAX_RANGE_WEIGHT)+1;
            addEdge(g,i,weight,vertices);
        }

        createRestConnections(g, edges, vertices);

        double d = (double)edges/(double)(vertices*(vertices-1)/2);
        System.out.println("WYGENEROWANO GRAF O "+ vertices + " WIERZCHOŁKACH I "+ countEdges(g) + " KRAWĘDZIACH");
        DecimalFormat formatter = new DecimalFormat("#0.000");
        System.out.println("GĘSTOŚĆ WYGENEROWANEGO GRAFU: DG = "+ formatter.format(d));
        return g;
    }

    private boolean containsEdge(int[][] g, int i, int j){
        return ((g[i][j]>0)||(g[j][i]>0));
    }

    private void addEdge(int[][] g, int i, int weight, int vertices){
        g[i][(i + 1) % vertices] = g[(i + 1) % vertices][i] = weight;
        addedEdges++;
    }

    private void addEdge(int[][] g, int i, int j, Random r){
        int weight = r.nextInt(MAX_RANGE_WEIGHT)+1;
        g[i][j] = g[j][i] = weight;
        addedEdges++;
    }
    private void createRestConnections(int[][] g, int howManyEdges, int v) {
        if (v > 2) {
            while (addedEdges < howManyEdges) {
                for (int i = 0; i < v; i++) {
                    int toConnect = rand.nextInt(v);
                    if (addedEdges < howManyEdges && !containsEdge(g,i, toConnect))
                        addEdge(g,i,toConnect,rand);
                }
            }
        } else if (v == 2) {
            addEdge(g,0,1,rand);
        }
    }

    private int[][] createCompleteGraph(int vertices) {
       int[][] g = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {
                addEdge(g,i,j,rand);
            }
        }

        float d = countEdges(g)/(vertices*(vertices-1)/2);
        System.out.println("WYGENEROWANO GRAF O "+ vertices + " WIERZCHOŁKACH I "+ countEdges(g) + " KRAWĘDZIACH");
        DecimalFormat formatter = new DecimalFormat("#0.000");
        System.out.println("GĘSTOŚĆ WYGENEROWANEGO GRAFU: DG = "+ formatter.format(d));

        return g;
    }


private int countEdges( int[][] g){
        int edges = 0;
    for(int i=0;i<g.length;i++){
        for(int j=i;j<g.length;j++){
            if(g[i][j]>0) edges++;
        }
    }

    return edges;
}

    public ArrayList<Edge> getAllUniqueEdges( int[][] g){
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i=0;i<g.length;i++){
            for(int j=i;j<g.length;j++){
                if(g[i][j]>0) edges.add(new Edge(i,j,g[i][j]));
            }
        }

        return edges;
    }

}

