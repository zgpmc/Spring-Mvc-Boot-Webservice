package tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * 创建时间:2018/12/19
 * 创建人:Administrator
 * 描述:
 */
public class ToolUserProperts
{
    /**
     * 根据配置文件名或完整路径读取配置文件
     *
     * @param fullFile 配置文件名
     * @return properties
     */
    public static Properties getPorperties(String fullFile) throws IOException
    {
        Properties properties = new Properties();
        if (fullFile == "" || fullFile.equals(""))
        {
            System.out.println("属性文件为空!~");
        } else
        {
            //加载属性文件
            InputStream inStream = ToolUserProperts.class.getClassLoader().getResourceAsStream(fullFile);
            try
            {
                properties.load(inStream);
            } catch (IOException e)
            {
                e.printStackTrace();
            } finally
            {
                inStream.close();
            }
        }
        return properties;
    }

    /**
     * 读取多个配置文件 返回文件名或路径为key的hashmap
     *
     * @param fullFilearr 配置文件路径数组
     * @return HashMap<String                               ,                                                               Properties>
     */
    public static HashMap<String, Properties> getPorperties(String[] fullFilearr) throws IOException
    {
        HashMap<String, Properties> propertiesHashMap = new HashMap<>();
        for (int i = 0; i < fullFilearr.length; i++)
        {
            if (!propertiesHashMap.containsKey(fullFilearr[i]))
            {
                Properties properties = getPorperties(fullFilearr[i]);
                propertiesHashMap.put(fullFilearr[i], properties);
            }
        }
        return propertiesHashMap;
    }
}
