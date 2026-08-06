/*
    Author : Rahul Chowdhary AKA Dino
    Instituite : Vellore Institute of Technology
*/

import java.io.*;
import java.util.*;

public class huffman {
    static FastReader in;
    static pw out;
    public static int max(int... values){int ans=Integer.MIN_VALUE;for(int v:values)ans=Math.max(ans,v);return ans;}
    public static long max(long... values){long ans=Long.MIN_VALUE;for(long v:values)ans=Math.max(ans,v);return ans;}
    public static int min(int... values){int ans=Integer.MAX_VALUE;for(int v:values)ans=Math.min(ans,v);return ans;}
    public static long min(long... values){long ans=Long.MAX_VALUE;for(long v:values)ans=Math.min(ans,v);return ans;}
    public static int[] read(int n) throws IOException{int[] arr=new int[n];for(int i=0;i<n;i++) arr[i]=in.i();return arr;}
    public static long[] readL(int n) throws IOException{long[] arr=new long[n];for(int i=0;i<n;i++) arr[i]=in.l();return arr;}
    public static void print(int[] a,int n) {for(int val:a) out.p(val+" "); out.pl();}
    public static void print(long[] a,int n) {for(long val:a) out.p(val+" "); out.pl();}

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis(); 
        if (System.getProperty("ONLINE_JUDGE") == null) {
            in = new FastReader(new FileInputStream("input.txt"));
            out = new pw(new FileOutputStream("output.txt"));
        } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        }
        int t = 1;
        huffman obj = new huffman();
        while(t-- > 0) obj.solveTestCase();
        long end = System.currentTimeMillis();
        System.err.println("Time: " + (end - start) + " ms");
        out.flush();
        out.close();
    }

    string s = new string("BCCABBDDAECCBBAEDDCC");
    int n = s.length();
    int a[] = new int[26];
    class Node{
    	//char , cnt
    	int[] val;
    	Node l,r;
    	Node(int[] val,Node l,Node r){
    		this.val = val;
    		this.l = l;
    		this.r = r;
    	}
    }
    Map<Character,string> codes;
    public void solveTestCase() throws IOException {
        //T.C : O()
        //S.C : O()
    	for(int i=0;i<n;i++){
    		a[s.c(i)-'A']++;
    	}
    	codes = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
		    @Override
		    public int compare(Node x, Node y) {
		        return x.val[1] - y.val[1];
		    }
		});


    	for(int i=0;i<26;i++){
    		if(a[i] != 0) pq.offer(new Node(new int[]{i,a[i]},null,null));
    	}

    	while(pq.size() >= 2){
    		Node s = pq.poll();
    		Node ss = pq.poll();
    		Node parent = new Node(
    			new int[]{-1,s.val[1] + ss.val[1]},
    			s,ss
    		);
    		pq.offer(parent);
    	}

    	Node root = pq.poll();
    	f(root,new string());
    	for(char key:codes.keySet()){
    		out.pl(key + " -> " + codes.get(key));
    	}

    }
    public void f(Node root,string code){
    	if(root == null) return;
    	if(root.l == null && root.r == null){
    		char ch = (char)(root.val[0] + 65);
    		codes.put(ch,code);
    		return;
    	}
    	string L = new string(code.toString());
    	string R = new string(code.toString());
    	f(root.l,L.add("0"));
    	f(root.r,R.add("1"));
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
        string[] split(String regex) {String[] parts = sb.toString().split(regex);string[] result = new string[parts.length];for (int i = 0; i < parts.length; i++) {result[i] = new string(parts[i]);}return result;}
        boolean contains(string substr) {return sb.toString().contains(substr.toString());}
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