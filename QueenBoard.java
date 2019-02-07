public class QueenBoard {
  private int[][] board;
  private int size;
  public QueenBoard (int size){
    this.size = size;
    board = new int[size][size];
    for (int i = 0; i < size; i++){
      for (int j = 0; i < size; j++){
        board[i][j]=0;
      }
    }
  }
  public boolean addQueen(int r, int c){
    if (board[r][c] == 0){
      board[r][c] = -1;
      for (int i = 0; i < size; i++){
        if (i != c){
          board[i][c] +=1;
        }
      }
      for (int i = 0; i < size; i++){
        if (i != r){
          board[r][i] +=1;
        }
      }
      /*
      * + + + +
      * + + + +
      * + + + +
      * + + + +
      */
      int offset = c - r;
      for (int i = (r > c ? r - c : ))
      return true;
    }
    return false;
  }
  public boolean solve(){

  }
  private boolean helper(int curCol, int numQ, boolean allSolutions){
    if (curCol < size){
      for (int i = 0; i < size; i ++;){

      }
    }
  }
  public String toString(){
    String b = "";
    for (int i = 0; i <size; i++){
      for (int j = 0; j < size; j++){
        if (board[i][j] == -1){
          b+="Q "
        }else{
          b+="_ "
        }
      }
      b+="\n";
    }
    return b;
  }
}
