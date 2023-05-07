import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = new int[] {1, -1, 0, 0};
	static int[] dy = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), m = fs.nextInt();
			int[][] a = new int[n][m];
			for (int i = 0; i < n; i++) {
				a[i] = fs.readArray(m);
			}
			boolean[][] visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				Arrays.fill(visited[i], false);
			}
			int max = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (!visited[i][j] && a[i][j] > 0) {
						int total = a[i][j];
						ArrayDeque<Cell> q = new ArrayDeque<>();
						q.addLast(new Cell(i, j));
						visited[i][j] = true;
						while (!q.isEmpty()) {
							Cell p = q.pollFirst();
							for (int k = 0; k < 4; k++) {
								int newRow = p.row + dx[k];
								int newCol = p.col + dy[k];
								if (isInside(newRow, newCol, n, m) && !visited[newRow][newCol] && a[newRow][newCol] > 0) {
									q.addLast(new Cell(newRow, newCol));
									visited[newRow][newCol] = true;
									total += a[newRow][newCol];
								}
							}
						}
						max = Math.max(max, total);
//						System.out.println((i + 1) + " " + (j + 1) + " = " + total);
					}
				}
			}
			System.out.println(max);
		}
		out.close();
	}
	
	static boolean isInside(int row, int col, int n, int m) {
		return 0 <= row && row < n && 0 <= col && col < m;
	}
	
	static class Cell {
		int row, col;
		
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
