import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Main {

    private static int[][] graph;
    private static Prim prim;
    private static GraphGenerator graphGenerator;
    private static int ITERATIONS_COUNT = 100;

    public static void main(String[] args){
        Font titleFont = new Font("TimesRoman", Font.BOLD, 16);
        Font restFont = new Font("TimesRoman", Font.PLAIN, 12);

        JFrame frame = new JFrame("Projekt GIS");

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Badanie algorytmów MST"));
        mainPanel.setPreferredSize(new Dimension());
        mainPanel.setBounds(10,10,400,400);

        JLabel titleStep1 = new JLabel("1. WYGENERUJ GRAF");
        titleStep1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel titleStep2 = new JLabel("2. URUCHOM ALGORYTM");
        titleStep2.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleStep1.setFont(titleFont);
        titleStep2.setFont(titleFont);

        JLabel provideNodesNum  = new JLabel("Podaj liczbę V wierzchołków grafu");
        provideNodesNum.setFont(restFont);
        provideNodesNum.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField vertValue = new JTextField(10);
        vertValue.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel empty = new JLabel(" ");
        empty.setMaximumSize(new Dimension(100,30));

        JLabel empty1 = new JLabel(" ");
        empty1.setMaximumSize(new Dimension(100,30));

        JLabel provideEdgesNum = new JLabel("Podaj liczbę E krawędzi grafu", SwingConstants.LEFT);
        provideEdgesNum.setFont(restFont);
        provideEdgesNum.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField edgesValue = new JTextField(10);
        edgesValue.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton generateBtn = new JButton("Generuj graf o V wierz. i E kraw.");
        generateBtn.setFont(restFont);
        JButton generateCompleteBtn = new JButton("Generuj graf zupełny");
        generateCompleteBtn.setFont(restFont);


        generateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateCompleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        generateBtn.addActionListener((ActionEvent e) -> {
            if(!vertValue.getText().equals("")&&!edgesValue.getText().equals("")) {
                int verticesValue = Integer.parseInt(vertValue.getText());
                graphGenerator = new GraphGenerator();
                String edges = edgesValue.getText().trim();
                graphGenerator.generateGraph(0, verticesValue, Integer.parseInt(edges));
                graph = graphGenerator.getGraph();
            }
        });
        generateCompleteBtn.addActionListener((ActionEvent e) -> {
            if(!vertValue.getText().equals("")) {
                int verticesValue = Integer.parseInt(vertValue.getText());
                graphGenerator = new GraphGenerator();
                graphGenerator.generateGraph(1,verticesValue,0);
                graph = graphGenerator.getGraph();
            }
        });

        ArrayList<TestData> testInput = new ArrayList();
        testInput.add(new TestData(10, 18));
        testInput.add(new TestData(10, 27));
        testInput.add(new TestData(10, 36));
        testInput.add(new TestData(10, 45));
        testInput.add(new TestData(50, 245));
        testInput.add(new TestData(50, 490));
        testInput.add(new TestData(50, 735));
        testInput.add(new TestData(50, 980));
        testInput.add(new TestData(50, 1225));
        testInput.add(new TestData(100, 990));
        testInput.add(new TestData(100, 1980));
        testInput.add(new TestData(100, 2970));
        testInput.add(new TestData(100, 3960));
        testInput.add(new TestData(100, 4950));
        testInput.add(new TestData(500, 24950));
        testInput.add(new TestData(500, 49900));
        testInput.add(new TestData(500, 74850));
        testInput.add(new TestData(500, 99800));
        testInput.add(new TestData(500, 124750));
        testInput.add(new TestData(1000, 99900));
        testInput.add(new TestData(1000, 199800));
        testInput.add(new TestData(1000, 299700));
        testInput.add(new TestData(1000, 399600));
        testInput.add(new TestData(1000, 499500));
        testInput.add(new TestData(5000, 2499500));
        testInput.add(new TestData(5000, 4999000));
        testInput.add(new TestData(5000, 7498500));
        testInput.add(new TestData(5000, 9998000));
        testInput.add(new TestData(5000, 12497500));
        testInput.add(new TestData(10000, 9999000));
        testInput.add(new TestData(10000, 19998000));
        testInput.add(new TestData(10000, 29997000));
        testInput.add(new TestData(10000, 39996000));
        testInput.add(new TestData(10000, 49995000));
        testInput.add(new TestData(20000, 39998000));
        testInput.add(new TestData(20000, 79996000));
        testInput.add(new TestData(20000, 119994000));
        testInput.add(new TestData(20000, 159992000));
        testInput.add(new TestData(20000, 199990000));

        for (TestData input : testInput) {
            System.out.println("\n\n ------- Results for " + input.vertices + " vx and " + input.edges + " eg");

            graphGenerator = new GraphGenerator();
            graphGenerator.generateGraph(0, input.vertices, input.edges);
            graph = graphGenerator.getGraph();

            prim = new Prim(graphGenerator.getPrimVertices(graph));

            for(int j = 0; j<ITERATIONS_COUNT;j++){
                prim.primMST();
            }
            prim.showTime(ITERATIONS_COUNT);

            ArrayList<Edge> uniqueEdges = graphGenerator.getAllUniqueEdges(graph);
            KruskalAlgorithm kruskal = new KruskalAlgorithm(graph.length, uniqueEdges.size());
            kruskal.setEdgeArray(uniqueEdges);

            for(int j = 0; j<ITERATIONS_COUNT;j++){
                kruskal.KruskalMST();
            }
            kruskal.showTime(ITERATIONS_COUNT);
        }



        JButton goPrim = new JButton("Uruchom algorytm Prima");
        goPrim.setFont(restFont);
        goPrim.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton goKruskal = new JButton("Uruchom algorytm Kruskala");
        goKruskal.setAlignmentX(Component.CENTER_ALIGNMENT);
        goKruskal.setFont(restFont);

        goPrim.addActionListener(e -> {
            //Alg. Prima
            prim = new Prim(graphGenerator.getPrimVertices(graph));

            for(int j = 0; j<ITERATIONS_COUNT;j++){
                prim.primMST();
            }
            prim.showTime(ITERATIONS_COUNT);
        });

        goKruskal.addActionListener(e -> {
            ArrayList<Edge> uniqueEdges = graphGenerator.getAllUniqueEdges(graph);
            KruskalAlgorithm kruskal = new KruskalAlgorithm(graph.length, uniqueEdges.size());
            kruskal.setEdgeArray(uniqueEdges);

            for(int j = 0; j<ITERATIONS_COUNT;j++){
                kruskal.KruskalMST();
            }
            kruskal.showTime(ITERATIONS_COUNT);
        });

        panel.add(titleStep1);
        JLabel space = new JLabel(" ");
        space.setMaximumSize(new Dimension(100,30));
        panel.add(space);
        panel.add(provideNodesNum);
        panel.add(vertValue);
        panel.add(empty);
        panel.add(provideEdgesNum);
        panel.add(edgesValue);
        panel.add(empty1);
        panel.add(generateBtn);
        panel.add(generateCompleteBtn);

        JLabel space1 = new JLabel(" ");
        space1.setMaximumSize(new Dimension(100,30));
        panel.add(space1);
        panel.add(titleStep2);
        panel.add(goPrim);
        panel.add(goKruskal);

        mainPanel.add(panel);
        frame.add(mainPanel);
        frame.setSize(420,450);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
