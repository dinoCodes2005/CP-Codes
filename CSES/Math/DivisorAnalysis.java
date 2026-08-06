import java.io.*;
import java.util.*;

public class DivisorAnalysis {
    static FastReader in;
    static pw out;
    long mod = (long)(1e9 + 7);
    long inv2 = modPow(2,mod-2,mod);
    public static int[] read(int n) throws IOException{
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=in.i();
        return arr;
    }

    public static void main(String[] args) throws Exception {
        // if (System.getProperty("ONLINE_JUDGE") == null) {
        //     in = new FastReader(new FileInputStream("input.txt"));
        //     out = new pw(new FileOutputStream("output.txt"));
        // } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        // }
        DivisorAnalysis obj = new DivisorAnalysis();
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
        /*
        Suppose primes are given as (x->prime,k->power) for n:
        n = 12
        2 -> 2
        3 -> 1
        (1 , 2 , 4) -> i have 2+1 choices
        (1 , 3) -> i have 1+1 choices
        So total possible divisors = 3 * 2 = 6
        
        Generalising:
        Let the number be represented with [p1,p2,...,pn] primes with respective counts [e1,e2,...,en]
        n = p1^e1 * p2^e2 * ... * pk^ek
        So for each prime pi I have either to take nothing from it , take 1 from it , take 2 from ,... , take all from it
        hence total (ei + 1) choices
        Considering all :
        prod((ei + 1)) [for all primes] = (e1 + 1) * (e2 + 1) * ... * (en + 1)
        This will give me the count of divisors
        
        For sum of divisors:
        Let the primes be:
        (1 + p1 + p1^2 + ... + p1^e1) = (p1^(e1+1) - 1)/(p1 - 1)
        ............................            *....*
        (1 + pn + pn^2 + ... + pn^e2) = (pn^(en+1) - 1)/(pn - 1)

        For product of divisors:
        36 = [1,2,3,4,6,9,12,18,36]
        Pairs = (1,36) , (2,18) , (3,12) , (4,9) , (6,6)
        So pairs with unique divisors = 4
        Hence product = 36^4 * sqrt(6)

        Hence when divisor count = d (odd) = isPerfectSquare
        Product = n ^ (d / 2) = n^(d/2) = sqrt(n)^d
        when even = notPerfectSquare : Hence calculation of sq will be wrong
        so we need to do n^(d/2) : 

        This means the product is always sqrt(n)^d or n^(d/2)
        But now we cannot do modPow(n,d>>1) since d is huge and modular inverse of 2 mod (p-1) does not exist
        which is not possible 
        So we are doing this :
        d = ((e1+1)/2)*(e2+1)*...*(en+1)
        We will be dividing only that exponent which is odd : Suppose here it is e1
        e1 + 1 will even
        hence we are doing (e1 + 1)/2

        Now one last thing:
        For sq^(d) we are using cntexp because the d value can be very large and it must be modded
        According to fermat little theorem:
        a^p = a mod p
        a^(p-1) = 1 mod p ---- 1)
        a^x*a^(p-1) = a^x mod p
        a^(x+p-1) = a^x mod p
        Lets check:
        a^(x+(p-1)+(p-1)) mod p = a^(x+(p-1))*a^(p-1) mod p
        From 1) we know that a^(p-1) = 1 mod p
        Hence a^(x+(p-1)+(p-1)) mod p = a^(x+(p-1))*1 mod p
        
        Generalising:
        a^(x+k(p-1)) = a^x mod p
        a^(x mod (p-1)) = a^x mod p

        So for modding in power we using (p-1)

        
        */
        int n = in.i();
        int a[][] = new int[n][2];
        for(int i=0;i<n;i++){
            a[i] = new int[]{in.i(),in.i()};
        }
        long cnt = 1;
        long sum = 1;
        long sq = 1;
        long num = 1;
        long cntexp = 1;
        boolean isPerfectSquare = true;
        for(int i=0;i<n;i++){
            long p = 1l*a[i][0];
            long e = 1l*a[i][1];
            if((e&1) == 1){
                isPerfectSquare = false;
            }
            sq = (sq * modPow(p,e >> 1,mod)) % mod;
            num = (num * modPow(p,e,mod)) % mod;
            cnt = (cnt * (e + 1)) % mod;
            cntexp = (cntexp * (e + 1)) % (mod - 1);
            long nr_s = (modPow(p,e+1,mod) - 1 + mod) % mod;
            long dr_s = modPow(p-1,mod-2,mod);
            long ns = (nr_s * dr_s) % mod;

            sum = (sum * ns) % mod;
        }
        long prod = 1;
        if (isPerfectSquare) {
            prod = modPow(sq, cntexp, mod);
        } else {
            long exp = 1;
            boolean divided = false;

            for (int i = 0; i < n; i++) {
                long x = a[i][1] + 1;

                if (!divided && (x % 2 == 0)) {
                    x /= 2;
                    divided = true;
                }

                exp = (exp * x) % (mod - 1);
            }

            prod = modPow(num, exp, mod);
        }
        out.pl(cnt+" "+sum+" "+prod);
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