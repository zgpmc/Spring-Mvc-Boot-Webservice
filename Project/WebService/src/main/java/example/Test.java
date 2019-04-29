package example;

import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * 创建时间:2019/01/07
 * 创建人:Administrator
 * 描述:
 */
public class Test
{
    public String test(String TaskCode)
    {
        String res = "";
        try
        {
            // 指出service所在完整的URL
            String endpoint = "http://192.168.0.123/FW/Bdc/Service/ZWPTService.asmx?wsdl";
            //调用接口的targetNamespace
            //targetNamespace 就是你用浏览器打开endpoint 路径加上?wsdl，即http://xxx/WebService1.asmx?wsdl 中的targetNamespace属性值
            String targetNamespace = "http://tempuri.org/";
            //所调用接口的方法method
            String method = "getTaskNotice";
            // 创建一个服务(service)调用(call)
            Service service = new Service();
            Call call = (Call) service.createCall();// 通过service创建call对象
            // 设置service所在URL
            call.setTargetEndpointAddress(new URL(endpoint));
            call.setOperationName(new QName(targetNamespace, method));
            call.setUseSOAPAction(true);
            //变量最好只是用String类型，其他类型会报错
            call.addParameter(new QName(targetNamespace, "in0"),
                    Constants.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);//设置参数名 state 第二个参数表示String类型,第三个参数表示入参
            call.setReturnType(Constants.XSD_STRING);// 设置返回类型
            String jsonString = (String) call.invoke(new Object[]{TaskCode});//此处为数组，有几个变量传几个变量
            System.out.println(jsonString);
            res = jsonString;
        } catch (ServiceException e)
        {
            e.printStackTrace();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
        return res;
    }
}
