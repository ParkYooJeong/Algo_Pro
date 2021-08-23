package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class backjoon_수정렬하기2 {
    static int N;
    static int[] copy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr=new int[N];
        copy=new int[N];
        for (int i = 0; i < N; i++) {
            arr[i]=Integer.parseInt(br.readLine());
        }

        mergeSort(arr,0,N-1);
        StringBuilder sb=new StringBuilder();
        for(int num:arr){
            sb.append(num).append("\n");
        }
        System.out.println(sb);

    }

    private static void mergeSort(int[] arr, int start, int end) {
        if(start<end){
            int middle=(start+end)/2;
            mergeSort(arr,start,middle);
            mergeSort(arr,middle+1,end);
            merge(arr,start,middle,end);
        }
    }

    private static void merge(int[] arr, int start, int middle, int end) {
        for (int i = start; i <= end; i++) {
            copy[i]=arr[i];
        }

        int leftPointer=start;
        int rightPoint=middle+1;
        int pointer=start;

        //오름차순병합
        while(leftPointer<=middle&&rightPoint<=end){
            if(copy[leftPointer]<copy[rightPoint]){
                arr[pointer++]=copy[leftPointer++];
            }else{
                arr[pointer++]=copy[rightPoint++];
            }
        }
        //왼쪽배열에 남은 애들
        for (int i = leftPointer; i <= middle; i++) {
            arr[pointer++]=copy[i];
        }
    }
}
