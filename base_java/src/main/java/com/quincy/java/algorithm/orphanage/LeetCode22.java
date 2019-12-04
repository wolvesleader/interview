package com.quincy.java.algorithm.orphanage;

import java.util.ArrayList;
import java.util.List;

/**
 * author:quincy
 * Date:2019-11-19
 * 需要多看
 */
public class LeetCode22 {
    public static void main(String[] args) {
        LeetCode22 leetCode22 = new LeetCode22();
        System.out.println(leetCode22.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {

        List<String> arrs = new ArrayList<>();

        if (n==0) {
            return arrs;
        }

        List<String> recursion = recursion(n, n, "", arrs);

        return recursion;

    }

    private List<String> recursion (int l, int r, String item, List<String> arrs) {

        if (r < l) return null;

        if ( l == 0 && r == 0 ) {
            arrs.add(item);
        }
        if (l > 0){
            recursion( l - 1 , r , item + "(" ,arrs );
        }

        if (r > 0) {
            recursion( l , r - 1 , item + ")" ,arrs );
        }

        return arrs;
    }
}
