package eu.linesofcode.taggerbot.client;

/**
 * @author Xperimental
 */
public abstract class ServiceBase {

    private TaggerClient client;

    protected ServiceBase(TaggerClient client) {
        this.client = client;
    }

    /**
     * @return the client
     */
    protected TaggerClient client() {
        return client;
    }

}
