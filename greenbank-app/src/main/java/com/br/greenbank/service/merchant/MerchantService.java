package com.br.greenbank.service.merchant;

import com.br.greenbank.domain.merchant.Merchant;
import com.br.greenbank.dto.merchant.CreateMerchantDto;
import com.br.greenbank.dto.merchant.MerchantEmailDto;
import com.br.greenbank.dto.merchant.MerchantNameDto;
import com.br.greenbank.dto.merchant.MerchantPasswordDto;
import com.br.greenbank.dto.merchant.UpdateMerchantDto;
import com.br.greenbank.model.merchant.MerchantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.br.greenbank.constants.ErrorHandlerConstants.EMAIL_ALREADY_EXISTS;
import static com.br.greenbank.constants.ErrorHandlerConstants.EMPTY_EMAIL;
import static com.br.greenbank.constants.ErrorHandlerConstants.EMPTY_NAME;
import static com.br.greenbank.constants.ErrorHandlerConstants.EMPTY_PASSWORD;
import static com.br.greenbank.constants.ErrorHandlerConstants.MERCHANT_NOT_FOUND;

@Service
public class MerchantService {

    protected MerchantHandler merchantImpl;
    private final MerchantRepository repository;

    public MerchantService(MerchantRepository repository) {
        this.repository = repository;
    }

    public List<Merchant> getAllMerchants() {
        return repository.findAllMerchants();
    }

    public Optional<Merchant> getMerchantById(String id) {
        return repository.findById(UUID.fromString(id));
    }

    @Transactional
    public Merchant createNewMerchant(CreateMerchantDto merchantDto) {
//        merchantImpl = new MerchantManagement();
        var merchant = merchantImpl.create(merchantDto);

        try {
            return repository.save(merchant);
        } catch (Exception e) {
            throw new IllegalArgumentException(EMAIL_ALREADY_EXISTS, e);
        }
    }

    @Transactional
    public void updateMerchant(String merchantId, UpdateMerchantDto merchantDto) {
        var id = UUID.fromString(merchantId);
        var findMerchant = repository.findById(id);

        if (findMerchant.isEmpty()) throw new IllegalArgumentException(MERCHANT_NOT_FOUND);

        if (merchantDto.companyName().isEmpty()) throw new IllegalArgumentException(EMPTY_NAME);

        if (merchantDto.email().isEmpty()) throw new IllegalArgumentException(EMPTY_EMAIL);

        if (merchantDto.password().isEmpty()) throw new IllegalArgumentException(EMPTY_PASSWORD);


//        merchantImpl = new MerchantManagement();
        var merchant = findMerchant.get();
        merchantImpl.update(merchant, merchantDto);
        repository.save(merchant);
    }

    @Transactional
    public void updatePassword(String merchantId, MerchantPasswordDto passwordDto) {
      var id = UUID.fromString(merchantId);
      var findMerchant = repository.findById(id);
//      merchantImpl = new MerchantManagement();

      if (findMerchant.isEmpty()) throw new IllegalArgumentException(MERCHANT_NOT_FOUND);

      if (passwordDto.password().isEmpty()) throw new IllegalArgumentException(EMPTY_PASSWORD);

      var merchant = findMerchant.get();
      merchantImpl.updatePassword(merchant, passwordDto);
      repository.save(merchant);
    }

    @Transactional
    public void updateEmail(String merchantId, MerchantEmailDto emailDto) {
      var id = UUID.fromString(merchantId);
      var findMerchant = repository.findById(id);
//      merchantImpl = new MerchantManagement();

      if (findMerchant.isEmpty()) throw new IllegalArgumentException(MERCHANT_NOT_FOUND);

      if (emailDto.email().isEmpty()) throw new IllegalArgumentException(EMPTY_EMAIL);

      var merchant = findMerchant.get();
      merchantImpl.updateEmail(merchant, emailDto);
      repository.save(merchant);
    }

    @Transactional
    public void updateMerchantName(String merchantId, MerchantNameDto nameDto) {
      var id = UUID.fromString(merchantId);
      var findMerchant = repository.findById(id);
//      merchantImpl = new MerchantManagement();

      if (findMerchant.isEmpty()) throw new IllegalArgumentException(MERCHANT_NOT_FOUND);

      if (nameDto.companyName().isEmpty()) throw new IllegalArgumentException(EMPTY_EMAIL);

      var merchant = findMerchant.get();
      merchantImpl.updateMerchantName(merchant, nameDto);
      repository.save(merchant);
    }

    @Transactional
    public void deleteById(String merchantId) {
        var id = UUID.fromString(merchantId);
        var findMerchant = repository.findById(id);

        if (findMerchant.isEmpty()) throw new IllegalArgumentException(MERCHANT_NOT_FOUND);

        repository.deleteById(id);
    }
}
