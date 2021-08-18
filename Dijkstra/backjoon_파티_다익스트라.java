package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


//다익스트라 DIST 배열 다시 상기하자
public class backjoon_파티_다익스트라 {
	static int N;
	static ArrayList<Node>[] al;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		al=new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) {
			al[i]=new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			al[a].add(new Node(b, cost));
		}

		int max = 0;
		for (int i = 1; i < N+1; i++) {
			if (i == X)
				continue;
			int res = dijkstra(i, X) + dijkstra(X, i);
			max = max < res ? res : max;
		}
		System.out.println(max);
	}

	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];

		Arrays.fill(dist, 10000000);
		dist[start] = 0;

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int s = node.s;
			int cost = node.cost;

			if (s == end)
				return cost;

			for (int i = 0; i < al[s].size(); i++) {
				Node next=al[s].get(i);
				if ( dist[next.s] > dist[s] + next.cost) {
					dist[next.s] = dist[s] + next.cost;
					pq.add(new Node(next.s, dist[next.s]));
				}
			}
		}
		return 0;
	}
}

class Node implements Comparable<Node> {
	int s;
	int cost;

	public Node(int s, int cost) {
		super();
		this.s = s;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

}