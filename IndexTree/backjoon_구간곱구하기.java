package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_구간곱구하기 {
    static int startIndex;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());


        int K = (int) (Math.log(N) / Math.log(2)) + 1;
        startIndex = (int) Math.pow(2, K);


        long[] indexTree = new long[startIndex * 2];
        Arrays.fill(indexTree, 1);

        for (int i = 1; i <= N; i++) {
            int value = Integer.parseInt(br.readLine());
            update(indexTree, i, value);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
//            System.out.println(Arrays.toString(indexTree));
            if (order == 1) {
                update(indexTree, from, to);
            } else {
                sb.append(multiply(indexTree, from, to)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int multiply(long[] indexTree, int from, int to) {
        from = startIndex + from - 1;
        to = startIndex + to - 1;

        long mul = 1;
        while (from <= to) {
            if (from % 2 == 1)
                mul = (mul  * indexTree[from] ) % MOD;
            if (to % 2 == 0)
                mul = (mul  * indexTree[to] ) % MOD;

            from = (from + 1) / 2;
            to = (to - 1) / 2;
        }
        return (int)mul;

    }

    private static void update(long[] indexTree, int index, int value) {
        index = startIndex + index - 1;
        indexTree[index] = value;
        index /= 2;

        while (index > 0) {
            indexTree[index] = (indexTree[index * 2] * indexTree[index * 2 + 1]) % MOD;
            index /= 2;
        }
    }
}
