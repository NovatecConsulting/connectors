/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *       under one or more contributor license agreements. Licensed under a proprietary license.
 *       See the License.txt file for more information. You may not use this file
 *       except in compliance with the proprietary license.
 */
package io.camunda.connector.comprehend.model;

import io.camunda.connector.aws.model.impl.AwsBaseRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ComprehendRequest extends AwsBaseRequest {

  @Valid @NotNull private InputData input;

  public @Valid @NotNull InputData getInput() {
    return input;
  }

  public void setInput(final @Valid @NotNull InputData input) {
    this.input = input;
  }
}
