class CustomStack {

private int[] stack;
private int[] inc;
private int top;
    public CustomStack(int maxSize) {
        stack = new int[maxSize];
        inc = new int[maxSize];
        top = -1;
    }
    
    public void push(int x) {
        if(top + 1 < stack.length){
            top++;
            stack[top] = x;
        }
    }
    
    public int pop() {
        if (top < 0) {
            return -1;
        }
        
        int result = stack[top] + inc[top];
        
        if (top > 0) {
            inc[top - 1] += inc[top];
        }
        
        inc[top] = 0;
        top--;
        return result;
    }
    
    public void increment(int k, int val) {
        int idx = Math.min(top, k - 1);
        if (idx >= 0) {
            inc[idx] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */