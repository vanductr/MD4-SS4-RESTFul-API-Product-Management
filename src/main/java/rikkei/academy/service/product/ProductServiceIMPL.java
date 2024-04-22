package rikkei.academy.service.product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.exception.DataExistException;
import rikkei.academy.model.dto.request.ProductRequest;
import rikkei.academy.model.entity.Product;
import rikkei.academy.repository.IProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product save(ProductRequest productRequest) throws DataExistException {
        Product product = modelMapper.map(productRequest, Product.class);
        if (productRepository.existsByName(productRequest.getName())) {
            throw new DataExistException("Tên Sản phẩm đã tồn tại!", "name");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Không tồn tại Id"));
        productRepository.deleteById(id);
    }

    @Override
    public Product save(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Không tồn tại Id"));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        return productRepository.save(product);
    }
}
