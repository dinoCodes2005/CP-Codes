import java.io.*;
import java.util.*;

public class D {
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
        D obj = new D();
        obj.solveTestCase();
        out.flush();
        out.close();
    }

    public void solveTestCase() throws Exception {
        // write code
        int n = in.i();
        int w = in.i();
        int b = in.i();
        int[] d = new int[n];
        int[] c = new int[n];
        long dp[][] = new long[w+1][3];
        for(int i=0;i<n;i++){
            d[i] = in.i();
            c[i] = in.i();
        }
        /*
          type 0 : new segment is starting 
          type 1 : at second element of the segment ; might take it or reject
                    if taken add +b for now +b for previous and + d[i]
          type 2 : at third element normally add +b when taking elements      
        */
        long next[][] = new long[w+1][3];
        for(int i=n-1;i>=0;i--){
            for(int cost=w;cost>=0;cost--){
                for(int type=2;type>=0;type--){
                    long take = 0;
                    long not_take =  next[cost][0];
                    if(cost + c[i] <= w){
                        if(type == 0) take = d[i] + next[cost+c[i]][1];
                        else if(type == 1) take = d[i] + 2l*b + next[cost+c[i]][2]; 
                        else take = d[i] + b + next[cost+c[i]][2];
                    }
                    dp[cost][type] = Math.max(take,not_take);
                }
            }
            for(int cost=w;cost >=0;cost--){
                for(int type=2;type>=0;type--){
                    next[cost][type] = dp[cost][type];
                }
            }
        }
        long res = dp[0][0];
        out.pl(res);
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