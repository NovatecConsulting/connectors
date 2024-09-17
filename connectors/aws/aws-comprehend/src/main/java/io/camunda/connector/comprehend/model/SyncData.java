/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *       under one or more contributor license agreements. Licensed under a proprietary license.
 *       See the License.txt file for more information. You may not use this file
 *       except in compliance with the proprietary license.
 */
package io.camunda.connector.comprehend.model;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateSubType;

@TemplateSubType(label = "Sync", id = "sync")
public record SyncData(
    @TemplateProperty(group = "input") String syncData,
    @TemplateProperty(group = "input") Boolean boo,
    @TemplateProperty(group = "input", label = "SAME") String same)
    implements InputData {}
