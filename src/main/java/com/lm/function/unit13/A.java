package com.lm.function.unit13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class A {

    static List<List<Integer>> concat (List<List<Integer>> a , List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

    static List<List<Integer>> insertAll (Integer first , List<List<Integer>> lists) {
        //lists.forEach(list -> list.add(first));
        List<List<Integer>> lists1 = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> integerList = new ArrayList<>();
            integerList.add(first);
            integerList.addAll(list);
            lists1.add(integerList);
        }
        return lists1;
    }

    public List<List<Integer>> subsets (List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> subList = list.subList(1, list.size());
        List<List<Integer>> subans = subsets(subList);
        List<List<Integer>> lists = insertAll(first, subans);
        return concat(subans , lists);
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(4);
        integers.add(9);
        A a = new A();
        List<List<Integer>> subsets = a.subsets(integers);
        System.out.println(subsets);
        ArrayList<Integer> integers1 = new ArrayList<>();
        integers1.add(9);
        List<List<Integer>> v= new ArrayList<>();
        v.add(integers1);
        ArrayList<Integer> integers2 = new ArrayList<>();
        integers2.add(9);
        List<List<Integer>> w = new ArrayList<>();
        w.add(integers2);
        v.addAll(w);
        System.out.println(v);
    }


}
