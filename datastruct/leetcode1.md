```java
leetcode 第一题，暴力求解是做出来了，第二种字典方法想不出，可能循环两遍能记住，
    一遍太巧妙了。想不出来
public class LeetCode01 {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
}

//看了答案，再做还是想不出。。。
public class LeetCode01 {
    public int[] twoSum(int[] nums, int target) {
           // 建立k-v ，一一对应的哈希表
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
            for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            hash.put(target-nums[i],i);
        }
    }
}

```







```java

/**
 * leetcode第7题
 * 自己写的效率贼低
 */
class Solution {
    public int reverse(int x) {
        int ret = 0;
        String res = "";
        if (x < 0) {
            String xm = x + "";
            String[] strs = xm.split("");
            for (int j = strs.length - 1; j >= 1; j--) {
                int a = Integer.valueOf(strs[j]);
                ret = ret + a;
                if (ret + a != 0) { //关键的一步，排除0情况
                    res = res + strs[j];
                }
            }
            res = "-" + res;
        } else {
            String xm = x + "";
            String[] strs = xm.split("");
            for (int j = strs.length - 1; j >= 0; j--) {
                int a = Integer.valueOf(strs[j]);
                ret = ret + a;
                if (ret + a != 0) {
                    res = res + strs[j];
                }
            }
        }
        int resi;
        try {
            resi = Integer.valueOf(res);
        } catch (Exception ex) {
            resi = 0;
        }
        return resi;
    }
}
```