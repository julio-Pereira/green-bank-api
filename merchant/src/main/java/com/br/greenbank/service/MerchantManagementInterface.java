package com.br.greenbank.service;

import com.br.greenbank.domain.Merchant;
import com.br.greenbank.dto.CreateMerchantDto;
import com.br.greenbank.dto.MerchantEmailDto;
import com.br.greenbank.dto.MerchantNameDto;
import com.br.greenbank.dto.MerchantPasswordDto;
import com.br.greenbank.dto.UpdateMerchantDto;

public interface MerchantManagementInterface {

    Merchant create(CreateMerchantDto merchantDto);
    void update(Merchant merchant, UpdateMerchantDto merchantDto);
    void updatePassword(Merchant user, MerchantPasswordDto merchantDto);
    void updateEmail(Merchant user, MerchantEmailDto merchantDto);
    void updateMerchantName(Merchant user, MerchantNameDto merchantDto);
}
