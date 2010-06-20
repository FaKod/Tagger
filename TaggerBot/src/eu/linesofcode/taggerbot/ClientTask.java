package eu.linesofcode.taggerbot;

import eu.linesofcode.taggerbot.client.TaggerClient;

/**
 * This interface describes an abstract task for interacting with the client.
 * 
 * @param <T>
 *            Specifies the return type of the task execution.
 * @author Xperimental
 */
public interface ClientTask {

    /**
     * Runs the task while using the client instance provided.
     * 
     * @param client
     *            Client instance to use for task.
     */
    boolean run(TaggerClient client);

}
