/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 *       under one or more contributor license agreements. Licensed under a proprietary license.
 *       See the License.txt file for more information. You may not use this file
 *       except in compliance with the proprietary license.
 */
package io.camunda.connector.comprehend.model;

import io.camunda.connector.generator.dsl.Property.FeelMode;
import io.camunda.connector.generator.java.annotation.TemplateProperty;
import jakarta.validation.constraints.NotNull;

public record ComprehendRequestData(
    @TemplateProperty(
            label = "Execution type",
            group = "input",
            type = TemplateProperty.PropertyType.Dropdown,
            defaultValue = "ASYNC",
            feel = FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(value = "ASYNC", label = "Asynchronous"),
              @TemplateProperty.DropdownPropertyChoice(value = "SYNC", label = "Real-time")
            },
            description = "Endpoint inference type")
        @NotNull
        ComprehendExecutionType executionType,
    @TemplateProperty(
            group = "input",
            label = "Text",
            description = "The document text to be analyzed")
        @NotNull
        String text,
    @TemplateProperty(
            label = "Document read action",
            group = "input",
            type = TemplateProperty.PropertyType.Dropdown,
            defaultValue = "TEXTRACT_DETECT_DOCUMENT_TEXT",
            feel = FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(
                  value = "TEXTRACT_DETECT_DOCUMENT_TEXT",
                  label = "Detect document text"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "TEXTRACT_ANALYZE_DOCUMENT",
                  label = "Analyze document")
            },
            description = "TODO")
        @NotNull
        ComprehendDocumentReadAction documentReadAction,
    @TemplateProperty(
            label = "Document read mode",
            group = "input",
            type = TemplateProperty.PropertyType.Dropdown,
            defaultValue = "SERVICE_DEFAULT",
            feel = FeelMode.disabled,
            choices = {
              @TemplateProperty.DropdownPropertyChoice(
                  value = "SERVICE_DEFAULT",
                  label = "Default"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "FORCE_DOCUMENT_READ_ACTION",
                  label = "Force document read action")
            },
            description = "TODO")
        @NotNull
        ComprehendDocumentReadMode documentReadMode,
    @TemplateProperty(
            label = "Analyze tables",
            group = "input",
            type = TemplateProperty.PropertyType.Boolean,
            defaultValueType = TemplateProperty.DefaultValueType.Boolean,
            defaultValue = "true")
        @NotNull
        boolean featureTypeTables,
    @TemplateProperty(
            label = "Analyze forms",
            group = "input",
            type = TemplateProperty.PropertyType.Boolean,
            defaultValueType = TemplateProperty.DefaultValueType.Boolean,
            defaultValue = "true")
        @NotNull
        boolean featureTypeForms,
    @TemplateProperty(
            label = "Input S3 URI",
            group = "input",
            description = "The Amazon S3 URI for the input data",
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        @NotNull
        String inputS3Uri,
    @TemplateProperty(
            label = "Input Format",
            group = "input",
            type = TemplateProperty.PropertyType.Dropdown,
            feel = FeelMode.disabled,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"),
            choices = {
              @TemplateProperty.DropdownPropertyChoice(
                  value = "ONE_DOC_PER_FILE",
                  label = "Each file is considered a separate document"),
              @TemplateProperty.DropdownPropertyChoice(
                  value = "ONE_DOC_PER_LINE",
                  label = "Each line in a file is considered a separate document")
            },
            description = "Specifies how the text in an input file should be processed")
        ComprehendInputFormat comprehendInputFormat,
    @TemplateProperty(
            group = "input",
            label = "Endpoint ARN",
            description = "The Amazon Resource Number (ARN) of the endpoint")
        @NotNull
        String endpointArn,
    @TemplateProperty(
            group = "input",
            label = "Client request token",
            description = "A unique identifier for the request",
            optional = true,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        String clientRequestToken,
    @TemplateProperty(
            group = "input",
            label = "Data Access Role ARN",
            description =
                "The ARN of the IAM role that grants Amazon Comprehend read access to your input data",
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        @NotNull
        String dataAccessRoleArn,
    @TemplateProperty(
            group = "input",
            label = "Document Classifier ARN",
            description = "The ARN of the document classifier to use to process the job",
            optional = true,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        String documentClassifierArn,
    @TemplateProperty(
            group = "input",
            label = "Flywheel ARN",
            description = "The ARN of the flywheel associated with the model",
            optional = true,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        String flywheelArn,
    @TemplateProperty(
            group = "input",
            label = "Job name",
            description = "The identifier of the job",
            optional = true,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        String jobName,
    @TemplateProperty(
            group = "input",
            label = "Output S3 URI",
            description = "S3 location where the date will be written",
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        @NotNull
        String outputS3Uri,
    @TemplateProperty(
            group = "input",
            label = "Output Kms Key Id",
            description = "KMS key id used for encrypt the output result",
            optional = true,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        String outputKmsKeyId,
    @TemplateProperty(
            group = "input",
            label = "Tags",
            description =
                "Tag consist of key and value.\n Use <:> as a separator between key "
                    + "and value, and < , > as a separator between tags.",
            optional = true,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        String tags,
    @TemplateProperty(
            group = "input",
            label = "Volume Kms Key Id",
            description =
                "KMS that Amazon Comprehend uses to encrypt data on the storage volume \n"
                    + " attached to the ML compute instance",
            optional = true,
            condition =
                @TemplateProperty.PropertyCondition(
                    property = "input.executionType",
                    equals = "ASYNC"))
        String volumeKmsKeyId) {}
