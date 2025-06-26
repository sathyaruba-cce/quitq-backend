package com.quitq.service;

import com.quitq.entity.Seller;
import com.quitq.repository.SellerRepository;
import com.quitq.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller registerSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Optional<Seller> getSellerByEmail(String email) {
        return sellerRepository.findByEmail(email);
    }

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) {
        Seller existing = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        existing.setName(seller.getName());
        existing.setEmail(seller.getEmail());
        existing.setPassword(seller.getPassword());
        existing.setContactNumber(seller.getContactNumber());
        existing.setAddress(seller.getAddress());

        return sellerRepository.save(existing);
    }

    @Override
    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
