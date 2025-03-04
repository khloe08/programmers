import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        
         PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
         int order =0;
        
        for(int i : priorities){
            pq.add(i);
          }
        
        while(!pq.isEmpty()){
        for(int a=0; a < priorities.length; a++){
            if(priorities[a] == pq.peek()){
                pq.poll();
                order++;
                if(a == location)
                 return order;
            }
            
        }
    }
    
            return order;
   }
}
