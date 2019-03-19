package com.huayutech.jpa_mysql.web;

import com.alibaba.fastjson.JSONObject;
import com.huayutech.jpa_mysql.domain.Product;
import com.huayutech.jpa_mysql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public String doPost(@RequestBody Product product) {

        productRepository.save(product);

        return "ok";
    }

    @GetMapping("/{id}")
    public Product doGet(@PathVariable Long id) {

        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity doDelete(@PathVariable Long id) {

        try {
            productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException exception) {
            // 对应id的product不存在
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    // 整体更新
    // 谨慎使用，因为如果某个字段没有提供值的话，会作为空值来进行写入
    @PutMapping
    public void doUpdate(@RequestBody Product product) {

        // 先检测id是否存在，否则的话，不存在的id会造成jpa创建一条新的记录
        if (product.getId() == null || !productRepository.existsById(product.getId()))
            throw new EntityNotFoundException();

        productRepository.save(product);
    }

    // 部分更新, 更新价格和名字
    @PatchMapping("/{id}")
    public void doUpdatePart(@PathVariable Long id, @RequestBody JSONObject json) {

        String name = ((String) json.get("name"));
        double price = ((double) json.get("price"));

        productRepository.updateNameAndPriceById(price, name, id);

    }

    // 部分更新，更新价格
    @PatchMapping("/{id}/price")
    public ResponseEntity doUpdatePrice(@PathVariable Long id, @RequestParam("price") double price) {

        int count = productRepository.updatePriceById(id, price);

        if (count == 0)
            throw new EntityNotFoundException();

        return new ResponseEntity(HttpStatus.OK);

    }


}
