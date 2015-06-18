package wuziqi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args){
//		Chessboard c = new Chessboard();
//		c.initBoard();
//		c.printBoard();
//		//获取键盘的输入
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String inputStr = null;
//		//br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
//		GoGangGame ggg = new GoGangGame();
//		try {
//			while( (inputStr = br.readLine()) != null ) {
//			/**
//			* 处理键盘输入
//			*/
//			ggg.isValid(inputStr);
//			
//			}
//		} catch (IOException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//	}
		//true为游戏结束
		GoGangGame ggg = new GoGangGame();
		boolean isOver = false;
		ggg.chessboard.initBoard();
		ggg.chessboard.printBoard();
		//获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		//br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		try {
			while( (inputStr = br.readLine()) != null ) {
				isOver = false;
				if( !ggg.isValid( inputStr ) ) {
					//如果不合法，要求重新输入，再继续
					continue;
				}
				//把对应的数组元素赋为"●"
				String chessman = Chessman.BLACK.getChessman();
				ggg.chessboard.setBoard( ggg.getposX() , ggg.getposY(), chessman);
				//判断用户是否赢了
				if( ggg.isWin()==1) {
					System.out.println("恭喜你获胜！");
					isOver = true;
				} else {   
					//计算机随机选择位置坐标
					int[] computerPosArr = ggg.computerDo();
					chessman = Chessman.WHITE.getChessman();
					ggg.chessboard.setBoard( computerPosArr[0] , computerPosArr[1] , chessman );
					//判断计算机是否赢了
					if( ggg.isWin()==-1) {
						System.out.println("计算机获胜了！");
						isOver = true;
					}
				}
				//如果产生胜者，询问用户是否继续游戏
				if( isOver ) {
					//如果继续，重新初始化棋盘，继续游戏
					try {
						if( ggg.isReplay( chessman ) ) {
							ggg.chessboard.initBoard();
							ggg.chessboard.printBoard();
							continue;
						}
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					//如果不继续，退出程序
					break;
				}
				ggg.chessboard.printBoard();
				System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}