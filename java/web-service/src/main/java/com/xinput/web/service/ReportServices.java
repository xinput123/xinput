package com.xinput.web.service;

import javax.jws.WebService;

/**
 * @author xinput
 * @date 2018-06-22 20:50
 */
@WebService
public interface ReportServices {
    String report(String message);
}
