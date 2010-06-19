package eu.linesofcode.taggerbot.client;

import eu.linesofcode.taggerbot.client.data.EUser;
import eu.linesofcode.taggerbot.client.data.Tuser;

/**
 * @author Xperimental
 */
public class UserService extends ServiceBase {

    /**
     * @param parent
     */
    protected UserService(TaggerClient parent) {
        super(parent);
    }

    public Tuser get() {
        EUser result = client().get("user");
        return result.getUser();
    }

    public Tuser get(int uid) {
        EUser result = client().get("user/User/" + uid);
        return result.getUser();
    }

}
