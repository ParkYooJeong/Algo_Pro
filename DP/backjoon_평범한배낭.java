package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP 를 일차원배열로 만들었더니 자기자신을 중복해서 쓰는 현상 발생...
//그래서 2차원 배열로 만들어서 전행에 자신의 무게를 더해 큰것을 반영
public class backjoon_평범한배낭 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int WEIGHT = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][WEIGHT + 1];
		int[][] items = new int[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			items[i][0] = w;
			items[i][1] = v;
		}

		for (int i = 1; i < N+1; i++) {
			int w = items[i-1][0];
			int v = items[i-1][1];

			for (int j = 1; j < WEIGHT + 1; j++) {
				if (j < w)
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = dp[i - 1][j - w] + v > dp[i - 1][j] ? dp[i - 1][j - w] + v : dp[i - 1][j];

			}
		}
		System.out.println(dp[N][WEIGHT]);
	}
}
