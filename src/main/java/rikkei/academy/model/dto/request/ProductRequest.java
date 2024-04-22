package rikkei.academy.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rikkei.academy.validation.ProductNotExist;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductRequest {
    @NotBlank
//    @ProductNotExist
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer stock;

    @Size(max = 100)
    private String description;
}
