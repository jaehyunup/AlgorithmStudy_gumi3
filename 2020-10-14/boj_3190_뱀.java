
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class boj_3190_뱀 {
	public static int[][] snakeMap;
	public static boolean[][] appleMap;
	public static Map<Integer,String> com;
	public static int sec=1;
	public static int[] dCols={1,0,-1,0};/* dcols,drow= 1 index부터 사용 */
	public static int[] dRow={0,1,0,-1};
	public static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		int K=Integer.parseInt(br.readLine());
		snakeMap=new int[N+1][N+1];
		appleMap=new boolean[N+1][N+1];
		/* 사과 위치 */
		for (int i = 0; i < K; i++) {
			String[] line=br.readLine().split(" ");
			appleMap[Integer.parseInt(line[0])][Integer.parseInt(line[1])]=true;
		}
		/*뱀 이동 커맨드 */
		com=new HashMap<Integer,String>();
		int L=Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			String[] line=br.readLine().split(" ");
			com.put(Integer.parseInt(line[0]),line[1]);
		}
		snakeMap[1][1]=1;
//		System.out.println(com);
		go(new Point(1,1,0),0,1);
		System.out.println(sec);
		
	}
	public static void go(Point head,int direction,int depth) {
		snakeMap[head.row][head.cols]=depth;

		int cRow=dRow[direction];
		int cCols=dCols[direction];
		int nextRow=head.row+cRow;
		int nextCols=head.cols+cCols;
//		System.out.println(nextRow+","+nextCols+" direction:"+direction+ " sec: "+sec+" snake : "+snakeMap[nextRow][nextCols]);
		if(nextRow<=0 || nextRow > N || nextCols<=0 || nextCols > N
				|| snakeMap[nextRow][nextCols]>0) { // 범위를 벗어낫거나 자기자신의 몸과 부딪힌다면
			return;
		}
		
		/*1. 뱀이 보는방향의 다음칸으로 머리추가
		 * 2. 사과 있다면?사과 없어지고 꼬리 그대로(머리도그대로)
		 * 3. 사과없다면? 꼬리없어짐.
		 * */
		if(appleMap[nextRow][nextCols]==false) { // 사과 없다면 꼬리삭제
			// 꼬리 삭제는, count를 통해 가장 작은놈을 삭제
			/* 꼬리찾기 */
			int minDepth=Integer.MAX_VALUE;
			int minRow=0;
			int minCols=0;
			for (int i = 1; i < snakeMap.length; i++) {
				for (int j = 1; j < snakeMap[i].length; j++) {
					if(snakeMap[i][j] > 0) { //뱀이 위치한곳일때
						if(minDepth!=Math.min(snakeMap[i][j],minDepth)) { //작은애를 찾았다면
							minDepth=Math.min(snakeMap[i][j],minDepth); //mindepth 변경하고
							minRow=i;// row 저장
							minCols=j; // cols 저장
//							System.out.println("min:"+minRow+","+minCols+" depth:"+minDepth);
						}
					}
				}
			}
			snakeMap[minRow][minCols]=0;
			
		}else { //  사과있다면 먹고 depth증가
			appleMap[nextRow][nextCols]=false;
		}
		depth++;
		// 사과가 있다면? 꼬리 그대로.
		/*sec 초가 끝난 뒤. */
		if(com.containsKey(sec)==true) { // 뱀 방향 변경이 있다면
			if(com.get(sec).equals("D")) { // 오른쪽
				direction+=1;
				if(direction==4) {
					direction=0;
					
				}
			}else if (com.get(sec).equals("L")){//왼쪽
				direction-=1;
				if(direction==-1) {
					direction=3;
				}
			}
		}
		sec++;
		go(new Point(nextRow,nextCols),direction,depth);
	
	}
	
	public static class Point{
		int row;
		int cols;
		int depth;
		
		public Point(int row, int cols, int depth) {
			super();
			this.row = row;
			this.cols = cols;
			this.depth = depth;
		}
		public int getDepth() {
			return depth;
		}
		public void setDepth(int depth) {
			this.depth = depth;
		}
		public Point(int row, int cols) {
			super();
			this.row = row;
			this.cols = cols;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getCols() {
			return cols;
		}
		public void setCols(int cols) {
			this.cols = cols;
		}
		public Point() {
		}
		@Override
		public String toString() {
			return "Point [row=" + row + ", cols=" + cols + ", depth=" + depth + "]";
		}
		
	
	}
}
