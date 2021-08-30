package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class backjoon_욕심쟁이판다 {
    static int N, max;
    static int[][] d = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        int[][] dp = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0) {
                    max = Math.max(dfs(dp, i, j), max);
                }
            }
        }

        System.out.println(max);
    }


    private static int dfs(int[][] dp, int x, int y) {
        if (dp[x][y] > 0)
            return dp[x][y];

        dp[x][y] = 1;

        int dx, dy;
        for (int i = 0; i < 4; i++) {
            dx = x + d[i][0];
            dy = y + d[i][1];

            if (dx < 0 || dy < 0 || dx >= N || dy >= N || map[dx][dy] <= map[x][y])
                continue;

            dp[x][y] = Math.max(dfs(dp, dx, dy) + 1, dp[x][y]);
        }

        return dp[x][y];
    }
}
