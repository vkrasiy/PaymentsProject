package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Caller;
import org.payments.controllers.comands.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandCaller implements Caller {
    private final Map<String, Command> commands = new HashMap();

    public CommandCaller() {
        commands.put("SIGNUP", new SignUpCommand());
        commands.put("SIGNIN", new SignInCommand());
        commands.put("ADDCARD", new AddCardCommand());
        commands.put("GETCARD", new GetCardCommand());
        commands.put("TOPUPACCOUNT", new TopUpAccountCommand());
        commands.put("GETUSER", new GetUserCommand());
        commands.put("GETPAYMENTS", new GetPaymentsCommand());
        commands.put("MAKEPAYMENT", new MakePaymentCommand());
        commands.put("ADMITPAYMENT", new AdmitPaymentCommand());
        commands.put("LOGOUT", new LogOutCommand());
        commands.put("MAIN", new GetMainCommand());
        commands.put("CHANGELANG", new ChangeLangCommand());
        commands.put("SIGNINPAGE", new GetSignInPageCommand());
        commands.put("MAKEPAYMENTPAGE", new GetMakePaymentPage());
    }

    @Override
    public Command call(String commandName) {
        return commands.get(commandName.toUpperCase());
    }
}
