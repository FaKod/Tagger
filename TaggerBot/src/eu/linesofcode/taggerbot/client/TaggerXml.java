package eu.linesofcode.taggerbot.client;

import java.io.ByteArrayOutputStream;

import org.simpleframework.xml.core.Persister;

/**
 * This class contains the XML parser and generator to create data classes from
 * XML and the other way around.
 * 
 * @author Xperimental
 */
public final class TaggerXml {

    private static TaggerXml instance;

    public static synchronized TaggerXml instance() {
        if (instance == null) {
            instance = new TaggerXml();
        }
        return instance;
    }

    private Persister parser;

    private TaggerXml() {
        parser = new Persister();
    }

    /**
     * Serialize object to XML.
     * 
     * @param payload
     *            Object to serialize.
     * @return String containing the result XML.
     */
    public String toXML(Object payload) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            parser.write(payload, stream);
        } catch (Exception e) {
            throw new TaggerClientException("Error while creating XML: "
                    + e.getMessage(), e);
        }
        return stream.toString();
    }

    /**
     * Deserialize an XML string into an object.
     * 
     * @param content
     *            String containing XML.
     * @return Object created from XML.
     */
    public <T> T fromXML(Class<? extends T> resultClass, String content) {
        T result = null;
        try {
            result = parser.read(resultClass, content);
        } catch (Exception e) {
            throw new TaggerClientException("Error while reading XML: "
                    + e.getMessage(), e);
        }
        return result;
    }

}
