package rikkei.academy.service.product;

import rikkei.academy.exception.DataExistException;
import rikkei.academy.model.dto.request.ProductRequest;
import rikkei.academy.model.entity.Product;
import rikkei.academy.service.IGenericService;

public interface IProductService extends IGenericService<Product, Long> {
    Product save(ProductRequest productRequest) throws DataExistException;

    Product save(ProductRequest productRequest, Long id);
}
