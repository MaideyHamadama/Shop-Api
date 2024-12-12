package entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.Brand;
class BrandTest {
    private Brand brand;
    @BeforeEach
    void setUp() {
        // Initialisation d'une instance de Brand avant chaque test
        brand = new Brand(1, "Nike");
    }
    @Test
    void testGettersAndSetters() {
        // Test des getters et setters pour les attributs de base
        assertEquals(1, brand.getIdBrand());
        assertEquals("Nike", brand.getBrandName());
        brand.setIdBrand(2);
        brand.setBrandName("Adidas");
        assertEquals(2, brand.getIdBrand());
        assertEquals("Adidas", brand.getBrandName());
    }
    @Test
    void testAddProductToBrand() {
        // Vérifie l'ajout d'un produit à la liste des produits associés
        Product product1 = new Product();
        product1.setIdProduct(101);
        product1.setProductName("Blue band");
        Product product2 = new Product();
        product2.setIdProduct(102);
        product2.setProductName("Green tree");
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        brand.setProducts(products);
        assertNotNull(brand.getProducts());
        assertEquals(2, brand.getProducts().size());
        assertEquals("Blue band", brand.getProducts().get(0).getProductName());
        assertEquals("Green tree", brand.getProducts().get(1).getProductName());
    }
    @Test
    void testDefaultConstructor() {
        // Test du constructeur par défaut
        Brand defaultBrand = new Brand();
        assertNotNull(defaultBrand);
        assertNull(defaultBrand.getBrandName());
        assertEquals(0, defaultBrand.getIdBrand());
    }
}
