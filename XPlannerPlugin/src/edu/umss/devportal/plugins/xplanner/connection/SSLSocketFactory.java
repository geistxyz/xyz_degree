/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.xplanner.connection;

import java.io.IOException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.axis.components.net.JSSESocketFactory;
import org.apache.axis.components.net.SecureSocketFactory;

/**
 *
 * @author Raul Lopez
 */
@SuppressWarnings("UseOfObsoleteCollectionType")
public class SSLSocketFactory extends JSSESocketFactory implements SecureSocketFactory {

    /**
     * Constructor SSLSocketFactory
     * @param attributes
     */
    public SSLSocketFactory(java.util.Hashtable attributes) {
        super(attributes);
    }

    /**
     * Initializes SSL Context
     *
     * This overrides the parent class to provide our SocketFactory implementation.
     * @throws IOException
     */
    @Override
    protected void initFactory() throws IOException {

        try {
            SSLContext context = getContext();
            sslFactory = context.getSocketFactory();
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw (IOException) e;
            }
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Gets a custom SSL Context.
     *
     * Initialize a SSLContext
     *
     * @return SSLContext
     * @throws WebServiceClientConfigException
     * @throws Exception
     */
    protected SSLContext getContext() throws Exception {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new java.security.SecureRandom());

            return sslContext;
        } catch (Exception e) {
            throw new Exception("Error creating context for SSLSocket!", e);
        }
    }

    /**
     *  Create a trust manager that trusts all certificates
     *  It is not using a particular keyStore
     */
    protected TrustManager[] getTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        return trustAllCerts;
    }
}
