package com.hzb.interview;

/**
 *
 * 4. 寻找两个有序数组的中位数
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MidOfSortedArray {

    public static void main(String[] args) {
        int[] n1 = null;
        int[] n2 = {2, 4};
        System.out.println(findMedianSortedArrays(n1, n2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] all = new int[nums1.length + nums2.length];

        int i =0, i1 = 0,i2 = 0;
        double mid = 0.0;

        while (i1 < nums1.length && i2 < nums2.length){

            if(nums1[i1] < nums2[i2]){
                all[i] = nums1[i1];
                i1 ++;
            }else {
                all[i] = nums2[i2];
                i2 ++;
            }
            i++;
        }

        // num1 剩余的添加
        for(int j = i1; j < nums1.length; j++){
            all[i] = nums1[j];
            i++;
        }

        // num2 剩余的添加
        for(int j = i2; j < nums2.length; j++){
            all[i] = nums2[j];
            i++;
        }

        if(all.length % 2 ==0){
            mid = (all[all.length/2 - 1] + all[all.length/2]) / 2.0;
        }else {
            mid = all[all.length/2];
        }

        return mid;
    }
}
