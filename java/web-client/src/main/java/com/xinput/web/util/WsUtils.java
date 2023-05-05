package com.xinput.web.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

/**
 * @author xinput
 * @date 2018-06-01 08:56
 */
public class WsUtils {

  private final static String wsdlUrl = "http://127.0.0.1:8092/ReportServices?wsdl";

  private final static String namespaceUri = "http://service.web.xinput.com/";

  private final static String methodName = "report";

  private static JaxWsDynamicClientFactory dcf;

  /**
   * 在jdk8中，webservice创建Client，会引入com.sun.tools.javac.util.SharedNameTable这个类，
   * 是SoftReferences型，不能释放，导致内存中大量这个类，old代很快就会满载.jdk9中才解决
   */
  private static Client client;

  private static QName name;

  static {
    dcf = JaxWsDynamicClientFactory.newInstance();
    client = dcf.createClient(wsdlUrl);
    name = new QName(namespaceUri, methodName);
  }

  public static Object[] webService(String messge) throws Exception {
    try {
      return client.invoke(name, messge);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
