package com.example.reyanthonypaano.personfinder.database.manager;

import android.content.Context;

import com.couchbase.lite.Array;
import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Expression;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.reyanthonypaano.personfinder.model.Person;

import java.util.Iterator;
import java.util.List;

public class PersonFinderDB {
    private DatabaseConfiguration config;
    private Database database;

    public PersonFinderDB(Context context) {
        this.config = new DatabaseConfiguration(context);

        try {
            this.database = new Database("person-finder", this.config);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public void insertPerson(MutableDocument record) {
        try {
            this.database.save(record);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(MutableDocument record, List<Person> updateData) {
        record = this.database.getDocument(record.getId()).toMutable();

        for(Person data: updateData) {
            record.setString("email", data.getEmail());
            record.setString("first_name", data.getFirstName());
            record.setString("middle_name", data.getMiddleName());
            record.setString("last_name", data.getLastName());
            record.setString("greetings", data.getGreetings());
            record.setString("longitude", data.getLongitude());
            record.setString("latitude", data.getLatitude());
            record.setBlob("image", data.getImage());
        }

        try {
            this.database.save(record);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getPersons() {
        Query query = QueryBuilder.select(SelectResult.all())
                .from(DataSource.database(this.database));

        ResultSet result = null;

        try {
            result = query.execute();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        return result;
    }
}
