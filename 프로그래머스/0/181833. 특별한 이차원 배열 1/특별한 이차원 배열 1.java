class Solution {
    public int[][] solution(int n) {
        
        int cnt = 0;
        int[][] result = new int[n][n];
        
        for(int i =0; i < n ; i++){
            for(int j=0; j < n; j++){
               
                if(j == cnt){ result [i][j] = 1;
                             }
                else{result[i][j]=0;}
                
                
                
            }
             cnt ++;
        }
        
        return result;
        
    }
}