package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class backjoon_동전 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] coin = new int[n];

			for (int i = 0; i < coin.length; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			int price = Integer.parseInt(br.readLine());

			int[] dp = new int[price + 1];
			dp[0] = 1;

			for (int i = 0; i < coin.length; i++) {
				for (int j = coin[i]; j < dp.length; j++) {
					dp[j] += dp[j - coin[i]];
				}
			}
			sb.append(dp[price]).append("\n");
		}
		System.out.println(sb);
	}

}
