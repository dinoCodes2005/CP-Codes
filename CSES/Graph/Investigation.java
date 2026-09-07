import java.io.*;
import java.util.*;

public class Investigation {
    static FastReader in;
    static pw out;
    final int dx[] = {1,0,-1,0};
    final int dy[] = {0,1,0,-1};
    final char here[] = {'R','D','L','U'};
    final long pinf = Long.MAX_VALUE;
    final int mod = (int)1e9 + 7;
    public static void main(String[] args) throws Exception {
        try {
            in = new FastReader(new FileInputStream("input.txt"));
            out = new pw(new FileOutputStream("output.txt"));
        } catch(Exception e) {
            in = new FastReader(System.in);
            out = new pw(System.out);
        }
        Investigation obj = new Investigation();
        obj.solveTestCase();
        out.flush();
        out.close();
    }
    class N{
        int node;
        long dt;
        N(int node,long dt){
            this.node = node;
            this.dt = dt;
        }
    }
    public void solveTestCase() throws Exception {
        // write code
        int n = in.i();
        int m = in.i();

        @SuppressWarnings("unchecked")
        List<int[]>[] map = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            map[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            int u = in.i();
            int v = in.i();
            int wt = in.i();
            map[u].add(new int[]{v,wt});
        }

        long d[] = new long[n+1];
        Arrays.fill(d,pinf);
        d[1] = 0;
        PriorityQueue<N> pq = new PriorityQueue<>((x,y)->Long.compare(x.dt,y.dt));
        pq.offer(new N(1,0l));
        while(!pq.isEmpty()){
            N now = pq.poll();
            int u = now.node;
            long dt = now.dt;

            for(int nei[]:map[u]){
                int v = nei[0];
                int wt = nei[1];

                long nd = dt + wt;
                if(nd >= d[v]) continue;
                d[v] = nd;
                pq.offer(new N(v,nd));
            }
        }

        /*
            c[node] = # of ways to reach the 'node' with minimum price
            c[1] = 1
            c[v] = c[p1] + c[p2] + ... + c[pn] ; where p's are the parent of node 'v'
        */

        int c[] = new int[n+1];
        c[1] = 1;
        boolean vis[] = new boolean[n+1];
        vis[1] = true;
        pq = new PriorityQueue<>((x,y)->Long.compare(x.dt,y.dt));
        pq.offer(new N(1,0));

        while(!pq.isEmpty()){
            N now = pq.poll();
            int u = now.node;
            long dt = now.dt;

            for(int nei[]:map[u]){
                int v = nei[0];
                int wt = nei[1];
                
                long nd = dt + wt;
                if(nd > d[v]) continue;

                if(nd == d[v]) {
                    c[v] = (c[v] + c[u]) % mod;
                    if(vis[v]) continue;
                    vis[v] = true;
                    pq.offer(new N(v,nd));
                }
            }
        }

        pq = new PriorityQueue<>((x,y)->Long.compare(x.dt,y.dt));
        pq.offer(new N(1,0));
        int min[] = new int[n+1];
        Arrays.fill(min,Integer.MAX_VALUE);
        int max[] = new int[n+1];
        Arrays.fill(max,Integer.MIN_VALUE);
        min[1] = max[1] = 0;
        vis = new boolean[n+1];
        vis[1] = true;

        while(!pq.isEmpty()){
            N now = pq.poll();
            int u = now.node;
            long dt = now.dt;

            for(int nei[]:map[u]){
                int v = nei[0];
                int wt = nei[1];
                
                long nd = dt + wt;
                if(nd > d[v]) continue;

                if(nd == d[v]) {
                    min[v] = Math.min(min[v],min[u]+1);
                    max[v] = Math.max(max[v],max[u]+1);
                    if(vis[v]) continue;
                    vis[v] = true;
                    pq.offer(new N(v,nd));
                }
            }
        }



        out.pl(d[n]+" "+c[n]+" "+min[n]+" "+max[n]);

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader(InputStream stream){br=new BufferedReader(new InputStreamReader(stream));}
        public FastReader(FileInputStream stream){br=new BufferedReader(new InputStreamReader(stream));}
        String n() throws IOException {while(st==null||!st.hasMoreTokens()) st=new StringTokenizer(br.readLine()); return st.nextToken();}
        string w() throws IOException{return new string(n());}
        int i() throws IOException {return Integer.parseInt(n());}
        long l() throws IOException {return Long.parseLong(n());}
        double d() throws IOException {return Double.parseDouble(n());}
        string nl() throws IOException {String line = br.readLine();return line == null ? new string() : new string(line);}
    }

    static class string {
        StringBuilder sb;
        string() { sb = new StringBuilder(); }
        string(java.lang.String s) { sb = new StringBuilder(s); }
        string add(Object o) { sb.append(o); return this; }
        string lower() { return new string(sb.toString().toLowerCase()); }
        public String toString() { return sb.toString(); }
        public char c(int i){return sb.charAt(i);}
        public int length(){return sb.length();}
        string reverse() { return new string(sb.reverse().toString()); }
        string substring(int start, int end) { return new string(sb.substring(start, end)); }
        string setCharAt(int index, char ch) { sb.setCharAt(index, ch); return this; }
        string deleteCharAt(int index) { sb.deleteCharAt(index); return this; }
        char[] toCharArray(){return sb.toString().toCharArray();}
        string insert(int offset, Object obj) { sb.insert(offset, obj); return this; }
        boolean equals(string other) { return sb.toString().equals(other.toString()); }
        string append(Object obj) { sb.append(obj); return this; }
        string remove(int start, int end) { sb.delete(start, end); return this; }
        string[] split(String regex) {
            String[] parts = sb.toString().split(regex);
            string[] result = new string[parts.length];
            for (int i = 0; i < parts.length; i++) {
                result[i] = new string(parts[i]);
            }
            return result;
        }
        boolean contains(string substr) {
            return sb.toString().contains(substr.toString());
        }
    }

    static class map<K,V> extends HashMap<K,V>{
        @Override public V get(Object k){ return super.get(k); }
        public V get(K k, V def){ return super.getOrDefault(k,def); }
        public map<K,V> p(K k, V v){ super.put(k,v); return this; }
        public V r(K k){ return super.remove(k); }
        public boolean ck(K k){ return super.containsKey(k); }
        public boolean hv(V v){ return super.containsValue(v); }
        public V cia(K k, java.util.function.Function<? super K, ? extends V> f){ return super.computeIfAbsent(k,f); }
    }

    static class pw extends PrintWriter {
        pw(OutputStream out) {super(out);}
        void p(Object x){print(x);}
        void pl(){println();}
        void pl(Object x) {println(x);}
    }
}