package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class backjoon_최솟값 {
    static int startIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int K = (int) (Math.log(N) / Math.log(2)) + 1;// 높이 2의 k승

        startIndex = (int) Math.pow(2, K);

        int[] indexTree = new int[2 * startIndex];

        Arrays.fill(indexTree, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            update(i, indexTree, num);
        }
//        System.out.println(Arrays.toString(indexTree));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(query(from, to, indexTree)).append("\n");
        }
        System.out.println(sb);

    }

    private static void update(int idx, int[] indexTree, int num) {

        idx += startIndex;
        indexTree[idx] = num;
        idx /= 2;

        while (idx > 0) {
            indexTree[idx] = Math.min(indexTree[idx * 2], indexTree[idx * 2 + 1]);
            idx /= 2;
        }
    }

    private static int query(int left, int right, int[] indexTree) {
        left += startIndex - 1;//1 based index
        right += startIndex - 1;

        int min = Integer.MAX_VALUE;

        while (left <= right) {
            if (left % 2 == 1)//짝 수 노드인 경우에는  왼쪽오른쪽 둘다 포함되므로 어차피 부모노드에서 검사
                min = (int) Math.min(indexTree[left], min);
            if (right % 2 == 0)
                min = (int) Math.min(indexTree[right], min);

            //left가 홀수일경우 right가 짝수일 경우를 고려해서 계산
            left = (left + 1) / 2;
            right = (right - 1) / 2;
        }
        return min;
    }
}
