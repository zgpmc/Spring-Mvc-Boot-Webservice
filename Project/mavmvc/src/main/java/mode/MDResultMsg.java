package mode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.ComEnum.EnumResult;

/**
 * 创建时间:2019/02/25
 * 创建人:pmc
 * 描述:
 */
public class MDResultMsg
{
    /**
     * 返回代码
     */
    public EnumResult code;
    /**
     * 返回消息
     */
    public String msg;
    /**
     * 返回数据
     */
    public Object data;
    /**
     * 是否弹框
     */
    public Boolean popout;

    public boolean getPopout()
    {
        return popout == null ? false : popout;
    }

    public void setPopout(boolean popout)
    {
        this.popout = popout;
    }

    public EnumResult getCode()
    {
        return code;
    }

    public void setCode(EnumResult code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public MDResultMsg()
    {

    }

    public MDResultMsg(EnumResult code, String msg, Object data, boolean popout)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.popout = popout;
    }

    public MDResultMsg(EnumResult code, String msg, boolean popout)
    {
        this.code = code;
        this.msg = msg;
        this.data = null;
        this.popout = false;
    }

    public MDResultMsg(EnumResult code, String msg)
    {
        this.code = code;
        this.msg = msg;
        this.data = null;
        this.popout = false;
    }

    @Override
    public String toString()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e)
        {
            return "";
        }
    }
}
