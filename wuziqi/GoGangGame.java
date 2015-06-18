package wuziqi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 游戏规则 
*/
public class GoGangGame {
	private int posX,posY;
	private final static int WIN_COUNT=5;
	Chessboard chessboard = new Chessboard();
	
	public int getposX()
	{
		return posX;
	}
	public int getposY()
	{
		return posY;
	}
	
	public boolean isValid(String inputStr)    //判断所下棋子是否有效
	{
		
		//将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt( posStrArr[0] ) - 1;
			posY = Integer.parseInt( posStrArr[1] ) - 1;				
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		//检查输入数值是否在范围之内
		if( posX < 0 || posX >= Chessboard.N || posY < 0 
			|| posY >= Chessboard.N ) {
			chessboard.printBoard();
			System.out.println( "X与Y坐标只能大于等于1,与小于等于" + Chessboard.N + ",请重新输入：" );
			return false;
		}
		//检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if( board[posX][posY] != "十" ) {
			chessboard.printBoard();
			System.out.println( "此位置已经有棋子，请重新输入：" );
			return false;
		}

		return true;


	}
	
	public int isWin()
	{
		//直线起点的X坐标
		int startX = 0;
		//直线起点Y坐标
		int startY = 0;
		//直线结束X坐标
		int endX = Chessboard.N - 1;
		//直线结束Y坐标
		int endY = endX;
		//同条直线上相邻棋子累积数
		int temp = 0;		
		//计算起点的最小X坐标与Y坐标
		temp = posX - WIN_COUNT + 1;
		startX = temp < 0 ? 0 : temp;
		temp = posY - WIN_COUNT + 1;
		startY = temp < 0 ? 0 : temp;
		//计算终点的最大X坐标与Y坐标
		temp = posX + WIN_COUNT - 1;
		endX = temp > Chessboard.N - 1 ? Chessboard.N - 1 : temp;
		temp = posY + WIN_COUNT - 1;
		endY = temp > Chessboard.N - 1 ? Chessboard.N - 1 : temp;

		String[][] board = chessboard.getBoard();
		int rowBlackCount = 1,rowWhiteCount = 1;
		int colBlackCount = 1,colWhiteCount = 1;
		int xieBlackCount = 1,xieWhiteCount = 1;
		int fanBlackCount = 1,fanWhiteCount = 1;
		//从左到右方向计算相同相邻棋子的数目
		for(int i = startY; i < endY; i++)
		{
			if(board[posX][i] == Chessman.BLACK.getChessman())
			{
				if(board[posX][i+1] == Chessman.BLACK.getChessman())
				{
					rowBlackCount++;
				}else if(rowBlackCount<WIN_COUNT){
					rowBlackCount = 1;
				}
			}else if(board[posX][i] == Chessman.WHITE.getChessman()){
				if(board[posX][i+1] == Chessman.WHITE.getChessman())
				{
					rowWhiteCount++;
				}else if(rowWhiteCount<WIN_COUNT){
					rowWhiteCount = 1;
				}
			}
		}
		//从上到下方向计算相同相邻棋子的数目
		for(int i = startX; i < endX; i++)
		{
			if(board[i][posY] == Chessman.BLACK.getChessman())
			{
				if(board[i+1][posY] == Chessman.BLACK.getChessman())
				{
					colBlackCount++;
				}else if(colBlackCount<WIN_COUNT){
					colBlackCount = 1;
				}
			}else if(board[i][posY] == Chessman.WHITE.getChessman()){
				if(board[i+1][posY] == Chessman.WHITE.getChessman())
				{
					colWhiteCount++;
				}else if(colWhiteCount<WIN_COUNT){
					colWhiteCount = 1;
				}
			}
		}
		//从对角线方向计算相同相邻棋子的数目
		int distance = (posX - startX)>(posY - startY)?(posY - startY):(posX - startX);
		int distance1 = (endX - posX)>(endY - posY)?(endY - posY):(endX - posX);
		for(int i = posX-distance,j = posY-distance;j < posY+distance1||i < posX+distance1; j++,i++)
		{
			if(board[i][j] == Chessman.BLACK.getChessman())
			{
				if(board[i+1][j+1] == Chessman.BLACK.getChessman())
				{
					xieBlackCount++;
				}else if(xieBlackCount<WIN_COUNT){
					xieBlackCount = 1;
				}
			}else if(board[i][j] == Chessman.WHITE.getChessman()){
				if(board[i+1][j+1] == Chessman.WHITE.getChessman())
				{
					xieWhiteCount++;
				}else if(xieWhiteCount<WIN_COUNT){
					xieWhiteCount = 1;
				}
			}
		}
		//从反对角线方向计算相同相邻棋子的数目
		int distance2 = (endX - posX)>(posY - startY)?(posY - startY):(endX - posX);
		int distance3 = (posX - startX)>(endY - posY)?(endY - posY):(posX - startX);
		for(int i = posX+distance2,j = posY-distance2;j < posY+distance3||i < posX-distance3; j++,i--)
		{
			if(board[i][j] == Chessman.BLACK.getChessman())
			{
				if(board[i-1][j+1] == Chessman.BLACK.getChessman())
				{
					fanBlackCount++;
				}else if(fanBlackCount<WIN_COUNT){
					fanBlackCount = 1;
				}
			}else if(board[i][j] == Chessman.WHITE.getChessman()){
				if(board[i-1][j+1] == Chessman.WHITE.getChessman())
				{
					fanWhiteCount++;
				}else if(fanWhiteCount<WIN_COUNT){
					fanWhiteCount = 1;
				}
			}
		}
		
		if(rowBlackCount>=5||colBlackCount>=5||xieBlackCount>=5||fanBlackCount>=5)
		{
//			System.out.println(Chessman.BLACK.getChessman()+"获胜!");
			return 1;
		}else if(rowWhiteCount>=5||colWhiteCount>=5||xieWhiteCount>=5||fanWhiteCount>=5){
//			System.out.println(Chessman.WHITE.getChessman()+"获胜!");
			return -1;
		}
		return 0;

	}
	
	public int[] computerDo(){
		int[] pos = new int[2];
	//随机生成x坐标，即二维数组具体一维的值
	int posX = (int)(Math.random() * ( Chessboard.N - 1 ) );
	//随机生成y坐标，即二维数组具体二维的值
	int posY = (int)(Math.random() * ( Chessboard.N - 1 ) );
	String[][] board = chessboard.getBoard();
	//当棋盘中的位置不是“十”的时候（已经有棋子），则再次生成新的坐标
	while( board[posX][posY] != "十" ) {
		posX = (int)(Math.random() * ( Chessboard.N - 1 ));
		posY = (int)(Math.random() * ( Chessboard.N - 1 ));		
	}
	pos[0]=posX;
	pos[1]=posY;
	return pos;
	}
	
	/**
	* 是否重新开始下棋。
	 * @param chessman "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay( String chessman ) throws Exception {
		chessboard.printBoard();
		System.out.println("再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if( br.readLine().equals("y") ) {
			//开始新一局
			return true;
		}
		return false;		
	}
	
	

}
