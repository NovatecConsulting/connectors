/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *       under one or more contributor license agreements. Licensed under a proprietary license.
 *       See the License.txt file for more information. You may not use this file
 *       except in compliance with the proprietary license.
 */
package io.camunda.connector.comprehend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.camunda.connector.generator.java.annotation.TemplateDiscriminatorProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@TemplateDiscriminatorProperty(name = "type", group = "input", label = "Choose type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  // channel
  @JsonSubTypes.Type(value = SyncData.class, name = "sync"),
  @JsonSubTypes.Type(value = ASyncData.class, name = "async")
})
public sealed interface InputData permits SyncData, ASyncData {}
