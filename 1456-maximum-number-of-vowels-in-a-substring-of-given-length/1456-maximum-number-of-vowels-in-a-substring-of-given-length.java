class Solution {
    public int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a' , 'e' , 'i' , 'o' , 'u');
        int cnt = 0;

        for(int i = 0; i < k ; i++){
            if(vowels.contains(s.charAt(i))){
                cnt++;
            }
        }
        
        int maxLen = cnt;

        for(int right = k; right < s.length(); right++){
            if(vowels.contains(s.charAt(right))){
                cnt++;
            }
            if(vowels.contains(s.charAt(right-k))){
                cnt--;
            }
            maxLen = Math.max(maxLen, cnt);
        }
        return maxLen;
    }
}