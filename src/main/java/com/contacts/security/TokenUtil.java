package com.contacts.security;

import com.contacts.exception.ContactsException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    public String getUserName(HttpHeaders header) {
        String token = header.get("Authorization").get(0);
        String jwt = token.replace("Bearer ", "");
        String[] splitString = jwt.split("\\.");
        String base64EncodedBody = splitString[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        try {
            JSONObject jsonObject = new JSONObject(body);
            return jsonObject.get("sub").toString();
        } catch (JSONException err) {
            throw new ContactsException("Token error");
        }
    }
}
