package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class backjoon_파티_플로이드 {
	static final int INF = 10000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][N + 1];

		for (int i = 0; i < arr.length; i++) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}
		for (int i = 0; i <  M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			arr[a][b] = cost;
		}

		for (int k = 1; k < arr.length; k++) {//경유지
			for (int i = 1; i < arr.length; i++) {//출발지
				for (int j = 1; j < arr.length; j++) {//도착지
					if (arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		int max = 0;
		for (int i = 1; i < arr.length; i++) {
			max = max < (arr[i][X] + arr[X][i]) ? arr[i][X] + arr[X][i] : max;
		}
		System.out.println(max);
	}
}
