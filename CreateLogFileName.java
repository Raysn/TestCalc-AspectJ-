//本ソースコードはJavaちょこっとリファレンス
//( http://java-reference.sakuraweb.com/java_date_format.html )を引用, 
//若しくは参考にして作成されています。

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class CreateLogFileName {

	private String fileName = "";

	public CreateLogFileName() {}

	public String createFileName() {
		Calendar calendar = Calendar.getInstance();
		//フォーマットを指定
		SimpleDateFormat s = new SimpleDateFormat( "yyyyMMdd-HHmmss" );
		
		fileName = "Log_" + s.format( calendar.getTime() ) + ".log";

		return fileName;
	}

}
