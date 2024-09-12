/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *       under one or more contributor license agreements. Licensed under a proprietary license.
 *       See the License.txt file for more information. You may not use this file
 *       except in compliance with the proprietary license.
 */
package io.camunda.connector.comprehend.caller;

import com.amazonaws.services.comprehend.AmazonComprehendClient;
import com.amazonaws.services.comprehend.model.ClassifyDocumentRequest;
import com.amazonaws.services.comprehend.model.DocumentReaderConfig;
import io.camunda.connector.comprehend.model.ComprehendRequestData;

public class SyncComprehendCaller {

  public Object call(
      AmazonComprehendClient amazonComprehendClient, ComprehendRequestData requestData) {
    ClassifyDocumentRequest classifyDocumentRequest =
        new ClassifyDocumentRequest()
            .withText(requestData.text())
            .withEndpointArn(requestData.endpointArn())
            .withDocumentReaderConfig(prepareDocumentReaderConfig(requestData));

    return amazonComprehendClient.classifyDocument(classifyDocumentRequest);
  }

  private DocumentReaderConfig prepareDocumentReaderConfig(ComprehendRequestData requestData) {
    return new DocumentReaderConfig()
        .withDocumentReadAction(requestData.documentReadAction().name())
        .withDocumentReadMode(requestData.documentReadMode().name());
  }
}
