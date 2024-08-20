/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
package io.camunda.connector.email.protocols.actions.smtp;

import io.camunda.connector.generator.dsl.Property;
import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateSubType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@TemplateSubType(id = "sendEmailSmtp", label = "Send Email using SMTP")
public final class SmtpSendEmail implements SmtpAction {
  @TemplateProperty(
      label = "test",
      group = "sendEmailSmtp",
      id = "data.smtpAction.test",
      description = "",
      feel = Property.FeelMode.optional)
  @Valid
  @NotNull
  String test;

  public @Valid @NotNull String getTest() {
    return test;
  }

  public void setTest(@Valid @NotNull String test) {
    this.test = test;
  }
}
