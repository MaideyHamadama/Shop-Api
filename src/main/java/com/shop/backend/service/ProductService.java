package com.shop.backend.service;

import com.shop.backend.dto.ProductDTO;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.Size;
import com.shop.backend.repository.ProductRepository;
import com.shop.backend.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SizeRepository sizeRepository;

    /**
     * Récupère tous les produits.
     *
     * @return Une liste de {@link ProductDTO} représentant tous les produits.
     */
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(new ProductDTO(product));
        }
        return productDTOs;
    }

    /**
     * Récupère les détails d'un produit par son ID.
     *
     * @param id L'ID du produit.
     * @return Un {@link ProductDTO} représentant les détails du produit.
     * @throws RuntimeException si le produit n'est pas trouvé.
     */
    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));
        return new ProductDTO(product);
    }

    public Product addSizeToProduct(int productId, int sizeId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + productId));
        Size size = sizeRepository.findById(sizeId)
                .orElseThrow(() -> new RuntimeException("Taille non trouvée avec l'ID : " + sizeId));

        product.addSize(size);
        productRepository.save(product);
        return product;
    }

    public Product removeSizeFromProduct(int productId, int sizeId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + productId));
        Size size = sizeRepository.findById(sizeId)
                .orElseThrow(() -> new RuntimeException("Taille non trouvée avec l'ID : " + sizeId));

        product.removeSize(size);
        productRepository.save(product);
        return product;
    }
}
