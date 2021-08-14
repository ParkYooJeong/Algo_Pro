package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//인오더: 중위순회 L R 루트 밑에서 부터 부모노드 거쳐서 탐색
//포스트 오더: 후위순회 L 루트 R 밑에서 부터 올라감(자식노드 부터)
//프리오더 : 전위순회 루트 L R DFS 순서와 같음
public class 트리의순회 {
	static int maxFloor;
	static int index;
	static int[] postorder;
	static int[] inorder;
	static int[] position;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		inorder = new int[num];
		postorder = new int[num];

		maxFloor = (int) (Math.log(num) / Math.log(2));// 이진로그 계산법

		for (int i = 0; i < num; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < num; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		// 중위순회 인덱스
		position = new int[100001];
		for (int i = 0; i < num; i++) {
			position[inorder[i]] = i;
		}

		// 전위순회
		preorder(0, num - 1, 0, num - 1);

		System.out.println(sb);

	}

	private static void preorder(int pl, int pr, int il, int ir) {
		if (pl > pr || il > ir)
			return;
		int root = postorder[pr];
		int rootIndex = position[root];
		int rcount = ir - rootIndex;
		sb.append(root).append(" ");

		// 왼쪽탐색
		preorder(pl, pr - rcount - 1, il, rootIndex - 1);
		preorder(pr - rcount, pr - 1, rootIndex + 1, ir);

	}

}
