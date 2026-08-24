import java.io.*;
import java.util.*;

public class LineSegmentIntersection {
    static FastReader in;
    static pw out;

    static class Point {
        double x,y;
        Point(double x,double y){
            this.x = x;
            this.y = y;
        }

        Point add(double t){
            return new Point(this.x+t,this.y+t);
        }

        Point add(Point p){
            return new Point(this.x+p.x,this.y+p.y);
        }

        Point sub(Point p){
            return new Point(this.x-p.x,this.y-p.y);
        }

        Point mul(double t){
            return new Point(this.x*t,this.y*t);
        }

        double cross(Point p2){
            return (this.x * p2.y) - (this.y * p2.x);
        }
    }
    public static void main(String[] args) throws Exception {
        try {
            in = new FastReader(new FileInputStream("input.txt"));
            out = new pw(new FileOutputStream("output.txt"));
        } catch(Exception e) {
            in = new FastReader(System.in);
            out = new pw(System.out);
        }
        int t = in.i();
        LineSegmentIntersection obj = new LineSegmentIntersection();
        while(t-- > 0) obj.solveTestCase();
        out.flush();
        out.close();
    }
    public static long[] readL(int n) throws IOException{
        long[] arr=new long[n];
        for(int i=0;i<n;i++) arr[i]=in.l();
        return arr;
    }
    public void solveTestCase() throws Exception {
        // write code

        /*
            Let a1 be the starting point of a the first line segment
            and d1 be the direction vector 
            and t be the parameter that shows the distance moved in the direction vector
            then along line 1 ,
            r = a1 + (t * d1) ; the intersecting point 

            for the second line , 
            let d2 be the direction vector along it 
            then i can get another vector (r - a2) along it 

            since d2 and (r - a2) along the same direction
            then cross product will be zero

            (r - a2) x d2 = 0 
            (a1 + (t * d1) - a2) x d2 = 0
            a1 x d2 - a2 x d2 + t*(d1 x d2) = 0
            (a2 x d2 - a1 x d2) / (d1 x d2) = t


            d2 x (a2 - a1) / (d1 x d2) = t
        */
        long x1 = in.l();
        long y1 = in.l();
        long x2 = in.l();
        long y2 = in.l();
        long x3 = in.l();
        long y3 = in.l();
        long x4 = in.l();
        long y4 = in.l();
        Point p1 = new Point(x1,y1);
        Point p2 = new Point(x2,y2);
        Point p3 = new Point(x3,y3);
        Point p4 = new Point(x4,y4);
        Point d1 = p2.sub(p1);
        Point d2 = p4.sub(p3);
        if(Double.compare(d1.cross(d2),0.0) == 0){
            out.pl("No");
            return;
        }
        double t = (p3.cross(d2) - p1.cross(d2))/ d1.cross(d2);
        Point r = p1.add(d1.mul(t));

        //check whether this point r lies in between line 2
        if(min(p1.x,p2.x) <= r.x && r.x <= max(p1.x,p2.x) &&
           min(p1.y,p2.y) <= r.y && r.y <= max(p1.y,p2.y) &&
           min(p3.x,p4.x) <= r.x && r.x <= max(p3.x,p4.x) &&
           min(p3.y,p4.y) <= r.y && r.y <= max(p3.y,p4.y)){
            out.pl("Yes");
        }else{
            out.pl("No");
        }
    }
    public static double min(double... values){double ans=Double.MAX_VALUE;for(double v:values)ans=Math.min(ans,v);return ans;}
    public static double max(double... values) {double ans = -Double.MAX_VALUE;for (double v : values) ans = Math.max(ans, v);return ans;}

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