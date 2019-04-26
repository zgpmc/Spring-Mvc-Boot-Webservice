package tool;

import java.util.UUID;

/**
 * 创建时间:2019/02/18
 * 创建人:Administrator
 * 描述:string 工具类
 */
public class ToolString
{
    /**
     * 验证字符串是否为null或""
     *
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string)
    {
        boolean res = false;
        if (string == null || string.isEmpty() || string.trim().isEmpty())
        {
            res = true;
        }
        return res;
    }

    /**
     * 得到一个GUID
     *
     * @return guid
     */
    public static String getGUid()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
