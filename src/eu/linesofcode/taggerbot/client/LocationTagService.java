package eu.linesofcode.taggerbot.client;

import java.util.ArrayList;
import java.util.List;

import eu.linesofcode.taggerbot.client.data.EIds;
import eu.linesofcode.taggerbot.client.data.ELocationTag;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;

/**
 * @author Xperimental
 */
public class LocationTagService extends ServiceBase {

    /**
     * @param client
     */
    protected LocationTagService(TaggerClient client) {
        super(client);
    }

    public List<Tlocationtag> byUser(int userId) {
        EIds tagIds = client().get(EIds.class,
                "locationtag/LocationTags/User/" + userId);
        List<Tlocationtag> result = new ArrayList<Tlocationtag>();
        for (String id : tagIds.getIdArray().getIds()) {
            Tlocationtag tag = byId(Integer.parseInt(id));
            result.add(tag);
        }
        return result;
    }

    /**
     * @param tagId
     * @return
     */
    private Tlocationtag byId(int tagId) {
        ELocationTag tag = client().get(ELocationTag.class,
                "locationtag/LocationTag/" + tagId);
        return tag.getLocationTag();
    }

}
