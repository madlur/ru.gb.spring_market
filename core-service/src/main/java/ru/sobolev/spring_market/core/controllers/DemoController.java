package ru.sobolev.spring_market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobolev.spring_market.api.dto.StringResponse;
import ru.sobolev.spring_market.core.integrations.CartServiceIntegration;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final CartServiceIntegration cartServiceIntegration;

    @GetMapping
    public StringResponse demo() {
        cartServiceIntegration.getUserCart("1");
        return new StringResponse("OK");
    }
}
