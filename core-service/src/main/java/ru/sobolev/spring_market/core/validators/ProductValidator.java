package ru.sobolev.spring_market.core.validators;

import org.springframework.stereotype.Component;
import ru.sobolev.spring_market.core.dto.ProductDto;
import ru.sobolev.spring_market.core.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice().intValue() < 1) {
            errors.add ("Цена продукта не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add ("Продукт не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
