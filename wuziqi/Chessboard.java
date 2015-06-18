package wuziqi;

public class Chessboard {
	
	private String[][] board;
	final static int N = 15;//棋盘大小
	
	public void initBoard()//初始化
	{
		board = new String[N][N];
		for(int i = 0;i<N;i++)
		{
			for(int j = 0;j<N;j++)
			{
				board[i][j] = "十";
			}
		}
	}
	public void printBoard()//输出棋盘
	{
		for(int i = 0;i<N;i++)
		{
			for(int j = 0;j<N;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	public void setBoard(int posX,int posY,String chessman)
	{
		board[posX][posY]=chessman;
	}
	public String[][] getBoard()
	{
		return board;
	}

}
