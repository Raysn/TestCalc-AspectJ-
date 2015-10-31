//本ソースコードはAspectJ 簡易リファレンス
//( http://netail.net/aosdwiki/index.php?AspectJ%2F%B4%CA%B0%D7%A5%EA%A5%D5%A5%A1%A5%EC%A5%F3%A5%B9 )
//を引用, 若しくは参考にして作成されています。

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public aspect Log {

	CreateLogFileName c = new CreateLogFileName();
	String fileName = c.createFileName();

	//実行前・実行後
	before(): execution( void CalcUI.start() ) {
		String message = "Executing CalcUI...";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}

	after(): execution( void CalcUI.start() ) {
		String message = "Successed to execute CalcUI";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}

	//値が返された時
	after() returning( double r ): execution( double Compute.sum( double, int ) ) {
		String message =  "sum(returning: " + r + ")";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}
	after() returning( double r ): execution( double Compute.subtract( double, int ) ) {
		String message = "subtract(returning: " + r + ")";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}

	after() returning( double r ): execution( double Compute.multiple( double, int ) ) {
		String message = "multiple(returning: " + r + ")";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}

	after() returning( double r ): execution( double Compute.divide( double, int ) ) {
		String message = "divide(returning: " + r + ")";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}

	after() returning(): execution( void Compute.setZero() ) {
		String message = "Compute.result: setZero()";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}

	//ボタンが押された時
	after(): execution( void actionPerformed( ActionEvent ) ) {
		String message = "a button is pushed: \"" + CalcUI.cmd + "\"";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}

	//マウスイベントがあった時
	after(): execution( void mouseClicked( MouseEvent ) ) {
		String message = "mouse is clicked at ( " + CalcUI.mousePoint.x + ", " + CalcUI.mousePoint.y + " ).";
		System.out.println( message );
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}
	after(): execution( void mouseEntered( MouseEvent ) ) {
		String message = "mouse is entered in a frame at ( " + CalcUI.mousePoint.x + ", " + CalcUI.mousePoint.y + " ).";
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}
	after(): execution( void mouseExited( MouseEvent ) ) {
		String message = "mouse is exited out a frame at ( " + CalcUI.mousePoint.x + ", " + CalcUI.mousePoint.y + " ).";
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}
	after(): execution( void mousePressed( MouseEvent ) ) {
		String message = "mouse is pressed at ( " + CalcUI.mousePoint.x + ", " + CalcUI.mousePoint.y + " ).";
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}
	after(): execution( void mouseReleased( MouseEvent ) ) {
		String message = "mouse is released at ( " + CalcUI.mousePoint.x + ", " + CalcUI.mousePoint.y + " ).";
		try{
			PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( new File( fileName ), true ) ) );
			pw.println( message );
			pw.close();
		}catch( IOException e ) {
			System.out.println( e );
		}
	}
	
}
