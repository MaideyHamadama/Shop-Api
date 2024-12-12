package controller;

import com.shop.backend.dto.ProductDTO;
import com.shop.backend.entity.Brand;
import com.shop.backend.entity.Category;
import com.shop.backend.controller.CategoryController;
import com.shop.backend.entity.Product;
import com.shop.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        ResponseEntity<Category[]> response = categoryController.getAllCategories();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());//assertEquals(200, response.getStatusCode());
        assertEquals(Category.values().length, response.getBody().length);
        assertArrayEquals(Category.values(), response.getBody());
    }

    @Test
    void testGetProductsByCategory_ValidCategoryWithProducts() {
        // Arrange

       Brand mockBrand = new Brand(1,"bottle");
       Product product1 = new Product(1, "Blue band", 19.99, "sweetlabel", mockBrand, Category.MEN);
       Product product2 = new Product(2, "Laguna", 29.99, "shorty", mockBrand, Category.MEN);
      
       when(productRepository.findByCategory(Category.MEN)).thenReturn(Arrays.asList(product1, product2));

      // Act
ResponseEntity<List<ProductDTO>> response = categoryController.getProductsByCategory("MEN");

    // Assert
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());//assertEquals(200, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());
    assertEquals("Blue band", response.getBody().get(0).getProductName());
    assertEquals("Laguna", response.getBody().get(1).getProductName());

    verify(productRepository, times(1)).findByCategory(Category.MEN);
}

    @Test
    void testGetProductsByCategory_ValidCategoryNoProducts() {
        // Arrange
        Category category = Category.WOMEN;

        when(productRepository.findByCategory(category)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<ProductDTO>> response = categoryController.getProductsByCategory("WOMEN");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());// assertEquals(200, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());

        verify(productRepository, times(1)).findByCategory(category);
    }

    @Test
    void testGetProductsByCategory_InvalidCategory() {
        // Act
        ResponseEntity<List<ProductDTO>> response = categoryController.getProductsByCategory("INVALID_CATEGORY");

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());//assertEquals(400, response.getStatusCode());
        assertNull(response.getBody());

        verifyNoInteractions(productRepository);
    }
   {/*  @Test
void testGetProductsByCategory_NullResult() {
    when(productRepository.findByCategory(Category.MEN)).thenReturn(null);

    ResponseEntity<List<ProductDTO>> response = categoryController.getProductsByCategory("MEN");

    assertNotNull(response);
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());//assertEquals(204, response.getStatusCode());
    assertNull(response.getBody());

    verify(productRepository, times(1)).findByCategory(Category.MEN);
   }*/}

}