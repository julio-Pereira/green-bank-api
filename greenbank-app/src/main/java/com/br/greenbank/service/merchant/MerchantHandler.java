package com.br.greenbank.service.merchant;

import com.br.greenbank.domain.merchant.Merchant;
import com.br.greenbank.dto.merchant.CreateMerchantDto;
import com.br.greenbank.dto.merchant.MerchantEmailDto;
import com.br.greenbank.dto.merchant.MerchantNameDto;
import com.br.greenbank.dto.merchant.MerchantPasswordDto;
import com.br.greenbank.dto.merchant.UpdateMerchantDto;

import java.time.Instant;
import java.util.UUID;

public class MerchantHandler implements IMerchant {

    @Override
    public Merchant create(CreateMerchantDto merchantDto) {
        var merchant = new Merchant(
                UUID.randomUUID(),
                merchantDto.companyName(),
                merchantDto.email(),
                merchantDto.password(),
                merchantDto.nationalId(),
                Instant.now(),
                Instant.now(),
                null,
                null
        );
        return merchant;
    }

    @Override
    public void update(Merchant merchant, UpdateMerchantDto merchantDto) {
        if (merchantDto.companyName() != null) {
            merchant.setCompanyName(merchantDto.companyName());
        }

        if (merchantDto.email() != null) {
            merchant.setEmail(merchantDto.email());
        }

        if (merchantDto.password() != null) {
            merchant.setPassword(merchantDto.password());
        }
    }

    @Override
    public void updatePassword(Merchant user, MerchantPasswordDto merchantDto) {
        if (merchantDto.password() != null && merchantDto.newPassword() != null) {
            user.setPassword(merchantDto.newPassword());
        }
    }

    @Override
    public void updateEmail(Merchant user, MerchantEmailDto merchantDto) {
        if (merchantDto.email() != null) {
            user.setEmail(merchantDto.email());
        }
    }

    @Override
    public void updateMerchantName(Merchant user, MerchantNameDto merchantDto) {
        if (merchantDto.companyName() != null) {
            user.setCompanyName(merchantDto.companyName());
        }
    }
}
