public int maxSumSubarray(int[] arr, int k) {
    int n = arr.length;
    int windowSum = 0;
    // first window
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    int maxSum = windowSum;
    for (int i = k; i < n; i++) {
        windowSum += arr[i];       // add next
        windowSum -= arr[i - k];   // remove previous
        maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
}
