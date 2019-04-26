package controller;

import bal.BalMain;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建时间:2019/02/28
 * 创建人:pmc
 * 描述:
 */
@Controller
@Scope("prototype")
@RequestMapping("/main")
@ResponseBody
public class CtrolMainList
{
    @RequestMapping("/list")
    public String mainList(String zddm)
    {
        BalMain balMain = new BalMain();
        return balMain.mainList(zddm);
    }
}
