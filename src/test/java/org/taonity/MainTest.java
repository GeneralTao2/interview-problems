package org.taonity;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


public class MainTest {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (!charSet.contains(s.charAt(right))) {
                charSet.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                while (charSet.contains(s.charAt(right))) {
                    charSet.remove(s.charAt(left));
                    left++;
                }
                charSet.add(s.charAt(right));
            }
        }

        return maxLength;
    }

    public String longestPalindrome(String s) {
        String maxString = "";
        if(s.length() == 1) {
            return s;
        }
        if(s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return String.valueOf(s.charAt(0));
            }
        }
        for (int i=1; i<s.length()-1; i++) {
            String currentString = "";
            for(int left = i-1, right = i+1; left >= 0 && right < s.length();) {
                String leftSubstring = s.substring(left, i);
                String rightRowSubstring = s.substring(i+1, right+1);
                String rightSubstring = new StringBuilder(rightRowSubstring).reverse().toString();
                if(leftSubstring.equals(rightSubstring)) {
                    currentString = leftSubstring + s.charAt(i) + rightRowSubstring;
                }
                left--;
                right++;
            }
            if(currentString.length() > maxString.length()) {
                maxString = currentString;
            }
        }
        for (int i=0; i<s.length(); i++) {
            String currentString = "";
            for(int left = i, right = i+1; left >= 0 && right < s.length();) {
                String leftSubstring = s.substring(left, i+1);
                String rightRowSubstring = s.substring(i+1, right+1);
                String rightSubstring = new StringBuilder(rightRowSubstring).reverse().toString();
                if(leftSubstring.equals(rightSubstring)) {
                    currentString = leftSubstring + rightSubstring;
                }
                left--;
                right++;
            }
            if(currentString.length() > maxString.length()) {
                maxString = currentString;
            }
        }
        if (maxString.isEmpty()) {
            return String.valueOf(s.charAt(s.length()-1));
        } else {
            return maxString;
        }
    }

    public String convert(String s, int numRows) {
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i=0; i<numRows; i++) {
            stringBuilders[i] = new StringBuilder();
        }
        int currentRow = 0;
        for(int i=0; i<s.length();) {
            while (currentRow < numRows && i < s.length()) {
                stringBuilders[currentRow].append(s.charAt(i));
                i++;
                currentRow++;
            }
            currentRow-=2;
            while (currentRow >= 0 && i < s.length()) {
                stringBuilders[currentRow].append(s.charAt(i));
                i++;
                currentRow--;
            }
            currentRow+=2;
        }
        return Arrays.stream(stringBuilders).map(StringBuilder::toString).collect(Collectors.joining());
    }

    public boolean isPalindrome(int x) {
        String xString = String.valueOf(x);
        int left = 0;
        int right = xString.length()-1;
        while (left < right) {
            if (xString.charAt(left) != xString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1) {
            return strs[0];
        }
        int i;
        boolean charsEquals = true;
        int minStrsLength = Collections.min(Arrays.stream(strs).map(String::length).toList());

        if(minStrsLength == 0) {
            return "";
        }
        for (i=0; i<minStrsLength; i++) {
            charsEquals = true;
            for (int j=0; j<strs.length-1; j++) {
                charsEquals = charsEquals && (strs[j].charAt(i) == strs[j+1].charAt(i));
            }
            if(charsEquals == false) {

                break;
            }
        }
        if(i==minStrsLength) {
            i--;
        }
        if(charsEquals == false) {
            i--;
        }

        return strs[0].substring(0, i+1);
    }

    @Test
    void longestCommonPrefix1() {
        assertThat(longestCommonPrefix(new String[]{"ab", "a"})).isEqualTo("a");
        assertThat(longestCommonPrefix(new String[]{"",""})).isEqualTo("");
        assertThat(longestCommonPrefix(new String[]{"flower","flow","flight"})).isEqualTo("fl");
    }

    @Test
    void convert() {
        assertThat(convert("P", 2)).isEqualTo("P");
    }

    @Test
    void longestPalindrome1() {
        assertThat(longestPalindrome("ada")).isEqualTo("ada");
        assertThat(longestPalindrome("adaaaaa")).isEqualTo("aaaaa");
        assertThat(longestPalindrome("123123123adaaaaasds")).isEqualTo("aaaaa");
    }

    @Test
    void lengthOfLongestSubstring() {
        assertThat(lengthOfLongestSubstring("pwwkew")).isEqualTo(9);
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    @Test
    void majorityElementTest() {
        assertThat(majorityElement(new int[]{6,6,6,7,7})).isEqualTo(6);
        assertThat(majorityElement(new int[]{3,2,3})).isEqualTo(2);
    }

    @Test
    void testXor() {
        System.out.println(3 ^ 2 ^ 3);
    }

    public void rotate1(int[] nums, int k) {
        int length = nums.length;
        int[] rotatedNums = new int[length];
        int shortenedK = k - Integer.valueOf(k/length)*length;
        for (int i=0; i<length; i++) {
            int srcIndex;
            if (i - shortenedK >= 0) {
                srcIndex = i - shortenedK;
            } else {
                srcIndex = i + (length - shortenedK);
            }
            rotatedNums[i] = nums[srcIndex];
        }
        System.arraycopy(rotatedNums, 0, nums, 0, nums.length);
    }

    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        int shortenedK = k - Integer.valueOf(k/length)*length;
        int currentJump = 0;
        int buffer = nums[currentJump];
        int count = nums.length;
        if (Math.abs(shortenedK - length/2.0) < 0.1) {
            int halthLength = length/2;
            for (int i=0; i< halthLength; i++) {
                int tmp = nums[i];
                nums[i] = nums[halthLength + i];
                nums[halthLength + i] = tmp;
            }
        } else {
            while (count-- >= 0) {
                int dstIndex;
                if (currentJump + shortenedK < length) {
                    dstIndex = currentJump + shortenedK;
                } else {
                    dstIndex = currentJump + shortenedK - length;
                }
                int currentBuffer = buffer;
                buffer = nums[dstIndex];
                nums[dstIndex] = currentBuffer;
                currentJump = dstIndex;
            }
        }
    }

    @Test
    void testRotate() {
        int[] nums = new int[] {1, 2, 3, 4};
        rotate2(nums, 41);
        assertThat(nums).containsExactly(4, 1, 2, 3);
    }

    // too much time
    public int maxProfit1(int[] prices) {
        int maxProfit = 0;
        for (int i=0; i<prices.length; i++) {
            for (int j=i+1; j<prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    // wrong
    public int maxProfit2(int[] prices) {
        List<Map.Entry<Integer, Integer>> datedPrice = new ArrayList(prices.length);
        for (int i=0; i<prices.length; i++) {
            datedPrice.add(new AbstractMap.SimpleEntry<>(i, prices[i]));
        }
        datedPrice.sort(Map.Entry.comparingByValue());
        var minimumDatedPrice = datedPrice.get(0);
        for(int i=datedPrice.size()-1; i>0; i--) {
            if(minimumDatedPrice.getKey() < datedPrice.get(i).getKey()) {
                return  datedPrice.get(i).getValue() - minimumDatedPrice.getValue();
            }
        }
        return 0;
    }

    // looked at solution
    public int maxProfit3(int[] prices) {
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else if (prices[i] - buy > profit) {
                profit = prices[i] - buy;
            }
        }
        return profit;
    }

    @Test
    void testProfit() {
        assertThat(maxProfit2(new int[] {2,4,1})).isEqualTo(2);
        assertThat(maxProfit2(new int[] {7,1,5,3,6,4})).isEqualTo(5);
    }

    public int maxProfit21(int[] prices) {
        int max = 0;
        int start = prices[0];
        int len = prices.length;
        for(int i = 1;i<len; i++){
            if(start < prices[i]) max += prices[i] - start;
            start = prices[i];
        }
        return max;
    }

    @Test
    void maxProfit21() {
        assertThat(maxProfit21(new int[] {1, 2, 100, 101})).isEqualTo(100);
    }

    public boolean canJump(int[] nums) {
        int length = nums.length;
        int i=length-1;
        int lastJumpIndex = length - 1;
        while(i>=0) {
            while (i>=0) {
                if(nums[i] >= lastJumpIndex - i) {
                    lastJumpIndex = i;
                }
                i--;
            }
        }
        return nums[0] >= lastJumpIndex;
    }

    @Test
    void testCanJump() {
        assertThat(canJump(new int[] {3,2,1,0,4})).isFalse();
    }

    public int jump(int[] nums) {
        int length = nums.length;
        int count = 0;
        for (int i=length-1; i>0;) {
            int maxJump = 0;
            for (int j=i; j>=0; j--) {
                if(nums[j] >= i - j) {
                    maxJump = j;
                }
            }
            i=maxJump;
            count++;
        }

        return count;
    }

    @Test
    void testJump() {
        assertThat(jump(new int[] {2,3,1,1,4})).isEqualTo(2);
    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int hIndex = 0;
        for (int i=citations.length - 1; i>=0; i--) {
            int supposedHIndex = citations.length - i;
            if(citations[i] >= supposedHIndex && supposedHIndex > hIndex) {
                hIndex = supposedHIndex;
            }
        }
        return hIndex;
    }

    class RandomizedSet {
        final List<Integer> array = new ArrayList<>();
        static final Random rand = new Random();

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            boolean isPresent = array.contains(val);
            array.add(val);
            return !isPresent;
        }

        public boolean remove(int val) {
            return array.remove(Integer.valueOf(val));
        }

        public int getRandom() {
            return array.get(rand.nextInt(array.size()));
        }
    }

    @Test
    void testArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.size());
        list.remove(1);
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }

    @Test
    void testRandomisedSet() {
        RandomizedSet randomizedSet = new RandomizedSet();
        assertThat(randomizedSet.insert(1)).isTrue(); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        assertThat(randomizedSet.remove(2)).isFalse(); // Returns false as 2 does not exist in the set.
        assertThat(randomizedSet.insert(2)).isTrue(); // Inserts 2 to the set, returns true. Set now contains [1,2].
        assertThat(randomizedSet.getRandom()).satisfiesAnyOf(
                it -> assertThat(it).isEqualTo(1),
                it -> assertThat(it).isEqualTo(2)
        ); // getRandom() should return either 1 or 2 randomly.
        assertThat(randomizedSet.remove(1)).isTrue(); // Removes 1 from the set, returns true. Set now contains [2].
        assertThat(randomizedSet.insert(2)).isFalse(); // 2 was already in the set, so return false.
        assertThat(randomizedSet.getRandom()).isEqualTo(2); // Since 2 is the only number in the set, getRandom() will always return 2.
    }

    public int[] productExceptSelf(int[] nums) {
        int zeroCount = 0;
        int allProduct = 1;
        int nonZeroProduct = 1;
        for (int num : nums) {
            allProduct *= num;
            if (num == 0) {
                zeroCount++;
            } else {
                nonZeroProduct *= num;
            }
        }

        for (int i=0; i<nums.length; i++) {
            if(nums[i] == 0) {
                if (zeroCount > 1) {
                    nums[i] = 0;
                } else {
                    nums[i] = nonZeroProduct;
                }
            } else {
                nums[i] = allProduct / nums[i];
            }
        }

        return nums;
    }
}