package com.dotweblabs.shape.client;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * Unit tests of {@link ShapeTest}
 * @author Kerby Martino
 * @since 0-SNAPSHOT
 * @version 0-SNAPSHOT
 *
 */
public class ShapeTest extends GWTTestCase {

    private static final String TEST_APP_ID = "123";

    @Override
    public String getModuleName() {
        return "com.dotweblabs.shape.Http";
    }

    public void testGet() {
        Shape.get("https://httpbin.org/get")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        log(throwable.getMessage());
                        fail();
                    }

                    @Override
                    public void onSuccess(String s) {
                        JSONObject json = (JSONObject) JSONParser.parseStrict(s);
                        JSONString url = json.get("url").isString();
                        JSONObject headers = json.get("headers").isObject();
                        assertNotNull(json);
                        assertNotNull(url);
                        assertNotNull(headers);
                        log(s);
                    }
                });
    }


    public void testPostFormField() {
        Shape.post("https://httpbin.org/post")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .queryString("name", "Mark")
                .field("middle", "O")
                .field("last", "Polo")
                .asJson(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        log(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        JSONObject json = (JSONObject) JSONParser.parseStrict(s);
                        JSONString url = json.get("url").isString();
                        JSONObject headers = json.get("headers").isObject();
                        JSONObject args = json.get("args").isObject();

                        assertNotNull(json);
                        assertNotNull(url);
                        assertNotNull(headers);
                        assertNotNull(args);

                        String name = args.get("name").isString().stringValue();
                        assertEquals("Mark", name);

                        log(s);
                    }
                });
    }

    public void testPostJson() {
        JSONObject payload = new JSONObject();
        payload.put("hello", new JSONString("world"));

        Shape.post("https://httpbin.org/post")
                //.header("accept", "application/json")
                //.header("Content-Type", "application/json")
                .queryString("name", "Mark")
                .body(payload.isObject().toString())
                .asJson(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        log(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        JSONObject json = (JSONObject) JSONParser.parseStrict(s);
                        JSONString url = json.get("url").isString();
                        JSONObject headers = json.get("headers").isObject();
                        JSONObject jsonField = json.get("json").isObject();

                        assertNotNull(json);
                        assertNotNull(url);
                        assertNotNull(headers);
                        assertNotNull(jsonField);

                        String accept = headers.get("Accept").isString().stringValue();
                        String contentType = headers.get("Content-Type").isString().stringValue();
                        String hello = jsonField.get("hello").isString().stringValue();

                        assertEquals("application/json", accept);
                        assertEquals("application/json", contentType);
                        assertEquals("world", hello);

                        log(s);
                    }
                });
    }

    public void testPut() {
        JSONObject payload = new JSONObject();
        payload.put("hello", new JSONString("world"));

        Shape.put("https://httpbin.org/put")
                .queryString("name", "Mark")
                .body(payload.isObject().toString())
                .basicAuth("john", "doe")
                .asJson(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        log(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        JSONObject json = (JSONObject) JSONParser.parseStrict(s);
                        JSONString url = json.get("url").isString();
                        JSONObject headers = json.get("headers").isObject();
                        JSONObject jsonField = json.get("json").isObject();

                        assertNotNull(json);
                        assertNotNull(url);
                        assertNotNull(headers);
                        assertNotNull(jsonField);

                        String accept = headers.get("Accept").isString().stringValue();
                        String authorization = headers.get("Authorization").isString().stringValue();
                        String contentType = headers.get("Content-Type").isString().stringValue();
                        String hello = jsonField.get("hello").isString().stringValue();

                        assertEquals("application/json", accept);
                        assertEquals("application/json", contentType);
                        assertEquals("world", hello);

                        String actual = "Basic " + Base64.btoa("john" + ":" + "doe");
                        assertEquals(actual, authorization);

                        log(s);
                    }
                });
    }

    public void testDelete() {
        JSONObject payload = new JSONObject();
        payload.put("hello", new JSONString("world"));
        Shape.delete("https://httpbin.org/delete")
                .queryString("name", "Mark")
                .body(payload.isObject().toString())
                .basicAuth("john", "doe")
                .asJson(new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        log(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        JSONObject json = (JSONObject) JSONParser.parseStrict(s);
                        JSONObject jsonField = json.get("json").isObject();
                        JSONString url = json.get("url").isString();
                        JSONObject headers = json.get("headers").isObject();

                        assertNotNull(json);
                        assertNotNull(url);
                        assertNotNull(headers);
                        assertNotNull(jsonField);

                        String accept = headers.get("Accept").isString().stringValue();
                        String authorization = headers.get("Authorization").isString().stringValue();
                        String contentType = headers.get("Content-Type").isString().stringValue();
                        String hello = jsonField.get("hello").isString().stringValue();

                        assertEquals("application/json", accept);
                        assertEquals("application/json", contentType);
                        assertEquals("world", hello);

                        String actual = "Basic " + Base64.btoa("john" + ":" + "doe");
                        assertEquals(actual, authorization);

                        log(s);
                    }
                });
    }

    public static void log(String s){
        System.out.println(s);
    }

}
