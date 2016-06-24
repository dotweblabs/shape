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

import com.google.gwt.http.client.RequestBuilder;

/**
 *
 * Fluent HTTP Client wrapper for GWT
 *
 * @author Kerby Martino
 * @since 0-SNAPSHOT
 * @version 0-SNAPSHOT
 */
public class Shape {
    public static GetRequest get(String url){
        return new GetRequest(url);
    };
    public static GetRequest head(String url){
        return new GetRequest(url);
    };
    public static HttpRequestWithBody post(String url){
        return new HttpRequestWithBody(url, RequestBuilder.POST);
    };
    public static HttpRequestWithBody put(String url){
        return new HttpRequestWithBody(url, RequestBuilder.PUT);
    };
    // TODO Not supported by RequestBuilder
    //public static HttpRequestWithBody patch(String url){
    //    return new HttpRequestWithBody(url, RequestBuilder.PATCH);
    //};
    //public static HttpRequestWithBody options(String url){
    //    return new HttpRequestWithBody(url, RequestBuilder.OPTIONS);
    //};
    public static HttpRequestWithBody delete(String url){
        return new HttpRequestWithBody(url, RequestBuilder.DELETE);
    };
}
