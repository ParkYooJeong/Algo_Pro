package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_최대공약수하나빼기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] num = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = new int[N + 1];
        int[] right = new int[N + 1];

        left[1]=num[1]; right[N]=num[N];
        for (int i = 2; i <= N; i++) {
            left[i] = euclidean(left[i - 1], num[i]);
        }
        for (int i = 1; i < N; i++) {
            right[N - i] = euclidean(right[N - i + 1], num[N - i]);
        }

        int max = 0;
        int idx = 0;
        int res;

        for (int i = 1; i <= N; i++) {
            if (i == N)
                res = left[i - 1];
            else if (i == 1)
                res = right[i + 1];
            else
                res = euclidean(left[i - 1], right[i + 1]);

            if (res > max) {
                max = res;
                idx = i;
            }
        }
        StringBuilder sb = new StringBuilder();

        if (num[idx] % max == 0)
            sb.append(-1);
        else
            sb.append(max).append(" ").append(num[idx]);

        System.out.println(sb);
    }

    private static int euclidean(int a, int b) {
        if (a == 0) {
            return b;
        } else if (b == 0)
            return a;
        int na = 1;
        while (na > 0) {
            na = a % b;
            a = b;
            b = na;
        }
        return a;
    }
}
