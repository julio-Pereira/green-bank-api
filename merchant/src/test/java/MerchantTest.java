import com.br.greenbank.domain.Merchant;
import com.br.greenbank.enums.CustomerType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MerchantTest {
    @Test
    public void shoudCreateNewMerchantSuccessfully() {
        Merchant merchant = new Merchant();
        merchant.setCompanyName("John Company inc.");
        merchant.setEmail("john.doe@johncompany.com");
        merchant.setNationalId("12.345.678/0001-01");
        merchant.setPassword("123456789");

        assertEquals("John Company inc.", merchant.getCompanyName());
        assertEquals("john.doe@johncompany.com", merchant.getEmail());
        assertEquals("12.345.678/0001-01", merchant.getNationalId());
        assertEquals("123456789", merchant.getPassword());
        assertEquals(CustomerType.MERCHANT, merchant.getCustomerType());
    }

    @Test
    public void shouldNotHaveMerchantDuplicatedMerchantEmails() {
        Merchant merchant = new Merchant();
        Merchant merchant1 = new Merchant();
        merchant.setCompanyName("John Company inc.");
        merchant.setEmail("john.doe@company.com");
        merchant.setNationalId("12.345.678/0001-01");
        merchant.setPassword("123456789");

        merchant1.setCompanyName("Smith Company inc.");
        merchant1.setEmail("john.doe@company.com");
        merchant1.setNationalId("12.345.679/0001-02");
        merchant1.setPassword("123456789");

        assertThrows(IllegalArgumentException.class, () -> {
            if (merchant.getEmail().equals(merchant1.getEmail()))
                throw new IllegalArgumentException("Email já cadastrado");
        });
    }

    @Test
    public void shouldNotHaveDuplicatedMerchantNationalIds() {
        Merchant merchant = new Merchant();
        Merchant merchant1 = new Merchant();
        merchant.setCompanyName("John Company inc.");
        merchant.setEmail("john.doe@company.com");
        merchant.setNationalId("12.345.678/0001-01");
        merchant.setPassword("123456789");

        merchant1.setCompanyName("Smith Company inc.");
        merchant1.setEmail("john.smith@company.com");
        merchant1.setNationalId("12.345.678/0001-01");
        merchant1.setPassword("123456789");

        assertThrows(IllegalArgumentException.class, () -> {
            if (merchant.getNationalId().equals(merchant1.getNationalId()))
                throw new IllegalArgumentException("CNPJ já cadastrado");
        });
    }
}
