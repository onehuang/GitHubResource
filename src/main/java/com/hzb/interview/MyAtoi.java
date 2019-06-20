package com.hzb.interview;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * 示例 1:
 *
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 *
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 *
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 * 示例 5:
 *
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−2^31) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyAtoi {

    public static void main(String[] args) {

        /**
         * "2147483646"
         * "2147483648"
         */
        String str = "-42";
        int a = myAtoi(str);
        System.out.println(a);
    }


    public static int myAtoi(String str) {

        //1. 获取数字字符串
        StringBuffer sb = new StringBuffer();
        boolean negative = false, has = false;

        for (int i=0; i < str.length(); i++){

            if(sb.length() == 0 && str.charAt(i) == '-' && !has){
                has = true;
                negative = true;
            }else if(sb.length() == 0 && str.charAt(i) == '+' && !has){
                has = true;
            }else if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                sb.append(str.charAt(i));
            }else if(sb.length() == 0 && str.charAt(i) == ' ' && !has){
                continue;
            }else {
                break;
            }
        }

        if(sb.length() == 0){
            return 0;
        }

        //2. 字符串转成数字 并且判断溢出情况
        int result = 0;
        for (int i = 0; i < sb.length(); i++){

            int num = sb.charAt(i) - '0';
            if(!negative){

                if(result  <= (Integer.MAX_VALUE - num) / 10){
                    result = result * 10 + num;
                }else {
                    return Integer.MAX_VALUE;
                }

            }else {
                // -32   -3   =  -3 * 10 - 2
                if(result >= (Integer.MIN_VALUE + num) / 10){
                    result = result * 10 - num;
                }else {
                    return Integer.MIN_VALUE;
                }
            }

        }

        return result;

    }
}
