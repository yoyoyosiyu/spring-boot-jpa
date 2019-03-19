package com.huayutech.jpa_mysql.repository;

import com.huayutech.jpa_mysql.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface ProductRepository extends JpaRepository<Product, Long> {


    @Modifying
    @Transactional
    @Query("update Product p set price=?1, name=?2 where p.id = ?3")
    public int updateNameAndPriceById(double price, String name, Long id);


    @Modifying
    @Transactional
    @Query("update Product set price=:price where id =:id")
    public int updatePriceById(@Param("id") Long id, @Param("price") double price);

}
