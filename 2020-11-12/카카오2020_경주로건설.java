class Solution {
   public static int answer = Integer.MAX_VALUE;
	public static int[] drow = { 0, 0, 0, 1, -1 };
	public static int[] dcols = { 0, 1, -1, 0, 0 };

	public int solution(int[][] board) {
		int N = board.length - 1;
		int[][] visited = new int[board.length][board.length];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited.length; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dfs(board, visited, 0, 0, 0, N, 0);
		return answer;
	}

	public void dfs(int[][] map, int[][] visited, int row, int cols, int direction, int N, int cost) {
		if(cost > visited[row][cols]) {
			return;
		}else {
			visited[row][cols]=cost;
		}

		if (row == N && cols == N) { // 종료조건
			answer=Math.min(answer,visited[N][N]);
			return;
		}

		if (row > N || cols > N || row < 0 || cols < 0) { // 범위벗어나면
			return;
		}
		
		for (int i = 1; i < drow.length; i++) {
			int nrow = row + drow[i];
			int ncols = cols + dcols[i];
			if (nrow > N || ncols > N || nrow < 0 || ncols < 0) { // 범위벗어나면
				continue;
			}
			if (map[nrow][ncols] == 0) { // 갈수있는길
				// cost 비교
				if (i != direction && direction != 0) { // 꺾인다면
					dfs(map, visited, nrow, ncols, i, N, cost + 600);
				} else { // 안꺾인다면
					dfs(map, visited, nrow, ncols, i, N, cost + 100);
				}
			}
		}
		
		
		return;
	}

}