package eu.linesofcode.taggerbot.client;

import eu.linesofcode.taggerbot.client.data.EStatus;
import eu.linesofcode.taggerbot.client.data.EStatusReturn;

/**
 * @author Xperimental
 */
public class StatusService extends ServiceBase {

    /**
     * @param taggerClient
     */
    protected StatusService(TaggerClient parent) {
        super(parent);
    }

    public EStatusReturn update(EStatus status) {
        return client().put(EStatusReturn.class, "Status", status);
    }

}
