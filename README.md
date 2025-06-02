### JOLT

JOLT stands for JSON-to-JSON Transformation. Its primary purpose is to reshape, filter, and modify JSON data structures

1. provides a set of transforms, that can be "chained" together to form the overall JSON to JSON transform.
2. focuses on transforming the structure of your JSON data, not manipulating specific values
3. JOLT is widely used in data processing environments, particularly within Apache NiFi, where it functions as a powerful 
   processor for manipulating JSON flow files. It's also used for transforming data from various sources like ElasticSearch, 
   MongoDB, and Cassandra.
4. Extracting data from a large JSON document for your own consumption.

### Camunda 8 JOLT Outbound Connector

Camunda 8 JOLT Connector is a custom-built connector (developed using the Camunda Connector SDK, typically in Java) that leverages JOLT transformations as a core part of its functionality and streamlines integrations where JSON data re-structuring is a recurring requirement, offering a powerful and flexible solution by combining Camunda's connector extensibility with JOLT's transformation capabilities

### Test with SaaS

#### Setting Up the Environment:

1. Navigate to Camunda [SaaS](https://console.camunda.io).
2. Create a cluster using the latest version available.
3. Select your cluster, then go to the `API` section and click `Create new Client`.
4. Ensure the `zeebe` checkbox is selected, then click `Create`.
5. Copy the configuration details displayed under the `Spring Boot` tab.
6. Paste the copied configuration into your `application.properties` file within your project.

### Launching Your Connector

1. Start your connector by executing `io.camunda.joltconnector.LocalConnectorRuntime` in your development environment.
2. Access the Web Modeler and create a new project.
3. Click on `Create new`, then select `Upload files`. Upload the connector template from the repository you have(https://github.com/charanteja469/Camunda-JOLT-Custom-Outbound-Connector/blob/master/element-templates/JOLT%20Connector.json).
4. In the same folder, create a new BPMN diagram.
5. Design and start a process that incorporates your new connector.

