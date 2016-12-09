/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package block1;

/**
 *
 * @author Humanoide
 */
public class PrePost {
    int[][] Pre;
    int [][] Post;
    
    public void PrePost(int[][] matrix){
        Pre=new int[matrix.length][matrix[0].length];
        
        for(int i=0;i<matrix.length;i++){
             for(int j=0;j<matrix[0].length;j++){
                 if(matrix[i][j]<0){
                     Pre[i][j]=matrix[i][j];
                     Post[i][j]=0;
                 }
                 if(matrix[i][j]>0){
                     Post[i][j]=matrix[i][j];
                     Pre[i][j]=0;
                 }
                 if(matrix[i][j]==0){
                     Post[i][j]=0;
                     Pre[i][j]=0;
                 }
            }
        }
    }

    
}
