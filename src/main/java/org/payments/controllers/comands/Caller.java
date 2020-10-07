package org.payments.controllers.comands;

public interface Caller {
    Command call(String commandName);
}
