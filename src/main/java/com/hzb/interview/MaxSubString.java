package com.hzb.interview;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubString {
    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));
        str = "bbbbb";
        System.out.println(lengthOfLongestSubstring(str));
        str = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static  int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int start =0, end = 0; end < s.length(); end ++){

            char c = s.charAt(end);
            if(map.containsKey(c)){
                start = Math.max(map.get(c), start);
            }
            max = Math.max(max, end - start + 1);
            map.put(c, end + 1);

        }
        return max;
    }



}
