# Algo_Pro
프로 준비

<details>
<summary>병합정렬(MergeSort)</summary>

```java
 private static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(arr, start, middle);
            mergeSort(arr, middle + 1, end);
            merge(arr, start, middle, end);
        }
    }

    private static void merge(int[] arr, int start, int middle, int end) {
        //이부분에서 copy배열 생성하면 n^2되므로 주의
        for (int i = start; i <= end; i++) {
            copy[i] = arr[i];
        }

        int leftPointer = start;
        int rightPoint = middle + 1;
        int pointer = start;

        //오름차순병합
        while (leftPointer <= middle && rightPoint <= end) {
            if (copy[leftPointer] < copy[rightPoint]) {
                arr[pointer++] = copy[leftPointer++];
            } else {
                arr[pointer++] = copy[rightPoint++];
            }
        }
        //왼쪽배열에 남은 애들
        for (int i = leftPointer; i <= middle; i++) {
            arr[pointer++] = copy[i];
        }
    }
 ```


</details>
