package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class backjoon_도시분할계획 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[] parents=new int[N+1];
        for (int i = 0; i <N+1 ; i++) {
            parents[i]=i;
        }

        PriorityQueue<int[]> pq=new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);

        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());

            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());

            pq.add(new int[]{a,b,c});
        }

        int sum=0;
        int last=0;
        int count=N-1;//시간 단축용

        while(!pq.isEmpty()&&count>0){
            int[] arr=pq.poll();
            int a=arr[0];
            int b=arr[1];
            int c=arr[2];

            if(union(parents,a,b)){
                sum+=c;
                last=c;
                count--;
            }
        }
        sum-=last;
        System.out.println(sum);
    }

    private static boolean union(int[] parents,int a, int b) {
        a=find(parents,a); b=find(parents,b);

        if(a==b)
            return false;

        parents[a]=b;
        return true;
    }

    private static int find(int[] parents, int a) {
        if(parents[a]==a)
            return a;
        return parents[a]=find(parents,parents[a]);
    }
}
