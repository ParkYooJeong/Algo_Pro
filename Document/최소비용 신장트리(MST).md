

[TOC]



# **최소비용 신장트리**

모든 정점들을 가장 적은 수의 간선과 비용으로  연결하는것.

**알고리즘 종류**

- 크루스칼 알고리즘(Kruskal)
- 프림 알고리즘(Prim)

**조건**

- 가중치의 합이 최소여야한다.
- n-1개의 간선만 사용해야 한다.
- 사이클이 포함되어서는 안된다.

두 노드가 같은 집합에 속해있으면 사이클이 형성된다. 

쉽게 말해서, 부모노드가 같은 두 노드를 연결했을 때 사이클이 형성된다.

 그 검사를 해주는  알고리즘을 Union-Find 알고리즘이라고 한다.


<br>

## 유니온 파인드(Union-Find)

---

union : 두 노드를 연결한다

find : 부모가 같은지 확인한다.(같은 그래프인지 확인)



**예시**

- {1,2,3,4,5,6}의 집합이 있다고 가정하자. 집합의 원소를 분리하면 다음과 같이 된다.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Dsu_disjoint_sets_init.svg/720px-Dsu_disjoint_sets_init.svg.png)



- union(1,2),union(2,5),union(5,6), union(5,8), union(3,4)를 했을 때 결과는 다음과 같다.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Dsu_disjoint_sets_final.svg/720px-Dsu_disjoint_sets_final.svg.png)

- 그래프로 표현을 하면 다음과 같이 된다.



![Tree](https://twpower.github.io/images/20180419_115/Tree.png)

- parent 배열

|  1   |  2   |  3   |  4   |  5   |  6   |  7   |  8   |
| :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: |
|  1   |  1   |  3   |  3   |  1   |  1   |  7   |  1   |



### 구현 

1. 배열 초기화 

~~~  java
for(int i=0;i<parent.length;i++){
    parent[i]=i;
}
~~~

2. 파라미터로 받은 x노드의 부모를 저장하고 리턴하는 메소드

```java
int find(int x){
	if(parent[x]==x)//루트노드를 찾을 때 까지 재귀를 돈다.
		return x;
	return parent[x]=find(parent[x]);//경유하는 노드를 모두 루트노드로 갱신 시킴
}
```

3. 두 노드를 연결하는 메소드( 부모를 동일하게 맞춘다 )

```java
boolean union(int a, int b){
    int ax=find(a);
    int bx=find(b);
    if(ax!=bx)//부모가 다르다면 합쳐준다.    
		parents[bx]=ax;

}
```

Union-Find를 사용해서 Kruskal 알고리즘을 구현해보자.

<br>
<br>

## 크루스칼(Kruskal) 알고리즘

---

크루스칼(Kruskal) 알고리즘은 그리디(Greedy) 기법을 이용한다. 

그리디 기법이란, 결정을 할때마다 그 순간에 가장 좋다고 생각되는 것을 해답을 선택함으로써 최종적인 해답에 도달하는 것이다.

먼저 그래프의 간선들을 가충지의 오름차순으로 정렬한다.

정렬된 간선들의 리스트에서 사이클을 형성하지 않는 간선을 찾아서 현재의 최소 비용 신장 트리의 집합에 추가한다.

만약 사이클을 형성하면 그 간선은 제외된다.



![img](https://t1.daumcdn.net/cfile/tistory/991BD6335A463E8221)



![img](https://t1.daumcdn.net/cfile/tistory/99748A335A463E8413)



![img](https://t1.daumcdn.net/cfile/tistory/99B2A5335A463E8508)



![img](https://t1.daumcdn.net/cfile/tistory/996C28335A463E8514)



![img](https://t1.daumcdn.net/cfile/tistory/99ACD8335A463E8609)



![img](https://t1.daumcdn.net/cfile/tistory/99B2A3335A463E8608)



![img](https://t1.daumcdn.net/cfile/tistory/996318335A463E8615)



```java
class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int cost;
    Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) {
    	return this.cost-o.cost;
    }
}

public class Main {
    public static int[] parent;
    public static PriorityQueue<Node> pq;
    
	//union-find
       public static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if(x != y) {
            parent[x]=y;
            return true;
        }                    
         return false;//부모노드가 같을시 union 안됨.
    }
    public static void main(String[] args) {
        pq = new PriorityQueue<>();
        pq.add(new Edge(1,4,4));
        pq.add(new Edge(1,2,6));
        pq.add(new Edge(2,3,5));
        pq.add(new Edge(2,4,3));
        pq.add(new Edge(2,5,7));
        pq.add(new Edge(2,6,8));
        pq.add(new Edge(3,6,8));
        pq.add(new Edge(4,5,9));
        pq.add(new Edge(5,6,11));
        
	//kruskal	
        parent = new int[7];
        for(int i = 1; i <= 6; i++) {
            parent[i] = i;
        }
		
		int sum = 0;
        
		while(!pq.isEmpty()){
			Edge edge = pq.poll();
    
			if(union(edge.v1, edge.v2)) {// 부모가 같지않으면(사이클 x)
				sum += edge.cost;	//최단거리 			
            }
		}
     	System.out.println(sum);
}
```

<br>

## 프림(Prim) 알고리즘

---

Kruskal의 알고리즘은 간선 선택을 기반으로 하는 알고리즘인 반면, Prim알고리즘은 정점 선택을 기반으로 하는 알고리즘이다.

프림(Prim) 알고리즘은 시작 정점에서 부터 출발하여 인접한 정점 중 최소 비용으로 이동 가능한 정점을 선택한다.

bfs





<DISTANCE 배열>

| 1    | 2    | 3    | 4    | 5    | 6    | 7    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 0    | INF  | INF  | INF  | INF  | INF  | INF  |

<우선순위 큐>

|      |      |      |       |      |      |
| ---- | ---- | ---- | ----- | ---- | ---- |
|      |      | 7 15 | 7  15 | 7 15 | 7 15 |
|      | 3 10 | 6 11 | 6 11  | 7 14 | 7 14 |
|      | 5 9  | 3 10 | 3 10  | 6 11 | 6 11 |
| 3 10 | 3 7  | 5 9  | 5 9   | 3 10 | 3 10 |
| 2 5  | 4 6  | 3 7  | 6 2   | 5 9  | 7 3  |

![img](https://t1.daumcdn.net/cfile/tistory/991BD6335A463E8221)

```java
public class Prim_pq {

	public static class Vertex implements Comparable<Vertex>{
		int v, dist;

		Vertex(int v, int dist){
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		int V = Integer.parseInt(input1[0]);
		int E = Integer.parseInt(input1[1]);

		int result = 0;

		List<Vertex> list[] = new ArrayList[V];

		for(int i = 0; i < V; i++) //인접리스트 사용 해당정점에 인접한 정점들을 넣어줌
            list[i] = new ArrayList<Vertex>();

		// 간선의 정보와 가중치 입력 받기
		for(int i = 0; i < E; i++) {
			String input2[] = br.readLine().split(" ");
			int a = Integer.parseInt(input2[0]) - 1;
			int b = Integer.parseInt(input2[1]) - 1;
			int c = Integer.parseInt(input2[2]);
			// 인접 리스트 구성
			list[a].add(new Vertex(b, c));  
			list[b].add(new Vertex(a, c));  
		}

		// 선택되었는지 아닌지 판단하기 위한 boolean 배열
		boolean visited[] = new boolean[V];
		// 현재 선택된 정점들로부터 도달할 수 있는 최소 거리 저장 배열
		int distance[] = new int[V];

		// 모두 최대 값으로 key를 갱신
		Arrays.fill(distance, Integer.MAX_VALUE);

		distance[0] = 0; // 처음 선택한 정점은 거리 0
		int cnt = 0;

		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		q.offer(new Vertex(0, distance[0]));

		while(true) {//bfs
			Vertex cur = q.poll();

			if(visited[cur.v]) continue; 
			visited[cur.v] = true;
			result += cur.dist;//최단거리
			cnt++;

			if(cnt == V) break;//모든 노드를 다돌았으면 끝

			for(Vertex v : list[cur.v]) {
				if(!visited[v.v] && distance[v.v] > v.dist) {//DISTANCE는 연산 속도를 빠르게 해주기 위해
					distance[v.v] = v.dist;
					q.offer(new Vertex(v.v, distance[v.v]));
				}
			}
		}
		System.out.println(result);
	}
}
```


<br>


#  최단경로

최단경로는 정점 u와 정점 v를 연결하는 경로 중에서 간선들의 가중치의 합이 최소가 되는 경로를 찾는 문제

**알고리즘 종류**

- 다익스트라 알고리즘(Dijkstra)

- 플로이드 알고리즘(Floyd)

- 벨만 포드 알고리즘(Bellman-Ford algorithm)
<br>

## 다익스트라 알고리즘(Dijkstra)

---

다익스트라 알고리즘은 네트워크에서 하나의 시작 정점으로부터 모든 다른 정점까지의 최단 경로를 찾는 알고리즘이다.

PRIM과 좀 헷갈렸다. 

PRIM은 모든 정점을 지나쳐 갈수있는 최소거리

다익스트라는 출발점에서 나머지 노드로 갈 수 있는 최소거리, VISITED 사용 안함(경유해서 갈경우 더짧을 수 있다.)

<img src="C:\Users\bakyj\AppData\Roaming\Typora\typora-user-images\image-20201025142601417.png" alt="image-20201025142601417" style="zoom:120%;" />

| 1    | 2    | 3    | 4    | 5    |
| ---- | ---- | ---- | ---- | ---- |
| 0    | INF  | INF  | INF  | INF  |



|      |      |      |      |      |      |
| ---- | ---- | ---- | ---- | ---- | ---- |
|      |      |      |      |      |      |
|      |      | 3 6  |      |      |      |
|      | 3 6  | 3 4  | 3 6  |      |      |
| 1 0  | 4 3  | 2 4  | 3 4  | 3 6  |      |



```java
import java.util.*;
 

class Element implements Comparable<Element>{
    private int index;
    private int distance;
    
    Element(int index, int distance){
        this.index = index;
        this.distance = distance;
    }
    
    public int compareTo(Element o){
        return this.distance <= o.distance ? -1 : 1;
    }
}
 
 

public class dijkstraTest05 {
    
    static boolean[] visit;
    static int[] dist;
    static int[][] ad;//인접 리스트 사용가능
    static int nE, nV;
    static final int inf = 100000;
    
    
        public static void ssp(int start){
            PriorityQueue <Element> q = new <Element> PriorityQueue();
            dist[start] = 0;
            q.offer(new Element(start, dist[start]));
            
            while(!q.isEmpty()){
                int cost = q.peek().distance;
                int here = q.peek().index;
                q.poll();
                
                if(cost > dist[here])//이부분 없애면 음수도가능한가?(사이클이 없다는 가정하에)                    
                    continue;
                
                System.out.print(here);
    
                for(int i = 0; i <= nV; i++){
                    if(ad[here][i] != 0 && dist[i] > dist[here] + ad[here][i]){//경유해서 가는 경우가 짧다면
                        dist[i] = dist[here] + ad[here][i];
                        q.offer(new Element(i, dist[i]));
                    }
                }       
            }
            
            System.out.println();
            for(int i =1 ; i <= nV; i++){
                System.out.print(dist[i]);
            }
        }
```


<br>


## 플로이드 알고리즘(Floyd)

---

모든 정점 사이의 최단 경로를 구하는 것이다.  

O(n^3)이기 때문에 정점의 갯수가 적을 때 유리하다.

동빈나의 블로그 참조^^♡

https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221234427842&proxyReferer=https:%2F%2Fwww.google.com%2F

```java
for(k=0;k<n;k++){//경유지
	for(i=0;i<n;i++){//출발지
		for(j=0;j<n;j++){//도착지
			if(A[i][k]+A[k][j]<A[i][j])
				A[i][j]=A[i][k]+A[k][j];
		}
	}
}
```



![image-20201025132011094](C:\Users\bakyj\AppData\Roaming\Typora\typora-user-images\image-20201025132011094.png)

|      | 1    | 2    | 3    | 4    |
| ---- | ---- | ---- | ---- | ---- |
| 1    | 0    | 5    | INF  | 8    |
| 2    | 7    | 0    | 9    | INF  |
| 3    | 2    | INF  | 0    | 4    |
| 4    | INF  | INF  | 3    | 0    |




<br>


##  벨만 포드 알고리즘(Bellman-Ford algorithm)

---

다익스트라 알고리즘과 마찬가지로 시작점을 정해 주면 다른 모든 정점으로의 최단 경로를 구하는데, 다익스트라 알고리즘보다는 시간이 오래 걸린다.

but, 이 알고리즘은 **간선 cost가 음수일 때**(시간이 거꾸로 갈때) 도 사용할 수가 있다. 

https://blog.naver.com/aver2933/222033146867

![img](https://mblogthumb-phinf.pstatic.net/20160825_263/kks227_14721227940303niv4_PNG/1.png?type=w2)



1을 경유해서 가는 경우가 더빠르지만 다익스트라는 1번을 경유하지않고 2번으로 가는 길을 선택한다.



```C
#define INF 1<<30

n= 정점의 갯수
int dist[n+1] ( 최단 거리를 담을 배열 )
dist의 원소를 INF으로 초기화.

bool is_negative_cycle=false;
for (int count = 1; count <= n; count++) { (총 n번 계산을 반복합니다. )

	for (int check_node = 1; check_node <= n; check_node) { (모든 정점을 탐색합니다. )
		if (dist[check_node] == INF) continue; ( 정점의 최단거리가 아직 갱신되지 않았으면 빠꾸)
		for (check_node에 붙은 정점 탐색) {
			int connect_node, connect_node_weight 값을 정해줍니다.
				if (dist[check_node] + connect_node_weight < dist[connect_node]) {
					dist[connect_node] = dist[check_node] + connect_node_weight
						if (count == n) is_negative_cycle = true; (이건 밑에 설명 )
			}
		}
	}

}




```

만약, 두 정점간에 서로의 가중치가 음수인 사이클이 생긴다면, 최단거리를 구하는게 의미가 없어집니다. 

그래서 그것을 캐치하는 것이 중요한데, 그 코드가 바로 if (count == n) is_negative_cycle = true; 입니다.

벨만 포드 알고리즘은 무조건, n-1번이하의 시행횟수로 모든 정점의 최단거리 갱신이 끝난다고 합니다. ( N-1번까지는 모든 정점을 거쳐서 갈 수 있는 경우도 있음. ) 

그래서, n번째를 따로 한번 더 시행을 하여서, 최단거리 값이 바뀌면, 음수 사이클이 있다고 판단이 가능합니다

