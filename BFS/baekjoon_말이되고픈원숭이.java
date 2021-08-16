package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//모든 상태에 따라 방문처리하기(남은 말 이동횟수)
//최단거리는 bfs
public class baekjoon_말이되고픈원숭이 {
	static int K, W, H;
	static int[][] map;
	static int[][] horse = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 } };
	static int[][] monkey = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
	static boolean[][][] visit;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		visit = new boolean[H][W][K + 1];
		map = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();


		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}

	}

	private static void solve() {

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0, 0, 0 });
		visit[0][0][0] = true;

		while (!q.isEmpty()) {
			int[] dot = q.poll();
			int x = dot[0];
			int y = dot[1];
			int hcount = dot[2];
			int count = dot[3];
			
			if (x == H - 1 && y == W - 1) {
				min = min > count ? count : min;
				continue;
			}

			count++;

			if (hcount < K) {
				for (int i = 0; i < 8; i++) {
					int dx = x + horse[i][0];
					int dy = y + horse[i][1];

					if (dx < 0 || dy < 0 || dx >= H || dy >= W || visit[dx][dy][hcount + 1] || map[dx][dy] == 1)
						continue;

					visit[dx][dy][hcount + 1] = true;
					q.add(new int[] { dx, dy, hcount + 1, count });
				}
			}

			for (int i = 0; i < 4; i++) {
				int dx = x + monkey[i][0];
				int dy = y + monkey[i][1];

				if (dx < 0 || dy < 0 || dx >= H || dy >= W || visit[dx][dy][hcount] || map[dx][dy] == 1)
					continue;

				visit[dx][dy][hcount] = true;
				q.add(new int[] { dx, dy, hcount, count });
			}
		}
	}
}
