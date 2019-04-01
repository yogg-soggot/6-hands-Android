package org.styleru.the6hands.domain.entities;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel
public class User extends RealmObject {

    @PrimaryKey
    private long id;

    private String firstName, photo200Url;

    public User() {
    }

    public User(long id, String firstName, String photo200Url) {
        this.id = id;
        this.firstName = firstName;
        this.photo200Url = photo200Url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoto200Url() {
        return photo200Url;
    }

    public void setPhoto200Url(String photo200Url) {
        this.photo200Url = photo200Url;
    }
}
