# ===========================
#  solution.sublime-snippet
#  Trigger: solution
# ===========================
```xml
<snippet>
<content><![CDATA[
import java.io.*;
import java.util.*;

public class solution {
    static FastReader in;
    static pw out;

    public static void main(String[] args) throws Exception {
        if (System.getProperty("ONLINE_JUDGE") == null) {
            in = new FastReader(new FileInputStream("input.txt"));
            out = new pw(new FileOutputStream("output.txt"));
        } else {
            in = new FastReader(System.in);
            out = new pw(System.out);
        }
        int t = in.i();
        solution obj = new solution();
        while(t-- > 0) obj.solveTestCase();
        out.flush();
        out.close();
    }

    public void solveTestCase() throws Exception {
        // write code
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
]]></content>
<tabTrigger>solution</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# ===========================
#  FUNCTION SNIPPETS
# ===========================

---

# abs_int.sublime-snippet  
Trigger: **abs**

```xml
<snippet>
<content><![CDATA[
public static int abs(int n){return Math.abs(n);}
]]></content>
<tabTrigger>abs</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# abs_long.sublime-snippet  
Trigger: **abs**

```xml
<snippet>
<content><![CDATA[
public static long abs(long n){return 1L * Math.abs(n);}
]]></content>
<tabTrigger>abs</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# gcd_int.sublime-snippet  
Trigger: **gcd**

```xml
<snippet>
<content><![CDATA[
public static int gcd(int a,int b){while(b!=0){int temp=b;b=a%b;a=temp;}return a;}
]]></content>
<tabTrigger>gcd</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# gcd_long.sublime-snippet  
Trigger: **gcd**

```xml
<snippet>
<content><![CDATA[
public static long gcd(long a,long b){while(b!=0){long temp=b;b=a%b;a=temp;}return a;}
]]></content>
<tabTrigger>gcd</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# lcm_int.sublime-snippet  
Trigger: **lcm**

```xml
<snippet>
<content><![CDATA[
public static int lcm(int a,int b){return (int)(a*b)/gcd(a,b);}
]]></content>
<tabTrigger>lcm</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# lcm_long.sublime-snippet  
Trigger: **lcm**

```xml
<snippet>
<content><![CDATA[
public static long lcm(long a,long b){return (long)(a*b)/gcd(a,b);}
]]></content>
<tabTrigger>lcm</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# max_int.sublime-snippet  
Trigger: **max**

```xml
<snippet>
<content><![CDATA[
public static int max(int... values){int ans=Integer.MIN_VALUE;for(int v:values)ans=Math.max(ans,v);return ans;}
]]></content>
<tabTrigger>max</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# max_long.sublime-snippet  
Trigger: **max**

```xml
<snippet>
<content><![CDATA[
public static long max(long... values){long ans=Long.MIN_VALUE;for(long v:values)ans=Math.max(ans,v);return ans;}
]]></content>
<tabTrigger>max</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# min_int.sublime-snippet  
Trigger: **min**

```xml
<snippet>
<content><![CDATA[
public static int min(int... values){int ans=Integer.MAX_VALUE;for(int v:values)ans=Math.min(ans,v);return ans;}
]]></content>
<tabTrigger>min</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# min_long.sublime-snippet  
Trigger: **min**

```xml
<snippet>
<content><![CDATA[
public static long min(long... values){long ans=Long.MAX_VALUE;for(long v:values)ans=Math.min(ans,v);return ans;}
]]></content>
<tabTrigger>min</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# swap.sublime-snippet  
Trigger: **swap**

```xml
<snippet>
<content><![CDATA[
public static void swap(int[] arr,int i,int j){int temp=arr[i];arr[i]=arr[j];arr[j]=temp;}
]]></content>
<tabTrigger>swap</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# isPrime.sublime-snippet  
Trigger: **isPrime**

```xml
<snippet>
<content><![CDATA[
public static boolean isPrime(int n){if(n<2)return false;for(int i=2;i*i<=n;i++){if(n%i==0)return false;}return true;}
]]></content>
<tabTrigger>isPrime</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# sieve.sublime-snippet  
Trigger: **sieve**

```xml
<snippet>
<content><![CDATA[
public static boolean[] sieve(int n){
    boolean[] isPrime=new boolean[n+1];
    Arrays.fill(isPrime,true);
    isPrime[0]=isPrime[1]=false;
    for(int i=2;i*i<=n;i++){
        if(isPrime[i]){
            for(int j=i*i;j<=n;j+=i){
                isPrime[j]=false;
            }
        }
    }
    return isPrime;
}
]]></content>
<tabTrigger>sieve</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# freqMap.sublime-snippet  
Trigger: **freqMap**

```xml
<snippet>
<content><![CDATA[
public static Map<Integer,Integer> freqMap(int[] arr){
    Map<Integer,Integer> map=new HashMap<>();
    for(int num:arr){
        if(map.containsKey(num)) map.put(num,map.get(num)+1);
        else map.put(num,1);
    }
    return map;
}
]]></content>
<tabTrigger>freqMap</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# isSorted.sublime-snippet  
Trigger: **isSorted**

```xml
<snippet>
<content><![CDATA[
public static boolean isSorted(int[] arr){
    for(int i=1;i<arr.length;i++){
        if(arr[i]<arr[i-1])return false;
    }
    return true;
}
]]></content>
<tabTrigger>isSorted</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# read.sublime-snippet  
Trigger: **read**

```xml
<snippet>
<content><![CDATA[
public static int[] read(int n) throws IOException{
    int[] arr=new int[n];
    for(int i=0;i<n;i++) arr[i]=in.i();
    return arr;
}
]]></content>
<tabTrigger>read</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# readL.sublime-snippet  
Trigger: **readL**

```xml
<snippet>
<content><![CDATA[
public static long[] readL(int n) throws IOException{
    long[] arr=new long[n];
    for(int i=0;i<n;i++) arr[i]=in.l();
    return arr;
}
]]></content>
<tabTrigger>readL</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# read2.sublime-snippet  
Trigger: **read2**

```xml
<snippet>
<content><![CDATA[
public static int[][] read2(int n,int m) throws IOException{
    int[][] arr=new int[n][m];
    for(int i=0;i<n;i++)
        for(int j=0;j<m;j++)
            arr[i][j]=in.i();
    return arr;
}
]]></content>
<tabTrigger>read2</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# mssGraph.sublime-snippet  
Trigger: **mssGraph**

```xml
<snippet>
<content><![CDATA[
public static int[][] mssGraph(int n,int[][] edges){
    int[][] dist=new int[n][n];
    int inf=Integer.MAX_VALUE/2;
    for(int[] row:dist) Arrays.fill(row,inf);
    for(int i=0;i<n;i++) dist[i][i]=0;
    for(int[] edge:edges){
        int u=edge[0],v=edge[1],w=edge[2];
        dist[u][v]=w;
        dist[v][u]=w;
    }
    return dist;
}
]]></content>
<tabTrigger>mssGraph</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# hasCycle.sublime-snippet  
Trigger: **hasCycle**

```xml
<snippet>
<content><![CDATA[
public static boolean hasCycle(Map<Integer,List<Integer>> graph,int n){
    boolean[] visited=new boolean[n];
    for(int i=0;i<n;i++)
        if(!visited[i] && dfs(i,-1,graph,visited))
            return true;
    return false;
}
]]></content>
<tabTrigger>hasCycle</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# dfs.sublime-snippet  
Trigger: **dfs**

```xml
<snippet>
<content><![CDATA[
public static boolean dfs(int u,int parent,Map<Integer,List<Integer>> g,boolean[] vis){
    vis[u]=true;
    if(!g.containsKey(u)) return false;
    for(int v:g.get(u)){
        if(!vis[v]){
            if(dfs(v,u,g,vis)) return true;
        } else if(v!=parent) return true;
    }
    return false;
}
]]></content>
<tabTrigger>dfs</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# LIS.sublime-snippet  
Trigger: **LIS**

```xml
<snippet>
<content><![CDATA[
public static int LIS(int[] a){
    List<Integer> list=new ArrayList<>();
    for(int val:a){
        int low=0,high=list.size()-1,pos=list.size();
        while(low<high){
            int mid=(low+high)/2;
            if(list.get(mid)>=val){
                pos=mid;
                high=mid-1;
            } else low=mid+1;
        }
        if(pos==list.size()) list.add(val);
        else list.set(pos,val);
    }
    return list.size();
}
]]></content>
<tabTrigger>LIS</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# modPow.sublime-snippet  
Trigger: **modPow**

```xml
<snippet>
<content><![CDATA[
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
]]></content>
<tabTrigger>modPow</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# nCr.sublime-snippet  
Trigger: **nCr**

```xml
<snippet>
<content><![CDATA[
public long nCr(long n,long r,long mod){
    if(r<0||r>n)return 0;
    r=Math.min(r,n-r);
    long res=1;
    for(long i=1;i<=r;i++){
        res=(res*((n-r+i)%mod))%mod;
        res=(res*modInverse(i,mod))%mod;
    }
    return res;
}
]]></content>
<tabTrigger>nCr</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# logx.sublime-snippet  
Trigger: **logx**

```xml
<snippet>
<content><![CDATA[
public int logx(int n,int x){
    int ans=(int)(Math.log(n)/Math.log(x));
    return ans;
}
]]></content>
<tabTrigger>logx</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# hashF.sublime-snippet  
Trigger: **hashF**

```xml
<snippet>
<content><![CDATA[
public long hashF(int x,int y){
    long hash=x*201326611L+y;
    return hash;
}
]]></content>
<tabTrigger>hashF</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# mergeSort.sublime-snippet  
Trigger: **mergeSort**

```xml
<snippet>
<content><![CDATA[
void mergeSort(int[] arr){
    if(arr.length<=1)return;
    int[] tmp=new int[arr.length];
    mergeSort(arr,tmp,0,arr.length-1);
}

void mergeSort(int[] arr,int[] tmp,int l,int r){
    if(l>=r)return;
    int m=l+(r-l)/2;
    mergeSort(arr,tmp,l,m);
    mergeSort(arr,tmp,m+1,r);
    for(int i=l;i<=r;i++) tmp[i]=arr[i];
    int i=l,j=m+1,k=l;
    while(i<=m && j<=r) arr[k++]=tmp[i]<=tmp[j]?tmp[i++]:tmp[j++];
    while(i<=m) arr[k++]=tmp[i++];
}
]]></content>
<tabTrigger>mergeSort</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# radixSort.sublime-snippet  
Trigger: **radixSort**

```xml
<snippet>
<content><![CDATA[
void radixSort(int[] arr){
    int n=arr.length;
    int[] tmp=new int[n];
    final int MASK=0xFF;
    final int BITS=32;
    for(int shift=0;shift<BITS;shift+=8){
        int[] count=new int[256];
        for(int i=0;i<n;i++) count[(arr[i]>>shift)&MASK]++;
        for(int i=1;i<256;i++) count[i]+=count[i-1];
        for(int i=n-1;i>=0;i--) tmp[--count[(arr[i]>>shift)&MASK]]=arr[i];
        System.arraycopy(tmp,0,arr,0,n);
    }
}
]]></content>
<tabTrigger>radixSort</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# ===========================
#  CLASS SNIPPETS
# ===========================

---

# BIT.sublime-snippet  
Trigger: **BIT**

```xml
<snippet>
<content><![CDATA[
class BIT{
    long[] t;
    int n;
    BIT(int n){
        this.n=n+1;
        t=new long[n+2];
    }
    void u(int i,long dx){
        for(++i;i<=n;i+=i&-i) t[i]+=dx;
    }
    long q(int l,int r){
        return q(r)-(l>0?q(l-1):0L);
    }
    long q(int i){
        long s=0;
        for(++i;i>0;i-=i&-i) s+=t[i];
        return s;
    }
}
]]></content>
<tabTrigger>BIT</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# ST.sublime-snippet  
Trigger: **ST**

```xml
<snippet>
<content><![CDATA[
class ST{
    int[] t;
    int[] lz;
    int n;
    ST(int n){
        this.n=n;
        t=new int[4*n];
        lz=new int[4*n];
        Arrays.fill(lz,Integer.MIN_VALUE);
    }
    void u(int s,int e,int val){
        u(0,0,n-1,s,e,val);
    }
    void u(int i,int l,int r,int s,int e,int val){
        if(lz[i]!=Integer.MIN_VALUE){
            t[i]=lz[i];
            if(l!=r){
                lz[2*i+1]=lz[i];
                lz[2*i+2]=lz[i];
            }
            lz[i]=Integer.MIN_VALUE;
        }
        if(l>e || r<s) return;
        if(s<=l && r<=e){
            t[i]=val;
            if(l!=r){
                lz[2*i+1]=val;
                lz[2*i+2]=val;
            }
        } else {
            int m=l+(r-l)/2;
            u(2*i+1,l,m,s,e,val);
            u(2*i+2,m+1,r,s,e,val);
            t[i]=Math.max(t[2*i+1],t[2*i+2]);
        }
    }
    int mx(int s,int e){
        return mx(0,0,n-1,s,e);
    }
    int mx(int i,int l,int r,int s,int e){
        if(lz[i]!=Integer.MIN_VALUE){
            t[i]=lz[i];
            if(l!=r){
                lz[2*i+1]=lz[i];
                lz[2*i+2]=lz[i];
            }
            lz[i]=Integer.MIN_VALUE;
        }
        if(l>e || r<s) return Integer.MIN_VALUE;
        if(s<=l && r<=e) return t[i];
        int m=l+(r-l)/2;
        int L=mx(2*i+1,l,m,s,e);
        int R=mx(2*i+2,m+1,r,s,e);
        return Math.max(L,R);
    }
}
]]></content>
<tabTrigger>ST</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# DSU.sublime-snippet  
Trigger: **DSU**

```xml
<snippet>
<content><![CDATA[
static class DSU{
    Map<Integer,Integer> rank=new HashMap<>(),parent=new HashMap<>(),size=new HashMap<>();
    DSU(Set<Integer> nodes){
        for(int node:nodes){
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
]]></content>
<tabTrigger>DSU</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# Trie.sublime-snippet  
Trigger: **Trie**

```xml
<snippet>
<content><![CDATA[
class Trie{
    private int[][] trie;
    private int[] count;
    private int nodeCount;
    private int capacity;

    public Trie(int n){
        capacity=n*(MAX_BITS+1)+5;
        trie=new int[capacity][2];
        count=new int[capacity];
        nodeCount=1;
    }

    public void insert(long num){
        int node=0;
        count[node]++;
        for(int i=MAX_BITS;i>=0;i--){
            int curr=(int)((num>>i)&1L);
            if(trie[node][curr]==0){
                trie[node][curr]=nodeCount++;
            }
            node=trie[node][curr];
            count[node]++;
        }
    }

    public void delete(long num){
        int node=0;
        count[node]--;
        for(int i=MAX_BITS;i>=0;i--){
            int bit=(int)((num>>i)&1L);
            int child=trie[node][bit];
            if(child==0)return;
            count[child]--;
            if(count[child]==0){
                trie[node][bit]=0;
                return;
            }
            node=child;
        }
    }

    public long maxXor(long num){
        long max=0;
        int node=0;
        for(int i=MAX_BITS;i>=0;i--){
            int curr=(int)((num>>i)&1L);
            if(trie[node][curr^1]!=0){
                max|=(1L<<i);
                node=trie[node][curr^1];
            } else if(trie[node][curr]!=0){
                node=trie[node][curr];
            } else break;
        }
        return max;
    }

    public long minXor(long num){
        long min=0;
        int node=0;
        for(int i=MAX_BITS;i>=0;i--){
            int curr=(int)((num>>i)&1L);
            if(trie[node][curr]!=0){
                node=trie[node][curr];
            } else {
                min|=(1L<<i);
                node=trie[node][curr^1];
            }
        }
        return min;
    }
}
]]></content>
<tabTrigger>Trie</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# MST.sublime-snippet  
Trigger: **MST**

(Your full MST class)

```xml
<snippet>
<content><![CDATA[
class MST{
    int n;
    int[] a;
    N root;

    MST(int[] a,int n){
        this.a=a;
        this.n=n;
        this.root=build(0,n-1);
    }

    N build(int l,int r){
        if(l==r){
            N now=new N(null,null);
            now.lst.add(a[l]);
            return now;
        } else {
            int m=l+(r-l)/2;
            N lft=build(l,m);
            N rgt=build(m+1,r);
            N now=new N(lft,rgt);
            List<Integer> A=lft.lst;
            List<Integer> B=rgt.lst;
            int i=0,j=0;
            List<Integer> nst=new ArrayList<>(A.size()+B.size());
            while(i<A.size() && j<B.size()){
                if(A.get(i)<=B.get(j)) nst.add(A.get(i++));
                else nst.add(B.get(j++));
            }
            while(i<A.size()) nst.add(A.get(i++));
            while(j<B.size()) nst.add(B.get(j++));
            now.lst=nst;
            return now;
        }
    }

    List<Integer> q(N root,int l,int r,int s,int e){
        if(root==null)return new ArrayList<>();
        if(l>e || r<s)return new ArrayList<>();
        if(s<=l && r<=e)return root.lst;
        int m=l+(r-l)/2;
        List<Integer> L=q(root.l,l,m,s,e);
        List<Integer> R=q(root.r,m+1,r,s,e);
        List<Integer> nst=new ArrayList<>(L.size()+R.size());
        int i=0,j=0;
        while(i<L.size() && j<R.size()){
            if(L.get(i)<=R.get(j)) nst.add(L.get(i++));
            else nst.add(R.get(j++));
        }
        while(i<L.size()) nst.add(L.get(i++));
        while(j<R.size()) nst.add(R.get(j++));
        return nst;
    }
}
]]></content>
<tabTrigger>MST</tabTrigger>
<scope>source.java</scope>
</snippet>
```

---

# DONE ✔  
This completes **every snippet you asked for**, with:

- Exact code  
- Correct triggers (rule C)  
- Correct filenames (rule A)  
- Full ZIP-friendly format  

---

If you want a *ready-to-download* file (`snippets.md`), say **EXPORT**, and I will generate it for download.
