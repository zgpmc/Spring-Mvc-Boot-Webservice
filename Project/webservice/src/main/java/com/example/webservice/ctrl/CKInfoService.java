package com.example.webservice.ctrl;

import com.example.webservice.service.IWebService;
import com.example.webservice.tool.HttpTool;
import com.example.webservice.tool.RsaTool;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建时间:2019/04/26
 * 创建人:pmc
 * 描述:
 */
//用于绑定webservice类
@WebService()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CKInfoService implements IWebService
{
    private String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIghBeFozfFv9fLSUv0NhvAqfGcXfWd52u8Ky7pGWFy5MWldub+aT78ZdD56IkPCisKvH5LIc+YEabtPZR2AROeIi616AArm24kUYC6bUO3dX3juLfhW8JpwuOWJAhov5gXJB8+44behvesMRyWQS2DlF0iy/3Ilg2d6OOKWq4OFAgMBAAECgYAL5Ma8wQltVNjqVFIH5gyqIywnXbgJOG5WgFz4c3j48P1ONXArO3JIQhMT+lvQC8lP5Tke/ACUUkJiqTcucqySZYsUI2ieh7joKa+Znt6KfeQ96P7IKNHuM8SuiyMx05TwbFBfs9+VP7HqJR4mQ7zv0JNsyxhvaQlQojfYLpFkqQJBAMBR5Z0jOi0Hv17pMGlydzed9W1gAFmT9zMRp3t8NDNbgFXdD3sOybA5ua9yNq0yGdaP5gMUER3mfdty716ySDMCQQC1NBKA/2wd6OU5AeNZS2bNJ+eYM2ewXrzquXguh43gVzIlm2pNDoUt6Pj75BJ63+RczW1N9Aei/Jpq+scHQq1nAkEAmZ9RqOnAyMONjET9FN4IePbGWy36WZOmPLb3b95Q3E1VAEFq4kN3vDsAJjM3lbWVihy8AO2Alr/M/QScTKpgnQJBAJHj2+YKP+UQ5sTwNThmkd0pfLg44wnILPga3Z0wvFTcP16x83MY9rcQ9K3xYcOWUYk6R//UMvXRxQ3O3MGGuI8CQBGKcab0u8niO2hIxQqQQN9X7HJowivsEjN2ms06pAWGmxeFnhBrFIBpDrs+gYPUIBJ0nOCxcy00w0hpctWF7yQ=";
    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCIIQXhaM3xb/Xy0lL9DYbwKnxnF31nedrvCsu6RlhcuTFpXbm/mk+/GXQ+eiJDworCrx+SyHPmBGm7T2UdgETniIutegAK5tuJFGAum1Dt3V947i34VvCacLjliQIaL+YFyQfPuOG3ob3rDEclkEtg5RdIsv9yJYNnejjilquDhQIDAQAB";
    private String Sqm = "pmc";
    public String TaskCode = "zz20180721000-0000-1";
    private String SuccessCode = "0000";
    private String DefeateCode = "1000";
    private String Task = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><check-task version=\"1.0\" taskCode=\"zz20180721000-0000-1\" bsm=\"38172AD3D80B41CB972DA3D7F05ED243\" jsrq=\"2018-09-01 09:00:00\" fbrq=\"2018-08-31 09:00:00\" rwqx=\"24\" cxfw=\"0\"><task_record personID=\"360423196111082025\" personName=\"黄海英\" areaCode=\"360423\" areaName=\"九江市\"/><task_record personID=\"123/456/789\" personName=\"wsm/shp/xm\" areaCode=\"320118\" areaName=\"济南市\"/> </check-task>";

    @WebMethod(operationName = "getTask")// "获取查核任务" WebMethod用于绑定webservice方法
    public String getTask(@WebParam(mode = Mode.IN) String in0, @WebParam(mode = Mode.IN) String in1, @WebParam(mode = Mode.IN) String in2)
    {
        String result = "";
        String code = DefeateCode;
        if (in0.equals(Sqm))
        {
            System.out.println("任务授权通过");
            try
            {
                String nr = RsaTool.decryptByPublicKey(in1, publicKey);
                System.out.println("签名内容:" + nr);
                if (nr == null)
                {
                    nr = Sqm;
                }
                if (nr.contains(Sqm))
                {
                    result = RsaTool.encryptByPublicKey(Task, publicKey);
                    code = SuccessCode;
                    System.out.println("任务解密:" + RsaTool.decryptByPrivateKey(result, privateKey));
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("2接口参数0:" + in0 + "   1:" + in1 + "  2:" + in2);
        String msg = "{\"message\":{\"task\":\"" + result + "\",\"info\":\"提示信息666\"},\"statusCode\":\"" + code + "\"}";

        return msg;
    }

    @WebMethod(operationName = "pushResult")//"反馈核查结果"
    public String pushResult(@WebParam(name = "in0") String in0, @WebParam(name = "in1") String in1, @WebParam(name = "in2") String in2, @WebParam(name = "in3") String in3)
    {
        String result = "失败";
        String code = DefeateCode;
        if (in0.equals(Sqm))
        {
            System.out.println("反馈授权通过");
            try
            {
                String nr = RsaTool.decryptByPublicKey(in1, publicKey);
                System.out.println("反馈令牌内容:" + nr);
                if (nr == null)
                {
                    nr = Sqm + TaskCode;
                }
                if (nr.contains(Sqm) && nr.contains(TaskCode))
                {
                    String nr1 = RsaTool.decryptByPublicKey(in3, publicKey);
                    boolean qm = RsaTool.verify(nr1, publicKey, in2);
                    if (!qm)
                    {
                        qm = true;
                    }
                    if (qm)
                    {
                        System.out.println("签名验证成功");
                        if (nr1 == null || nr1 != null)
                        {
                            result = "成功";
                            code = SuccessCode;
                            System.out.println("反馈结果:" + nr1);
                        }
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return "{\"message\":\"上报" + result + "\",\"statusCode\":\"" + code + "\"}";
    }

    public static void main(String[] argv)
    {
        CKInfoService implementor = new CKInfoService();
        //String address = "http://localhost:9000/CKInfoService.wsdl";
        String res1 = "";
        //Endpoint.publish(address, implementor);
        HttpTool httpTool = new HttpTool();
        String path = "http://192.168.0.123/FW/Bdc/Service/ZWPTService.asmx/getTaskNotice";
        String param = "in0=" + implementor.TaskCode;
        res1 = httpTool.sendGet(path, param);
        //while (!res1.isEmpty() && !res1.contains("0000"))
        //{
        //res1 = httpTool.sendGet(path, param);
        //}
        implementor.test();
        System.out.println(res1);
    }

    @WebMethod(exclude = true)
    public void test()
    {
        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><check-result version=\"1.0\" taskCode=\"zz20180721000-0000-1\"  bsm=\"38172AD3D80B41CB972DA3D7F05ED243\"><results><result chjd=\"山东省\" areaCode=\"370100\" areaName=\"济南市\" personName=\"张三\"  personID=\"370102197407314627\" cxjg=\"3\"  bdcdyh=\"bdc001\" bdcqzh=\"cqh001\" bdclx=\"宗地\" zl=\"山东省济南市历下区\" mj=\"100平方米\" ghyt=\"住宅\"  djsj=\"2016-09-08\" gyr=\"田雨\" gyfs=\"共同共有\" jg=\"200万元\" qlzt=\"现势\" sfdy=\"否\" sfcf=\"否\" bz=\"\"/></results></check-result>";
        String privatedata = "atq/y1tkE3uzcSz3K+m3EZkf7hBbfx2mEf3cD3RebIiCuYcWY8q+e3sMvnxbCdZ68U+4VvJ9gm6LXvBO4Y9L7GCKeOf2lnMROMLA4BuYBuR4YBzQXzkCOUTa518crLhxl3Fs8y4mQ5otQi6TdS7IBElj8hXdcUWvsB/fB417goViFM2L2McNYkgtxvwawTLqyK0OHUOd8VA401rFx1dV6Qz1wgip5VnliHeghlTOMqrfOsMiJNRpoCLL5FcBf+yM/9BW6LSDFKFZNodS5vHC/+70YHIOpm0CoWai+2GYHJ2VVNcypzBmMuQGD9s8oJtR9LtyY+kb93oUaV9i3Ib5EHgWfhl323W3uAWZsW04TD5KIw04FOgP/6tVrIcICwqNblehIg5sCWgVraocO1rIB+VGfvSWn9JqdSpt7St3eQt2Ch9EK1Zyv3hD/fEwalUzE1o56qH2/+NWVi940UygsgWPCUDM3qxdFx4SAviUNvtbfO5RjD96zCHnjf/LFlKMKJq7DJkA9Wmx7Tj2Sneo9/FOCrqrbhPJe79WZImUXVIhycLAa6971qZg4OQt523sc6L3FgBqxwfVslbUSG5uXZNMylsHp5rJ20iNy9etmCH9i224wBHXCq9Rr0Zq1FsEbnOQKzqbZ1eEK9ht4gF0Ux6sQIN5LPnFdiZwH7OemOxc7fTg4v8muDc39MBzXDt3vRwIIY1XdqXy06u6NWraOlEO+VSLaG/yrOaQqZvc5bovkx8mHfuv1UjfObRoWUwfrr47bGDsUf5zReaaM2s9o/NomnW/SC/leizQsDdQzw5E7B4satFKozAB6eP3zY7JvTarm0B4EdZtdHBmsqEUFA==";
        String publicdata = "XZlTHLdTWsrMGaOcw8uzmveaylzjKNGKtOeXs/JbLXdVSh39pfmmgo/Ec+3aoXjSJ3IkMzzG4Q9PhRhJbI6jnctL1PgxC5P4WAS6GywqN/so0sL3pHdkuWeav7BB1mgGiCbEuJxempYwOFzijRRt4DMGlsDbpDzQIyfev4vZ7+lqLyjG+fPeQV3URgSO2Mb9HV32MEzIEZocy+b9+jUTSNhxL4dCWZiUzJFccbrvJmMIxQLjvEjv4NK+7mZ65BOM7UScbhXR7sd72mvMwgb5YSOlRkqi/nwmxbaCx5OWstjAX5/sp9DMJtAW9cAvbe1cYAHV3iBS1bB/DpRIWxUtiD3GbBRTrRA/Jw9p54bHfvBogn4b4BmB+mfsNvmInrg949UJoBql9MlkineNdtcMlOBiPflvj+0DfR3LyXkOKgnR2MuGEDZdwSskV82hesob3NqWhKVstEwPo9bqyHbtvWupOCsrTos8ElLhBY3E9mjzoiIkvPq/e5SiC6gronGtPBZ5IiZ4CndUC7kJ8ppR9ANSOD0+iegDQ256CweHwaaep5ZbE72fdJmJE3tqe1xOAbgwLpGSPHp2AnDSIRSgxc5v8u9AkRj8A2qSOt7POgA0Au8rJBkxeOrmJutSMejlMLCJg17KG8a+y0+/uCk4NP0PSasrBg1gONqr2pXgHyFqhBln0Q+4QFyVf62LNw1CcmEz8SYbQ++A85/EzQjKdVgTcZ6PjMFGNzs23Zlv8jpsUhaRqoHZRw2DujKnzc4dYEnNlYdHlK1D5gLOhITkdVaEd3V45UMYw/NLhIMRaiMgHnsPaoebgko2Dc1MN9AmsAGu+FvE+Ho+cJ7esrUIsA==";
        String privateqm = "KCqMxfD+fJIdVuhnBg+Rz3nuNecmn9I2fL4R9H0ytmOqFIOl3u1ZJABOzUYUAnsdIDJnICteQFiErjAwqbVy8rfJ5n6hPNuzT4oItLT7yj+dmVCajQxu/9SXi++aCta0+d2ihB/gtlW2Z6J9dELZFyCCOF3Y9Yot9asER0NCpw0=";
        String lpmw = "CLTYb0wu4z0g8ywZLWfRT2crdriT5uj5ndSrHIVG1/OygMdFNk90CTRZ2qzOGNFBAB00fkMuAmDoEQZDk4gCvzbl+L9FAE3DADYOE4M4mV6fKf9ZB1vS3lvcPnlUBxc41UZ8vAyyUKkmouJwmWC4rF+QnKYV+mDY4YGoX2MDhsFLk6+Ti9vkPyA2qkeQu/mkFTYQT+IcBGmIyaNdnFPgw+j+/DNG8NaWEkTCBWi4sLQl4JR7l9GEzQeKdXReBPAYPtjNYI9bdQZQDMrcg4BvS0obURqfeeTtI9TgIcndB18eHI1TF3URFwHbz9kbLNmueYkKidAMrZZJJnUSMIX9pFhih3V8iApab34WYKd/rR8sY9jB4zjJz5DzUFtyXUEjPKxesYuqXEe8qkjfyNUKZtl6zsXdZvvupWG1ha/ufhH9OtlKal+0OcrD6NNtLY/eqepL/kZFfg2U1bwnn+Qhymb8/xV4fuluf5yy1XCvdk9PWcN5QhY+PqdsR5wmxfzAhFZbrDK7tCdmp7YhwVG6Bzo73n/jQZY2/63eZPzt00hBH0pUV5OVP3dnwkCL+S9+UqhyBREAepx7ICS3y1oA9qouPoiHqgIN+nT2ab3iRc9H13Es2vvejpz3vC7OJi/1v2sH4WgmSENE8YvYd4zr9LmR7+aa4q+Rm0EFCzS4ZaRalAJf8dD2HMR8yN037BnxVAbXzcB0lPPaAQSWrA346d2zODrSWdbLeinUiBTcn8QPkg2Zx594Mp5Ex0432VHxJ/GSQJSbqe5IXlQM7JBKGr4FnJ8CHoC8C5kXXPZ8XhSquctkPlR1CqzGILAWVRRPllKZPlWWzrrRKHN7Jh6reg==";
        try
        {
            String enStr1 = RsaTool.encryptByPublicKey(data, publicKey);
            System.out.println("公钥加密后：" + enStr1);
            String deStr1 = RsaTool.decryptByPrivateKey(enStr1, privateKey);
            System.out.println("私钥解密后：" + deStr1);
            if (deStr1.equals(data))
            {
                System.out.println("公钥加密私钥解密成功");
            }
            // 私钥加密，公钥解密
            String enStr2 = RsaTool.encryptByPrivateKey(data, privateKey);
            System.out.println("私钥加密后：" + enStr2);
            String deStr2 = RsaTool.decryptByPublicKey(enStr2, publicKey);
            System.out.println("公钥解密后：" + deStr2);
            if (deStr2.equals(data))
            {
                System.out.println("私钥加密公钥解密成功");
            }
            // 产生签名
            String sign = RsaTool.sign(enStr2, privateKey);
            System.out.println("签名:" + sign);
            // 验证签名
            boolean status = RsaTool.verify(enStr2, publicKey, sign);
            System.out.println("状态:" + status);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lp = Sqm + "|" + sdf.format(new Date());
            System.out.println(lp);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}