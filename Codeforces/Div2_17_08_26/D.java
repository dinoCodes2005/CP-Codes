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
        int t = in.i();
        D obj = new D();
        while(t-- > 0) {
            out.pl("---------------Test Case------------");
            obj.solveTestCase();
        }
        out.flush();
        out.close();
    }

    public void solveTestCase() throws Exception {
        // write code
        long S = in.l();
        int q = in.i();
        List<Integer> f = new ArrayList<>();
        List<Integer> fc = new ArrayList<>();
        for(long i=1;i*i<=S;i++){
            if(S % i == 0){
                f.add((int)i);
                if(i != (S / i)) fc.add((int)(S/i));
            }
        }
        Collections.sort(fc);
        f.addAll(fc);
        out.p("F = ");
        for(long val:f) out.p(val+" ");
        out.pl();
        int sz = f.size();
        long p[] = new long[sz];
        long px[] = new long[sz];
        px[0] = f.get(0);
        p[0] = S;
        long max_w = f.get(0);
         for(int i=1;i<sz;i++){
            long w = f.get(i);
            long h = S / f.get(i);
            long extra_w = w-max_w;
            max_w = w;
            p[i] = p[i-1] + (extra_w * h);
            px[i] = px[i-1] + f.get(i);
        }
        out.p("P[i] = ");
        for(long val:p) out.p(val+" ");
        out.pl();
        out.p("Px[i] = ");
        for(long val:px) out.p(val+" ");
        out.pl();

        while(q-->0){
            int x = in.i();
            int y = in.i();
            int posx = 0;
            int l = 0;
            int r = sz-1;
            while(l <= r){
                int mid = l+(r-l)/2;
                if(f.get(mid) <= x){
                    l = mid + 1;
                    posx = mid;
                }else r = mid - 1;
            }
            long ans = p[posx];
            long rgt = (x - f.get(posx));
            if(rgt > 0)
            rgt *= min(y,(S / f.get(posx+1)));
            ans += rgt;

            l = 0;
            r = sz-1;
            int posy = 0;
            while(l <= r){
                int mid = l+(r-l)/2;
                if(S / f.get(mid) > y){
                    l = mid + 1;
                    posy = mid;
                }else r = mid - 1;
            }
            out.pl("Posx = "+posx);
            out.pl("Posy = "+posy);
            long xs = x < f.get(posy) ? px[posy-1] : px[posy];
            long xxs = posy >= 1 ? (x - px[posy-1]) * (S / px[posy]) : 0;
            long extra_above = p[posy] - y*xs - xxs;
            ans -= extra_above;
            out.pl(ans);

        }
        // out.pl(p[sz-1]);
    }
    public static long min(long... values){long ans=Long.MAX_VALUE;for(long v:values)ans=Math.min(ans,v);return ans;}
    public long intersect(long x,long y,long p,long q){
        return min(x,p) * min(y,q);
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