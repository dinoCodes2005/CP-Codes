import java.io.*;
import java.util.*;

public class PrimeMultiples {
    static FastReader in;
    static pw out;

    public static void main(String[] args) throws Exception {
        // if (System.getProperty("ONLINE_JUDGE") == null) {
        //     in = new FastReader(new FileInputStream("input.txt"));
        //     out = new pw(new FileOutputStream("output.txt"));
        // } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        // }
        PrimeMultiples obj = new PrimeMultiples();
        obj.solveTestCase();
        out.flush();
        out.close();
    }

    public static long[] read(int n) throws IOException{
        long[] arr=new long[n];
        for(int i=0;i<n;i++) arr[i]=in.l();
        return arr;
    }

    public static long gcd(long a,long b){while(b!=0){long temp=b;b=a%b;a=temp;}return a;}

    public void solveTestCase() throws Exception {
        // write code
        long n = in.l();
        int k = in.i();
        long a[] = read(k);
        long c = 0;
        /*
        Trying out all masks :
        For an array of length k we have masks ranging from [1...(1 << k)-1]
        We will find the first set bit and init lcm = a[j]
        For every choice we are calculating the cumulative lcm
        Example: 2 , 5
        masks = 01,10,11

        when mask = 01;
        lcm = 5
        c = 20 / 5 = 4 ; [5,10,15,20]

        when mask = 10;
        lcm = 2;
        c = 20 / 2 = 10 ; [2,4,6,8,10,12,14,16,18,20]

        when mask = 11;
        lcm = 5*2 = 10
        c = 20 / 10 = 2; {we have 2 repeated elements}[10,20]
        total = 10 + 4 - 2 = 12

        -------------------------------------------------------

        Example : 2, 3, 5 and n = 50

        masks = 001, 010, 011, 100, 101, 110, 111

        when mask = 001
        lcm = 5
        c = 50 / 5 = 10
        [5,10,15,20,25,30,35,40,45,50]

        when mask = 010
        lcm = 3
        c = 50 / 3 = 16
        [3,6,9,12,15,18,21,24,27,30,33,36,39,42,45,48]

        when mask = 100
        lcm = 2
        c = 50 / 2 = 25
        [2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50]

        Current count = 10 + 16 + 25 = 51

        when mask = 011
        lcm = 3 * 5 = 15
        c = 50 / 15 = 3
        [15,30,45]

        These numbers appear in both the multiples of 3 and the multiples of 5,
        so they have been counted twice. Delete them once.

        Current count = 51 - 3 = 48

        when mask = 101
        lcm = 2 * 5 = 10
        c = 50 / 10 = 5
        [10,20,30,40,50]

        These numbers appear in both the multiples of 2 and the multiples of 5,
        so they have been counted twice. Delete them once.

        Current count = 48 - 5 = 43

        when mask = 110
        lcm = 2 * 3 = 6
        c = 50 / 6 = 8
        [6,12,18,24,30,36,42,48]

        These numbers appear in both the multiples of 2 and the multiples of 3,
        so they have been counted twice. Delete them once.

        Current count = 43 - 8 = 35

        Notice what happened to 30:

        Initially:
        +1 (multiple of 2)
        +1 (multiple of 3)
        +1 (multiple of 5)

        Then:
        -1 (2 & 3)
        -1 (2 & 5)
        -1 (3 & 5)

        Current contribution of 30 = 0

        But 30 should be counted once.

        when mask = 111
        lcm = 2 * 3 * 5 = 30
        c = 50 / 30 = 1
        [30]

        30 was removed one extra time while removing all pairwise intersections,
        so we add it back once.

        Final count = 35 + 1 = 36

        
        */
        for(int i=1;i<(1<<k);i++){
            int j = 0;
            while(j < k && (i & (1 << (k-1-j))) == 0){
                j++;
            }
            long lcm = a[j];
            j++;
            int bits = 1;
            boolean ok = true;
            for(;j<k;j++){
                if((i & (1 << (k-1-j))) != 0) {
                    if(lcm > n / a[j]) {
                        ok = false;
                        break;
                    }
                    lcm *= a[j];
                    bits++;
                }
            }
            if(!ok) continue;
            if((bits & 1) == 1){
                c += (n / lcm);
            }else c -= (n / lcm);
        }

        out.pl(c);

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