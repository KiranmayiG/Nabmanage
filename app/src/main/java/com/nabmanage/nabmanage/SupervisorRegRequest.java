package com.nabmanage.nabmanage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kiranmayi on 01-06-2017.
 */
public class SupervisorRegRequest extends StringRequest {

    private static final String SV_REQUEST_URL = "http://192.168.0.6/Nabmanage/SupervisorReg.php";

    private Map<String,String> params;

    public SupervisorRegRequest(String name, String location, String username, String password, Response.Listener<String> listener){
        super(Method.POST,SV_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name",name);
        params.put("location", location);
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

