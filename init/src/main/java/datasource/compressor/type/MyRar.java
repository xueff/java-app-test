///*
// * 文件名：		MyRar.java
// * 创建日期：	2013-7-22
// * 最近修改：	2013-7-22
// * 作者：		徐犇
// */
//package datasource.compressor.type;
//
//import datasource.compressor.abs.Archiver;
//import javassist.bytecode.CodeIterator;
//
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.io.*;
//
///**
// * @author ben
// *
// */
//public final class MyRar extends Archiver {
//
//	private static FileNameExtensionFilter filter = new FileNameExtensionFilter(
//			"RAR压缩文件(*.rar)", "rar");
//
//	@Override
//	public final void doArchiver(File[] files, String destpath)
//			throws IOException {
//	}
//
//	public final String crackRar(File srcfile, CodeIterator ci) throws Exception {
//		boolean ret = false;
//		// 系统安装winrar的路径
//		String cmd = "rar.exe";
////		String cmd = "C:\\Program Files\\WinRAR\\Rar.exe";
//		String target = srcfile.getAbsolutePath();
//		String pass = ci.nextCode();
//
//		while (!ret && pass != null) {
//			String unrarCmd = String.format("%s t -p%s %s", cmd, pass, target);
//			Runtime rt = Runtime.getRuntime();
//			Process pre = rt.exec(unrarCmd);
//			InputStreamReader isr = new InputStreamReader(pre.getInputStream(),
//					"gbk");
//			BufferedReader bf = new BufferedReader(isr);
//			String line = null;
//			while ((line = bf.readLine()) != null) {
////				System.out.println(line);
//				if (line.indexOf("全部成功") >= 0) {
//					ret = true;
//					break;
//				}
//			}
//			bf.close();
//			if (!ret) {
//				pass = ci.nextCode();
//			}
//		}
//		if (ret) {
//			return pass;
//		} else {
//			return null;
//		}
//	}
//
//	@Override
//	public final void doUnArchiver(File srcfile, String destpath,
//			String password) throws IOException {
//		try {
//			Archive a = new Archive(srcfile, password, false);
//			FileHeader fh;
//			while ((fh = a.nextFileHeader()) != null) {
//				File f = new File(destpath + "/"
//						+ fh.getFileNameString().trim());
//
//				if (fh.isDirectory()) {
//					f.mkdirs();
//					continue;
//				}
//
//				/*
//				 * 父目录不存在则创建
//				 */
//				File parent = f.getParentFile();
//				if (!parent.exists()) {
//					parent.mkdirs();
//				}
//
//				FileOutputStream fos = new FileOutputStream(f);
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
//
//				a.extractFile(fh, bos);
//
//				bos.flush();
//				bos.close();
//			}
//			a.close();
//		} catch (RarException e) {
//			throw new WrongPassException();
//		}
//	}
//
//	@Override
//	public final FileNameExtensionFilter getFileFilter() {
//		return filter;
//	}
//
//	/**
//	 * 是否已准备好(暴力破解rar密码)
//	 */
//	public static boolean isReady() {
//		try {
//			Runtime.getRuntime().exec("rar.exe");
//		} catch (IOException e) {
//			return false;
//		}
//		return true;
//	}
//}
