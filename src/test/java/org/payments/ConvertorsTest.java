package org.payments;

import org.junit.BeforeClass;
import org.junit.Test;
import org.payments.convertors.impl.UserConvertor;
import org.payments.dtos.impl.BalanceDTO;
import org.payments.dtos.UserDTO;
import org.payments.entities.User;

import static org.junit.Assert.assertEquals;


public class ConvertorsTest {
    static UserConvertor userConvertor;
    static User user;

    @BeforeClass
    public static void initializeFields(){
        userConvertor = new UserConvertor(User.class, UserDTO.class);
        user = User.newBuilder()
                .setId(1)
                .setPhone("0681537991")
                .setFirst_name("Vitalik")
                .setLast_name("Krasiy")
                .setEmail("vitalikmanager@gmail.com")
                .setLogin("vkrasiy")
                .setBalanceId(1).build();
    }

    @Test
    public void testConvertors(){
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setId(1);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setPhone("0681537991");
        userDTO.setFirst_name("Vitalik");
        userDTO.setLast_name("Krasiy");
        userDTO.setEmail("vitalikmanager@gmail.com");
        userDTO.setLogin("vkrasiy");
        userDTO.setBalance(balanceDTO);
        User user = userConvertor.toEntity(userDTO);
        assertEquals(ConvertorsTest.user, user);


    }
}
