package com.example.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 创建时间:2019/04/26
 * 创建人:pmc
 * 描述:
 */
@WebService
public interface IWebService
{
    @WebMethod
    String getTask(@WebParam() String in0, @WebParam() String in1, @WebParam() String in2);

    @WebMethod
    String pushResult(@WebParam(name = "in0") String in0, @WebParam(name = "in1") String in1, @WebParam(name = "in2") String in2, @WebParam(name = "in3") String in3);
}
