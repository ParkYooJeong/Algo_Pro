package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//x+y+z=k  x+y=k-z로 생각
//TreeSet에 넣어도 되겠군...(contains 사용)
public class backjoon_세수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        long[] twoSum = new long[N * N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                twoSum[idx++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(twoSum);
        int max = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int target = arr[i] - arr[j];
                if (binarySearch(twoSum, target, 0, N * N-1)) {
                    max = max < target+arr[j] ? target+arr[j] : max;
                }
            }
        }

        System.out.println(max);
    }

    private static boolean binarySearch(long[] arr, int target, int left, int right) {

        if (left > right) {
            return false;
        }
        int mid = (left + right) / 2;

        if (target == arr[mid])
            return true;
        else if (target < arr[mid])
            return binarySearch(arr, target, left, mid - 1);
        else
            return binarySearch(arr, target, mid + 1, right);
    }

}
