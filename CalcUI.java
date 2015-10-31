//本ソースコードはJavaDrive( http://www.javadrive.jp/ )内のソースコードを引用,
//若しくは参考にして作成されています。

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;

public class CalcUI extends JFrame implements ActionListener, MouseListener {

	static int WINDOW_COUNTER = 0;
	static String WINDOW_TITLE = "簡易電卓";
	static final int WINDOW_WIDTH = 228;
	static final int WINDOW_HEIGHT = 320;
	static double result = 0;
	int n = -1;

	JFrame frame;
	JPanel resultField; //計算結果表示用のパネル
	JPanel buttonField; //ボタン置くところ
	JLabel resultLabel; //計算結果を表示するラベル
	static Point mousePoint; //マウス座標
	//ボタン
	JButton button0;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton buttonDot;
	JButton buttonPlus;
	JButton buttonMinus;
	JButton buttonCross;
	JButton buttonDiv;
	JButton buttonAllClear;
	JButton buttonEqual;

	//前に押されたのが数字(operand)か記号(operator)か
	String previousKey = "none";

	//キー操作を記録しておく変数
	static String cmd = "";

	//計算させるクラス
	Compute com = new Compute();

	public void start() {
		//フレームの作成
		frame = new JFrame( WINDOW_TITLE );
		frame.setFont( new Font( "Arial", Font.PLAIN, 13 ) );
		frame.setBounds( 10, 10, WINDOW_WIDTH, WINDOW_HEIGHT );
		frame.setResizable( false );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		frame.addMouseListener( this );

		//計算結果表示用のパネルを作成
		resultField = new JPanel();
		resultField.setBackground( Color.WHITE );
		resultField.setPreferredSize( new Dimension( WINDOW_WIDTH - 8, 60 ) );

		//ボタン置くパネルを作成
		buttonField = new JPanel();
		buttonField.setBackground( Color.ORANGE ); //デバッグ用にオレンジに着色
		buttonField.setOpaque( false );
		buttonField.setLayout( null );
		buttonField.setBounds( 20, 116, WINDOW_WIDTH - 40, 50 );

		//計算結果表示用のラベルを作成
		resultLabel = new JLabel();
		resultLabel.setFont( new Font( "Arial", Font.PLAIN, 28 ) );
		resultLabel.setPreferredSize( new Dimension( WINDOW_WIDTH - 8, 50 ) );
		resultLabel.setHorizontalAlignment( JLabel.RIGHT );
		resultLabel.setVerticalAlignment( JLabel.BOTTOM );

		//計算結果表示周りを組み合わせる
		resultField.add( resultLabel );
		frame.add( resultField, BorderLayout.NORTH );
		frame.add( buttonField );

		//各種ボタンを配置
		button7 = makeCalcButton( button7, "7", 15, 20, 1, 11 );
		buttonField.add( button7 );
		button7.addActionListener( this );
		button8 = makeCalcButton( button8, "8", 67, 20, 1, 11 );
		buttonField.add( button8 );
		button8.addActionListener( this );
		button9 = makeCalcButton( button9, "9", 119, 20, 1, 11 );
		buttonField.add( button9 );
		button9.addActionListener( this );
		buttonDiv = makeCalcButton( buttonDiv, "÷", 171, 20, 1, 10 );
		buttonField.add( buttonDiv );
		buttonDiv.addActionListener( this );

		button4 = makeCalcButton( button4, "4", 15, 62, 1, 11 );
		buttonField.add( button4 );
		button4.addActionListener( this );
		button5 = makeCalcButton( button5, "5", 67, 62, 1, 11 );
		buttonField.add( button5 );
		button5.addActionListener( this );
		button6 = makeCalcButton( button6, "6", 119, 62, 1, 11 );
		buttonField.add( button6 );
		button6.addActionListener( this );
		buttonCross = makeCalcButton( buttonCross, "×", 171, 62, 1, 10 );
		buttonField.add( buttonCross );
		buttonCross.addActionListener( this );

		button1 = makeCalcButton( button1, "1", 15, 104, 1, 11 );
		buttonField.add( button1 );
		button1.addActionListener( this );
		button2 = makeCalcButton( button2, "2", 67, 104, 1, 11 );
		buttonField.add( button2 );
		button2.addActionListener( this );
		button3 = makeCalcButton( button3, "3", 119, 104, 1, 11 );
		buttonField.add( button3 );
		button3.addActionListener( this );
		buttonMinus = makeCalcButton( buttonMinus, "-", 171, 104, 1, 10 );
		buttonField.add( buttonMinus );
		buttonMinus.addActionListener( this );

		button0 = makeCalcButton( button0, "0", 15, 146, 1, 11 );
		buttonField.add( button0 );
		button0.addActionListener( this );
		//buttonDot = makeCalcButton( buttonDot, ".", 67, 146, 1, 11 );
		//buttonField.add( buttonDot );
		//buttonDot.setEnabled( false );
		//buttonDot.addActionListener( this );
		//buttonEqual = makeCalcButton( buttonEqual, "=", 119, 146, 1, 10 );
		buttonEqual = makeCalcButton( buttonEqual, "=", 67, 146, 2, 10 );
		buttonField.add( buttonEqual );
		buttonEqual.addActionListener( this );
		buttonPlus = makeCalcButton( buttonPlus, "+", 171, 146, 1, 10 );
		buttonField.add( buttonPlus );
		buttonPlus.addActionListener( this );

		buttonAllClear = makeCalcButton( buttonAllClear, "AC", 15, 188, 2, 11 );
		buttonField.add( buttonAllClear );
		buttonAllClear.addActionListener( this );

		try {
			//UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
			UIManager.setLookAndFeel( "com.sun.java.swing.plaf.motif.MotifLookAndFeel" );
			SwingUtilities.updateComponentTreeUI( this );
		} catch( Exception e ) {
			e.printStackTrace();
		}

		frame.setVisible( true );
	}

	//ボタンを作る処理をまとめたメソッド
	public JButton makeCalcButton( JButton button, String buttonName, int x, int y, int buttonSize, int fontSize ) {
		button = new JButton( buttonName );
		button.setFont( new Font( "Arial", Font.PLAIN, fontSize ) );
		//button.setPreferredSize( new Dimension( 10, 10 ) );
		button.setLayout( null );
		button.setBounds( x, y, 42 * buttonSize + 10 * (buttonSize - 1), 32 );
		return button;
	}

	String previousCmd = "";
	public void actionPerformed( ActionEvent e ) {
		
		cmd = e.getActionCommand();
		/*
		 *  result = 0で初期化しておいて, 
		 *  数字ボタンが押される→resultに格納
		 *  →計算符号が押される→各メソッドに渡して
		 *  計算結果を返す
		 */

		//正規表現で数字を数字かどうかを検知する
		Pattern pt = Pattern.compile( "[0-9]" );
		Matcher mt = pt.matcher( cmd );

		if( mt.find() ) { //数字だった場合
			if( previousKey == "operand" ) {
				n = n * 10 + Integer.parseInt( cmd );
			} else {
				n = Integer.parseInt( cmd );
			}

			//次のボタンイベントのために前に押されたボタンの種別を記録
			previousKey = "operand";
			
			resultLabel.setText( Integer.toString( n ) );
		} else { //符号だった場合
			//次のボタンイベントのために前に押されたボタンの種別を記録
			previousKey = "operator";

			if( cmd == "+" ) {
				result = com.sum( result, n );
				previousCmd = cmd;
			}
			else if( cmd == "-" ) {
				result = com.subtract( result, n );
				previousCmd = cmd;
			}
			else if( cmd == "×" ) {
				result = com.multiple( result, n );
				previousCmd = cmd;
			}
			else if( cmd == "÷" ) {
				result = com.divide( result, n );
				previousCmd = cmd;
			}
			else if( cmd == "=" ) {
				if( previousCmd == "+" ) {
					result = com.sum( result, n );
				} else if( previousCmd == "-" ) {
					result = com.subtract( result, n );
				} else if( previousCmd == "×" ) {
					result = com.multiple( result, n );
				} else if( previousCmd == "÷" ) {
					result = com.divide( result, n );
				}

				resultLabel.setText( Double.toString( result ) );

				allClear( "setZero" );
			}
			else if( cmd == "AC" ) {
				allClear( "all" );
			}
			else {
				resultLabel.setText( "Unexpected Error!" );
			}
		}
	}

	public void allClear( String option ) {
		//option:
		//all: 全部やる
		//setZero: 最後の行以外やる
		if( option == "all" || option == "setZero" ) {
			result = 0;
			com.setZero();
			if( option == "all" ) {
				resultLabel.setText( "0" );
			}
		}
	}

	//マウスイベント
	public void mouseClicked( MouseEvent e ) {
		mousePoint = e.getPoint();
	}
	public void mouseEntered( MouseEvent e ) {
		mousePoint = e.getPoint();
	}
	public void mouseExited( MouseEvent e ) {
		mousePoint = e.getPoint();
	}
	public void mousePressed( MouseEvent e ) {
		mousePoint = e.getPoint();
	}
	public void mouseReleased( MouseEvent e ) {
		mousePoint = e.getPoint();
	}

	public static void main( String args[] ) {
		CalcUI cal = new CalcUI();
		cal.start();
	}

}
