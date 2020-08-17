/*
 * 文件名：		MyBZip2.java
 * 创建日期：	2013-7-24
 * 最近修改：	2013-7-24
 * 作者：		徐犇
 */
package datasource.compressor.type;

import datasource.compressor.CompressorPackage;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * @author Administrator
 *
 */
public final class MyBZip2 extends CompressorPackage {

	private static FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"BZIP2压缩文件(*.bz2)", "bz2");
	
	@Override
	public final void doCompress(File file, String destpath) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		File gf = new File(destpath);
		FileOutputStream fos = new FileOutputStream(gf);
		BZip2CompressorOutputStream gzos = new BZip2CompressorOutputStream(fos);
		BufferedOutputStream bos = new BufferedOutputStream(gzos);
		readAndWrite(bis, bos);
	}

	@Override
	public final void doUnCompress(File srcFile, String destpath) throws IOException {
		FileInputStream fis = new FileInputStream(srcFile);
		BZip2CompressorInputStream gzis = new BZip2CompressorInputStream(fis);
		BufferedInputStream bis = new BufferedInputStream(gzis);

		File tf = new File(destpath);
		FileOutputStream fos = new FileOutputStream(tf);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		readAndWrite(bis, bos);
	}

	@Override
	public final FileNameExtensionFilter getFileFilter() {
		return filter;
	}
}
