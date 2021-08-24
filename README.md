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



<details>
    <summary>세그먼트트리(SegmentTree)</summary>

```java
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
```

</details>
