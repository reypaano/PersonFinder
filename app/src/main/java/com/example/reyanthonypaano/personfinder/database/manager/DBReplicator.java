package com.example.reyanthonypaano.personfinder.database.manager;

import com.couchbase.lite.Database;
import com.couchbase.lite.Endpoint;
import com.couchbase.lite.ReplicatorConfiguration;
import com.couchbase.lite.URLEndpoint;

import java.net.URI;
import java.net.URISyntaxException;

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
    }
}
