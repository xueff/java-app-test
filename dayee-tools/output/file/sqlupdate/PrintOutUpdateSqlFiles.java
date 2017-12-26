package output.file.sqlupdate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * dayee���ݿ��ļ������ļ����������̨
 * �� ������ʼʱ��
 * �ڴ�ӡ����    console company
 * 
 * �ۣ�����ע����� �ļ���  console ƴд���� ���
 * ����̨��ӡ��Ӧ·����sql�ļ�
 * @author cooltain
 */
public class PrintOutUpdateSqlFiles {
    //////////////////////////////////////////////////�ֶ�������ʼ//////////////////////////////////////////////////////
    // ����SQL��Ŀ�ļ���ŵ�·��    1  2 3��
    private static String LOCALE_PROJECT_PATH = "D:\\ProgStudio\\workspacerelease1116\\release1116_wintalent_other";
    private static String LOCALE_PROJECT_PATH2 = "\\sql\\patch\\8x";
    private static String LOCALE_PROJECT_PATH3 = "\\8.v1.08";
    
    //�ļ�������ʼ����
    private static int dateStart = 20171201;
    
    private static boolean printType = true;  //��ӡ���� false console    true:company
    //////////////////////////////////////////////////�ֶ����ý���//////////////////////////////////////////////////////
    
    
    
    
    private static StringBuffer fileString = new StringBuffer(); ///������ı�
    private static Map<Integer,List<String>> fileDateAndNameMap = new HashMap<Integer,List<String>>(); ///���ѹ��ˣ����ڣ��ļ���
    private static List<String> fileNameConsoleOrdered = new LinkedList<String>(); //console  ��������������h
    private static List<String> fileNameCompanyOrdered = new LinkedList<String>(); //company  �����������ļ���
    
    private static String getCompletePath(){
        return LOCALE_PROJECT_PATH +LOCALE_PROJECT_PATH2 +LOCALE_PROJECT_PATH3;
    }
    
    public static void main(String[] args) throws Exception {
        String completePath = getCompletePath();
        
        //��ȡpathName��File����  
        File dirFile = new File(completePath);  
        //�жϸ��ļ���Ŀ¼�Ƿ���ڣ�������ʱ�ڿ���̨�������  
        if (!dirFile.exists()) {  
            System.out.println("do not exit");  
            return ;  
        }  
        //�ж��������һ��Ŀ¼�����ж��ǲ���һ���ļ���ʱ�ļ�������ļ�·��  
        if (!dirFile.isDirectory()) {  
            if (dirFile.isFile()) {  
                System.out.println(dirFile.getCanonicalFile());  
            }  
            return ;  
        }  
          
        //��ȡ��Ŀ¼�µ������ļ�����Ŀ¼��  
        String[] fileList = dirFile.list(); 
        
        chooseNeedFiles(fileList);
        
        if (printType) {
            for (String filename : fileNameCompanyOrdered) {  
                //�ļ���
                File file = new File(dirFile.getPath(),filename);  
                printOutFiles(file);
            } 
        }else{
            for (String filename : fileNameConsoleOrdered) {  
                //�ļ���
                File file = new File(dirFile.getPath(),filename);  
                printOutFiles(file);
            }  
        }
        
        outPutInFile();
        
       
    }
    private static void outPutInFile() {
        byte[] buff=new byte[]{};  
        FileOutputStream out;
        try   
        {  
            buff=fileString.toString().getBytes();  
            if (printType) {
                    out = new FileOutputStream("D://conpany-sql.txt");  
            }else{
                    out = new FileOutputStream("D://console-sql.txt");  
                    //�ļ���
            }
            out.write(buff,0,buff.length);  
        }   
        catch (FileNotFoundException e)   
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
 
    }

    private static void printOutFiles(File file) {
        fileString.append("## "+file.getPath());
        System.out.println("## "+file.getPath());
        if(!file.isFile())
            return;
        InputStream in = null;  
        try {  
            // �����ļ������ļ���������  
            in = new FileInputStream(file);  
            // �����ֽ�����  
            byte[] data = new byte[1024];  
            // ��ȡ���ݣ��ŵ��ֽ���������  
            in.read(data);  
            System.out.println(new String(data));  
            fileString.append(new String(data));
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // �ر�������  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        
    }
    
    

    /**
     * ��ȡҪ���µ��ļ�(������)
     * @param dirFile
     * @param fileList
     */
	private static void chooseNeedFiles(String[] fileList) {
	    for (String fileName : fileList) {
	        outofDateFileRemove(fileName); 
        }
	    
	    Date now = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    Integer nowDateInt = Integer.valueOf(formatter.format(now));
	    
	    
	    for (int i = dateStart; i < nowDateInt; i++) {
	        if(Integer.valueOf(String.valueOf(i).substring(6,8)) > 30){
	            i += 68;
//	            System.out.println("****"+i);
	            continue;
	        }
	        if(Integer.valueOf(String.valueOf(i).substring(4, 6)) >12){
	            i = Integer.valueOf((String.valueOf(i).substring(0, 4) + "0100"))+10000;
//	            System.out.println("/////////////////"+i);
	            continue;
	        }
	        List<String> oneDayFiles = fileDateAndNameMap.get(i);
	        if(oneDayFiles != null){
	            for (String fileName : oneDayFiles) {
	                if(fileName.contains("console")){
	                    fileNameConsoleOrdered.add(fileName);
	                }else{
	                    fileNameCompanyOrdered.add(fileName);
//	                    System.out.println(fileName);
	                }
	            }
	            
	        }
	     }
	       
	    
//	   
    }
	
	private static void outofDateFileRemove(String fileName) {
	    //1.��������
	    int datafile = 0;
        try {
            datafile = Integer.valueOf(fileName.substring(0, 8));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        if(datafile <dateStart)
            return ;

        if(fileDateAndNameMap.containsKey(datafile)){
            fileDateAndNameMap.get(datafile).add(fileName);
        }else{
            List<String> filesNameList = new ArrayList<String>();
            filesNameList.add(fileName);
            fileDateAndNameMap.put(datafile, filesNameList);
        }
        
//        System.out.println("ɸѡ��file=="+fileName);
	}

}
