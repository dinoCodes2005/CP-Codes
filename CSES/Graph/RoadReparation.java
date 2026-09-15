import java.io.*;
import java.util.*;

public class RoadReparation {
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
        RoadReparation obj = new RoadReparation();
        obj.solveTestCase();
        out.flush();
        out.close();
    }
    class N{
        int node;
        long dt;
        N(int node,long dt){
            this.node = node;
            this.dt = dt;
        }
    }
    public void solveTestCase() throws Exception {
        // write code
        int n = in.i();
        int m = in.i();

        int e[][] = new int[m][3];
        for(int i=0;i<m;i++){
            int u = in.i();
            int v = in.i();
            int wt = in.i();
            e[i] = new int[]{u,v,wt};
        }

        DSU dsu = new DSU(n);
        long c = 0;
        Arrays.sort(e,(x,y)->(x[2]-y[2]));
        int cnt = n-1;
        for(int i=0;i<m;i++){
            if(!dsu.checkParent(e[i][0], e[i][1])) {
                c += 1l*e[i][2];
                dsu.unionByRank(e[i][0], e[i][1]);
                cnt--;
            }
        }

        if(cnt != 0){
            out.pl("IMPOSSIBLE");
            return;
        }

        out.pl(c);
        
    }

    static class DSU{
        Map<Integer,Integer> rank=new HashMap<>(),parent=new HashMap<>(),size=new HashMap<>();
        DSU(int n){
            for(int node=1;node<=n;node++){
                rank.put(node,0);
                parent.put(node,node);
                size.put(node,1);
            }
        }
        int findPar(int n){
            if(parent.get(n)==n) return n;
            int p=findPar(parent.get(n));
            parent.put(n,p);
            return p;
        }
        void unionBySize(int u,int v){
            int up=findPar(u),vp=findPar(v);
            if(up==vp)return;
            int su=size.get(up),sv=size.get(vp);
            if(su<sv){
                parent.put(up,vp);
                size.put(vp,su+sv);
            } else {
                parent.put(vp,up);
                size.put(up,su+sv);
            }
        }
        void unionByRank(int u,int v){
            int up=findPar(u),vp=findPar(v);
            if(up==vp)return;
            int ru=rank.get(up),rv=rank.get(vp);
            if(ru<rv) parent.put(up,vp);
            else if(rv<ru) parent.put(vp,up);
            else {
                parent.put(up,vp);
                rank.put(vp,rv+1);
            }
        }
        boolean checkParent(int u,int v){
            return findPar(u)==findPar(v);
        }
        Map<Integer,Integer> parentMap(){
            return parent;
        }
    }

    static class FastReader {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        public FastReader(InputStream stream){in=stream;}
        private int read() throws IOException {if(ptr>=len){len=in.read(buffer);ptr=0;if(len<=0)return -1;}return buffer[ptr++];}
        String n() throws IOException {int c;do{c=read();}while(c<=32);StringBuilder sb=new StringBuilder();while(c>32){sb.append((char)c);c=read();}return sb.toString();}
        string w() throws IOException{return new string(n());}
        int i() throws IOException {int c;do{c=read();}while(c<=32);int res=0;while(c>32){res=res*10+c-'0';c=read();}return res;}
        long l() throws IOException {int c;do{c=read();}while(c<=32);long res=0;while(c>32){res=res*10+c-'0';c=read();}return res;}
        double d() throws IOException{return Double.parseDouble(n());}
        string nl() throws IOException{return new string(n());}
    }

    static class string {
        StringBuilder sb;
        string() { sb = new StringBuilder(); }
        string(int capacity) { sb = new StringBuilder(capacity); }
        string(java.lang.String s) { sb = new StringBuilder(s); }
        string add(Object o) { sb.append(o); return this; }
        string add(int o) { sb.append(o); return this; }
        string add(long o) { sb.append(o); return this; }
        string add(char o) { sb.append(o); return this; }
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
        string append(int obj) { sb.append(obj); return this; }
        string append(long obj) { sb.append(obj); return this; }
        string append(char obj) { sb.append(obj); return this; }
        string remove(int start, int end) { sb.delete(start, end); return this; }
        string[] split(String regex) {
            String[] parts = sb.toString().split(regex);
            string[] result = new string[parts.length];
            for(int i=0;i<parts.length;i++) result[i] = new string(parts[i]);
            return result;
        }
        boolean contains(string substr) { return sb.toString().contains(substr.toString()); }
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

    static class pw {
        private final OutputStream out;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        pw(OutputStream out){this.out=out;}
        private void flushBuffer() throws IOException {if(ptr>0){out.write(buffer,0,ptr);ptr=0;}}
        private void write(int c) throws IOException {if(ptr==buffer.length)flushBuffer();buffer[ptr++]=(byte)c;}
        void p(int x) throws IOException {if(x==0){write('0');return;}if(x<0){write('-');x=-x;}int len=0,y=x;while(y>0){len++;y/=10;}while(len>0){int div=1;for(int i=1;i<len;i++)div*=10;write('0'+x/div);x%=div;len--;}}
        void p(Object x) throws IOException {byte[] b=x.toString().getBytes();for(byte c:b)write(c);}
        void pl(){try{write('\n');}catch(IOException e){throw new RuntimeException(e);}}
        void pl(int x) throws IOException {p(x);write('\n');}
        void pl(Object x) throws IOException {p(x);write('\n');}
        void flush() throws IOException {flushBuffer();out.flush();}
        void close() throws IOException {flush();out.close();}
    }
}