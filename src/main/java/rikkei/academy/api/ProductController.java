package rikkei.academy.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.exception.DataExistException;
import rikkei.academy.model.dto.request.ProductRequest;
import rikkei.academy.model.dto.response.ResponseDtoSuccess;
import rikkei.academy.service.product.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    // API get all
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    // API Thêm mới
    @PostMapping
    public ResponseEntity<?> addNewProduct(@Valid @RequestBody ProductRequest productRequest) throws DataExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.save(productRequest), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    // API Sửa Sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.save(productRequest, id), HttpStatus.OK), HttpStatus.OK);
    }

    // API Xoá Sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // API Lấy thông tin chi tiết của sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> detailProductById(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }
}
