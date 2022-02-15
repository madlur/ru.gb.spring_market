package ru.sobolev.spring_market.api.dto;


public class ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String category_name;

    public ProductDto(ProductDto product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category_name = product.getCategory_name();
    }

    public ProductDto(Long id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;

    }

    public ProductDto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
