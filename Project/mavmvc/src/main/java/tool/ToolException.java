package tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 创建时间:2019/02/21
 * 创建人:Administrator
 * 描述:
 */
public class ToolException
{
    public void exceptionToCotrol(Exception e)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        String exception = baos.toString();
        System.out.println("exception:" + exception);
        try
        {
            baos.close();
        } catch (IOException e1)
        {
        }
    }
}
