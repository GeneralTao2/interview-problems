package org.taonity;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public int maxProfit2(int[] prices) {
        List<Map.Entry<Integer, Integer>> datedPrice = new ArrayList<>(prices.length);
        for (int i=0; i<prices.length; i++) {
            datedPrice.add(new AbstractMap.SimpleEntry<>(i, prices[i]));
        }
        var minimumDatedPrice = datedPrice.get(0);
        datedPrice.sort(Map.Entry.comparingByValue());
        for(int i=datedPrice.size()-1; i>0; i--) {
            if(minimumDatedPrice.getKey() < datedPrice.get(i).getKey()) {
                return  datedPrice.get(i).getValue() - minimumDatedPrice.getValue();
            }
        }
        return 0;
    }
}