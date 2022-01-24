package leetcode;

/**
 * @program algorithm
 * @description
 * @author: hauerwu
 * @create: 2022-01-18 17:13
 **/

public class Solution4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int []result = new int[nums1.length+nums2.length];
        int i=0;
        int j=0;
        int k=0;
        for(;j<nums1.length && k<nums2.length;i++){
            if(nums1[j] <= nums2[k]){
                result[i] = nums1[j];
                j++;
            }else{
                result[i] = nums2[k];
                k++;
            }
        }

        if(j<nums1.length){
            for(;j<nums1.length;j++,i++){
                result[i] = nums1[j];
            }
        }else{
            for(;k<nums2.length;k++,i++){
                result[i] = nums2[k];
            }
        }

        if(result.length%2 == 0){
            return (result[result.length/2-1]+result[result.length/2])/2;
        }else{
            return result[result.length/2];
        }
    }
}
