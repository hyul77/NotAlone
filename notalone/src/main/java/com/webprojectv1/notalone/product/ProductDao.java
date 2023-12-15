package com.webprojectv1.notalone.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
public class ProductDao {
    @Autowired
    private IProductRepository productRepository;

    // C(Insert) & U(Update)
    // save :  엔티티의 ID가 이미 존재하면 업데이트를 수행하고, 
    //         ID가 없으면 새로운 엔티티를 저장하기 때문에 합침
    public void insertUpdateProduct(Product productEntity) {
        log.info("[ProductDao] Product Insert And Update : " + productEntity.toString());
        productRepository.save(productEntity);
    }

    // R(Select)
    public List<Product> selectProductAll() {
        log.info("[ProductDao] Product Select All");
        // productRepository에서 select * from product;
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    // U(Update)
    // public void updateProduct(Product productEntity) {
    //     log.info("Product Update: " + productEntity.toString());
    //     productRepository.save(productEntity);
    // }

    // D(Delete) : id
    public void deleteProduct(long productId) {
        Product savedProduct = productRepository.getReferenceById(productId);
        // 삭제하고자 하는 데이터를 DB에서 확인
        if (savedProduct == null) {
            log.info("[ProductDao] Failed Product Delete : Does not exist. : " + productId);
            return;
        }
        log.info("[ProductDao] Product Delete");
        productRepository.deleteById(productId);
    }
}
