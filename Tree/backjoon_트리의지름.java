package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//트리 DFS
public class backjoon_트리의지름 {
	static int max;
	static ArrayList<int[]>[] al;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		al = new ArrayList[N + 1];
		for (int j = 0; j < al.length; j++) {
			al[j] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			al[a].add(new int[] { b, cost });
		}

		dfs(1);
		System.out.println(max);
	}

	private static int dfs(int cur) {
		if (al[cur].size() == 0)
			return 0;
		
		//이진트리가 아닐 수 있으므로
		int first=0;//첫번째로 큰 간선
		int second=0;// 두번째로큰 간선
		
		int max_line = 0;
		
		for (int i = 0; i < al[cur].size(); i++) {
			int[] next = al[cur].get(i);//다음 노드
			
			int res = dfs(next[0]) + next[1];// 이어진 간선 + 다음 이어진 노드의 최대 간선의 합
			
			max_line = max_line < res ? res : max_line;// 최대 간선
			
			if(res>=first) {
				second=first;
				first=res;
			}
			else {
				if(res>second)
					second=res;
			}
		}
		
		max = max < first+second ? first+second : max;// 지름 최댓값 왼쪽 오른쪽
		return max_line;
	}
}
