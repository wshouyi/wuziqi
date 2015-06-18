package wuziqi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * ��Ϸ���� 
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
	
	public boolean isValid(String inputStr)    //�ж����������Ƿ���Ч
	{
		
		//���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt( posStrArr[0] ) - 1;
			posY = Integer.parseInt( posStrArr[1] ) - 1;				
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		//���������ֵ�Ƿ��ڷ�Χ֮��
		if( posX < 0 || posX >= Chessboard.N || posY < 0 
			|| posY >= Chessboard.N ) {
			chessboard.printBoard();
			System.out.println( "X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.N + ",���������룺" );
			return false;
		}
		//��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if( board[posX][posY] != "ʮ" ) {
			chessboard.printBoard();
			System.out.println( "��λ���Ѿ������ӣ����������룺" );
			return false;
		}

		return true;


	}
	
	public int isWin()
	{
		//ֱ������X����
		int startX = 0;
		//ֱ�����Y����
		int startY = 0;
		//ֱ�߽���X����
		int endX = Chessboard.N - 1;
		//ֱ�߽���Y����
		int endY = endX;
		//ͬ��ֱ�������������ۻ���
		int temp = 0;		
		//����������СX������Y����
		temp = posX - WIN_COUNT + 1;
		startX = temp < 0 ? 0 : temp;
		temp = posY - WIN_COUNT + 1;
		startY = temp < 0 ? 0 : temp;
		//�����յ�����X������Y����
		temp = posX + WIN_COUNT - 1;
		endX = temp > Chessboard.N - 1 ? Chessboard.N - 1 : temp;
		temp = posY + WIN_COUNT - 1;
		endY = temp > Chessboard.N - 1 ? Chessboard.N - 1 : temp;

		String[][] board = chessboard.getBoard();
		int rowBlackCount = 1,rowWhiteCount = 1;
		int colBlackCount = 1,colWhiteCount = 1;
		int xieBlackCount = 1,xieWhiteCount = 1;
		int fanBlackCount = 1,fanWhiteCount = 1;
		//�����ҷ��������ͬ�������ӵ���Ŀ
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
		//���ϵ��·��������ͬ�������ӵ���Ŀ
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
		//�ӶԽ��߷��������ͬ�������ӵ���Ŀ
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
		//�ӷ��Խ��߷��������ͬ�������ӵ���Ŀ
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
//			System.out.println(Chessman.BLACK.getChessman()+"��ʤ!");
			return 1;
		}else if(rowWhiteCount>=5||colWhiteCount>=5||xieWhiteCount>=5||fanWhiteCount>=5){
//			System.out.println(Chessman.WHITE.getChessman()+"��ʤ!");
			return -1;
		}
		return 0;

	}
	
	public int[] computerDo(){
		int[] pos = new int[2];
	//�������x���꣬����ά�������һά��ֵ
	int posX = (int)(Math.random() * ( Chessboard.N - 1 ) );
	//�������y���꣬����ά��������ά��ֵ
	int posY = (int)(Math.random() * ( Chessboard.N - 1 ) );
	String[][] board = chessboard.getBoard();
	//�������е�λ�ò��ǡ�ʮ����ʱ���Ѿ������ӣ������ٴ������µ�����
	while( board[posX][posY] != "ʮ" ) {
		posX = (int)(Math.random() * ( Chessboard.N - 1 ));
		posY = (int)(Math.random() * ( Chessboard.N - 1 ));		
	}
	pos[0]=posX;
	pos[1]=posY;
	return pos;
	}
	
	/**
	* �Ƿ����¿�ʼ���塣
	 * @param chessman "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay( String chessman ) throws Exception {
		chessboard.printBoard();
		System.out.println("����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if( br.readLine().equals("y") ) {
			//��ʼ��һ��
			return true;
		}
		return false;		
	}
	
	

}
