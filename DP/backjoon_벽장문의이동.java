package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class backjoon_벽장문의이동 {
    static int N, M, ans = 987654321;
    static int[] open;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        open = new int[N - 2];

        for (int i = 0; i < open.length; i++) {
            open[i] = Integer.parseInt(br.readLine());
        }


        dfs(A, B, 0, 0);
        System.out.println(ans);
    }

    private static void dfs(int open1, int open2, int level, int cnt) {
        if (level == N - 2) {
            ans = Math.min(ans, cnt);
            return;
        }

        dfs(open1, open[level], level + 1, cnt + Math.abs(open2 - open[level]));
        dfs(open[level], open2, level + 1, cnt + Math.abs(open1 - open[level]));
    }
}
