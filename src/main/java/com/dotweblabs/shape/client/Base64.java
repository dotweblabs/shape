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

/**
 *
 * @author Kerby Martino
 * @since 0-SNAPSHOT
 * @version 0-SNAPSHOT
 *
 */
public class Base64 {
    public static native String btoa(String a) /*-{
        return window.btoa(a);
    }-*/;
}
