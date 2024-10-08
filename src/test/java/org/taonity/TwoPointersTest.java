package org.taonity;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class TwoPointersTest {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {

        }
    }
}
