import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;

public class KruskalAlgorithm {
    private long time = 0;
    private OnFinishedListener onFinishedListener;

        class EdgeK implements Comparable<EdgeK>
        {
            int src, dest, weight;

            public int compareTo(EdgeK compareEdge)
            {
                return this.weight - compareEdge.weight;
            }
        }

        class subset
        {
            int parent, rank;
        };

        int V, E;
        EdgeK originalEdge[];
        EdgeK edge[];

        KruskalAlgorithm(int v, int e)
        {
            V = v;
            E = e;
            edge = new EdgeK[E];
            for (int i=0; i<e; ++i)
                edge[i] = new EdgeK();
        }

    public void setEdgeArray(ArrayList<Edge> edges) {
            int i=0;
        for (Edge item: edges) {
            edge[i].src = item.getStartVertex();
            edge[i].dest = (int) item.getEndV();
            edge[i].weight = (int) item.getWeight();
            i++;
        }

        originalEdge = edge.clone();
    }

    // znajduje subset z danym wierzchołkiem
        int find(subset subsets[], int i)
        {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);

            return subsets[i].parent;
        }

        // realizuje scalanie zbiorów
        void Union(subset subsets[], int x, int y)
        {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;

            else
            {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }

        private int weight;
        private EdgeK[] result;
        // realizuje algorytm Kruskala

        void KruskalMST()
        { weight = 0;

            edge = originalEdge.clone();

            long timeStartK = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());

            result = new EdgeK[V];  // wynik
            int e = 0;

            for (int i=0; i<V; ++i)
                result[i] = new EdgeK();

            // Sortuje krawedzie niemalejąco
            Arrays.sort(edge);;

            subset subsets[] = new subset[V];
            for(int i=0; i<V; ++i)
                subsets[i]=new subset();

            for (int v = 0; v < V; ++v)
            {
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            int i = 0;
            while (e < V - 1)
            {
                // Wybiera krawędź o najmniejszej wadze
                EdgeK next_edge = new EdgeK();
                next_edge = edge[i++];

                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);

                // Jeżeli kawedz nie tworzy cklu dodaje ja do rozwiazania
                if (x != y)
                {
                    weight += next_edge.weight;
                    result[e++] = next_edge;
                    Union(subsets, x, y);
                }
            }

            long timeStopK = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());

            time += timeStopK-timeStartK;
            // print the contents of result[] to display
            // the built MST
            //showResults(result,e);

        }

        public void  showTime(int iterations){
            String t = "Czas wykonania algorytmu KRUSKALA wynósł: \n"+ (time/iterations) + " mikrosekund.\n";
            String w = "Suma wag: " + weight;
            System.out.println("CZAS WYKONYWANIA ALGORYTMU KRUSKALA WYNIÓSŁ: "+ (time/iterations) + " mikrosekund");
            System.out.println("SUMA WAG: " + weight);
            onFinishedListener.onFinished(t, w);
            showResults(result);
        }

        private void showResults(EdgeK[] results) {
            JDialog dialog = new JDialog();


            System.out.println("Na MST skladaja się nastepujace krawedzie:");

            for (EdgeK item : results) {
                System.out.println(item.src + " -- " +
                        item.dest + " == " + item.weight);
            }

        }

    public void setOnFinishedListener(OnFinishedListener onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }
}
