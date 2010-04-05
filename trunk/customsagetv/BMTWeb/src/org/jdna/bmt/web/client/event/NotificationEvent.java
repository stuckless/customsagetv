package org.jdna.bmt.web.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NotificationEvent extends GwtEvent<NotificationEventHandler> {
    public static final GwtEvent.Type<NotificationEventHandler> TYPE =  new GwtEvent.Type<NotificationEventHandler>();

    public static enum MessageType {INFO, ERROR, WARN}
    
    private MessageType messageType = MessageType.INFO;
    private String message;
    private Throwable exception;
    
    public NotificationEvent(String message) {
        this(MessageType.INFO, message, null);
    }
    
    public NotificationEvent(MessageType messageType, String message) {
        this(messageType, message,null);
    }
    
    public NotificationEvent(MessageType messageType, String message, Throwable ex) {
        this.message=message;
        this.exception=ex;
        this.messageType = messageType;
    }
    
    @Override
    protected void dispatch(NotificationEventHandler handler) {
        handler.onNotification(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<NotificationEventHandler> getAssociatedType() {
        return TYPE;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getException() {
        return exception;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
