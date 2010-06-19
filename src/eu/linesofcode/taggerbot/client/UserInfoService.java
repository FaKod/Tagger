package eu.linesofcode.taggerbot.client;

import eu.linesofcode.taggerbot.client.data.EUserInfo;
import eu.linesofcode.taggerbot.client.data.Tuserinfo;

/**
 * @author Xperimental
 */
public class UserInfoService extends ServiceBase {

    /**
     * @param client
     */
    protected UserInfoService(TaggerClient client) {
        super(client);
    }

    public Tuserinfo get(int uid) {
        EUserInfo result = client().get("user/UserInfo/" + uid);
        return result.getUserInfo();
    }

    public Tuserinfo update(Tuserinfo info) {
        EUserInfo request = new EUserInfo();
        request.setUserInfo(info);
        EUserInfo result = client().post("user/UserInfo/" + info.getId(),
                request);
        return result.getUserInfo();
    }

}
