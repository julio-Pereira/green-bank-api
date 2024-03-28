package com.br.greenbank.controller.merchant;

import com.br.greenbank.model.merchant.Merchant;
import com.br.greenbank.dto.merchant.CreateMerchantDto;
import com.br.greenbank.dto.merchant.MerchantEmailDto;
import com.br.greenbank.dto.merchant.MerchantNameDto;
import com.br.greenbank.dto.merchant.MerchantPasswordDto;
import com.br.greenbank.dto.merchant.UpdateMerchantDto;
import com.br.greenbank.service.merchant.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.br.greenbank.constants.PathConstants.MERCHANT_BASE_ROUTE;

@RestController
@RequestMapping(MERCHANT_BASE_ROUTE)
public class MerchantController {
    private MerchantService service;

    public MerchantController(MerchantService service) {
        this.service = service;
    }


    @GetMapping("/")
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        var users = service.getAllMerchants();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Merchant>> getMerchantById(@PathVariable("id") String id) {
        var user = service.getMerchantById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateMerchantDto merchantDto) {
        var user = service.createNewMerchant(merchantDto);
        return ResponseEntity.created(URI.create(String.format(MERCHANT_BASE_ROUTE, user.toString()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody UpdateMerchantDto merchantDto) {
        service.updateMerchant(id, merchantDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String merchantId) {
        service.deleteById(merchantId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/merchantname")
    public ResponseEntity<Void> updateMerchantName(@PathVariable("id") String id, @RequestBody MerchantNameDto nameDto) {
        service.updateMerchantName(id, nameDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") String id, @RequestBody MerchantPasswordDto passwordDto) {
        service.updatePassword(id, passwordDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}/email")
    public ResponseEntity<Void> updateEmail(@PathVariable("id") String id, @RequestBody MerchantEmailDto emailDto) {
        service.updateEmail(id, emailDto);
        return ResponseEntity.noContent().build();
    }
}
