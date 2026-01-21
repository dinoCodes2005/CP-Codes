/*

    Author : Rahul Chowdhary A.K.A Dino
    Institute : Vellore Institute of Technology

*/

import java.io.*; import java.util.*;

class Codechef {
    static FastReader in;
    static pw out;
    static final long MODL = 1000000007;
    static final int MOD = 1000000007;
    public static int[] read(int n) throws IOException { int arr[] = new int[n]; for(int i=0;i<n;i++){ arr[i] = in.i(); } return arr; } 
    public static long[] readL(int n) throws IOException { long arr[] = new long[n]; for(int i = 0; i < n; i++) { arr[i] = in.l(); } return arr; }
    public static int[][] read2(int n, int m) throws IOException { int arr[][] = new int[n][m]; for(int i=0;i<n;i++){ for(int j=0;j<m;j++){ arr[i][j] = in.i(); } } return arr; } 
    public static void print(int[] arr) throws IOException { for(int i=0;i<arr.length;i++){ out.print(arr[i]+" "); } out.println(); }
    public static void print(long[] arr) throws IOException { for(int i=0;i<arr.length;i++){ out.print(arr[i]+" "); } out.println(); }
    public static void main(String[] args) throws Exception {
        in = new FastReader(System.in);
        out = new pw(System.out);
        Codechef obj = new Codechef();
        int t = 1; while (t-- > 0) { obj.solveTestCase(); }
        // obj.solveTestCase();
        out.flush(); out.close();
    }

    Map<String,Boolean> vis;
    Map<String,List<String>> map;
    int n,m;
    int a[][];
    public void solveTestCase() throws IOException {
        //T.C : O(2*n^2)
        //S.C : O(1)
        int a[] = {8,3,6,2,5};
        f(a);
    }
    int sum = 0;
    public void f(int a[]){
        int n = a.length;
        divide(0,n-1,a);
        out.pl(sum);
    }

    public void divide(int l,int r,int[] a){
        if(l < r){
            int m = l + (r - l)/2;
            divide(l,m,a);
            divide(m+1,r,a);
            conquer(l,m,r,a);
        }
    }


    public void solveTestCase() throws IOException{
        //T.C : O(n)
        //S.C : O(1)
        int n = in.i();
        int m = in.i();
        int h = in.i();
        int a[] = read(n);
        int nums[] = a.clone();
        TreeMap<Integer,Integer> orig = new TreeMap<>();
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        for(int val:a) {
            orig.put(val,orig.getOrDefault(val,0)+1);
            tm.put(val,tm.getOrDefault(val,0)+1);
        }
        for(int i=0;i<m;i++){
            int b = in.i();
            int c = in.i();
            tm.put(nums[b-1],tm.get(nums[b-1])-1);
            nums[b-1] += c;
            tm.put(nums[b-1],tm.getOrDefault(nums[b-1],0)+1);
            if(tm.lastKey() > h){
                nums = a.clone();
                tm = orig;
            }
        }

        print(nums,n);
        

    }
    public void solveTestCase() throws IOException{
        //T.C : O(n)
        //S.C : O(1)
        int n = in.i();
        int a[] =read(n);
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(a[i] >= 0) min = Math.min(min,a[i]);
        }   
        int max = 0;
        if(min != Integer.MAX_VALUE){
            Set<Integer> set = new HashSet<>();
            for(int i=0;i<n;i++) set.add(a[i] - min);
            for(int mex=0;mex<=n;mex++){
                if(!set.contains(mex)) {
                    max = mex;
                    break;
                }
            }
        }

        int lneg = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(a[i] < 0) lneg = Math.max(lneg,a[i]);
        }
        if(lneg != Integer.MIN_VALUE){
            Set<Integer> hs = new HashSet<>();
            for(int i=0;i<n;i++){
                hs.add(a[i] - lneg);
            }

            for(int mex=0;mex<=n;mex++){
                if(!hs.contains(mex)) {
                    max = Math.max(mex,max);
                    break;
                }
            }
        }

        out.pl(max);

    }

    public void conquer(int l, int m, int r, int[] a) {
        int rsz = r - m;
        int[] rp = new int[rsz];
        rp[0] = a[m+1];
        for (int i = m+2; i <= r; i++) {
            rp[i-m-1] = rp[i-m-2] + a[i];
        }

        for (int x = l; x <= m; x++) {
            int pos = Arrays.binarySearch(a, m+1, r+1, a[x]);
            if (pos < 0) pos = -(pos + 1);
            int cnt = pos - (m + 1);
            if (cnt > 0) {
                int sumRight = rp[cnt - 1];
                sum += (long)a[x] * cnt - sumRight;
            }
        }

        int i = l, j = m+1, idx = 0;
        int[] temp = new int[r - l + 1];

        while (i <= m && j <= r) {
            if (a[i] <= a[j]) temp[idx++] = a[i++];
            else temp[idx++] = a[j++];
        }
        while (i <= m) temp[idx++] = a[i++];
        while (j <= r) temp[idx++] = a[j++];

        for (int k = l; k <= r; k++) a[k] = temp[k-l];
    }



    void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length - 1);
    }

    void mergeSort(int[] arr, int[] tmp, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(arr, tmp, l, m);
        mergeSort(arr, tmp, m + 1, r);
        
        for (int i = l; i <= r; i++) tmp[i] = arr[i];
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) arr[k++] = tmp[i] <= tmp[j] ? tmp[i++] : tmp[j++];
        while (i <= m) arr[k++] = tmp[i++];
    }

    void radixSort(int[] arr) {
        int n = arr.length;
        int[] tmp = new int[n];
        final int MASK = 0xFF;
        final int BITS = 32;
        for (int shift = 0; shift < BITS; shift += 8) {
            int[] count = new int[256];
            for (int i = 0; i < n; i++)
                count[(arr[i] >> shift) & MASK]++;
            for (int i = 1; i < 256; i++)
                count[i] += count[i - 1];
            for (int i = n - 1; i >= 0; i--)
                tmp[--count[(arr[i] >> shift) & MASK]] = arr[i];
            System.arraycopy(tmp, 0, arr, 0, n);
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

    static class pw extends PrintWriter {
        pw(OutputStream out) {
            super(out);
        }
        void p(Object x){
            print(x);
        }
        void pl(){
            println();
        }
        void pl(Object x) {
            println(x);
        }
    }

}

