//KRUSKAL ALGORİTMASI NEDİR?
//Minimum ağaç (minimum spanning tree) problemini çözen bir algoritmadır. 
//Kruskal algoritması, grafikteki tüm düğümleri içeren bir ağacı oluşturur 
//ve bu ağacın kenar ağırlıklarının toplamı minimum olacak şekilde çalışır. 
//Ağaç içerisinde cycle olmamalıdır. Ağaç dalları gibi hiç bir nokta birbiri 
//ile kapanmamalı yolu kapatmamalıdır.

package kruskal;

import java.util.*;

public class kruskal {
	
	//MAİN BAŞLANGIÇ NOKTASIDIR. GRAPH SINIFINDA NESNE OLUŞTURULUR,
	//VE ALGORİTMADAKİ DÜĞÜM VE KENARLAR OLUŞTURULUR,
	//KRUSKALMST METODU İLE DE ALGORİTMA ÇALIŞTIRLIR.
    public static void main(String[] args) {
    	Graph graph = new Graph(7, 9);
        graph.addEdge(0, 0, 1, 2);
        graph.addEdge(1, 0, 2, 4);
        graph.addEdge(2, 1, 3, 7);
        graph.addEdge(3, 1, 4, 1);
        graph.addEdge(4, 2, 4, 3);
        graph.addEdge(5, 2, 5, 5);
        graph.addEdge(6, 3, 6, 8);
        graph.addEdge(7, 4, 6, 6);
        graph.addEdge(8, 5, 6, 9);
        
        graph.kruskalMST();
    }
}

     //EDGE GRAFİKTEKİ KENARI TEMSİL EDER, SRC (BAŞLANGIÇ), DEST (DESTINATION=HEDEF), WEIGHT (AĞIRLIK)
    //ALANLARINI İÇERİR. 
   //COMPARABLE ARAYÜZÜ, KENARLARI KÜÇÜKTEN BÜYÜĞE GÖRE SIRALAMAK İÇİN DE COMPARETO METODU KULLANILIR.	
class Edge implements Comparable<Edge> {
    int src, dest, weight;
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}
    
   //GRAPH, MST OLUŞTURMAK İÇİN KRUSKAL ALGORİTMASINI İÇERİYOR,
   //ADDEDGE METODU KENARLARI EKLİYOR,
   //FIND VE UNION YAPISI MST YAPISINI OLUŞTURUYOR VE DÖNGÜLERİ KONTROL EDİYOR, 
   //KRUSKALMST ALGORİTMAYI ÇALIŞTIRIYOR VE MST Yİ OLUŞTURUYOR.
class Graph {
    private final int V, E;
    private final Edge[] edges;

    
    //GRAPH\\
    public Graph(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
    }
    public void addEdge(int index, int src, int dest, int weight) {
        edges[index] = new Edge(src, dest, weight);
    }
    
    //FIND VE UNION\\
    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }
    private void union(int[] parent, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);
        parent[xroot] = yroot;
    }
    
    //KRUSKALMST\\
    public void kruskalMST() {
        Edge[] result = new Edge[V - 1];
        int e = 0;
        int i = 0;
        Arrays.sort(edges);
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        while (e < V - 1 && i < E) {
            Edge nextEdge = edges[i++];
            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);
            if (x != y) {
                result[e++] = nextEdge;
                union(parent, x, y);
            }
        }
        
        //EKRANA KRUSKAL ALGORİTMASI (MST) YAZDIRIYOR VE ALTINA ALGORİTMAYI OLUŞTURARAK KODU TAMAMLIYOR.
        System.out.println("Kruskal Algoritması (Minimum Spanning Tree)");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
    }
}
