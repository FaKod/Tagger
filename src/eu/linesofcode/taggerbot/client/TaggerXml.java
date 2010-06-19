package eu.linesofcode.taggerbot.client;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

import eu.linesofcode.taggerbot.client.data.ECircleOfFriends;
import eu.linesofcode.taggerbot.client.data.EIds;
import eu.linesofcode.taggerbot.client.data.ELocationTag;
import eu.linesofcode.taggerbot.client.data.EPlanIds;
import eu.linesofcode.taggerbot.client.data.EStatus;
import eu.linesofcode.taggerbot.client.data.EStatusReturn;
import eu.linesofcode.taggerbot.client.data.EUser;
import eu.linesofcode.taggerbot.client.data.EUserAndUserInfo;
import eu.linesofcode.taggerbot.client.data.EUserInfo;
import eu.linesofcode.taggerbot.client.data.Tcircleoffriends;
import eu.linesofcode.taggerbot.client.data.Tdemand;
import eu.linesofcode.taggerbot.client.data.Tfeaturepoint;
import eu.linesofcode.taggerbot.client.data.Tfeaturetoobserve;
import eu.linesofcode.taggerbot.client.data.TidArray;
import eu.linesofcode.taggerbot.client.data.Tlocation;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;
import eu.linesofcode.taggerbot.client.data.Tplan;
import eu.linesofcode.taggerbot.client.data.Troute;
import eu.linesofcode.taggerbot.client.data.Tuser;
import eu.linesofcode.taggerbot.client.data.Tuserinfo;
import eu.linesofcode.taggerbot.client.data.Tuserlocation;
import eu.linesofcode.taggerbot.client.data.Twaypoint;

/**
 * This class contains the XML parser and generator to create data classes from
 * XML and the other way around.
 * 
 * @author Xperimental
 */
public final class TaggerXml {

    private static XStream parser;

    public synchronized static XStream xml() {
        if (parser == null) {
            initParser();
        }
        return parser;
    }

    /**
     * Creates the parser instance and sets its options.
     */
    private static void initParser() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("-", "_");
        parser = new XStream(new XppDriver(replacer));
        parser.alias("tdemand", Tdemand.class);
        parser.alias("tlocation", Tlocation.class);
        parser.alias("tuser", Tuser.class);
        parser.alias("tuserinfo", Tuserinfo.class);
        parser.alias("tplan", Tplan.class);
        parser.alias("tcircleoffriends", Tcircleoffriends.class);
        parser.alias("tlocationtag", Tlocationtag.class);
        parser.alias("tfeaturetoobserve", Tfeaturetoobserve.class);
        parser.alias("tfeaturepoint", Tfeaturepoint.class);
        parser.alias("troute", Troute.class);
        parser.alias("twaypoint", Twaypoint.class);
        parser.alias("tidArray", TidArray.class);
        parser.alias("tuserlocation", Tuserlocation.class);
        parser.alias("E_userAndUserInfo", EUserAndUserInfo.class);
        parser.alias("E_user", EUser.class);
        parser.alias("E_userInfo", EUserInfo.class);
        parser.alias("E_planIds", EPlanIds.class);
        parser.alias("E_circleOfFriends", ECircleOfFriends.class);
        parser.alias("E_Ids", EIds.class);
        parser.alias("E_locationTag", ELocationTag.class);
        parser.alias("E_status", EStatus.class);
        parser.alias("E_statusReturn", EStatusReturn.class);
    }

    private TaggerXml() {
    }

}
