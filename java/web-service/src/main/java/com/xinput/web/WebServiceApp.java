package com.xinput.web;

import com.xinput.web.service.ReportServices;
import com.xinput.web.service.ReportServicesImpl;

import javax.xml.ws.Endpoint;

/**
 * 启动后访问 http://127.0.0.1:8092/ReportServices?wsdl
 */
public class WebServiceApp {

    private final static String address = "http://127.0.0.1:8092/ReportServices";

    public static void main(String[] args) {
        System.out.println("web service start");
        ReportServices reportServices = new ReportServicesImpl();
        Endpoint.publish(address, reportServices);
        System.out.println("web service started");
    }
}
