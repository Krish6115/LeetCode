class Solution {
    public String lexicographicallySmallestString(String s) {
        int n = s.length();
        StringBuilder[][] dp = new StringBuilder[n][n];

        return findSmallestString(0, n-1, s, dp).toString();
    }

    private StringBuilder findSmallestString(int l, int r, String s, StringBuilder[][] dp) {
        if (l == r){
            new StringBuilder().append(s.charAt(l));
        }
        if (l > r){
            return new StringBuilder();
        }
        if (dp[l][r] != null){
            return dp[l][r];
        }
        StringBuilder result = new StringBuilder().append(s.charAt(l));
        result.append(findSmallestString(l+1, r, s, dp));
        char ch1 = s.charAt(l);
        for(int j=l+1; j<= r; j++){
            char ch2 = s.charAt(j);
            boolean condition = ((ch1-'a')-(ch2-'a')+26)%26 == 1 || ((ch1-'a')-(ch2-'a')+26)%26 == 25;
            if (condition && findSmallestString(l+1, j-1, s, dp).length() == 0){
                StringBuilder temp = findSmallestString(j+1, r, s, dp);
                if (temp.compareTo(result) < 0){
                    result = temp;
                }
            }
        }
        return dp[l][r] = result;
    }
}
