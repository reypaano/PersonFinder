package com.example.reyanthonypaano.personfinder.database.manager;

import com.couchbase.lite.BasicAuthenticator;
import com.couchbase.lite.Database;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.Replicator;
import com.couchbase.lite.ReplicatorChange;
import com.couchbase.lite.ReplicatorChangeListener;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.URLEndpoint;
import com.couchbase.lite.internal.support.Log;

import java.net.URI;
import java.net.URISyntaxException;

import static android.content.ContentValues.TAG;

public class DBReplicator {
    private Endpoint targetEndpoint;

    public DBReplicator(String uri, Database database) {
        try {
            this.targetEndpoint = new URLEndpoint(new URI(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ReplicatorConfiguration replConfig = new ReplicatorConfiguration(database, this.targetEndpoint);

        replConfig.setReplicatorType(ReplicatorConfiguration.ReplicatorType.PUSH_AND_PULL);
        replConfig.setAuthenticator(new BasicAuthenticator("admin", "admin"));

        Replicator replicator = new Replicator(replConfig);

        replicator.addChangeListener(new ReplicatorChangeListener() {
            @Override
            public void changed(ReplicatorChange change) {
                if(change.getStatus().getError() != null) {
                    Log.i(TAG, "Error code :: " + change.getStatus().getError().getCode());
                }
            }
        });

        replicator.start();
    }
}
