import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 1005번. ACM Craft - DP & 위상 정렬 문풀 
 * @author MYLG
 *
 */
public class Main {
	static int TC;
	static int N, M;
	static int[] indegree;
	static int[] madeTime; //각 건물 짓는 데 걸리는 시간
	static int[] dy;//직전 건물 다음으로 현재 dy[i] 건물 짓는 데 걸리는 시간 담기 
	
	//실행 메인
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		TC = kb.nextInt();
		for(int t= 0; t<TC; t++) {
			N = kb.nextInt();
			M = kb.nextInt();
			indegree = new int[N+1];
			madeTime = new int[N+1];
			dy = new int[N+1];
			
			List<ArrayList<Integer>> graph = new ArrayList<>();
			for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
			
			for(int i=1; i<=N; i++) {
				//각 건물 짓느 시간 담기
				madeTime[i] = kb.nextInt();
			}
			
			for(int i=0; i<M; i++) {
				int a = kb.nextInt();
				int b = kb.nextInt();
				graph.get(a).add(b);
				indegree[b]++;
			}
			
			int W = kb.nextInt();//목표 건물
			/**
			 * 위상 정렬 시작 
			 */
			Queue<Integer> Q = new LinkedList<>();
			for(int i=1; i<=N; i++) {
				if(indegree[i] == 0) {
					Q.offer(i);
				}
				dy[i] = madeTime[i];//초기화
			}
			
			while(!Q.isEmpty()) {
				int cur = Q.poll();
				
				for(int nx : graph.get(cur)) {
					dy[nx] = Math.max(dy[nx], dy[cur] + madeTime[nx]);
					indegree[nx]--;
					if(indegree[nx]==0) Q.offer(nx);
				}
			}
			
			System.out.println(dy[W]);			
		}
	}
}