package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_최솟값과최댓값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree2 tree = new SegmentTree2(arr, N);
        StringBuilder sb=new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min=tree.minFind(1, N, a, b, 1);
            int max= tree.maxFind(1, N, a, b, 1);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);

    }
}

class SegmentTree2 {
    int[] minTree;
    int[] maxTree;

    public SegmentTree2(int[] arr, int N) {
        minTree = new int[N * 4];
        maxTree = new int[N * 4];
        minInit(arr, 1, N, 1);
        maxInit(arr, 1, N, 1);
    }

    private int maxInit(int[] arr, int left, int right, int node) {
        if (left == right)
            return maxTree[node] = arr[left];

        int mid = (left + right) / 2;
        return maxTree[node] = Math.max(maxInit(arr, left, mid, node * 2), maxInit(arr, mid + 1, right, node * 2 + 1));
    }

    private int minInit(int[] arr, int left, int right, int node) {
        if (left == right)
            return minTree[node] = arr[left];

        int mid = (left + right) / 2;
        return minTree[node] = Math.min(minInit(arr, left, mid, node * 2), minInit(arr, mid + 1, right, node * 2 + 1));
    }

    //left right 현재 인덱스 범위
    //a b는 구하는 범위
    public int minFind(int left, int right, int a, int b, int node) {
        if (a <= left && right <= b) {
            return minTree[node];
        } else if (left > b || right < a) {
            return Integer.MAX_VALUE;
        }
        int mid = (left + right) / 2;
        return Math.min(minFind(left, mid, a, b, node * 2), minFind(mid + 1, right, a, b, node * 2 + 1));
    }

    public int maxFind(int left, int right, int a, int b, int node) {
        if (a <= left && right <= b) {
            return maxTree[node];
        } else if (left > b || right < a) {
            return 0;
        }
        int mid = (left + right) / 2;
        return Math.max(maxFind(left, mid, a, b, node * 2), maxFind(mid + 1, right, a, b, node * 2 + 1));
    }
}