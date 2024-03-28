package com.br.greenbank.service.merchant;

import com.br.greenbank.model.merchant.Merchant;
import com.br.greenbank.dto.merchant.CreateMerchantDto;
import com.br.greenbank.dto.merchant.MerchantEmailDto;
import com.br.greenbank.dto.merchant.MerchantNameDto;
import com.br.greenbank.dto.merchant.MerchantPasswordDto;
import com.br.greenbank.dto.merchant.UpdateMerchantDto;

public interface IMerchant {

    Merchant create(CreateMerchantDto merchantDto);
    void update(Merchant merchant, UpdateMerchantDto merchantDto);
    void updatePassword(Merchant user, MerchantPasswordDto merchantDto);
    void updateEmail(Merchant user, MerchantEmailDto merchantDto);
    void updateMerchantName(Merchant user, MerchantNameDto merchantDto);
}
