package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_전구 {
    static int[] color;
    static int[][] d;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        color = new int[N + 1];
        d = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            color[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i < N; i++) {//차이
            for (int j = 1; j + i <= N; j++) {//시작점
                int s = j;
                int e = j + i;

//                d[i][j] = 1 << 30;
                d[s][e] = 987654321;
                for (int k = s; k < e; k++) {//합치기
                    d[s][e] = Math.min(d[s][k] + d[k + 1][e] + (color[s] == color[k + 1] ? 0 : 1), d[s][e]);
                }

            }
        }
        System.out.println(d[1][N]);

        //리커시브
//      System.out.println(cal(1, N));
    }

    private static int cal(int i, int j) {
        if (i == j)
            return 0;
        if (d[i][j] == 0) {
            d[i][j] = 987654321;
            for (int k = i; k < j; k++) {
                d[i][j] = Math.min(d[i][j], cal(i, k) + cal(k + 1, j) + ((color[i] == color[k + 1]) ? 0 : 1));
            }
        }
        return d[i][j];
    }
}
