package com.google.android.gms.nearby.messages.samples.nearbydevices;

import com.google.android.gms.nearby.messages.Message;
import com.google.gson.Gson;

import java.nio.charset.Charset;
import java.util.Random;

class DeviceMessage {
    private static final Gson gson = new Gson();

    private final int major;
    private final int minor;

    static Message newNearbyMessage() {
        DeviceMessage deviceMessage = new DeviceMessage(new Random().nextInt(100), 100 + new Random().nextInt(100));
        return new Message(gson.toJson(deviceMessage).getBytes(Charset.forName("UTF-8")));
    }

    static DeviceMessage fromNearbyMessage(Message message) {
        String nearbyMessageString = new String(message.getContent()).trim();
        return gson.fromJson(
                (new String(nearbyMessageString.getBytes(Charset.forName("UTF-8")))),
                DeviceMessage.class);
    }

    private DeviceMessage(int a, int b) {
        major = a;
        minor = b;
    }

    String getMessageBody() {
        return gson.toJson(this);
    }
}