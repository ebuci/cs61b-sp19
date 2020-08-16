public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> A = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            A.addLast(word.charAt(i));
        }
        return A;
    }

    /** iteration approach
    public boolean isPalindrome(String word){
        Deque<Character> A = wordToDeque(word);
        while(A.size() > 1){
            if(!A.removeFirst().equals(A.removeLast())){
                return false;
            }
        }
        return true;
    } */

    public boolean isPalindrome(String word) {
        Deque<Character> A = wordToDeque(word);
        return isPalindromeHelper(A);
    }

    public boolean isPalindromeHelper(Deque<Character> A) {
        if(A.size()< 2) {
            return true;
        }
        if(!A.removeFirst().equals(A.removeLast())) {
            return false;
        }
        return isPalindromeHelper(A);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> A = wordToDeque(word);
        while(A.size() > 1) {
            Character first = A.removeFirst();
            Character last = A.removeLast();
            if(!cc.equalChars(first, last)){
                return false;
            }
        }
        return true;
    }


}
