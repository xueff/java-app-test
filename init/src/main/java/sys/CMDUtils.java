package sys;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * java 执行cmd
 */
public class CMDUtils {
    private static Logger log = LoggerFactory.getLogger(CMDUtils.class);

    public static void main(String[] args) {
        execute("rm -rf "+"./"+"666");
    }
    @Test
    public void test1() throws IOException{
//直接打开应用程序--无窗口
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kD:/MTS/MTS/MTS.JobServer.V2/LogicJobs/StatisticalChargeConsumptionJob/StatisticalChargeConsumptionJob/bin/Debug/StatisticalChargeConsumptionJob.exe\"2018-12-1212:00:00\",\"2018-12-1312:00:00\"");//打开一个批处理文件
//直接打开应用程序--有窗口
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kstartD:/MTS/MTS/MTS.JobServer.V2/LogicJobs/StatisticalChargeConsumptionJob/StatisticalChargeConsumptionJob/bin/Debug/StatisticalChargeConsumptionJob.exe\"2018-12-1212:00:00\",\"2018-12-1312:00:00\"");//打开一个批处理文件

//Runtime.getRuntime().exec("D:/MTS/MTS/MTS.JobServer.V2/LogicJobs/StatisticalChargeConsumptionJob/StatisticalChargeConsumptionJob/bin/Debug/StatisticalChargeConsumptionJob.exe\"2018-12-1212:00:00\",\"2018-12-1312:00:00\"");//打开一个批处理文件
//运行jar
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe"+
//"/kstart"+
//"java-jarE:\\localRepository\\maven\\com\\xiezhu\\lanhu\\0.0.1-SNAPSHOT\\lanhu-0.0.1-SNAPSHOT.jar");//打开一个批处理文件
        Runtime.getRuntime().exec("E:\\ProgramFiles\\DingDing\\main\\current\\DingTalk.exe");//通过cmd窗口执行命令
//


///********可以通过cmd命令打开软件或者是做其他*****/
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kstartE:/酷狗/KGMusic/KuGou.exe");//通过cmd窗口执行命令
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kstartE:/php/Test/第一个html/界面.html");//通过cmd命令打开一个网页
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kmkdirC:\\Users\\liqiang\\Desktop\\java键的1");//通过cmd创建目录用两个反斜杠
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kmkdirC:\\Users\\liqiang\\Desktop\\java键的2");//通过cmd创建目录用两个反斜杠
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/ccalc");//通过cmd打开计算器
    }

    @Test
    public void test2()throws IOException{
        /********可以通过cmd命令打开软件或者是做其他*****/
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/cosk");//通过屏幕软键盘
    }



    /**
     * @Author li cong
     * @Description 更新的数据源，执行挂载命令
     * @Date 2019/11/26 4:34 下午
     */
    public static boolean execute(String command) {
        boolean result = false;
        try {
                Runtime runtime = Runtime.getRuntime();
                Long start = System.currentTimeMillis();
                Process process = runtime.exec(command);
                //等待子线程线程执行完毕再进行执行
                int i = process.waitFor();
                log.info("waitFor"+i);
                //接收执行完成后，获得的返回值
                i = process.exitValue();
                log.info("exitValue"+i);
                log.info("执行时间：" + (System.currentTimeMillis() - start));
                if (i == 0) {
                    log.info("执行完成");
                } else {
                    log.info("执行失败，需要root用户执行，返回值：" + i);
                    //log.info("---process.getInputStream()---");
                    //readProcessInfo(process.getInputStream(),System.out);
                    log.info("---process.getErrorStream()---");
                    readProcessInfo(process.getErrorStream(), System.out);
                    readProcessInfo(process.getInputStream(), System.out);
                }
                process.destroy();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return result;
    }

    /**
     * @Author li cong
     * @Description
     * @Date 2019/11/26 4:41 下午
     */
    private static void readProcessInfo(InputStream inputStream, PrintStream out) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String lines = "";
            while ((line = reader.readLine()) != null) {
                out.println(line);
                lines += line;
            }
            log.error("readProcessInfo "+lines);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String readProcessInfo(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
