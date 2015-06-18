package wuziqi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args){
//		Chessboard c = new Chessboard();
//		c.initBoard();
//		c.printBoard();
//		//��ȡ���̵�����
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String inputStr = null;
//		//br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
//		GoGangGame ggg = new GoGangGame();
//		try {
//			while( (inputStr = br.readLine()) != null ) {
//			/**
//			* �����������
//			*/
//			ggg.isValid(inputStr);
//			
//			}
//		} catch (IOException e) {
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
//	}
		//trueΪ��Ϸ����
		GoGangGame ggg = new GoGangGame();
		boolean isOver = false;
		ggg.chessboard.initBoard();
		ggg.chessboard.printBoard();
		//��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		//br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		try {
			while( (inputStr = br.readLine()) != null ) {
				isOver = false;
				if( !ggg.isValid( inputStr ) ) {
					//������Ϸ���Ҫ���������룬�ټ���
					continue;
				}
				//�Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
				String chessman = Chessman.BLACK.getChessman();
				ggg.chessboard.setBoard( ggg.getposX() , ggg.getposY(), chessman);
				//�ж��û��Ƿ�Ӯ��
				if( ggg.isWin()==1) {
					System.out.println("��ϲ���ʤ��");
					isOver = true;
				} else {   
					//��������ѡ��λ������
					int[] computerPosArr = ggg.computerDo();
					chessman = Chessman.WHITE.getChessman();
					ggg.chessboard.setBoard( computerPosArr[0] , computerPosArr[1] , chessman );
					//�жϼ�����Ƿ�Ӯ��
					if( ggg.isWin()==-1) {
						System.out.println("�������ʤ�ˣ�");
						isOver = true;
					}
				}
				//�������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
				if( isOver ) {
					//������������³�ʼ�����̣�������Ϸ
					try {
						if( ggg.isReplay( chessman ) ) {
							ggg.chessboard.initBoard();
							ggg.chessboard.printBoard();
							continue;
						}
					} catch (Exception e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					//������������˳�����
					break;
				}
				ggg.chessboard.printBoard();
				System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

	}
}