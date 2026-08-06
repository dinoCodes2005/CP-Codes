import java.io.*;
import java.util.*;

public class BracketSequencesI {
    static FastReader in;
    static pw out;
    static long mod = (long)1e9 + 7;

    public static void main(String[] args) throws Exception {
        // if (System.getProperty("ONLINE_JUDGE") == null) {
        //     in = new FastReader(new FileInputStream("input.txt"));
        //     out = new pw(new FileOutputStream("output.txt"));
        // } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        // }
        BracketSequencesI obj = new BracketSequencesI();
        obj.solveTestCase();
        out.flush();
        out.close();
    }

    public long nCr(long n,long r,long mod){
        if(r<0||r>n)return 0;
        r=Math.min(r,n-r);
        long res=1;
        for(long i=1;i<=r;i++){
            res=(res*((n-r+i)%mod))%mod;
            res=(res*modInverse(i,mod))%mod;
        }
        return res;
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

    public static long modInverse(long val,long mod){
        return modPow(val,mod-2,mod);
    }

    public void solveTestCase() throws Exception {
        // write code
        /*
        Catalan proof :
        Let the bracket sequence be :- (()())()()
        For the first opening bracket it can be segregated as : (()()) | ()()
        If we consider the anything inside the first closed bracket as A and outside as B
        we get : ( A ) | B
        Let n be the number of pairs of bracket
        and k be the number of pairs of bracket in A
        then for C(n) = sum(C(k) * C(n-1-k)) [for k = 0 to n-1]
        T.C : O(n^2)

        We need to improve this :

        Understand in terms of graph;
        Suppose we consider the same valid bracket sequence as movement in graph:
        ( -> means move right
        ) -> means move up

        Then for the valid bracket sequence we will notice that when following this path: RRURUURURU 
        We never cross the primary diagonal (y = x)

        Now we need to get the paths that cross the primary diagonal:
        If we try to trace such paths , we will notice that the refelction of such paths 
        always intersect the path (y = x + 1) and ends at (n-1,n+1)

        Hence we can write that valid paths = (2n C n) - (2n C n-1)
                                            = (2n)!       (2n)!
                                            -------   -  ---------
                                            n! * n!       (n-1)! * (n+1)!

                                            = (2n)!       (2n)! * n
                                            -------   -  ---------
                                            n! * n!       (n)! * (n+1) * (n!)

                                            = (2n)!       (2n)! * n
                                            -------   -  --------------
                                              (n!)^2      (n!)^2 * (n+1)

                                            = (2n C n) - (2n C n)(n/(n+1))
                                            = (2n C n)(1 - (n / (n + 1)))
                                            = (2n C n)((n+1-n) / (n + 1))
                                            = (2n C n)(1/(n+1))
        
        */
        int n = in.i();
        if(n%2 == 1){
            out.pl(0);
            return;
        }
        n /= 2;

        long total = nCr(2*n, n, mod);
        long inv = modInverse(n+1, mod);
        long res = (total * inv) % mod;
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