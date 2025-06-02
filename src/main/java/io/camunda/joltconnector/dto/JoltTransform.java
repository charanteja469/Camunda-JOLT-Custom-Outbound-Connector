package io.camunda.joltconnector.dto;

import com.bazaarvoice.jolt.Chainr;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JoltTransform {

    private final ObjectMapper mapper = new ObjectMapper();

    public Object transform(Object inputJsonObj1, Object joltSpec1) throws JsonProcessingException {

        Object inputJsonObj = inputJsonObj1;

        String inputJson;

        if (inputJsonObj instanceof String) {
            inputJson = (String) inputJsonObj;
        } else if (inputJsonObj instanceof Map) {
            inputJson = mapper.writeValueAsString(inputJsonObj);
        } else {
            throw new IllegalArgumentException("inputJson must be a String or Map, but got: " + inputJsonObj.getClass());
        }

        Map<String, Object> inputMap = mapper.readValue(inputJson, Map.class);

        List<Object> joltSpec = (List<Object>) joltSpec1 ;

        Chainr chainr = Chainr.fromSpec(joltSpec);
        Object transformedOutput = chainr.transform(inputMap);

        System.out.println("JOLT Transformation Completed");


        return transformedOutput;
    }
}
