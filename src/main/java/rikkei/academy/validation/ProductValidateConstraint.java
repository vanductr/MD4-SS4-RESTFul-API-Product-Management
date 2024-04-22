package rikkei.academy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rikkei.academy.repository.IProductRepository;

@Component
public class ProductValidateConstraint implements ConstraintValidator<ProductNotExist, String> {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return !productRepository.existsByName(string);
    }
}
