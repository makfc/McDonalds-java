package com.newrelic.agent.android.payload;

import com.newrelic.agent.android.payload.PayloadSender.CompletionHandler;
import java.util.concurrent.Callable;

class PayloadReaper implements Callable<PayloadSender> {
    final CompletionHandler handler;
    final PayloadSender sender;

    public PayloadReaper(PayloadSender sender, CompletionHandler handler) {
        if (sender == null) {
            throw new NullPointerException("Must provide payload sender!");
        }
        this.sender = sender;
        this.handler = handler;
    }

    public PayloadSender call() throws Exception {
        try {
            PayloadSender payloadSender = this.sender.call();
            if (this.handler != null) {
                this.handler.onResponse(payloadSender);
            }
            return payloadSender;
        } catch (Exception e) {
            if (this.handler != null) {
                this.handler.onException(this.sender, e);
            }
            return null;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayloadReaper)) {
            return false;
        }
        return this.sender.payload.equals(((PayloadReaper) o).sender.payload);
    }

    public String getUuid() {
        return this.sender.getPayload().getUuid();
    }
}
