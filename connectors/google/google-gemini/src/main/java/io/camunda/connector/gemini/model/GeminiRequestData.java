/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *             under one or more contributor license agreements. Licensed under a proprietary license.
 *             See the License.txt file for more information. You may not use this file
 *             except in compliance with the proprietary license.
 */
package io.camunda.connector.gemini.model;

import io.camunda.connector.generator.dsl.Property;
import io.camunda.connector.generator.java.annotation.TemplateProperty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record GeminiRequestData(
    @TemplateProperty(
            label = "Insert media",
            group = "input",
            description = "Media link.",
            feel = Property.FeelMode.disabled,
            optional = true)
        String contentURL,
    @TemplateProperty(
            label = "File type",
            group = "input",
            description = "Select file type.",
            feel = Property.FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(value = "TEXT", label = "Text"),
              @TemplateProperty.DropdownPropertyChoice(value = "VIDEO", label = "Video"),
              @TemplateProperty.DropdownPropertyChoice(value = "IMAGE", label = "Image"),
              @TemplateProperty.DropdownPropertyChoice(value = "DOCUMENT", label = "Document"),
              @TemplateProperty.DropdownPropertyChoice(value = "AUDIO", label = "Audio"),
            },
            optional = true)
        FileType fileType,
    @TemplateProperty(
            label = "Prompt",
            group = "input",
            description = "Insert prompt.",
            feel = Property.FeelMode.disabled)
        @NotNull
        String contentText,
    @TemplateProperty(
            label = "System instructions",
            group = "input",
            description = "System instructions inform how the model should respond.",
            feel = Property.FeelMode.disabled,
            tooltip =
                "System instructions inform how the model should respond."
                    + " Use them to give the model context to understand the task, "
                    + "provide more custom responses and adhere to specific guidelines. "
                    + "Instructions apply each time you send a request to the model."
                    + "<a href=\"https://cloud.google.com/vertex-ai/generative-ai/docs/learn/prompts/system-instructions?hl=en\" Learn more about system instructions </a>",
            optional = true)
        String systemInstrText,
    @TemplateProperty(
            label = "Grounding",
            group = "input",
            description = "Source.",
            tooltip =
                "Grounding connects model output to verifiable sources of information. "
                    + "This is useful in situations where accuracy and reliability are important."
                    + "<a href=\"https://cloud.google.com/vertex-ai/generative-ai/docs/grounding/overview?hl=en\" Learn more about grounding </a>",
            feel = Property.FeelMode.disabled,
            optional = true)
        boolean grounding,
    @TemplateProperty(
            label = "datastore",
            group = "input",
            description = "Vertex AI datastore path",
            feel = Property.FeelMode.disabled,
            optional = true)
        String dataStorePath,
    @TemplateProperty(
            label = "datastore",
            group = "input",
            description = "Vertex AI datastore path",
            optional = true,
            tooltip =
                "A stop sequence is a series of characters (including spaces) that stops response generation if the model encounters it."
                    + " The sequence is not included as part of the response. You can add up to five stop sequences.")
        List<String> stopSequences,
    @TemplateProperty(
            label = "Hate speech",
            group = "input",
            feel = Property.FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(value = "OFF", label = "OFF"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_ONLY_HIGH",
                  label = "Block few"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_MEDIUM_AND_ABOVE",
                  label = "Block some"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_LOW_AND_ABOVE",
                  label = "Block most"),
            },
            tooltip =
                "You can adjust the likelihood of receiving a model response that could contain harmful content. "
                    + "Content is blocked based on the probability that it's harmful."
                    + "<a href=\"https://cloud.google.com/vertex-ai/docs/generative-ai/learn/responsible-ai?hl=en#safety_filters_and_attributes\" Learn more </a>",
            optional = true)
        BlockingDegree hateSpeach,
    @TemplateProperty(
            label = "Dangerous content",
            group = "input",
            feel = Property.FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(value = "OFF", label = "OFF"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_ONLY_HIGH",
                  label = "Block few"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_MEDIUM_AND_ABOVE",
                  label = "Block some"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_LOW_AND_ABOVE",
                  label = "Block most"),
            },
            tooltip =
                "You can adjust the likelihood of receiving a model response that could contain harmful content. "
                    + "Content is blocked based on the probability that it's harmful."
                    + "<a href=\"https://cloud.google.com/vertex-ai/docs/generative-ai/learn/responsible-ai?hl=en#safety_filters_and_attributes\" Learn more </a>",
            optional = true)
        BlockingDegree dangerousContent,
    @TemplateProperty(
            label = "Sexually explicit content",
            group = "input",
            feel = Property.FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(value = "OFF", label = "OFF"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_ONLY_HIGH",
                  label = "Block few"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_MEDIUM_AND_ABOVE",
                  label = "Block some"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_LOW_AND_ABOVE",
                  label = "Block most"),
            },
            tooltip =
                "You can adjust the likelihood of receiving a model response that could contain harmful content. "
                    + "Content is blocked based on the probability that it's harmful."
                    + "<a href=\"https://cloud.google.com/vertex-ai/docs/generative-ai/learn/responsible-ai?hl=en#safety_filters_and_attributes\" Learn more </a>",
            optional = true)
        BlockingDegree sexuallyExplicit,
    @TemplateProperty(
            label = "Harassment content",
            group = "input",
            feel = Property.FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(value = "OFF", label = "OFF"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_ONLY_HIGH",
                  label = "Block few"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_MEDIUM_AND_ABOVE",
                  label = "Block some"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "BLOCK_LOW_AND_ABOVE",
                  label = "Block most"),
            },
            tooltip =
                "You can adjust the likelihood of receiving a model response that could contain harmful content. "
                    + "Content is blocked based on the probability that it's harmful."
                    + "<a href=\"https://cloud.google.com/vertex-ai/docs/generative-ai/learn/responsible-ai?hl=en#safety_filters_and_attributes\" Learn more </a>",
            optional = true)
        BlockingDegree harassment,
    @TemplateProperty(
            label = "Temperature",
            group = "input",
            feel = Property.FeelMode.disabled,
            optional = true,
            tooltip =
                "Temperature controls the randomness in token selection.\n"
                    + "A lower temperature is good when you expect a true or correct response. \n"
                    + "A temperature of 0 means the highest probability token is usually selected.\n"
                    + "A higher temperature can lead to diverse or unexpected results. Some models have a higher temperature max to encourage more random responses.")
        float temperature,
    @TemplateProperty(
            label = "Output token limit",
            group = "input",
            feel = Property.FeelMode.disabled,
            optional = true,
            tooltip =
                "Output token limit determines the maximum amount of text output from one prompt. "
                    + "A token is approximately four characters.")
        int maxOutputTokens,
    @TemplateProperty(
            label = "Seed",
            group = "input",
            feel = Property.FeelMode.disabled,
            optional = true,
            tooltip =
                "Setting a seed value is useful when you make repeated requests and want the same model response.\n"
                    + "Deterministic outcome isn’t guaranteed. Changing the model or other settings can cause variations "
                    + "in the response even when you use the same seed value.")
        int seed,
    @TemplateProperty(
            label = "Top-K ",
            group = "input",
            feel = Property.FeelMode.disabled,
            optional = true,
            tooltip =
                "Top-K specifies the number of candidate tokens when the model is selecting an output token. "
                    + "Use a lower value for less random responses and a higher value for more random responses.")
        float topK,
    @TemplateProperty(
            label = "Top-P ",
            group = "input",
            description = "Vertex AI datastore path",
            feel = Property.FeelMode.disabled,
            optional = true,
            tooltip =
                "Top-p changes how the model selects tokens for output."
                    + " Tokens are selected from most probable to least until the sum of their probabilities equals the top-p value."
                    + " For example, if tokens A, B, and C have a probability of .3, .2, and .1 and the top-p value is .5, then the model will select either A or B as the next token (using temperature)."
                    + " For the least variable results, set top-P to 0.")
        float topP) {}
