import java.io.*;
import java.util.*;

public class PermutationOrder {
    static FastReader in;
    static pw out;
    static long f[] = new long[21];
    public static void main(String[] args) throws Exception {
        // if (System.getProperty("ONLINE_JUDGE") == null) {
        //     in = new FastReader(new FileInputStream("input.txt"));
        //     out = new pw(new FileOutputStream("output.txt"));
        // } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        // }
        f[0] = f[1] = 1;
        for(int i=2;i<=(int)20;i++){
            f[i] = (i*f[i-1]);
        }
        int t = in.i();
        PermutationOrder obj = new PermutationOrder();
        while(t-- > 0) obj.solveTestCase();
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

    public static long modInv(long val,long mod){
        return modPow(val,mod-2,mod);
    }
    public static int[] read(int n) throws IOException{
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=in.i();
        return arr;
    }
    
    public void solveTestCase() throws Exception {
        // write code
        int t = in.i();
        List<Integer> lst = new ArrayList<>();
        int n = in.i();
        for(int i=0;i<=n;i++) lst.add(i);
        if(t == 1){
            int temp = n;
            long k = in.l();
            long ok = k;
            if(n == 1){
                out.pl(lst.get(1));
                return;
            }
            if(n == 2){
                if(k == 1)  out.pl(lst.get(1) + " " + lst.get(2));
                else out.pl(lst.get(2) + " " + lst.get(1));
                return;
            }
            int res[] = new int[n];
            int i = 0;
            while(n > 2){
                long block_size = f[n-1];
                int block_no = (int)((k + block_size - 1) / block_size);
                int x = lst.get(block_no);
                res[i++] = x;
                lst.remove(block_no);
                n--;
                k %= block_size;
                if(k == 0) k = block_size;
            }
            res[i] = lst.get(1);
            res[i+1] = lst.get(2);
            if(ok == 2 || k == 2){
                int ex = res[temp-1];
                res[temp-1] = res[temp-2];
                res[temp-2] = ex;
            }
            for(int val:res) out.p(val+" ");
            out.pl();
        }else{
            int p[] = read(n);
            long k = 0;
            for(int i=0;i<n;i++){
                int val = p[i];
                for(int j=1;j<lst.size();j++){
                    if(lst.get(j) == val) {
                        k += ((j-1)*f[n-i-1]);
                        lst.remove(j);
                        break;
                    }
                }
            }

            out.pl(k+1);
        }
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