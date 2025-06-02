package io.camunda.joltconnector.dto;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateProperty.PropertyType;

public record MyJoltConnectorRequest(@TemplateProperty(group = "compose",
        type = PropertyType.Text)Object inputJson, Object joltSpec) {}
