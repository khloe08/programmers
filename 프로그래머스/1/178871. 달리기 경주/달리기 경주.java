import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        
     Map<String,Integer> orders = new HashMap<>();
        
        for(int i = 0; i < players.length ;i++){
            orders.put(players[i],i);
        }
                
        for(String call : callings){
            
         int curr = orders.get(call);
         String loser = players[curr-1];
       
       
          orders.put(call,curr-1);          
          orders.put(loser,curr);
            
          players[curr] = loser;
          players[curr-1] = call;
            
        }
        
       
     // 순위를 기준으로 정렬하여 최종 배열 생성
        answer = orders.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()) // 순위(value) 기준 정렬
                .map(Map.Entry::getKey) // 이름(key)만 추출
                .toArray(String[]::new);
    
        
        
        return answer;
    }
    
     

    
}