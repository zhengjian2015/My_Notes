

# LeetCode1

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





# LeetCode805

学习集合后的简单题

```java
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        TreeSet<String> set = new TreeSet<>();
        String[] codes = {".-", "-...", "-.-.", "-..", ".",
                "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
                "..-", "...-", ".--", "-..-", "-.--", "--.."};
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(codes[word.charAt(i) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
```





# LeetCode 349

自己想的

```java
			TreeSet<Integer> set = new TreeSet<>();
            TreeSet<Integer> set1 = new TreeSet<>();
            for(int a : nums1) {
                set.add(a);
            }
            for(int a1 : nums2) {
                set1.add(a1);
            }
            Map<Integer,Integer> map = new HashMap<>();
            for(int a : set) {
                map.put(a,(map.get(a) == null ? 0 : map.get(a) )+1);
            }
            for(int b : set1) {
                map.put(b,(map.get(b) == null ? 0 : map.get(b) )+1);
            }
            TreeSet<Integer> set2 = new TreeSet<>();
            for(Integer a:map.keySet()) {
                if(map.get(a) == 2)
                    set2.add(a);
            }
             List<Integer> b = new ArrayList<>();
            for(int s : set2) {
                b.add(s);
            }
            int[] strings = new int[b.size()];

            for(int i=0;i<b.size();i++) {
                strings[i] = b.get(i);
            }
            return strings;
```

时间复杂度和空间复杂度太高了

bobo老师解法

```java
			TreeSet<Integer> set = new TreeSet<>();
            for (int num : nums1) {
                set.add(num);
            }
            List<Integer> list = new ArrayList<>();
            for (int num : nums2) {
                if (set.contains(num)) {
                    list.add(num);
                    set.remove(num);
                }
            }
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
```

空间复杂度低很多，时间复杂度还是很高



# LeetCode 350

```java
		int[] nums1 = {1, 2, 2, 4};
        int[] nums2 = {1, 2, 2, 4, 5};
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if(map.containsKey(num)) {
                list.add(num);
                map.put(num,map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }
        int[] arr = new int[list.size()];
        for(int i=0;i<list.size();i++)
            arr[i] = list.get(i);
```

