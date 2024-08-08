/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *       under one or more contributor license agreements. Licensed under a proprietary license.
 *       See the License.txt file for more information. You may not use this file
 *       except in compliance with the proprietary license.
 */
package io.camunda.connector.textract.suppliers.util;

import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractAsync;
import com.amazonaws.services.textract.AmazonTextractAsyncClientBuilder;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import io.camunda.connector.aws.CredentialsProviderSupport;
import io.camunda.connector.textract.model.TextractRequest;

public class AmazonTextractClientUtil {

  private AmazonTextractClientUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static AmazonTextract getSyncTextractClient(final TextractRequest request) {
    return AmazonTextractClientBuilder.standard()
        .withCredentials(CredentialsProviderSupport.credentialsProvider(request))
        .withRegion(request.getConfiguration().region())
        .build();
  }

  public static AmazonTextractAsync getAsyncTextractClient(final TextractRequest request) {
    return AmazonTextractAsyncClientBuilder.standard()
        .withCredentials(CredentialsProviderSupport.credentialsProvider(request))
        .withRegion(request.getConfiguration().region())
        .build();
  }
}
