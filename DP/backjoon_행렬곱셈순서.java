package dp;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class backjoon_행렬곱셈순서 {
    static int[][] arr, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][2];
        d = new int[N + 1][N+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {//차이
            for (int j = 1; j+i <= N; j++) {//시작점
                int s=j;
                int e=j+i;

                d[s][e] = 987654321;
                for (int k = s; k < e; k++) {
                    d[s][e] = Math.min(d[s][k]+d[k+1][e] + arr[s][0] * arr[k][1] * arr[e][1], d[s][e]);
                }
            }
        }
        System.out.println(d[1][N]);

        //recursive
//        System.out.println(cal(1, N));

    }

    private static int cal(int i, int j) {
        if (i == j) return 0;

        if (d[i][j] == 0) {
            d[i][j] = 987654321;
            for (int k = i; k < j; k++) {
                d[i][j] = Math.min(cal(i, k) + cal(k + 1, j) + arr[i][0] * arr[k][1] * arr[j][1], d[i][j]);
            }
        }

        return d[i][j] ;
    }
}
