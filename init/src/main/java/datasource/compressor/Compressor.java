/*
 * 文件名：		Compressor.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 压缩解压的基类
 * @author ben
 *
 */
public abstract class Compressor {
	
	/**
	 * 压缩文件抽象方法，子类实现具体功能
	 * @param file 需要进行压缩的文件
	 * @param destpath 目标文件的文件名
	 * @throws IOException
	 */
	public abstract void doCompress(File file, String destpath) throws IOException;
	
	/**
	 * 解压缩文件抽象方法，子类实现具体功能
	 * @param srcFile 需要进行解压的文件
	 * @param destpath 目标文件的文件名
	 * @throws IOException
	 */
	public abstract void doUnCompress(File srcFile, String destpath) throws IOException;
	
	/**
	 * 从bis读取数据并写入bos中
	 * @param bis
	 * @param bos
	 * @throws IOException
	 */
	public void readAndWrite(BufferedInputStream bis, BufferedOutputStream bos) throws IOException {
		byte[] buf = new byte[1024];
		int len;
		while((len = bis.read(buf)) > 0) {
			bos.write(buf, 0, len);
		}
		bos.flush();
		bos.close();
		bis.close();
	}

	/**
	 * @return 本归档类对应文件的文件过滤器
	 */
	public abstract FileNameExtensionFilter getFileFilter();
}
