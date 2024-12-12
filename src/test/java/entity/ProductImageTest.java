package entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.ProductImage;
import com.shop.backend.entity.Color;

class ProductImageTest {
    @Test
    void testProductImageGettersAndSetters() {
        // Arrange
        Product mockProduct = Mockito.mock(Product.class);
        ProductImage productImage = new ProductImage();
        // Act
        productImage.setIdProdImage(1);
        productImage.setImageURL("http://example.com/image.jpg");
        productImage.setColor(Color.RED);
        productImage.setProduct(mockProduct);
        // Assert
        Assertions.assertEquals(1, productImage.getIdProdImage());
        Assertions.assertEquals("http://example.com/image.jpg", productImage.getImageURL());
        Assertions.assertEquals(Color.RED, productImage.getColor());
        Assertions.assertEquals(mockProduct, productImage.getProduct());
    }
    @Test
    void testProductImageConstructor() {
        // Arrange
        ProductImage productImage = new ProductImage(1, "http://example.com/image.jpg", Color.BLUE);
        // Assert
        Assertions.assertEquals(1, productImage.getIdProdImage());
        Assertions.assertEquals("http://example.com/image.jpg", productImage.getImageURL());
        Assertions.assertEquals(Color.BLUE, productImage.getColor());
        Assertions.assertNull(productImage.getProduct()); // Vérifie que le produit est null par défaut
    }
    @Test
    void testSetProduct() {
        // Arrange
        ProductImage productImage = new ProductImage();
        Product mockProduct = Mockito.mock(Product.class);
        // Act
        productImage.setProduct(mockProduct);
        // Assert
        Assertions.assertEquals(mockProduct, productImage.getProduct());
    }
}

