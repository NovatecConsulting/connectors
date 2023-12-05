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
package io.camunda.connector.generator.dsl;

import com.fasterxml.jackson.annotation.JsonProperty;

public sealed interface PropertyBinding {

  @JsonProperty("type")
  String type();

  record ZeebeInput(String name) implements PropertyBinding {

    @Override
    public String type() {
      return "zeebe:input";
    }
  }

  record ZeebeTaskHeader(String key) implements PropertyBinding {

    @Override
    public String type() {
      return "zeebe:taskHeader";
    }
  }

  record ZeebeTaskDefinition(String property) implements PropertyBinding {

    public static ZeebeTaskDefinition TYPE = new ZeebeTaskDefinition("type");
    public static ZeebeTaskDefinition RETRIES = new ZeebeTaskDefinition("retries");

    @Override
    public String type() {
      return "zeebe:taskDefinition";
    }
  }
}