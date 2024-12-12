package dto;
import com.shop.backend.entity.ProductImage;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.Color;
import org.junit.jupiter.api.Test;
import com.shop.backend.dto.ProductImageDTO;
import static org.assertj.core.api.Assertions.assertThat;
class ProductImageDTOTest {
    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        ProductImageDTO productImageDTO = new ProductImageDTO();
        // Assert
        assertThat(productImageDTO.getIdProdImage()).isEqualTo(0);
        assertThat(productImageDTO.getImageURL()).isNull();
        assertThat(productImageDTO.getColor()).isNull();
        assertThat(productImageDTO.getProductId()).isEqualTo(0);
    }
    @Test
    void testParameterizedConstructor_WithValidProductImage() {
        // Arrange
        Product product = new Product();
        product.setIdProduct(1001);
        ProductImage productImage = new ProductImage();
        productImage.setIdProdImage(1);
        productImage.setImageURL("http://example.com/image.jpg");
        productImage.setColor(Color.RED);
        productImage.setProduct(product);
        // Act
        ProductImageDTO productImageDTO = new ProductImageDTO(productImage);
        // Assert
        assertThat(productImageDTO.getIdProdImage()).isEqualTo(1);
        assertThat(productImageDTO.getImageURL()).isEqualTo("http://example.com/image.jpg");
        assertThat(productImageDTO.getColor()).isEqualTo("RED");
        assertThat(productImageDTO.getProductId()).isEqualTo(1001);
    }
    @Test
    void testParameterizedConstructor_WithNullProduct() {
        // Arrange
        ProductImage productImage = new ProductImage();
        productImage.setIdProdImage(1);
        productImage.setImageURL("http://example.com/image.jpg");
        productImage.setColor(Color.BLUE);
        productImage.setProduct(null);
        // Act
        ProductImageDTO productImageDTO = new ProductImageDTO(productImage);
        // Assert
        assertThat(productImageDTO.getIdProdImage()).isEqualTo(1);
        assertThat(productImageDTO.getImageURL()).isEqualTo("http://example.com/image.jpg");
        assertThat(productImageDTO.getColor()).isEqualTo("BLUE");
        assertThat(productImageDTO.getProductId()).isEqualTo(0); // Default value for null product
    }
    @Test
    void testGettersAndSetters() {
        // Arrange
        ProductImageDTO productImageDTO = new ProductImageDTO();
        // Act
        productImageDTO.setIdProdImage(5);
        productImageDTO.setImageURL("http://example.com/new-image.jpg");
        productImageDTO.setColor("GREEN");
        productImageDTO.setProductId(2002);
        // Assert
        assertThat(productImageDTO.getIdProdImage()).isEqualTo(5);
        assertThat(productImageDTO.getImageURL()).isEqualTo("http://example.com/new-image.jpg");
        assertThat(productImageDTO.getColor()).isEqualTo("GREEN");
        assertThat(productImageDTO.getProductId()).isEqualTo(2002);
    }
}

