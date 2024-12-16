package com.pdtech.talentnet.user.repositories;

import com.pdtech.talentnet.user.models.User;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserRepository {

    private final DynamoDbClient dynamoDbClient;
    private static final String TABLE_NAME = "talent-net-users";

    public UserRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public List<User> getAllUsers() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        // Perform the scan operation
        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);

        // Extract and convert the result into a List of User objects
        List<User> users = new ArrayList<>();
        for (Map<String, AttributeValue> item : scanResponse.items()) {
            User user = mapItemToUser(item);
            users.add(user);
        }

        return users;
    }

    private User mapItemToUser(Map<String, AttributeValue> item) {
        User user = new User();
        user.setUserid(item.get("userid").s());  // Assuming the partition key is "id"
        user.setName(item.get("name").s());  // Assuming "name" attribute is present
        user.setEmail(item.get("email").s());  // Assuming "age" attribute is present
        return user;
    }

}
