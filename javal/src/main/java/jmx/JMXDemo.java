package jmx;

import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JMXDemo {

    private static final String HOST = "172.28.16.37"; // configure host <my_host> here
    private static final String PORT = "8999"; // configure port <my_port> here

    private static void testJMX() throws Exception {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi://" + HOST + "/jndi/rmi://" + HOST + ":" + PORT
                + "/jmxrmi");
        System.out.println("before connect");
        JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
        System.out.println("connected");
        try {
            MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();

            ObjectName mbeanName = new ObjectName("java.lang:type=Memory");
            javax.management.openmbean.CompositeDataSupport obj = null;

            obj = (javax.management.openmbean.CompositeDataSupport) mbeanServerConnection.getAttribute(mbeanName,
                    "HeapMemoryUsage");
            Set<String> keySet = obj.getCompositeType().keySet();

            for (String key : keySet) {
                System.out.print(key + "=" + obj.get(key) + ", ");
            }
        } finally {
            jmxConnector.close();
        }
        System.out.println("\n==========================");
    }

    public static void main(String args[]) throws Exception {
        testJMX();
    }

}