/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
package io.camunda.connector.slack.outbound.model;

import com.slack.api.methods.response.conversations.ConversationsCreateResponse;
import io.camunda.connector.slack.outbound.SlackResponse;
import io.camunda.connector.slack.outbound.dto.Conversation;

public record ConversationsCreateSlackResponse(Conversation channel) implements SlackResponse {

  public ConversationsCreateSlackResponse(ConversationsCreateResponse response) {
    this(new Conversation(response.getChannel()));
  }
}
