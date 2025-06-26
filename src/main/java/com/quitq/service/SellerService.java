package com.quitq.service;

import com.quitq.entity.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    Seller registerSeller(Seller seller);
    Optional<Seller> getSellerByEmail(String email);
    List<Seller> getAllSellers();
    Seller updateSeller(Long id, Seller seller);
    void deleteSeller(Long id);
}