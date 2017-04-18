/**
 *
 * Copyright (c) 2016 Dotweblabs Web Technologies and others. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.dotweblabs.shape.client;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kerby Martino
 * @since 0-SNAPSHOT
 * @version 0-SNAPSHOT
 *
 */
public class GetRequest {

    static final Logger logger = Logger.getLogger(GetRequest.class.getName());

    private String url;
    private Multimap<String,String> headerMap;
    private Map<String, String> queryMap = null;
    private Map<String,Object> fields = null;

    private String authorization = null;
    private int TIMEOUT = 60000;

    public GetRequest(String url) {
        setUrl(url);
        headerMap = ArrayListMultimap.create();
    }

    public GetRequest header(String header, String value) {
        if(headerMap == null){
            headerMap = ArrayListMultimap.create();
        }
        if(value != null) {
            headerMap.put(header, value);
        }
        return this;
    }

    public GetRequest basicAuth(String username, String password) {
        authorization = "Basic " + Base64.btoa(username + ":" + password);
        return this;
    }

    public void asJson(final AsyncCallback<String> callback){
        if(queryMap != null && !queryMap.isEmpty()){
            url = url + "?";
            url = url +  queries(queryMap);
        }
        RequestBuilder b = new RequestBuilder(RequestBuilder.GET, url);
        b.setTimeoutMillis(TIMEOUT);
        if(headerMap != null){
            // Set default first
            headerMap.put("Content-Type", "application/json");
            headerMap.put("accept", "application/json");
            for (Map.Entry<String,String> entry : headerMap.entries()) {
                if(entry.getKey() != null && entry.getValue() != null
                        && !entry.getKey().isEmpty() && !entry.getValue().isEmpty()) {
                    b.setHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        if(authorization != null){
            b.setHeader("Authorization", authorization);
        }
        try {
            b.sendRequest(null, new RequestCallback() {
                public void onResponseReceived(Request request, Response response) {
                    String resp = response.getText();
                    callback.onSuccess(resp);
                }
                public void onError(Request request, Throwable exception) {
                    callback.onFailure(exception);
                }
            });
        } catch (RequestException ex) {
            ex.printStackTrace();
            callback.onFailure(ex);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String queries(Map<String,String> parmsRequest){
        StringBuilder sb = new StringBuilder();
        for ( String k: parmsRequest.keySet() ) {
            String vx = URL.encodeComponent( parmsRequest.get(k));
            if ( sb.length() > 0 ) {
                sb.append("&");
            }
            sb.append(k).append("=").append(vx);
        }
        return sb.toString();
    }

    public void setTimeout(int timeout) {
        this.TIMEOUT = timeout;
    }

}
