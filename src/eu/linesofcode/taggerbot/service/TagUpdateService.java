package eu.linesofcode.taggerbot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import eu.linesofcode.taggerbot.ClientState;
import eu.linesofcode.taggerbot.ClientTask;
import eu.linesofcode.taggerbot.Logger;
import eu.linesofcode.taggerbot.client.TaggerClient;
import eu.linesofcode.taggerbot.client.data.Tlocationtag;
import eu.linesofcode.taggerbot.client.data.Tuser;

/**
 * This service gets the location tags for the current user and all his COF on a
 * regular basis.
 * 
 * @author Xperimental
 */
public class TagUpdateService extends Service {

    private static final int INITIAL_DELAY = 5;

    private static final int UPDATE_INTERVAL = 120;

    private ScheduledExecutorService worker = Executors
            .newSingleThreadScheduledExecutor();

    /*
     * (non-Javadoc)
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("Created tag service.");
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        Logger.d("Destroyed tag service.");
        worker.shutdown();
        super.onDestroy();
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onStart(android.content.Intent, int)
     */
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Logger.d("Started tag service.");

        worker.scheduleAtFixedRate(new TagUpdater(), INITIAL_DELAY,
                UPDATE_INTERVAL, TimeUnit.SECONDS);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
        // Can't bind to this service.
        return null;
    }

    private class TagUpdater implements Runnable {

        /*
         * (non-Javadoc)
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            if (ClientState.getState().isConnected()) {
                Logger.d("Getting own location tags from server...");
                ClientState.getState().doTask(new ClientTask() {

                    @Override
                    public boolean run(TaggerClient client) {
                        List<Tlocationtag> ownTags = ClientState.getState()
                                .getOwnTags();
                        Tuser me = client.user().get();
                        List<Tlocationtag> serverTags = client.tags().byUser(
                                Integer.parseInt(me.getId()));
                        Logger.d("Own tags: DB: " + ownTags.size()
                                + " Server: " + serverTags.size());
                        List<Tlocationtag> added = new ArrayList<Tlocationtag>();
                        List<Tlocationtag> removed = new ArrayList<Tlocationtag>();
                        for (Tlocationtag tag : ownTags) {
                            if (tagInList(serverTags, tag)) {
                                serverTags.remove(tag);
                            } else {
                                removed.add(tag);
                            }
                        }
                        added.addAll(serverTags);
                        ClientState.getState().modifyOwnTags(added, removed);
                        return true;
                    }

                    private boolean tagInList(List<Tlocationtag> list,
                            Tlocationtag tag) {
                        for (Tlocationtag t : list) {
                            if (t.getId().equals(tag.getId())) {
                                return true;
                            }
                        }
                        return false;
                    }
                });
            }
        }

    }

}
