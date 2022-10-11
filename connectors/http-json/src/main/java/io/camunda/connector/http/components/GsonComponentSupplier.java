/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.camunda.connector.http.components;

import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import io.camunda.connector.http.auth.Authentication;
import io.camunda.connector.http.auth.BasicAuthentication;
import io.camunda.connector.http.auth.BearerAuthentication;
import io.camunda.connector.http.auth.NoAuthentication;

public class GsonComponentSupplier {

  private GsonComponentSupplier() {}

  private static final GsonFactory GSON_FACTORY = new GsonFactory();

  private static final Gson GSON =
      new GsonBuilder()
          .serializeNulls()
          .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
          .registerTypeAdapterFactory(
              RuntimeTypeAdapterFactory.of(Authentication.class, "type")
                  .registerSubtype(NoAuthentication.class, "noAuth")
                  .registerSubtype(BasicAuthentication.class, "basic")
                  .registerSubtype(BearerAuthentication.class, "bearer"))
          .create();

  public static Gson gsonInstance() {
    return GSON;
  }

  public static GsonFactory gsonFactoryInstance() {
    return GSON_FACTORY;
  }
}
