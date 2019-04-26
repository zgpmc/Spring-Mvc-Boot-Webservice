package bal;

import common.ComEnum.EnumResult;
import db.DBConnection;
import mode.MDResultMsg;
import mode.MDTest;

import java.util.List;

/**
 * 创建时间:2019/02/28
 * 创建人:pmc
 * 描述:
 */
public class BalMain extends BalBaseYW
{
    DBConnection dbConnection = new DBConnection();

    public String mainList(String zddm)
    {
        MDResultMsg mdResultMsg = new MDResultMsg();
        String sql = "select bsm,bdcdyh,zl,zddm from bdcinfo.xs_h where length(zl)>15 and zddm=? and rownum<11";
        List<MDTest> testList = dbConnection.select(MDTest.class, sql, zddm);
        if (testList.size() > 0)
        {
            mdResultMsg.code = EnumResult.success;
            mdResultMsg.data = testList;
        }
        return mdResultMsg.toString();
    }
}
