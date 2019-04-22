package org.styleru.the6hands.domain.entities;

import android.util.Base64;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Image {

    private long id, idUser, idApartment;
    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdApartment() {
        return idApartment;
    }

    public void setIdApartment(long idApartment) {
        this.idApartment = idApartment;
    }

    public byte[] getImage() {
        return Base64.decode(path, Base64.DEFAULT);
    }

    public void setPath(String path) {
        this.path = path;
    }
}
