package com.hzb.interview;

/**
 * 9. 回文数
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome3(121));
    }
    public static boolean isPalindrome(int x) {

        if(x < 0){
            return false;
        }else if(x < 10){
            return true;
        }

        String str = String.valueOf(x);
        StringBuffer sb = new StringBuffer(str).reverse();

        return sb.toString().equals(str);

    }

    public static boolean isPalindrome2(int x) {

        String str = x + "";
        for(int i = 0; i < str.length() / 2 ; i++){
            if(str.charAt(i) != str.charAt(str.length() - i - 1)){
                return false;
            }
        }
        return true;

    }

    public static boolean isPalindrome3(int x) {
        if (x < 0 || x != 0 && x % 10 == 0) return false;
        else if (x < 10) return true;
        int y = 0;
        while (x > y) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return x == y || x == y / 10;
    }

}
