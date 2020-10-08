package org.payments.convertors.impl;

import org.payments.convertors.AbstractConvertor;
import org.payments.dtos.impl.BalanceDTO;
import org.payments.dtos.UserDTO;
import org.payments.entities.Balance;
import org.payments.entities.User;
import org.payments.repository.BalanceRepository;
import org.payments.repository.UserRepository;
import org.payments.repository.impl.BalanceRepositoryImpl;
import org.payments.repository.impl.UserRepositoryImpl;

import javax.annotation.PostConstruct;
import java.util.Optional;

public class UserConvertor extends AbstractConvertor<User, UserDTO> {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final BalanceRepository balanceRepository = new BalanceRepositoryImpl();
    private final BalanceConvertor balanceConvertor = new BalanceConvertor(Balance.class, BalanceDTO.class);

    public UserConvertor(Class<User> userClass, Class<UserDTO> userDTOClass) {
        super(userClass, userDTOClass);
    }


    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO =  UserDTO.newBuilder()
                .setPhone(user.getPhone())
                .setId(user.getId())
                .setLogin(user.getLogin())
                .setEmail(user.getEmail())
                .setFirst_name(user.getFirst_name())
                .setLast_name(user.getLast_name())
                .build();
        convertSpecificFieldsToDto(user, userDTO);
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user =  User.newBuilder()
                .setPhone(userDTO.getPhone())
                .setBalanceId(userDTO.getBalance().getId())
                .setId(userDTO.getId())
                .setLogin(userDTO.getLogin())
                .setEmail(userDTO.getEmail())
                .setFirst_name(userDTO.getFirst_name())
                .setLast_name(userDTO.getLast_name())
                .build();
        convertSpecificFieldsToEntity(userDTO, user);
        return user;
    }

    @Override
    public void convertSpecificFieldsToEntity(UserDTO source, User destination) {
        destination.setBalanceId(source.getBalance().getId());
    }

    @Override
    public void convertSpecificFieldsToDto(User source, UserDTO destination) {
        destination.setBalance(balanceConvertor.toDto(balanceRepository.getBalanceById(source.getBalanceId()).orElseGet(Balance::new)));
    }
}
