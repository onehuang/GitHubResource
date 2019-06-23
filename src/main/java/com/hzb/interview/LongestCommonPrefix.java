package com.hzb.interview;


/**
 * 14. 最长公共前缀
 *
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {

        String[] arg = {"abcd", "abc", "abcd"};
        System.out.println(longestCommonPrefix(arg));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        String prefix = strs[0];
        for(int i=1; i< strs.length; i++){

            while (strs[i].indexOf(prefix) !=0 ){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()) return "";
            }
        }
        return prefix;

    }
}
