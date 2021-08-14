package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class expert_1244 {
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= test; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String num = st.nextToken();
			int change = Integer.parseInt(st.nextToken());
			max=0;
			
			int[] number = new int[num.length()];

			for (int i = 0; i < number.length; i++) {
				number[i] = num.charAt(i) - '0';
			}
			if(number.length<change) 
				change = number.length;
			
			com(number, 0, change);

			sb.append("#").append(" ").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	private static void com(int[] number, int count, int end) {
		if (count == end) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < number.length; i++) {
				sb.append(number[i]);
			}
			int num = Integer.parseInt(sb.toString());
			max = max < num ? num : max;
			return;
		}
		
		for (int i = 0; i < number.length; i++) {
			for (int j = i + 1; j < number.length; j++) {
				int temp = number[i];
				number[i] = number[j];
				number[j] = temp;
				com(number, count + 1, end);
				temp = number[i];
				number[i] = number[j];
				number[j] = temp;
			}
		}
	}


}
