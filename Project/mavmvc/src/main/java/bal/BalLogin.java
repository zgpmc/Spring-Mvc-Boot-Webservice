package bal;

import common.ComEnum.EnumResult;
import mode.MDResultMsg;
import mode.MDUser;
import tool.ToolEncrypt;
import tool.ToolString;

/**
 * 创建时间:2018/12/28
 * 创建人:Administrator
 * 描述:
 */
public class BalLogin extends BalBase
{
    //private DBConnection dbConnection = new DBConnection();

    public String checkLogin(String name, String passd)
    {
        MDResultMsg MDResultMsg = new MDResultMsg();
        String SQL = "select * sys_user where loginname=? and password=?";
        if (!ToolString.isNullOrEmpty(name) || !ToolString.isNullOrEmpty(passd))
        {
            passd = ToolEncrypt.md5Encrypt(passd);
            MDUser mdUser = DB.selectFirstOrNull(MDUser.class, SQL, name, passd);
            if (mdUser != null)
            {
                if (!isLoging(mdUser))
                {
                    MDResultMsg.code = EnumResult.success;
                    MDResultMsg.msg = loginMsg;
                    MDResultMsg.data = mdUser;
                } else
                {
                    MDResultMsg.code = EnumResult.defeate;
                    MDResultMsg.msg = loginMsg;
                    MDResultMsg.popout = true;
                }
            } else
            {
                MDResultMsg.code = EnumResult.defeate;
                MDResultMsg.msg = "用户名或密码错误！";
                MDResultMsg.popout = true;
            }
        } else
        {
            MDResultMsg.code = EnumResult.defeate;
            MDResultMsg.msg = "用户名或密码错误！";
            MDResultMsg.popout = true;
        }
        return MDResultMsg.toString();
    }

    public String sign(String name, String passd)
    {
        MDResultMsg MDResultMsg = new MDResultMsg();
        MDResultMsg.code = EnumResult.defeate;
        MDResultMsg.msg = "注册失败！";
        if (!ToolString.isNullOrEmpty(name) && !ToolString.isNullOrEmpty(passd))
        {
            String guid = ToolString.getGUid();
            String psd = ToolEncrypt.md5Encrypt(passd);
            String SQL = "insert into sys_user(userid,loginname,password) values(?,?,?)";
            int res = DB.insert(SQL, guid, name, passd);
            if (res > 0)
            {
                MDResultMsg.code = EnumResult.success;
                MDResultMsg.msg = "注册成功！";
                MDUser mdUser = new MDUser();
                mdUser.USERID = guid;
                mdUser.LOGINNAME = name;
                isLoging(mdUser);
            }
        }
        return MDResultMsg.toString();
    }
}
