package eu.linesofcode.taggerbot.map;

import eu.linesofcode.taggerbot.client.data.Tlocationtag;

/**
 * Listener interface for notifying user interaction with location tags.
 * 
 * @author Xperimental
 */
public interface TagOverlayListener {

    void tagTapped(Tlocationtag tag);

}
