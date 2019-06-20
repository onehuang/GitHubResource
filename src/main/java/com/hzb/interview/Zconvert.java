package com.hzb.interview;


import java.util.ArrayList;
import java.util.List;

/**
 * 6. Z 字形变换
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 *
 * L         I
 * E      E  S       G
 * E    D    H    N
 * T  O      I  I
 * C         R
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Zconvert {

    public static void main(String[] args) {
        String str = "ABAB";
        System.out.println(convert(str, 2));
    }

    public static String convert(String s, int numRows) {

        /**
         *  L     D     R
         *  E   O E   I I
         *  E C   I H   N
         *  T     S     G
         */
        int rows = Math.min(numRows, s.length());
        List<StringBuffer> list = new ArrayList<>();

        for (int i= 0; i< rows; i++){
            list.add(new StringBuffer());
        }
        //当前行
        int currentRow = 0;
        // 移动方向
        boolean going = true;

        for (int i=0; i< s.length(); i++){

            list.get(currentRow).append(s.charAt(i));
            if(rows > 1) {
                currentRow += going ? 1 : -1;
            }

            //收尾行移动翻转
            if(currentRow  == 0 || currentRow == rows -1){
                going = !going;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (StringBuffer buffer : list) {
            sb.append(buffer);
        }

        return sb.toString();

    }

}
