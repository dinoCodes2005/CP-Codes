import java.io.*;
import java.util.*;

public class ChristmasParty {
    static FastReader in;
    static pw out;
    long mod = (long)1e9 + 7;
    public static void main(String[] args) throws Exception {
        // if (System.getProperty("ONLINE_JUDGE") == null) {
        //     in = new FastReader(new FileInputStream("input.txt"));
        //     out = new pw(new FileOutputStream("output.txt"));
        // } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        // }
        ChristmasParty obj = new ChristmasParty();
        obj.solveTestCase();
        out.flush();
        out.close();
    }

    public static long modPow(long base,long exp,long mod){
        long res=1L;
        base%=mod;
        while(exp>0){
            if((exp&1L)==1L) res=(res*base)%mod;
            base=(base*base)%mod;
            exp>>=1;
        }
        return res;
    }

    Long dp[];

    public void solveTestCase() throws Exception {
        // write code
        int n = in.i();
        dp = new Long[n+1];
        dp[0] = 1l;
        dp[1] = 0l;
        for(int i=2;i<=n;i++){
            dp[i] = (((dp[i-1] + dp[i-2]) % mod) * (i-1l)) % mod;
        }
        out.pl(dp[n]);
    }

    public long f(int n){
        if(n < 2) return n^1l;
        if(dp[n] != null) return dp[n];
        /*
          when there are n people in the party
          for first person : (n-1) ways to distribute
          if the first guy gave to second guy ; 
          then for second guy he has (n-1) possibilities [first guy is removed from problem]

          if the first guy gave to some other guy ; 
          then for second guy he has (n-2) possibilities [first guy and the other guy is removed from the problem]
          gives f(n) = (n-1) * (f(n-1) + f(n-2))

          Now base case:
          if there are no guys then it means there is only way i.e is no transfer of gift so f(0) = 1
          if there is only one guy then it means there is no way to transfer gift without giving it to himself i.e f(1) = 0

          So,
          f(n) = (n-1) * (f(n-1) + f(n-2)) [for n>=2 otherwise n^1]

        */
        
        return dp[n] = (((f(n-1) + f(n-2)) % mod) * (n-1l)) % mod;
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