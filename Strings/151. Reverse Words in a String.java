class Solution {
    public String reverseWords(String s) {
        int left = 0;
        int right = s.length() - 1;

        // Step 1: Trim leading and trailing spaces
        while (left <= right && s.charAt(left) == ' ') left++;
        while (right >= left && s.charAt(right) == ' ') right--;

        StringBuilder temp = new StringBuilder();
        StringBuilder ans = new StringBuilder();

        while (left <= right) {
            char ch = s.charAt(left);

            if (ch != ' ') {
                temp.append(ch);
            } else if (temp.length() > 0) {
                // We hit a space and a word exists, so add it to the front
                if (ans.length() > 0) {
                    ans.insert(0, ' ');
                }
                ans.insert(0, temp);
                temp.setLength(0); // Clear temp
            }

            left++;
        }

        // Add the last word if it exists
        if (temp.length() > 0) {
            if (ans.length() > 0) {
                ans.insert(0, ' ');
            }
            ans.insert(0, temp);
        }

        return ans.toString();
    }
}
