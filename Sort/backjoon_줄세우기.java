package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class backjoon_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] students = new int[N + 1];
        ArrayList<Integer>[] al = new ArrayList[N + 1];
        for (int i = 0; i < al.length; i++) {
            al[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            students[b]++;
            al[a].add(b);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < students.length; i++) {
            if (students[i] == 0)
                q.add(i);
        }
        StringBuilder sb=new StringBuilder();
        while (!q.isEmpty()) {
            int person = q.poll();
            sb.append(person).append(" ");

            for (int next : al[person]) {
                students[next]--;
                if(students[next]==0)
                    q.add(next);
            }
        }
        System.out.println(sb);
    }
}
