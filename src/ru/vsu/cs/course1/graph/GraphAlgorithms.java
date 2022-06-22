package ru.vsu.cs.course1.graph;

import java.util.*;
import java.util.function.Consumer;

public class GraphAlgorithms {

    /**
     * Поиск в глубину, реализованный рекурсивно
     * (начальная вершина также включена)
     *
     * @param graph   граф
     * @param from    Вершина, с которой начинается поиск
     * @param visitor Посетитель
     */
    public static void dfsRecursion(Graph graph, int from, Consumer<Integer> visitor) {
        boolean[] visited = new boolean[graph.vertexCount()];

        class Inner {
            void visit(Integer curr) {
                visitor.accept(curr);
                visited[curr] = true;
                for (Integer v : graph.adjacencies(curr)) {
                    if (!visited[v]) {
                        visit(v);
                    }
                }
            }
        }
        new Inner().visit(from);
    }

    public record Edge(int v1, int v2) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge that = (Edge) o;
            return (v1 == that.v1 && v2 == that.v2) || (v1 == that.v2 && v2 == that.v1);
        }

        public boolean isContainNumber(int v1) {
            return this.v1 == v1 || this.v2 == v1;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v1, v2);
        }
    }

    public static class EdgeAndEdges {

        private Edge edge;
        private ArrayList<Edge> edges;

        public EdgeAndEdges(Edge edge, ArrayList<Edge> edges) {
            this.edge = edge;
            this.edges = edges;
        }

        public ArrayList<Edge> getEdges() {
            return edges;
        }
    }
//    public static Graph makeNAgreeGraph2(Graph graph, int n) {
//        Graph copied = null;
//        Graph vanillaGraph = graph.copy();
//        int vertex = graph.vertexCount();
//        ArrayList<EdgeAndEdges> edges = new ArrayList<>();
//        for (int v1 = 0; v1 < vertex; v1++) {
//            for (int v2 = v1 + 1; v2 < vertex; v2++) {
//                if (!graph.isAdj(v1, v2) && v1 != v2) {
//                    if (!solve(graph, v1, v2, n, 0, v1, new ArrayList<>(), edges)) {
//                        graph.removeAdge(v1, v2);
//                    }
//                }
//            }
//        }
//        graph = vanillaGraph.copy();
//        int count = 0;
//        int bestCounter = -1;
//        for (int i = 0; i < edges.size(); i++) {
//            graph.addAdge(edges.get(i).edge.v1, edges.get(i).edge.v2);
//            count++;
//            for (int v1 = 0; v1 < vertex; v1++) {
//                for (int v2 = v1 + 1; v2 < vertex; v2++) {
//                    if (!graph.isAdj(v1, v2) && v1 != v2) {
//                        if (!solve(graph, v1, v2, n, 0, v1, new ArrayList<>(), new ArrayList<>())) {
//                            count++;
//                        }
//                    }
//                }
//            }
//            if (bestCounter == -1) {
//                bestCounter = count;
//                copied = graph.copy();
//            } else if (bestCounter > count) {
//                bestCounter = count;
//                copied = graph.copy();
//            }
//            graph = vanillaGraph.copy();
//            count = 0;
//        }
//        return copied;
//        boolean condition = true;
//        for (EdgeAndEdges e : edges) {
//            for (EdgeAndEdges e1 : edges) {
//                if (e1.edge.equals(e.edge)) {
//                    continue;
//                }
//                graph.addAdge(e1.edge.v1, e1.edge.v2);
//                if (solve2(graph, e.edge.v1, e.edge.v2, n, 0, e.edge.v1, new ArrayList<>(), new ArrayList<>())) {
//                    for (Edge e2 : e.edges) {
//                        if (!solve2(graph, e2.v1, e2.v2, n, 0, e.edge.v1, new ArrayList<>(), new ArrayList<>())) {
//                            graph.removeAdge(e1.edge.v1, e1.edge.v2);
//                            condition = false;
//                            break;
//                        }
//                    }
//                    if (condition) {
//                        graph.removeAdge(e.edge.v1, e.edge.v2);
//                        if (!e1.edges.contains(e.edge)) {
//                            e1.edges.add(e.edge);
//                        }
//                        for (Edge e3 : e.edges) {
//                            if (!e1.edges.contains(e3)) {
//                                e1.edges.add(e3);
//                            }
//                        }
//                    }
//                }
//                graph.removeAdge(e1.edge.v1, e1.edge.v2);
//            }
//            condition = true;
//        }
//        ArrayList<EdgeAndEdges> newEdges = new ArrayList<>(edges);
//        for (EdgeAndEdges e : edges) {
//            for (EdgeAndEdges e1 : newEdges) {
//                if (e1.edge.equals(e.edge)) {
//                    continue;
//                }
//                if (e1.edges.containsAll(e.edges) && e1.edges.contains(e.edge) && e.edges.size() != e1.edges.size()) {
//                    newEdges.remove(e);
//                    break;
//                }
//                if (e.edge.v2 == e1.edge.v2 && e1.edges.size() > e.edges.size()) {
//                    newEdges.remove(e);
//                    break;
//                }
//            }
//        }
//        for (EdgeAndEdges e : newEdges) {
//            graph.addAdge(e.edge.v1, e.edge.v2);
//        }
//        return graph;
//    }

    public static class GraphAndEdges{

        private Graph graph;
        private List<Edge> edges;

        public GraphAndEdges(Graph graph, List<Edge> edges) {
            this.graph = graph;
            this.edges = edges;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public Graph getGraph() {
            return graph;
        }
    }

    public static class IntAndEdges {
        private int count;
        private List<Edge> edges;

        public IntAndEdges(int count, List<Edge> edges) {
            this.count = count;
            this.edges = edges;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public int getCount() {
            return count;
        }
    }
    
    //recheck - во время второго прохождения по ребрам
    private static IntAndEdges forLoop(Graph graph, int n, boolean recheck, ArrayList<Edge> edges) {
        int vertex = graph.vertexCount();
        List<Edge> edges1 = new ArrayList<>();
        int count = 1;
        for (int v1 = 0; v1 < vertex; v1++) {
            for (int v2 = v1 + 1; v2 < vertex; v2++) {
                if (!graph.isAdj(v1, v2) && v1 != v2) {
                    if (!solve(graph, v1, v2, n, 0, v1, new ArrayList<>(), edges)) {
                        if (!recheck) {
                            graph.removeAdge(v1, v2);
                        } else {
                            count++;
                            Edge edge = new Edge(v1,v2);
                            edges1.add(edge);
                        }
                    }
                }
            }
        }
        IntAndEdges intAndEdges = new IntAndEdges(count, edges1);
        return intAndEdges;
    }

    /**
     * в первом цикле forLoop берутся все возможные ребра
     * потом в следующем цикле мы идем по этим ребрам, и перепровреям пути
     * и выбираем самый лучший(меньше всего построенных ребер)
     * и возвращаем этот лучший граф
     */
    public static GraphAndEdges makeNAgreeGraph(Graph graph, int n) {
        Graph copied = graph;
        Graph vanillaGraph = graph.copy();
        ArrayList<Edge> edges = new ArrayList<>();
        IntAndEdges intAndEdges = null;
        forLoop(graph, n, false, edges);
        graph = vanillaGraph.copy();
        int count;
        int bestCounter = -1;
        for (Edge edge : edges) {
            graph.addAdge(edge.v1, edge.v2);
            intAndEdges = forLoop(graph, n, true, new ArrayList<>());
            if (bestCounter == -1) {
                bestCounter = intAndEdges.getCount();
                copied = graph.copy();
            } else if (bestCounter > intAndEdges.getCount()) {
                bestCounter = intAndEdges.getCount();
                copied = graph.copy();
            }
            graph = vanillaGraph.copy();
        }
        GraphAndEdges graphAndEdges = new GraphAndEdges(copied, intAndEdges.getEdges());
        return graphAndEdges;
    }

    private static boolean solve(Graph graph, int v1, int v2, int n, int currentCountOfN, int initialVertex, ArrayList<Integer> noCheckVerticies,
                                  ArrayList<Edge> edges) {
        boolean ifSuccesful;
        for (Integer child : graph.adjacencies(v1)) {
            if (noCheckVerticies.contains(child)) {
                continue;
            }
            currentCountOfN++;
            if (currentCountOfN > n) {
                break;
            }
            if (graph.isAdj(child, v2) && currentCountOfN + 1 <= n) {
                return true;
            } else {
                noCheckVerticies.add(v1);
                ifSuccesful = solve(graph, child, v2, n, currentCountOfN, initialVertex, noCheckVerticies, edges);
                if (ifSuccesful) {
                    return true;
                } else {
                    currentCountOfN--;
                }
            }
            if (initialVertex == v1) {
                noCheckVerticies.clear();
                currentCountOfN = 0;
            }
        }
        if (initialVertex == v1) {
            graph.addAdge(v1, v2);
            edges.add(new Edge(v1, v2));
        }
        return false;
    }

//    public static void makeNAgreeGraph(Graph graph, int n) {
//        int vertex = graph.vertexCount();
//        ArrayList<Edge> list = new ArrayList<>();
//        int listCounter = 0;
//        ArrayList<Edge> allEdges = new ArrayList<>();
//        for (int v1 = 0; v1 < vertex; v1++) {
//            for (int v2 = v1 + 1; v2 < vertex; v2++) {
//                if (!graph.isAdj(v1, v2) && v1 != v2) {
//                    solve(graph, v1, v2, v1, n, 0, v1, list, new ArrayList<>(), false, list);
//                } else {
//                    allEdges.add(new Edge(v1, v2));
//                }
//                if (list.size() != listCounter) {
//                    graph.removeAdge(list.get(listCounter).v1, list.get(listCounter).v2);
//                    listCounter++;
//                }
//            }
//        }
//        for (Edge e : list) {
//            graph.addAdge(e.v1, e.v2);
//        }
//        ArrayList<ArrayList<Edge>> usedPaths = new ArrayList<>();
//        ArrayList<Edge> fullPath = new ArrayList<>();
//        ArrayList<Edge> usedEdges = new ArrayList<>(list);
//        boolean cond = true;
//        for (Edge e : list) {
//            for (ArrayList<Edge> edges : usedPaths) {
//                if (edges.contains(e)) {
//                    cond = false;
//                    break;
//                }
//            }
//            if (cond) {
//                graph.removeAdge(e.v1, e.v2);
//                solve(graph, e.v1, e.v2, e.v1, n, 0, e.v1, fullPath, usedPaths, true, usedEdges);
//                usedPaths.add(new ArrayList<>(fullPath));
//                for (Edge e1 : fullPath) {
//                    if (usedEdges.contains(e1)) {
//                        usedEdges.remove(e1);
//                    }
//                }
//                fullPath.clear();
//            }
//            cond = true;
//        }
//        int s = 0;
//    }
//
//
//    private static boolean solve(Graph graph, int v1, int v2, int lastTakenVertex, int n, int currentCountOfN, int initialVertex,
//                                 ArrayList<Edge> list, ArrayList<ArrayList<Edge>> usedPaths, boolean recheck, ArrayList<Edge> list2) {
//        boolean ifSuccesful;
//        for (Integer child : graph.adjacencies(v1)) {
//            if (child == initialVertex || child == lastTakenVertex) {
//                continue;
//            }
//            currentCountOfN++;
//            if (currentCountOfN > n) {
//                break;
//            }
//            if (graph.isAdj(child, v2) && currentCountOfN + 1 <= n) {
//                if (recheck) {
//                    for (ArrayList<Edge> edges : usedPaths) {
//                        boolean is = false;
//                        for (Edge e : edges) {
//                            is = e.isContainNumber(initialVertex);
//                            if (is && edges.size() != n) {
//                                break;
//                            }
//                        }
//                        if (is) {
//                            ArrayList<Edge> asd = new ArrayList<>();
//                            for (Edge e : edges) {
//                                asd.add(e);
//                                if (graph.isAdj(e.v1, v2) && e.v1 != child && !list2.contains(new Edge(e.v1, v2))) {
//                                    asd.add(new Edge(e.v1, v2));
//                                    list.clear();
//                                    list.addAll(asd);
//                                    return true;
//                                }
//                                if (graph.isAdj(e.v2, v2) && e.v2 != child && !list2.contains(new Edge(e.v2, v2))) {
//                                    asd.add(new Edge(e.v2, v2));
//                                    list.clear();
//                                    list.addAll(asd);
//                                    return true;
//                                }
//                            }
//                        }
//                    }
//                    list.add(new Edge(v1, child));
//                    list.add(new Edge(v2, child));
//                }
//                return true;
//            } else {
//                if (recheck) {
//                    list.add(new Edge(v1, child));
//                }
//                ifSuccesful = solve(graph, child, v2, v1, n, currentCountOfN, initialVertex, list, usedPaths, recheck, list2);
//                if (ifSuccesful) {
//                    return true;
//                } else {
//                    if (recheck) {
//                        list.remove(currentCountOfN - 1);
//                    }
//                    currentCountOfN--;
//                }
//            }
//            if (initialVertex == v1) {
//                currentCountOfN = 0;
//            }
//        }
//        if (initialVertex == v1) {
//            if (!recheck) {
//                list.add(new Edge(v1, v2));
//            }
//            graph.addAdge(v1, v2);
//            return false;
//        }
//        return false;
//    }

    /**
     * Поиск в глубину, реализованный с помощью стека
     * (не совсем "правильный"/классический, т.к. "в глубину" реализуется только "план" обхода, а не сам обход)
     *
     * @param graph   граф
     * @param from    Вершина, с которой начинается поиск
     * @param visitor Посетитель
     */
    public static void dfs(Graph graph, int from, Consumer<Integer> visitor) {
        boolean[] visited = new boolean[graph.vertexCount()];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(from);
        visited[from] = true;
        while (!stack.empty()) {
            Integer curr = stack.pop();
            visitor.accept(curr);
            for (Integer v : graph.adjacencies(curr)) {
                if (!visited[v]) {
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }
    }

    /**
     * Поиск в ширину, реализованный с помощью очереди
     * (начальная вершина также включена)
     *
     * @param graph   граф
     * @param from    Вершина, с которой начинается поиск
     * @param visitor Посетитель
     */
    public static void bfs(Graph graph, int from, Consumer<Integer> visitor) {
        boolean[] visited = new boolean[graph.vertexCount()];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(from);
        visited[from] = true;
        while (queue.size() > 0) {
            Integer curr = queue.remove();
            visitor.accept(curr);
            for (Integer v : graph.adjacencies(curr)) {
                if (!visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                }
            }
        }
    }

    /**
     * Поиск в глубину в виде итератора
     * (начальная вершина также включена)
     *
     * @param graph граф
     * @param from  Вершина, с которой начинается поиск
     * @return Итератор
     */
    public static Iterable<Integer> dfs(Graph graph, int from) {
        return new Iterable<Integer>() {
            private Stack<Integer> stack = null;
            private boolean[] visited = null;

            @Override
            public Iterator<Integer> iterator() {
                stack = new Stack<>();
                stack.push(from);
                visited = new boolean[graph.vertexCount()];
                visited[from] = true;

                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        return !stack.isEmpty();
                    }

                    @Override
                    public Integer next() {
                        Integer result = stack.pop();
                        for (Integer adj : graph.adjacencies(result)) {
                            if (!visited[adj]) {
                                visited[adj] = true;
                                stack.add(adj);
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }

    /**
     * Поиск в ширину в виде итератора
     * (начальная вершина также включена)
     *
     * @param from Вершина, с которой начинается поиск
     * @return Итератор
     */
    public static Iterable<Integer> bfs(Graph graph, int from) {
        return new Iterable<Integer>() {
            private Queue<Integer> queue = null;
            private boolean[] visited = null;

            @Override
            public Iterator<Integer> iterator() {
                queue = new LinkedList<>();
                queue.add(from);
                visited = new boolean[graph.vertexCount()];
                visited[from] = true;

                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        return !queue.isEmpty();
                    }

                    @Override
                    public Integer next() {
                        Integer result = queue.remove();
                        for (Integer adj : graph.adjacencies(result)) {
                            if (!visited[adj]) {
                                visited[adj] = true;
                                queue.add(adj);
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }
}
