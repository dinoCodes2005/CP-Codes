import java.io.*;
import java.util.*;

public class test {
    static FastReader in;
    static PrintWriter out;
    
    public static void main(String[] args) throws Exception {
        in = new FastReader(System.in);
        out = new PrintWriter(System.out);
        
        solve();
        
        out.flush();
        out.close();
    }

    static void solve() throws Exception {
        int n = in.i();
        int a[][] = new int[n][3];
        List<Integer> lst = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            a[i][0] = in.i(); // start
            a[i][1] = in.i(); // end
            a[i][2] = in.i(); // reward
            lst.add(a[i][0]);
            lst.add(a[i][1]);
        }
        
        // Coordinate compression
        Collections.sort(lst);
        Map<Integer, Integer> compressed = new HashMap<>();
        int day = 1;
        for(int i = 0; i < lst.size(); i++) {
            if(i > 0 && lst.get(i).equals(lst.get(i - 1))) continue;
            compressed.put(lst.get(i), day++);
        }
        
        // Group projects by end day
        Map<Integer, List<int[]>> endsAt = new HashMap<>();
        for(int[] row : a) {
            int compressedEnd = compressed.get(row[1]);
            int compressedStart = compressed.get(row[0]);
            if(!endsAt.containsKey(compressedEnd)) {
                endsAt.put(compressedEnd, new ArrayList<>());
            }
            endsAt.get(compressedEnd).add(new int[]{compressedStart, row[2]});
        }
        
        // DP
        long[] dp = new long[day];
        for(int i = 1; i < day; i++) {
            dp[i] = dp[i - 1];
            List<int[]> ends = endsAt.get(i);
            if(ends != null) {
                for(int[] p : ends) {
                    dp[i] = Math.max(dp[i], dp[p[0] - 1] + p[1]);
                }
            }
        }
        
        out.println(dp[day - 1]);
    }
    
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        
        public FastReader(InputStream stream) {
            br = new BufferedReader(new InputStreamReader(stream));
        }
        
        String n() throws IOException {
            while(st == null || !st.hasMoreTokens()) 
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        
        int i() throws IOException { 
            return Integer.parseInt(n()); 
        }
        
        long l() throws IOException { 
            return Long.parseLong(n()); 
        }
    }
}