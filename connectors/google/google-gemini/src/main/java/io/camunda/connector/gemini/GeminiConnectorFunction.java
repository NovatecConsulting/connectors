/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *             under one or more contributor license agreements. Licensed under a proprietary license.
 *             See the License.txt file for more information. You may not use this file
 *             except in compliance with the proprietary license.
 */

package io.camunda.connector.gemini;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.vertexai.Transport;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.*;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseStream;
import com.google.common.collect.ImmutableMap;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.gemini.model.GeminiRequest;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@OutboundConnector(
    name = "Google Gemini Outbound Connector",
    inputVariables = {"authentication", "configuration", "input"},
    type = "io.camunda:google-gemini:1")
@ElementTemplate(
    id = "io.camunda.connectors.GoogleGemini.v1",
    name = "GoogleGemini Outbound Connector",
    description =
        " A large language model (LLM) created by Google AI. It's a multimodal model, meaning it can understand"
            + " and work with different types of information like text, code, audio, images, and video",
    inputDataClass = GeminiRequest.class,
    version = 1,
    propertyGroups = {
      @ElementTemplate.PropertyGroup(id = "authentication", label = "Authentication"),
      @ElementTemplate.PropertyGroup(id = "input", label = "Configure input")
    },
    documentationRef = "", // toDo
    icon = "" // toDo
    )
public class GeminiConnectorFunction implements OutboundConnectorFunction {
  @Override
  public Object execute(OutboundConnectorContext context) throws Exception {
    // toDo tempo code just to check api call
    VertexAI.Builder builder =
        new VertexAI.Builder()
            .setProjectId("silicon-bolt-438910-q6")
            .setLocation("us-central1")
            .setTransport(Transport.GRPC)
            //   .setScopes(List.of("https://www.googleapis.com/auth/cloud-platform")) // todo
            .setCustomHeaders(ImmutableMap.of())
            .setCredentials(getCredentials());

    //        try (VertexAI vertexAi = new VertexAI("silicon-bolt-438910-q6", "us-central1"); ) {
    try (VertexAI vertexAi = builder.build()) {
      GenerationConfig generationConfig =
          GenerationConfig.newBuilder()
              .setMaxOutputTokens(8192)
              .setTemperature(1F)
              .setTopP(0.95F)
              .build();
      List<SafetySetting> safetySettings =
          Arrays.asList(
              SafetySetting.newBuilder()
                  .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                  .setThreshold(SafetySetting.HarmBlockThreshold.OFF)
                  .build(),
              SafetySetting.newBuilder()
                  .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                  .setThreshold(SafetySetting.HarmBlockThreshold.OFF)
                  .build(),
              SafetySetting.newBuilder()
                  .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                  .setThreshold(SafetySetting.HarmBlockThreshold.OFF)
                  .build(),
              SafetySetting.newBuilder()
                  .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                  .setThreshold(SafetySetting.HarmBlockThreshold.OFF)
                  .build());
      GenerativeModel model =
          new GenerativeModel.Builder()
              .setModelName("gemini-1.5-flash-002")
              .setVertexAi(vertexAi)
              .setGenerationConfig(generationConfig)
              .setSafetySettings(safetySettings)
              .build();

      var content = ContentMaker.fromMultiModalData("tell a story");
      var video1 =
          PartMaker.fromMimeTypeAndData(
              "video/*", "https://youtube.com/shorts/0aBihVlVKOI?si=HdU89Cx0xYdbs2xW");

      ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(content);

      // Do something with the response
      responseStream.stream().collect(Collectors.toSet());
    }

    return null;
  }

  public static GoogleCredentials getCredentials() throws Exception {

    GoogleCredentials credentials =
        ServiceAccountCredentials.fromStream(
                new FileInputStream("/Users/vdenov/Desktop/silicon-bolt.json"))
            .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

    credentials.refreshIfExpired();

    return credentials;
  }
}
