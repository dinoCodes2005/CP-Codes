import java.io.*;
import java.util.*;

public class B {
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
        int t = in.i();
        B obj = new B();
        while(t-- > 0) obj.solveTestCase();
        out.flush();
        out.close();
    }

    public void solveTestCase() throws Exception {
        // write code
        int n = in.i();
        string a = in.nl();
        string b = in.nl();
        string c = in.nl();
        string res = new string();
        for(int i=0;i<n;i++){
            int z = (a.c(i) == '0' ? 1 : 0) + (b.c(i) == '0' ? 1 : 0) + (c.c(i) == '0' ? 1 : 0);
            if(z >= 2) res.append("0");
            else res.append("1");
        }
        int ans = 0;
        for(int i=0;2*i<n;i++){
            if(a.c(i) != res.c(i)){
                int j = i+1;
                while(j < n && a.c(j) != res.c(i)){
                    j++;
                }
                a.setCharAt(i, a.c(i)=='0'?'1':'0');
                a.setCharAt(j, a.c(j)=='0'?'1':'0');
                ans += (j-i);
            } 
            if(b.c(i) != res.c(i)){
                int j = i+1;
                while(j < n && b.c(j) != res.c(i)){
                    j++;
                }
                b.setCharAt(i, b.c(i)=='0'?'1':'0');
                b.setCharAt(j, b.c(j)=='0'?'1':'0');
                ans += (j-i);
            }
            if(c.c(i) != res.c(i)){
                int j = i+1;
                while(j < n && c.c(j) != res.c(i)){
                    j++;
                }
                c.setCharAt(i, c.c(i)=='0'?'1':'0');
                c.setCharAt(j, c.c(j)=='0'?'1':'0');
                ans += (j-i);
            }
        }
        out.pl(ans);
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