package entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import com.shop.backend.entity.Product;
import com.shop.backend.entity.Brand;
import com.shop.backend.entity.ProductImage;
import com.shop.backend.entity.Size;
import com.shop.backend.entity.Category;
class ProductTest {
    private Product product;
    @Mock
    private Brand mockBrand;
    @Mock
    private ProductImage mockImage;
    @Mock
    private Size mockSize1;
    @Mock
    private Size mockSize2;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1, "Blue Band", 19.99, "Cotton strech", mockBrand, Category.WOMEN);
    }
    @Test
    void testAddImage() {
        // Act
        product.addImage(mockImage);
        // Assert
        assertThat(product.getImage()).isEqualTo(mockImage);
        verify(mockImage, times(1)).setProduct(product);
    }
    @Test
    void testAddSize() {
        // Arrange
        Set<Size> sizes = new HashSet<>();
        product.setSizes(sizes);
        // Act
        product.addSize(mockSize1);
        // Assert
        assertThat(product.getSizes()).contains(mockSize1);
    }
    @Test
    void testRemoveSize() {
        // Arrange
        Set<Size> sizes = new HashSet<>();
        sizes.add(mockSize1);
        sizes.add(mockSize2);
        product.setSizes(sizes);
        // Act
        product.removeSize(mockSize1);
        // Assert
        assertThat(product.getSizes()).doesNotContain(mockSize1);
    }
    @Test
    void testSetAndGetBrand() {
        // Act
        product.setBrand(mockBrand);
        // Assert
        assertThat(product.getBrand()).isEqualTo(mockBrand);
    }
    @Test
    void testSetAndGetPrice() {
        // Act
        product.setPrice(19.99);
        // Assert
        assertThat(product.getPrice()).isEqualTo(19.99);
    }
    @Test
    void testConstructorInitialization() {
        // Assert
        assertThat(product.getIdProduct()).isEqualTo(1);
        assertThat(product.getProductName()).isEqualTo("Blue Band");
        assertThat(product.getPrice()).isEqualTo(19.99);
        assertThat(product.getDescription()).isEqualTo("Cotton strech");
        assertThat(product.getBrand()).isEqualTo(mockBrand);
        assertThat(product.getCategory()).isEqualTo(Category.WOMEN);
    }
}
