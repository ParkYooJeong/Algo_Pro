package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_구간합구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] arr = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree tree = new SegmentTree(arr, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long dif=c-arr[b];
                arr[b]=c;//arr 배열에도 업데이트
                tree.update(1, N, b, dif, 1);
            }
            else {
                System.out.println(tree.sum(1, N, 1, b, (int) c));
            }
        }
    }
}

class SegmentTree {
    long[] segmentTree;

    SegmentTree(long[] arr, int n) {
        segmentTree = new long[n * 4];//다시확인
        init(arr, 1, n, 1);
    }

    //node를 root로 하는 서브트리를 초기화하고 이구간의 최소치를 반환한다.
    private long init(long[] arr, int left, int right, int node) {

        if (left == right) {
            //최말단층
            return segmentTree[node] = arr[left];
        }
        int mid = (left + right) / 2;

        //루트노드에 자식노드 합친 값
        return segmentTree[node] = init(arr, left, mid, node * 2) + init(arr, mid + 1, right, node * 2 + 1);
    }

    //인덱스구간 left right
    //구해야 하는 범위 start end
    public long sum(int left, int right, int node, int start, int end) {
        if (right < start || left > end)
            return 0;

        //구해야하는 구간안에 포함되어있는 경우
        if (start <= left && right <= end) {
            return segmentTree[node];
        }
        int mid = (left + right) / 2;
        return sum(left, mid, node * 2, start, end) + sum(mid + 1, right, node * 2 + 1, start, end);
    }

    //idx left right는 arr 배열의 인덱스
    //node는 인덱스 트리의 인덱스
    public void update(int left, int right, int idx, long dif, int node) {
        if (idx < left || idx > right)
            return;

        //범위 안에 있으면 내려가면서 갱신 차이만큼 더해준다.
        segmentTree[node] += dif;
        if (left == right)
            return;

        int mid = (left + right) / 2;
        update(left, mid, idx, dif, node * 2);
        update(mid + 1, right, idx, dif, node * 2 + 1);
    }
}