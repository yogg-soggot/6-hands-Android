package org.styleru.the6hands.data.vkmethods;

import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.styleru.the6hands.domain.entities.User;

public class VkUserRequest extends VKRequest<User> {

    public VkUserRequest() {
        super("users.get");
        addParam("fields", "photo_200");
    }

    @Override
    public User parse(@NotNull JSONObject r) throws Exception {
        JSONObject response = r.getJSONArray("response").getJSONObject(0);
        return new User(response.getLong("id"), response.getString("first_name"),
                response.getString("photo_200"));
    }
}
