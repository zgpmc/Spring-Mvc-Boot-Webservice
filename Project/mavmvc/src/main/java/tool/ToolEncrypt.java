package tool;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 创建时间:2019/02/26
 * 创建人:pmc
 * 描述:
 */
public class ToolEncrypt
{
    /**
     * md5加密
     *
     * @param string 加密字符串
     * @return string
     */
    public static String md5Encrypt(String string)
    {
        String res = "";
        try
        {
            // 生成一个MD5加密计算摘要
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            res = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e)
        {
        }
        return res;
    }
}
