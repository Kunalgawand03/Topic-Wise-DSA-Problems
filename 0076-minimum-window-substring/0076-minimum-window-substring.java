class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for(char ch: t.toCharArray()){
            need.put(ch, need.getOrDefault(ch,0)+1);
        }

        int left = 0;
        int formed = 0;
        int required = need.size();
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        for(int right = 0; right < s.length(); right++){
            char ch = s.charAt(right);
            window.put(ch, window.getOrDefault(ch, 0)+1);

            if(need.containsKey(ch) && window.get(ch).equals(need.get(ch))){
                formed++;
            }

            while(formed == required){
                if(right - left + 1  < minLen){
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar)-1);

                if(need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)){
                    formed--;
                }                

                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);

    }
}