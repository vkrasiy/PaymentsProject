package org.payments.services.impl;

import org.payments.convertors.impl.UserConvertor;
import org.payments.dtos.impl.BalanceDTO;
import org.payments.dtos.impl.CardDTO;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.dtos.UserDTO;
import org.payments.services.PaymentService;
import org.payments.util.SignatureMaker;
import org.payments.util.impl.SignManager;
import org.payments.entities.User;
import org.payments.repository.RepositoryFactory;
import org.payments.repository.impl.RepositoryFactoryImpl;
import org.payments.services.BalanceService;
import org.payments.services.CardService;
import org.payments.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserConvertor userConvertor;
    private final RepositoryFactory repositoryFactory;
    private final BalanceService balanceService;
    private final SignatureMaker signatureMaker;
    private final CardService cardService;
    private final PaymentService paymentService;

    public UserServiceImpl() {
        repositoryFactory = new RepositoryFactoryImpl();
        userConvertor = new UserConvertor(User.class, UserDTO.class);
        balanceService = new BalanceServiceImpl();
        signatureMaker = new SignManager();
        cardService = new CardServiceImpl();
        paymentService = new PaymentServiceImpl();

    }

    public UserDTO getUser(int id) {
        return repositoryFactory.getUserRepository().getUser(id)
                .map(userConvertor::toDto).orElse(UserDTO.newBuilder().build());
    }

    @Override
    public UserDTO signIn(String login, String password, boolean isAdmin) {
        String signedPass = signatureMaker.getSignature(password);
        User user = repositoryFactory.getUserRepository().getUser(login, signedPass, isAdmin);
        return user != null ? userConvertor.toDto(user) : null;
    }

    @Override
    public UserDTO signUp(UserDTO userDTO, String password) {
        UserDTO newUser = null;
        String signedPass = signatureMaker.getSignature(password);
        userDTO.setBalance(balanceService.createBalance());
        User user = userConvertor.toEntity(userDTO);
        user.setPassword(signedPass);
        newUser = userConvertor.toDto(repositoryFactory.getUserRepository().getUser(repositoryFactory.getUserRepository().addUser(user)).get());
        return newUser;
    }


    @Override
    public boolean addCardToUserBalance(BalanceDTO balanceDTO, int tariffId) {
        boolean cardAdded = false;
        CardDTO cardDTO = cardService.createCardForUser(balanceDTO.getId(), tariffId);
        if (cardDTO != null) {
            balanceDTO.addCard(cardDTO);
            cardAdded = true;
        }
        return cardAdded;
    }

    @Override
    public PaymentDTO makePayment(PaymentDTO paymentDTO) {
        return paymentService.checkBalanceBeforeAdmitting(paymentDTO)
                ? paymentService.createPayment(paymentDTO) : null;
    }

    @Override
    public List<PaymentDTO> getAllPaymentsByUser(UserDTO userDTO, int start, int total, String order) {
        return paymentService.getAllPayments(userDTO.getBalance().getId(), start, order);
    }

    @Override
    public boolean topUpAccount(CardDTO cardDTO, double sumToUpdate) {
        cardDTO.setAmount(cardDTO.getAmount() + sumToUpdate);
        return cardService.increaseAmount(cardDTO);
    }
}
