package ProductControllerTest;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.queryHandlers.GetProductQueryHandler;
import com.example.demo.SpringBootDemoApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class GetProductQueryHandlerTest {

    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductQueryHandler_validProductId_returnsProduct() {
        Product product = new Product();
        product.setId(6);
        product.setName("test");
        product.setDescription("description");
        product.setPrice(100.0);
        product.setQuantity(10);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ResponseEntity<ProductDTO> actualResponse = getProductQueryHandler.execute(product.getId());
        ProductDTO expectedResponse = new ProductDTO(product);

        assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    public void getProductQueryHandler_invalidProductId_throwsProductNotFoundException() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(1));
        assertEquals("Product not found", exception.getMessage());

    }
}
