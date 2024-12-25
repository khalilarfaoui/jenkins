package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final MeterRegistry meterRegistry;

    private List<Product> products = new ArrayList<>();

    // Inject MeterRegistry via le constructeur
    public ProductService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public List<Product> getAllProducts() {
        logger.warn("Get");
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            return product;
        } else {
            // Log de l'avertissement
            logger.warn("Update failed: Product with ID {} not found", id);

            // Incrémenter un compteur pour l'avertissement
            meterRegistry.counter("product.update.warn", "type", "not_found").increment();
            return null;
        }
    }

    public boolean deleteProduct(Long id) {
        boolean isRemoved = products.removeIf(p -> p.getId().equals(id));
        if (!isRemoved) {
            // Log de l'avertissement
            logger.warn("Delete failed: Product with ID {} not found", id);

            // Incrémenter un compteur pour l'avertissement
            meterRegistry.counter("product.delete.warn", "type", "not_found").increment();
        }
        return isRemoved;
    }
}
