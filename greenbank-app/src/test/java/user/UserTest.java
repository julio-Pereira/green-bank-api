package user;

import com.br.greenbank.model.user.User;
import com.br.greenbank.enums.CustomerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void shouldCreateNewUserSuccessfully() {
        User user = new User();
        user.setFullName("John Doe");
        user.setEmail("john.doe@email.com");
        user.setPassword("123456");
        user.setNationalId("123.456.789-01");

        assertEquals("John Doe", user.getFullName());
        assertEquals("john.doe@email.com", user.getEmail());
        assertEquals("123456", user.getPassword());
        assertEquals("123.456.789-01", user.getNationalId());
        assertEquals(CustomerType.USER, user.getCustomerType());
    }

    @Test
    public void shouldNotHaveDuplicatedUserEmails() {
        User user = new User();
        User user1 = new User();

        user.setFullName("John Doe");
        user.setEmail("john.doe@email.com");
        user.setNationalId("123.456.789-01");
        user.setPassword("123456");

        user1.setFullName("John Doe Smith");
        user1.setEmail("john.doe@email.com");
        user1.setNationalId("123.456.789-02");
        user1.setPassword("123456");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            if (user.getEmail().equals(user1.getEmail())) throw new IllegalArgumentException("Email já cadastrado");
        });
    }

    @Test
    public void shouldNotHaveDuplicatedUserNationalIds() {
        User user = new User();
        User user1 = new User();

        user.setFullName("John Doe");
        user.setEmail("john.doe@email.com");
        user.setNationalId("123.456.789-01");
        user.setPassword("123456");

        user1.setFullName("John Doe Smith");
        user1.setEmail("john.smith@email.com");
        user1.setNationalId("123.456.789-01");
        user1.setPassword("123456");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            if (user.getNationalId().equals(user1.getNationalId())) throw new IllegalArgumentException("CPF já cadastrado");
        });
    }
}
