package pro;

//dp는 어렵다...
//풀었던 문제지만 그때도 못풀었던거 같은데 다시풀어도 똑같이 못푼다.ㅠㅠ

public class Programmers_2xn타일링 {
	public int solution(int n) {
		int[] dp=new int[n+1];
		
		dp[0]=1;//만들수 없지만 1을 넣는다.
		dp[1]=1;
		dp[2]=2;
		
		//길이 -1에 세로 하나 추가. 길이 -2에 가로 두개 추가.
		
		for (int i = 3; i <= n; i++) {
			dp[i]=(dp[i-1]+dp[i-2])%1000000007;
		}
		return dp[n];
    }

}
