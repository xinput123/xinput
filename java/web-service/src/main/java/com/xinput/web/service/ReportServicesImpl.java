package com.xinput.web.service;

import javax.jws.WebService;

/**
 * @author xinput
 * @date 2018-06-22 20:51
 */
@WebService(endpointInterface = "com.xinput.web.service.ReportServices", serviceName = "ReportServices")
public class ReportServicesImpl implements ReportServices {

    @Override
    public String report(String message) {
        return "report: " + message;
    }
}
