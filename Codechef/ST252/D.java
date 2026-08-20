import java.io.*;
import java.util.*;

public class D {
    static FastReader in;
    static pw out;
    static long mod = 998244353;
    public static void main(String[] args) throws Exception {
        try {
            in = new FastReader(new FileInputStream("input.txt"));
            out = new pw(new FileOutputStream("output.txt"));
        } catch(Exception e) {
            in = new FastReader(System.in);
            out = new pw(System.out);
        }
        int t = in.i();
        D obj = new D();
        while(t-- > 0) obj.solveTestCase();
        out.flush();
        out.close();
    }
    public static int[] read(int n) throws IOException{
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=in.i();
        return arr;
    }
    public static long modPow(long base,long exp){
        long res=1L;
        base%=mod;
        while(exp>0){
            if((exp&1L)==1L) res=(res*base)%mod;
            base=(base*base)%mod;
            exp>>=1;
        }
        return res;
    }
    public void solveTestCase() throws Exception {
        // write code
        int n = in.i();
        int a[] = read(n);
        int f[] = new int[102];
        for(int val:a) f[val]++; 
        long res = 0;
        for(int mex=0;mex<=101;mex++){
            for(int max=mex-1;max<=Math.min(100,mex+1);max++){
                if(mex == max || max == -1) continue;
                if(f[max] == 0) continue;
                long lt = 1;
                //all elements less than mex should be there at least one time
                //let there be x occurences of the a[i]th element 
                //there will be one case were it will be 0000...
                //so ways = 2^x - 1
                int ub = mex;
                if(max < mex) ub = max;
                for(int i=0;i<ub;i++){
                    lt = (lt * (modPow(2,f[i])-1 + mod)) % mod;
                }
                if(max < mex){
                    lt = (lt * (modPow(2,f[max])-1+mod)) % mod;
                    res = (res + lt) % mod;
                    continue;
                }

                //all elements greater than mex are completely optional 
                //so ways = 2^(n - count(elements > mex))
                long gt = 1;
                for(int i=mex+1;i<max;i++){
                    gt = (gt * modPow(2,f[i])) % mod; 
                }   
                //at least one element == max must be there 
                //so ways = 2^(f[max]-1)
                long max_ways = (modPow(2,f[max]) - 1 + mod) % mod;
                gt = (gt * max_ways) % mod;
                long curr = (gt * lt) % mod;
                res = (res + curr) % mod;
            }
        }

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