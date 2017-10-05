package com.hzb;

import java.util.*;
import java.util.LinkedList;

/**
 *
 */
public class ArrayLearn {
    public  static  void  main(String[] args){
        long time = System.currentTimeMillis();
        int a[] =  GetArrayNoRepeat(1000);
        System.out.println("方法1耗时：" + (System.currentTimeMillis() - time));
//        PrintArray(a);
        time = System.currentTimeMillis();
        a =  GetArrayNoReapat2(1000);
        System.out.println("方法2耗时：" + (System.currentTimeMillis() - time));

//        PrintArray(a);

    }

    /**
     * 生成不重复的随机数
     * 利用set的不重复性
     */
    public static int[] GetArrayNoRepeat(int n) {
        if (n <= 0 ) return null;
        int[] a = new int[n];
        Random random = new Random();
        Set set= new LinkedHashSet();
        while (set.size() < n) {
            set.add(random.nextInt(n));
        }
        Iterator<Integer> it = set.iterator();
        for(int i = 0; it.hasNext(); i++){
            a[i] = it.next();
        }

        return a;
    }

    public static int[] GetArrayNoReapat2(int n){
        int[] a = new int[n];
        Random random = new Random();
        List list = new ArrayList<Integer>((int)(n * 1.2));

        for(int i=0; i< n; i++){
            list.add(i);
        }
        int length = 0;
        for(int i=0; i< n; i++){
            length = list.size();
            a[i] = (Integer)list.remove(random.nextInt(length));
        }


        return a;
    }

    public static void  PrintArray(int[] t){
        if (t == null) return;
        for(int i=0; i< t.length; i++){
            System.out.print(t[i] + " ");
        }
        System.out.println();

    }
}