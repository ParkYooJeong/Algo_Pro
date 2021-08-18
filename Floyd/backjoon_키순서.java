package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//플로이드 오랜만에 하니까 어색쓰..... 경출도 ~~~~~~

public class backjoon_키순서 {
	final static int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] tall = new int[N + 1][N + 1];

		for (int i = 0; i < tall.length; i++) {
			Arrays.fill(tall[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			tall[a][b] = 1;
		}

		for (int i = 1; i < tall.length; i++) {// 경유지
			for (int j = 1; j < tall.length; j++) {// 출발지
				for (int k = 1; k < tall.length; k++) {// 도착지
					if (tall[j][k] > tall[j][i] + tall[i][k]) {
						tall[j][k] = 1;
					}
				}
			}
		}
		int answer = 0;

		for (int i = 1; i < tall.length; i++) {// 확인할 학생번호
			int count = 0;

			for (int j = 1; j < tall.length; j++) {
				if (i == j)
					continue;
				if (tall[i][j] == 1)
					count++;
				else if (tall[j][i] == 1)
					count++;
			}

			if (count == N - 1)
				answer++;
		}
		System.out.println(answer);
	}
}
