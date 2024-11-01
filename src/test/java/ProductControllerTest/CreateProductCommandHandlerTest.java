package ProductControllerTest;

import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Exception.ProductNotValidException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.commandHandlers.CreateProductCommandHandler;
import com.example.demo.SpringBootDemoApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = SpringBootDemoApplication.class)
public class CreateProductCommandHandlerTest {

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createCommandHandler_validProduct_returnsSuccess(){
        Product product = new Product();
        product.setId(6);
        product.setName("test");
        product.setDescription("description");
        product.setPrice(100.0);
        product.setQuantity(10);

        ResponseEntity response = createProductCommandHandler.execute(product);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void createCommandHandler_invalidProduct_throwsProductNotValidException(){
        Product product = new Product();
        product.setId(2);
        product.setName("test");
        product.setDescription("description");
        product.setPrice(-100.0);
        product.setQuantity(10);

        ProductNotValidException exception = assertThrows(ProductNotValidException.class,() -> createProductCommandHandler.execute(product));

        assertEquals("Product price cannot be negative", exception.getSimpleResponse().getMessage());
    }


}
