package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_내리막길 {
	static int h,w;
	static int[][] d = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
	static int[][] memo ;
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		int[][] map = new int[h][w];
		memo = new int[h][w];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < h; i++) {
			Arrays.fill(memo[i],-1);
		}
		System.out.println(dfs(map,0,0));
		

	}

	private static int dfs(int[][] map, int x, int y) {
		if (x == h - 1 && y == w - 1) {
			return 1;
		}
		if(memo[x][y]>=0)
			return memo[x][y];
		
		memo[x][y]=0;//-1을 0으로 바꿔서 방문처리
		
		for (int i = 0; i < 4; i++) {
			int dx = x + d[i][0];
			int dy = y + d[i][1];

			if (dx < 0 || dy < 0 || dx >= h || dy >= w || map[dx][dy] >= map[x][y])
				continue;
			
			if(memo[dx][dy]!=0)
				memo[x][y]+=
				
			dfs(map,dx,dy);
		}	
		return memo[x][y];
	}

}
