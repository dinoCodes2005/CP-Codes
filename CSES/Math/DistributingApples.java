import java.io.*;
import java.util.*;

public class DistributingApples {
    static FastReader in;
    static pw out;  
    static long mod = (long)1e9 + 7;
    static long f[] = new long[(int)1e6+1];
    static long invf[] = new long[(int)1e6+1];

    

    public static void main(String[] args) throws Exception {
        // if (System.getProperty("ONLINE_JUDGE") == null) {
        //     in = new FastReader(new FileInputStream("input.txt"));
        //     out = new pw(new FileOutputStream("output.txt"));
        // } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        // }
        
        f[0] = f[1] = 1;
        for(int i=2;i<=(int)1e6;i++){
            f[i] = (i*f[i-1]) % mod;
        }
        invf[(int)1e6] = modInv(f[(int)1e6], mod);
        for(int i=(int)1e6;i>=1;i--){
            invf[i-1] = (invf[i] * i) % mod;
        }
        DistributingApples obj = new DistributingApples();
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

    public long nCr(long n,long r,long mod){
        long nr = f[(int)n];
        long dr = (invf[(int)r] * invf[(int)(n-r)]) % mod;
        return (nr * dr) % mod;
    }

    public static long modInv(long val,long mod){
        return modPow(val,mod-2,mod);
    }

    public void solveTestCase() throws Exception {
        /*
        Suppose apples(n) = 10;
        * |  *  |  * |  *  |  *  |  *  |  *  |  *  |  *  |   *
        For 10 apples i can do 9 partitions ,
        Now suppose i have to distribute among 4 children
        Means i have to select 3 partitions 
        Because making 3 partitions will give me 4 blocks
        Therefore total ways = 9*8*7 = (n-1)!/(n-k)!
        But this total ways is also counting duplicates which consists of the permutation of the partitions 
        We dont care about the permutations 
        Only the size of block
        Hence we have to remove that 
        Total ways = (n-1)!/((n-k)!*(k-1)!) = nCr(n-1,k-1)

        Now we can choose 1 children that gets at least one apple
                          2 children that gets at least one apple
                          3 children that gets at least one apple
                                    .
                                    .
                                    .
                          i children that gets at least one apple
        So for choosing children we have ways (choose) = nCr(children,i)

        Hence ans = summation of (nCr(children,k) * nCr(apples-1,k-1)) [k=1:min(apples,children)]
        */
        int children = in.i();
        int apples = in.i();
        long res = 0;
        for(int k = 1;k <= Math.min(children,apples);k++){
            long choose = nCr(children, k, mod);

            long distribute = nCr(apples-1, k-1, mod);
            res = (res + (choose * distribute) % mod) % mod;
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