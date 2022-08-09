import java.util.*;

public class Main {
    public static void main (String[] args) {
        /*
         * 1. Спросить, сколько рёбер
         * 2. Прописать длины для каждых рёбер
         * 3. Алгоритм Краскала
         * 4. Принт
         */
        int size = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите колличество рёбер графа: ");
        size = sc.nextInt();
        int[][] graph = new int[size][size];
        createGraph(size, graph);
        printGraph(graph);
        System.out.println("Получаем лабиринт...");
        int[][] tree = kraskal(graph);
        printGraph(tree);

    }
    private static void createGraph (int size, int[][] graph) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    System.out.println("Введите вес от " + i + " до " + j);
                    int tmp = sc.nextInt();
                    graph[i][j] = tmp;
                    graph[j][i] = tmp;
                }
            }
        }
    }

    private static int[][] kraskal (int[][] graph) {
        List<Integer> indexes = new ArrayList<>();
        int[][] tree = new int[graph.length][graph.length];
        indexes.add(0);
        int minInd = 0;
        int minWeight = -1;
        int ind1 = 0;
        for (int i = 0, sz = graph.length; i < sz; i++) {

            for (int ind : indexes) {

                int tmp = getMinWeightInd(graph, ind, indexes);
                if (tmp > -1) {
                    if (minWeight < 0) {
                        ind1 = ind;
                        minWeight = graph[ind][tmp];
                        minInd = tmp;
                    } else if (tmp < minInd) {
                        ind1 = ind;
                        minWeight = graph[ind][tmp];
                        minInd = tmp;
                    }
                }
            }
            if (minWeight > -1) {
                indexes.add(minInd);
                tree[ind1][minInd] = minWeight;
                tree[minInd][ind1] = minWeight;
            }
            minWeight = -1;
            minInd = 0;
        }

        return tree;
    }
    private static int getMinWeightInd (int[][] graph, int ind, List<Integer> indexes) {
        int min = 0;
        int minInd = -1;
        for (int j = 0; j < graph.length; j++) {
            if (!indexes.contains(j)) {
                if (minInd < 0) {
                    if (ind != j) {
                        min = graph[ind][j];
                        minInd = j;
                    }
                } else {
                    if (graph[ind][j] < min) {
                        min = graph[ind][j];
                        minInd = j;
                    }
                }
            }
        }
        return minInd;
    }

    private static void printGraph (int[][] graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0, sz = graph.length; i < sz; i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        for (int i = 0, sz = graph.length; i < sz; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < sz; j++) {
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}

