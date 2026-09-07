import java.io.*;
import java.util.*;

public class Labyrinth {
    static FastReader in;
    static pw out;

    public static void main(String[] args) throws Exception {
        try {
            in = new FastReader(new FileInputStream("input.txt"));
            out = new pw(new FileOutputStream("output.txt"));
        } catch(Exception e) {
            in = new FastReader(System.in);
            out = new pw(System.out);
        }
        Labyrinth obj = new Labyrinth();
        obj.solveTestCase();
        out.flush();
        out.close();
    }
    public static string[] read(int n) throws IOException{
        string[] arr=new string[n];
        for(int i=0;i<n;i++) arr[i]=in.nl();
        return arr;
    }
    final int dx[] = {1,0,-1,0};
    final int dy[] = {0,1,0,-1};
    final char here[] = {'R','D','L','U'};
    final int pinf = Integer.MAX_VALUE;
    public class E{
        int i,j;
        E(int i,int j){
            this.i = i;
            this.j = j;
        }
    }
    public void solveTestCase() throws Exception {
        // write code
        int n = in.i();
        int m = in.i();
        string a[] = read(n);
        int si = -1;
        int sj = -1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(a[i].c(j) == 'A') {
                    si = i;
                    sj = j;
                }
            }
        }

        Queue<E> q = new ArrayDeque<>();
        q.add(new E(si,sj));
        int moves = 0;
        int d[][] = new int[n][m];
        int p[][] = new int[n][m];
        for(int row[]:d) Arrays.fill(row,pinf);
        d[si][sj] = 0;
        p[si][sj] = pinf;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                E e = q.poll();
                int r = e.i;
                int c = e.j;
                for(int dir=0;dir<4;dir++){
                    int nr = r + dy[dir];
                    int nc = c + dx[dir];
                    if(nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                    if(a[nr].c(nc) == '#') continue;
                    if(d[nr][nc] != pinf) continue;
                    d[nr][nc] = moves + 1;
                    p[nr][nc] = dir;
                    if(a[nr].c(nc) == 'B'){
                        out.pl("YES");
                        string ans = new string();
                        while (p[nr][nc] != pinf) { 
                            int d_now = p[nr][nc];
                            ans.add(here[d_now]);
                            nr -= dy[d_now];
                            nc -= dx[d_now];
                        }
                        out.pl(ans.length());
                        out.pl(ans.reverse());
                        return;
                    }

                    q.add(new E(nr,nc));
                }
            }
            moves++;
        }

        out.pl("NO");
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