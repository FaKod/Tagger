package eu.linesofcode.taggerbot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * This class provides the methods for accessing the Tagger server as a client.
 * 
 * @author Xperimental
 */
public class TaggerClient {

    private static final String SERVER_BASE = "http://linesofcode.eu:8090/Tagger/V1/";
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n";

    private CredentialsProvider credsProvider;
    private DefaultHttpClient client;

    private UserService userService;

    private StatusService statusService;

    private UserInfoService userInfoService;

    /**
     * Creates a new client instance connecting to the Tagger server with the
     * provided credentials.
     * 
     * @param username
     *            Username to use for login.
     * @param password
     *            Password to use for login.
     */
    public TaggerClient(String username, String password) {
        userService = new UserService(this);
        userInfoService = new UserInfoService(this);
        statusService = new StatusService(this);

        credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(null, -1),
                new UsernamePasswordCredentials(username, password));
        client = new DefaultHttpClient();
        client.setCredentialsProvider(credsProvider);
    }

    @SuppressWarnings("unchecked")
    private <T> T execute(HttpRequestBase request) {
        T result = null;
        try {
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new TaggerClientException("HTTP status: "
                        + response.getStatusLine().getStatusCode());
            }
            HttpEntity entity = response.getEntity();
            String content = readEntity(entity);
            result = (T) TaggerXml.xml().fromXML(content);
        } catch (IOException e) {
            throw new TaggerClientException(e);
        }
        return result;
    }

    /**
     * @param entity
     * @return
     */
    private String readEntity(HttpEntity entity) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    entity.getContent()));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new TaggerClientException("Error while reading response.", e);
        }
        return sb.toString();
    }

    /**
     * @param payload
     * @return
     */
    private StringEntity createXmlEntity(Object payload) {
        StringEntity entity;
        try {
            String postXml = TaggerXml.xml().toXML(payload);
            entity = new StringEntity(XML_HEADER + postXml);
            entity.setContentType("application/xml");
        } catch (UnsupportedEncodingException e) {
            throw new TaggerClientException(e);
        }
        return entity;
    }

    protected <T> T get(String method) {
        HttpGet request = new HttpGet(SERVER_BASE + method);
        return execute(request);
    }

    protected <T> T post(String method, Object payload) {
        StringEntity entity = createXmlEntity(payload);
        HttpPost request = new HttpPost(SERVER_BASE + method);
        request.setEntity(entity);
        return execute(request);
    }

    protected <T> T put(String method, Object payload) {
        StringEntity entity = createXmlEntity(payload);
        HttpPut request = new HttpPut(SERVER_BASE + method);
        request.setEntity(entity);
        return execute(request);
    }

    public UserService user() {
        return userService;
    }

    public UserInfoService userInfo() {
        return userInfoService;
    }

    public StatusService status() {
        return statusService;
    }
}
