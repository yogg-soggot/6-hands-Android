package org.styleru.the6hands.domain.entities;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Facility {

    private long id;
    private String name, icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
