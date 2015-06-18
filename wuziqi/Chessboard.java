package wuziqi;

public class Chessboard {
	
	private String[][] board;
	final static int N = 15;//���̴�С
	
	public void initBoard()//��ʼ��
	{
		board = new String[N][N];
		for(int i = 0;i<N;i++)
		{
			for(int j = 0;j<N;j++)
			{
				board[i][j] = "ʮ";
			}
		}
	}
	public void printBoard()//�������
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
