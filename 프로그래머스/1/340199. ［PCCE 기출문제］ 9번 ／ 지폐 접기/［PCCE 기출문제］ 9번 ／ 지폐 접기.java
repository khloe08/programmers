class Solution {
    public int solution(int[] wallet, int[] bill) {
       int answer = 0;
       
        //초기화
        if(wallet[0]<wallet[1]){swap(wallet);}
        if(bill[0]<bill[1]){swap(bill);}
   
        //지폐와 지갑비교
while(true){    
       if (wallet[0] >= bill[0] && wallet[1] >= bill[1]) {
                return answer;  // 지갑 크기가 지폐보다 크거나 같다면 종료
            
       }else{
    
         if(bill[0] > bill[1]){
               int fold = bill[0]/2;
                answer++;
                bill[0] = fold;
               if(fold < bill[1]){
                   swap(bill);
                }
              
           }else{
                int fold = bill[1]/2;
                answer++;
                bill[1] = fold;
               if(fold > bill[0]){
                   swap(bill);
                }
        
            } 
                   
           }
       }
    }
        
 public int[] swap(int[] arr){
                int a = arr[0];
                arr[0] = arr[1];
                arr[1] = a;
                return arr;        
        }
        
    
    
    
}
    
