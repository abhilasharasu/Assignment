package utils;

import java.util.UUID;

public class PayloadBuilder {

    // Valid Payload
    public static String validPayload() {

        return "{\n" +
                "\"api_key\":\"sk_test_12345\",\n" +
                "\"mock_id_number\":\"999988887777\",\n" +
                "\"device_data\":\"android_14\",\n" +
                "\"request_id\":\"" + generateRequestId() + "\",\n" +
                "\"timestamp\":\"" + System.currentTimeMillis() + "\"\n" +
                "}";
    }

    // Invalid Empty Payload
    public static String invalidPayload() {

        return "{\n" +
                "\"api_key\":\"\",\n" +
                "\"mock_id_number\":\"\",\n" +
                "\"device_data\":\"\",\n" +
                "\"request_id\":\"\",\n" +
                "\"timestamp\":\"\"\n" +
                "}";
    }

    // Invalid API Key Payload
    public static String invalidApiKeyPayload() {

        return "{\n" +
                "\"api_key\":\"invalid_key\",\n" +
                "\"mock_id_number\":\"999988887777\",\n" +
                "\"device_data\":\"android_14\",\n" +
                "\"request_id\":\"" + generateRequestId() + "\",\n" +
                "\"timestamp\":\"" + System.currentTimeMillis() + "\"\n" +
                "}";
    }

    // Invalid Aadhaar Payload
    public static String invalidAadhaarPayload() {

        return "{\n" +
                "\"api_key\":\"sk_test_12345\",\n" +
                "\"mock_id_number\":\"123\",\n" +
                "\"device_data\":\"android_14\",\n" +
                "\"request_id\":\"" + generateRequestId() + "\",\n" +
                "\"timestamp\":\"" + System.currentTimeMillis() + "\"\n" +
                "}";
    }

    // Missing Device Data Payload
    public static String missingDevicePayload() {

        return "{\n" +
                "\"api_key\":\"sk_test_12345\",\n" +
                "\"mock_id_number\":\"999988887777\",\n" +
                "\"request_id\":\"" + generateRequestId() + "\",\n" +
                "\"timestamp\":\"" + System.currentTimeMillis() + "\"\n" +
                "}";
    }

    // Dynamic Request ID Generator
    private static String generateRequestId() {

        return UUID.randomUUID().toString();
    }
}