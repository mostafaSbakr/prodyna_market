package com.prodyna.prodynamarket.services;

import com.prodyna.prodynamarket.models.Product;
import com.prodyna.prodynamarket.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //    TODO --> no duplicate product name
    public void createNewProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(int id) {
        if (productRepository.findById(id).isPresent())
            return productRepository.findById(id).get();
        else
            throw new NoSuchElementException();
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public void deleteProductByName(String name) {
        productRepository.deleteByName(name);
    }

    public Product getProductByName(String name) {
        return productRepository.findProductByName(name).get(0);
    }

    public List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList<>();
        productRepository.findAll().forEach(productsList::add);
        return productsList;
    }
}
