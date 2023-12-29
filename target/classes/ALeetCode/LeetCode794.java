package ALeetCode;

/**
 * @author Xingjian LONG <longxingjian@kuaishou.com>
 * Created on 2021-12-09
 */
public class LeetCode794 {
    public boolean validTicTacToe(String[] board) {
        //X 与 O 的个数相等
        //X 比 O 多一个
        // 赢的状态只能有一个
        int xCnt = 0;
        int oCnt = 0;
        for(String row : board){
            for(int i = 0; i<3; i++){
                if(row.charAt(i) == 'X'){
                    xCnt++;
                }
                else if(row.charAt(i) == 'O'){
                    oCnt++;
                }
            }
        }
        if(xCnt == oCnt || xCnt == oCnt+1){
            boolean end = false;
            //三横
            for(int i = 0; i < 3; i++){
                if(board[i].charAt(0)!= ' ' && board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)){
                    if(end){
                        return false;
                    }else{
                        end = true;
                    }
                }
            }

            //三竖
            for(int i = 0; i < 3; i++){
                if(board[0].charAt(i)!= ' ' && board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)){
                    if(end){
                        return false;
                    }else{
                        end = true;
                    }
                }
            }

            //两对角
            if(board[0].charAt(0)!= ' ' && board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
                if(end){
                    return false;
                }else{
                    end = true;
                }
            }
            if(board[0].charAt(2) != ' ' && board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
                if(end){
                    return false;
                }else{
                    end = true;
                }
            }

            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        LeetCode794 leetCode794 = new LeetCode794();
        leetCode794.validTicTacToe(new String[]{"XXX","OOX","OOX"});
    }
}
