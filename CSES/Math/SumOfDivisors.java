import java.io.*;
import java.util.*;

public class SumOfDivisors {
    static FastReader in;
    static pw out;
    long mod = (long)(1e9 + 7);

    long inv2 = modPow(2, mod-2, mod);
    public static void main(String[] args) throws Exception {
        // if (System.getProperty("ONLINE_JUDGE") == null) {
        //     in = new FastReader(new FileInputStream("input.txt"));
        //     out = new pw(new FileOutputStream("output.txt"));
        // } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        // }
        SumOfDivisors obj = new SumOfDivisors();
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

    public void solveTestCase() throws Exception {
        // write code
        long n = in.l();
        long res = 0;
        /*
            S(6) = s(1) +    --- 1  (1)
                   s(2) +    --- 3  (1,2)
                   s(3) +    --- 4  (1,3)
                   s(4) +    --- 7  (1,2,4)
                   s(5) +    --- 6  (1,5)
                   s(6)      --- 11 (1,2,3,6)

            S(6) = 1*6 + 3*2 + 2*3 + 4*1 + 6*1 
            So basically we need to find the contribution of each divisors 
            s(d) =d * floor(n / d)
            S(n) = i * floor(n / i) [for all (1<=i<=n)]
            Since n is very large we cannot iterate for all n 

            Lets take example of n = 20

            d       |       q = n / d
            --------------------------
            6       |       3
            7       |       2
            8       |       2
            9       |       2
            10      |       2
            11      |       1


            As we can see here that 2's are repeated for [7,8,9,10]
            Instead of calculating that one by one we can calculate them at once 

            Let 'q' be the repeating quotient
            q = n / d
            So q <= (n / d) < (q + 1)

            Taking first inequality ,
            q <= n / d
            d <= n / q
            So every divisor in this block must satisfy d <= n / q
            Hence the upper bound : r = n / q
            This means from current position divisor 'l' till 'r' all have same quotient 

            Hence we can write that as : q * (l + (l+1) + ... + r) = q * sum(l,r)
            sum(l,r) = sum(r) - sum(l-1) = (r * (r + 1) / 2) - (l * (l - 1) / 2)
            But we cannot use r * (r + 1) since it will exceed 10^24 long range
            So expanding the formula = (r^2 + r - l^2 + l) / 2
                                     = ((r + l)(r - l) + (r + l)) / 2
                                     = (r + l)(r - l + 1) / 2

            After this we will enter new block that will start from l = r + 1
            And so on 
            
            Now the TC :
            We know when checking till sqrt(n) we can get all the divisors
            One divisor will be (i) and other will be (n / i)
            Hence the total distinct divisors are 2*sqrt(n)
            So the loop will run for 2*sqrt(n) operations


         */       
        for(long l = 1;l <= n;){
            long q = n / l;
            long r = n / q;
            res = (res + ((q * sum(l,r,mod)) % mod)) % mod;
            l = r + 1;
        }

        out.pl(res);

    }

    public long sum(long l,long r,long mod){
        long f = ((l + r) % mod);
        long s = (r - l + 1) % mod;
        long nr = ((f * s) % mod);
        return (nr * inv2) % mod;
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