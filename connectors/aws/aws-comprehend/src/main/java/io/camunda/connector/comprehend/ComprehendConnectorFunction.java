/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *       under one or more contributor license agreements. Licensed under a proprietary license.
 *       See the License.txt file for more information. You may not use this file
 *       except in compliance with the proprietary license.
 */
package io.camunda.connector.comprehend;

import com.amazonaws.services.comprehend.model.*;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.comprehend.caller.SyncComprehendCaller;
import io.camunda.connector.comprehend.model.ASyncData;
import io.camunda.connector.comprehend.model.ComprehendRequest;
import io.camunda.connector.comprehend.model.ComprehendRequestData;
import io.camunda.connector.comprehend.model.InputData;
import io.camunda.connector.comprehend.model.SyncData;
import io.camunda.connector.comprehend.supplier.ComprehendClientSupplier;
import io.camunda.connector.generator.java.annotation.ElementTemplate;

@OutboundConnector(
    name = "AWS Comprehend",
    inputVariables = {"authentication", "configuration", "input"},
    type = "io.camunda:aws-comprehend:1")
@ElementTemplate(
    id = "io.camunda.connectors.AWSCOMPREHEND.v1",
    name = "AWS Comprehend Outbound Connector",
    description = "Execute Comprehend models",
    inputDataClass = ComprehendRequest.class,
    version = 1,
    propertyGroups = {
      @ElementTemplate.PropertyGroup(id = "authentication", label = "Authentication"),
      @ElementTemplate.PropertyGroup(id = "configuration", label = "Configuration"),
      @ElementTemplate.PropertyGroup(id = "input", label = "Configure input")
    },
    documentationRef =
        "https://docs.camunda.io/docs/next/components/connectors/out-of-the-box-connectors/amazon-comprehend/",
    icon = "icon.svg")
public class ComprehendConnectorFunction implements OutboundConnectorFunction {

  private ComprehendClientSupplier clientSupplier;

  private SyncComprehendCaller syncComprehendCaller;

  public ComprehendConnectorFunction(
      ComprehendClientSupplier clientSupplier, SyncComprehendCaller syncComprehendCaller) {
    this.clientSupplier = clientSupplier;
    this.syncComprehendCaller = syncComprehendCaller;
  }

  @Override
  public Object execute(OutboundConnectorContext context) throws Exception {
    var request = context.bindVariables(ComprehendRequest.class);

    InputData input = request.getInput();
    if (input instanceof SyncData) {

    var client = clientSupplier.getSyncClient(request);

    return syncComprehendCaller.call(client , input);

    } else {
      throw new IllegalArgumentException();
    }

    return null;
  }

  private DocumentReaderConfig prepareDocumentReaderConfig(ComprehendRequestData requestData) {
    return new DocumentReaderConfig()
        .withDocumentReadAction(requestData.documentReadAction().name())
        .withDocumentReadMode(requestData.documentReadMode().name());
  }
}
