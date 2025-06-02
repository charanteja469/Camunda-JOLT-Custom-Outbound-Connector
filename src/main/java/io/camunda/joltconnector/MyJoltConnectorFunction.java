package io.camunda.joltconnector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import io.camunda.joltconnector.dto.JoltTransform;
import io.camunda.joltconnector.dto.MyJoltConnectorRequest;
import io.camunda.joltconnector.dto.MyJoltConnectorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
        name = "JOLT Connector",
        inputVariables = {"inputJson", "joltSpec"},
        type = "io.camunda:template:1")
@ElementTemplate(
        id = "349377b0-b54d-494c-812b-60396960d0f7",
        name = "JOLT Connector",
        version = 1,
        description = "Camunda 8 Jolt Custom Connector uses the JOLT library for dynamic JSON data transformations.",
        inputDataClass = MyJoltConnectorRequest.class)
public class MyJoltConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyJoltConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) throws JsonProcessingException {
    final var connectorRequest = context.bindVariables(MyJoltConnectorRequest.class);
    return executeConnector(connectorRequest);
  }

  private final ObjectMapper mapper = new ObjectMapper();

  private MyJoltConnectorResult executeConnector(final MyJoltConnectorRequest connectorRequest) throws JsonProcessingException {

    Object inputJsonObj = connectorRequest.inputJson();
    Object joltSpec =connectorRequest.joltSpec();
    JoltTransform joltTransform = new JoltTransform();
    LOGGER.info("Executing my connector with request {}", connectorRequest);
    Object output=joltTransform.transform(inputJsonObj,joltSpec);
    return new MyJoltConnectorResult(output);
  }
}
