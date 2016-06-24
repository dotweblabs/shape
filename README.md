#Shape: A fluent HTTP Client for GWT (and Javascript soon!)

This library wraps GWT RequestBuilder into a fluent API.

```
Shape.post("http://httpbin.org/post")
  .queryString("name", "Mark")
  .field("last", "Polo")
  .asJson()
```