
# Camunda 8 JOLT Custom Outbound Connector

Camunda 8 JOLT Connector is a custom-built connector (developed using the Camunda Connector SDK, typically in Java) that leverages JOLT transformations as a core part of its functionality and streamlines integrations where JSON data re-structuring is a recurring requirement, offering a powerful and flexible solution by combining Camunda's connector extensibility with JOLT's transformation capabilities

![image](https://github.com/user-attachments/assets/5c653fa0-50e0-4b21-88cc-e604c2538be8)


### JOLT

JOLT stands for JSON-to-JSON Transformation. Its primary purpose is to reshape, filter, and modify JSON data structures

1. provides a set of transforms, that can be "chained" together to form the overall JSON to JSON transform.
2. focuses on transforming the structure of your JSON data, not manipulating specific values
3. JOLT is widely used in data processing environments, particularly within Apache NiFi, where it functions as a powerful 
   processor for manipulating JSON flow files. It's also used for transforming data from various sources like ElasticSearch, 
   MongoDB, and Cassandra.
4. Extracting data from a large JSON document for your own consumption.

   you can check the JOLT here https://jolt-demo.appspot.com/

## Test with SaaS and Self-Managed

#### Setting Up the Saas Environment:

1. Navigate to Camunda [SaaS](https://console.camunda.io).
2. Create a cluster using the latest version available.
3. Select your cluster, then go to the `API` section and click `Create new Client`.
4. Ensure the `zeebe` checkbox is selected, then click `Create`.
5. Copy the configuration details displayed under the `Spring Boot` tab.
6. Paste the copied configuration into your `application.properties` file within your project.

#### Setting Up the Self-Managed Environment:

1. Set up the Camunda 8 Self-Managed(https://docs.camunda.io/docs/self-managed/setup/deploy/local/docker-compose/).
2. Cluster endpoint is http://localhost:26500
3. uncomment the properties in test resource folder
4. download desktop modeler if requires (https://camunda.com/download/modeler/)


### Launching Your Connector

1. Start your connector by executing `io.camunda.joltconnector.LocalConnectorRuntime` in your development environment.
2. Access the Web Modeler or Desktop Modeler and create a new project.
3. Click on `Create new`, then select `Upload files`. Upload the connector template from the repository you have(https://github.com/charanteja469/Camunda-JOLT-Custom-Outbound-Connector/blob/master/element-templates/JOLT%20Connector.json).

 NOTE: if your using Desktop modeler--> go to modeler folder-->resources-->element-templates-->Past the above downloaded JOLT 
       Connector Template

4. In the same folder, create a new BPMN diagram.
5. Design and start a process that incorporates your new connector.


# STEP BY STEP Process to Configure and Use JOLT Connector

1. Create a workflow with Start event, Task, End Event
2. select the task and click on element change type and search for JOLT Connector

![image](https://github.com/user-attachments/assets/f4d49c86-54ea-4926-acaa-f9867549da3b)


3. provide the input Json data

![image](https://github.com/user-attachments/assets/476c00c5-1bb1-491a-8b51-489d709a4861)

#### Input json:

{
  "customerID": "CUST1316834",
  "firstName": "Charan",
  "lastName": "Teja",
  "contact": {
    "email": "charan.teja@example.com",
    "phone": "9876543210"
  },
  "address": {
    "street": "123 Main St",
    "city": "Anytown",
    "zipCode": "12345"
  },
  "accountInfo": {
    "accountNumber": "ACC987654321",
    "accountType": "Savings",
    "balance": 10000.75
  }}


4. provide the JSON Specification

![image](https://github.com/user-attachments/assets/c9a11612-c006-4052-9c3b-3676780f9071)

#### JSON Specification:

[
  {
    "operation": "shift",
    "spec": {
      "customerID": "customer.id",
      "firstName": null,
      "lastName": null,
      "contact": {
        "email": "customer.contact.email",
        "phone": "customer.contact.phone"
      },
      "address": {
        "street": "customer.address.street",
        "city": "customer.address.city",
        "zipCode": "customer.address.postalCode"
      },
      "accountInfo": {
        "accountNumber": "customer.account.number",
        "accountType": "customer.account.type"
      }
    }
  },
  {
    "operation": "modify-overwrite-beta",
    "spec": {
      "customer": {
        "fullName": "=concat(@(1,firstName), ' ', @(1,lastName))"
      }
    }
  }
]



5. Configure the output Result Expression

![image](https://github.com/user-attachments/assets/2eb27b2f-5c9c-41a1-8b99-1baefeaae065)

#### Result Expression : 

{"Transformed":myProperty}


6. Deploy the process and Start the Process
7. Start the Connector Runtime

   ![image](https://github.com/user-attachments/assets/e8475a72-2ae1-4ca9-81d0-7e138ff3f65f)


![image](https://github.com/user-attachments/assets/fb90d6b3-b817-4089-96f3-b3588164f1df)


8. The Connector executed sucessfully. Now check the JOLT Transformed output in the operate and also notice the message in the 
   Connector springboot project

 ![image](https://github.com/user-attachments/assets/27f4154f-1748-4526-bde4-f9fa668de20f)

 ![image](https://github.com/user-attachments/assets/a23632b0-c96c-46db-a588-ad32420faa09)


#### Transformed Output:

 	
{"customer":{"fullName":" ","contact":{"email":"charan.teja@example.com","phone":"9876543210"},"id":"CUST1316834","address":{"street":"123 Main St","city":"Anytown","postalCode":"12345"},"account":{"number":"ACC987654321","type":"Savings"}}}


## Refer Camunda BPMN File

you can refer the Camunda BPMN file here https://github.com/charanteja469/Camunda-JOLT-Custom-Outbound-Connector/blob/master/JOLT_Connector.bpmn

 










