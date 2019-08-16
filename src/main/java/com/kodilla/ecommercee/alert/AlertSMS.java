package com.kodilla.ecommercee.alert;

import com.kodilla.ecommercee.domain.User;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.MessageStatus;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.messages.TextMessage;

public class AlertSMS {

    public boolean sendSms(User user) {

        NexmoClient client = NexmoClient.builder().apiKey("").apiSecret("").build();

        TextMessage message = new TextMessage("Ecommercee", user.getPhoneNumber(), "Your order has been placed.");

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
            return true;
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
            return false;
        }
    }
}
