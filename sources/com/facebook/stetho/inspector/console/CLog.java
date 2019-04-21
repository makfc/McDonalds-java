package com.facebook.stetho.inspector.console;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console.ConsoleMessage;
import com.facebook.stetho.inspector.protocol.module.Console.MessageAddedRequest;
import com.facebook.stetho.inspector.protocol.module.Console.MessageLevel;
import com.facebook.stetho.inspector.protocol.module.Console.MessageSource;

public class CLog {
    private static final String TAG = "CLog";

    public static void writeToConsole(ChromePeerManager chromePeerManager, MessageLevel logLevel, MessageSource messageSource, String messageText) {
        LogRedirector.m7433d(TAG, messageText);
        ConsoleMessage message = new ConsoleMessage();
        message.source = messageSource;
        message.level = logLevel;
        message.text = messageText;
        MessageAddedRequest messageAddedRequest = new MessageAddedRequest();
        messageAddedRequest.message = message;
        chromePeerManager.sendNotificationToPeers("Console.messageAdded", messageAddedRequest);
    }

    public static void writeToConsole(MessageLevel logLevel, MessageSource messageSource, String messageText) {
        ConsolePeerManager peerManager = ConsolePeerManager.getInstanceOrNull();
        if (peerManager != null) {
            writeToConsole(peerManager, logLevel, messageSource, messageText);
        }
    }
}
