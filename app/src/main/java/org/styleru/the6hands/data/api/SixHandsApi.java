package org.styleru.the6hands.data.api;

import org.styleru.the6hands.domain.entities.Apartment;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SixHandsApi {
    String BASE_URL = "http://localhost/api/";

    @GET("apartment")
    Single<List<Apartment>> getUserApartments(@Query("userId") long userId);
}
