# Algo_Pro
프로 준비


[최소신장트리 & 최단경로 알고리즘](./Document/최소비용신장트리(MST).md)

<details>
 <summary>최소공통조상(LCA)</summary>
 <br>
 
 - 초기화
 ```java
 
            H = (int) (Math.log(N) / Math.log(2)) + 1;
 
            depth = new int[N+1];
            parent = new int[H + 1][N + 1];
 
            for (int i = 2; i <= N; i++) {
                parent[0][i] = Integer.parseInt(st.nextToken());//2의 0승 위의 노드
                depth[i] = depth[parent[0][i]] + 1;//현재 노드는 부모노드의 +1
            }
 
            for (int k = 1; k <= H; k++) {
                for (int v = 1; v <= N; v++) {
                    parent[k][v] = parent[k - 1][parent[k - 1][v]];// 표를 채워줌
                }
            }
 ```
 
 - 로직
                                   
 ```java
     private static int lca(int a, int b) {
        // a>b swap
        if (depth[a] < depth[b]) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
 
        // a==b가 되도록 2^k 만큼 올라간다.
        int diff = depth[a] - depth[b];
        for (int k = H; k >= 0; k--) {
            if (diff >= (1 << k)) {
                a = parent[k][a];
                diff = depth[a] - depth[b];
            }
        }
        if (a == b)
            return a;
        // p[k][a]==p[k][b] 가 되도록 올라간다.
        for (int k = H; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
 
        return parent[0][a];
    }
 ```
 </details>

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
<summary>위상정렬</summary>

```
 //순서가 정해져 있는 작업을 차례로 수행할때
 // 사이클 없는 그래프일때 가능
 
 //1. 진입차수가 0인 정점을 큐에 삽입
 //2. 큐에서 원소를 꺼내 연결된 모든 간선을 제거
 //3. 제거한 간선 중에 진입차수0이면 큐에 삽입
 //4. 큐가 빌때 까지 2~3번 반복
 
 //큐에서 꺼내는 순서가 위상정렬 결과
```
 
</details> 

<details>
    <summary>인덱스트리(IndexTree)</summary>
<br>
 
- 배열 생성
 
 ```java
        int K = (int) (Math.log(N) / Math.log(2)) + 1;// 높이 2의 k승

        startIndex = (int) Math.pow(2, K);

        int[] indexTree = new int[2 * startIndex];

        Arrays.fill(indexTree, Integer.MAX_VALUE);
 ```
 
 - 최솟값 
 
 ```java
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
 ```
 </details>
 
 
<details>
    <summary>세그먼트트리(SegmentTree)</summary>
<br>
 
 - 구간 합
 
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

 - 구간 최대 최소
 
```java
package pro;

class SegmentTree3 {
    int[] minTree;
    int[] maxTree;

    public SegmentTree3(int[] arr, int N) {
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


    // left right 현재 인덱스 범위
// a b는 구하는 범위
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


    public int minUpdate(int left, int right, int[] arr, int change, int idx, int node) {
        int mid = (left + right) / 2;
        if (left == right) {
            return minTree[node] = arr[left];
        } else if (idx < left || idx > right)// 없으면 시간초과
            return minTree[node];

        return minTree[node] = Math.min(minUpdate(left, mid, arr, change, idx, node * 2),
                minUpdate(mid + 1, right, arr, change, idx, node * 2 + 1));
    }

    public int maxUpdate(int left, int right, int[] arr, int change, int idx, int node) {

        int mid = (left + right) / 2;
        if (left == right) {
            return maxTree[node] = arr[left];
        } else if (idx < left || idx > right)// 없으면 시간초과
            return maxTree[node];

        return maxTree[node] = Math.max(maxUpdate(left, mid, arr, change, idx, node * 2),
                maxUpdate(mid + 1, right, arr, change, idx, node * 2 + 1));
    }
```



</details>

 <details>
    <summary>에라토스테네스의 체</summary>

  ```java
  boolean[] arr = new boolean[b + 1];
        arr[1]=true;//1제외

        for (int i = 2; i <= b; i++) {
            if (arr[i])//이미 지워진 수 건너 뛰기
                continue;

            for (int j = 2 * i; j <= b; j += i) {
                //지울 때 자기자신은 지우지 않는다.
                arr[j] = true;
            }
        }
  ```
 </details>

 <details>
    <summary>유클리드 호제법(Euclidean algorithm)</summary>

```java
  //a와 b의 최대공약수를 구하기 위해서 a mod b=c => b mod c 무한반복 하다가 c가 0일 때 b가 최대공약수
  
  private static GCD(int a, int b){
    int tmp;
    while(b){      //b가 0이 될 때까지
      tmp = a % b;
      a = b;
      b = c;
  }
  return a;
}
```
 </details>
