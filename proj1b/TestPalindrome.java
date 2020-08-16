import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offbyone = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void isPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));

    }

    @Test
    public void testOffByOne(){
        OffByOne obo = new OffByOne();
        assertTrue(obo.equalChars('a', 'b'));  // true
        assertTrue(obo.equalChars('r', 'q'));  // true
        assertFalse(obo.equalChars('a', 'e'));  // false
        assertFalse(obo.equalChars('z', 'a'));  // false
        assertFalse(obo.equalChars('a', 'a'));  // false
    }

    @Test
    public void testOffByN(){
        OffByN offBy5 = new OffByN(5);
        assertTrue(offBy5.equalChars('a', 'f'));  // true
        assertTrue(offBy5.equalChars('f', 'a'));  // true
        assertFalse(offBy5.equalChars('f', 'h'));  // false
    }

    @Test
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindrome("flake",offbyone));
        assertTrue(palindrome.isPalindrome("", offbyone));
        assertTrue(palindrome.isPalindrome("a", offbyone));
        assertFalse(palindrome.isPalindrome("aa", offbyone));
        assertFalse(palindrome.isPalindrome("xyz", offbyone));
    }

}