package com.br.greenbank.config;

import com.br.greenbank.model.merchant.Merchant;
import com.br.greenbank.model.user.User;
import com.br.greenbank.model.merchant.MerchantRepository;
import com.br.greenbank.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MerchantRepository merchantRepository;

    public TestConfig(UserRepository userRepository, MerchantRepository merchantRepository) {
        this.userRepository = userRepository;
        this.merchantRepository = merchantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User(UUID.randomUUID(), "Maria Brown", "maria.brown@email.com",
                "123456", "123.456.789-10", Instant.now(), Instant.now(), null, null, null);

        Merchant merchant1 = new Merchant(UUID.randomUUID(), "Alex Green Company", "alex.green@email.com",
                "123456", "123.456.789/0001-10", Instant.now(), Instant.now(), null, null);

        userRepository.saveAll(Arrays.asList(user1));

        merchantRepository.saveAll(Arrays.asList(merchant1));

    }
}
