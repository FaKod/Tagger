package eu.linesofcode.taggerbot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpDelete;
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

    private LocationTagService locationTagService;

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
        locationTagService = new LocationTagService(this);

        credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(null, -1),
                new UsernamePasswordCredentials(username, password));
        client = new DefaultHttpClient();
        client.setCredentialsProvider(credsProvider);
    }

    private <T> T execute(Class<? extends T> resultClass,
            HttpRequestBase request) {
        T result = null;
        try {
            HttpResponse response = client.execute(request);
            if ((response.getStatusLine().getStatusCode() / 100) != 2) {
                throw new TaggerClientException("HTTP status: "
                        + response.getStatusLine().getStatusCode());
            }
            HttpEntity entity = response.getEntity();
            String content = readEntity(entity);
            result = (T) TaggerXml.instance().fromXML(resultClass, content);
            entity.consumeContent();
        } catch (IOException e) {
            throw new TaggerClientException("Communication error: "
                    + e.getMessage(), e);
        } catch (Exception e) {
            throw new TaggerClientException(
                    "Error while getting result from server: " + e.getMessage(),
                    e);
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
            String postXml = TaggerXml.instance().toXML(payload);
            entity = new StringEntity(XML_HEADER + postXml);
            entity.setContentType("application/xml");
        } catch (UnsupportedEncodingException e) {
            throw new TaggerClientException(e);
        }
        return entity;
    }

    protected <T> T get(Class<? extends T> resultClass, String method) {
        HttpGet request = new HttpGet(SERVER_BASE + method);
        return execute(resultClass, request);
    }

    protected <T> T post(Class<? extends T> resultClass, String method,
            Object payload) {
        StringEntity entity = createXmlEntity(payload);
        HttpPost request = new HttpPost(SERVER_BASE + method);
        request.setEntity(entity);
        return execute(resultClass, request);
    }

    protected <T> T put(Class<? extends T> resultClass, String method,
            Object payload) {
        StringEntity entity = createXmlEntity(payload);
        HttpPut request = new HttpPut(SERVER_BASE + method);
        request.setEntity(entity);
        return execute(resultClass, request);
    }

    protected void delete(String method) {
        HttpDelete request = new HttpDelete(SERVER_BASE + method);
        try {
            HttpResponse response = client.execute(request);
            if ((response.getStatusLine().getStatusCode() / 100) != 2) {
                throw new TaggerClientException("HTTP status: "
                        + response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            throw new TaggerClientException("Communication error: "
                    + e.getMessage(), e);
        } catch (Exception e) {
            throw new TaggerClientException(
                    "Error while getting result from server: " + e.getMessage(),
                    e);
        }
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

    public LocationTagService tags() {
        return locationTagService;
    }

}
