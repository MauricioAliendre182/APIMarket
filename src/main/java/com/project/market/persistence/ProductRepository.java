package com.project.market.persistence;

import com.project.market.domain.dto.Product;
import com.project.market.domain.repository.ProductRepositoryDTO;
import com.project.market.persistence.crud.ProductCrudReposittory;
import com.project.market.persistence.entities.DomainProduct;
import com.project.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Here we can use @Repository or @Component which is a generalization
// indicating that is a spring component
@Repository
public class ProductRepository implements ProductRepositoryDTO {

    //@Autowired inyects the dependency but the notation of these classes needs to be from spring
    @Autowired
    private ProductCrudReposittory productCrudReposittory;

    // We will call the mapper
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<DomainProduct> products = (List<DomainProduct>) productCrudReposittory.findAll();
        return mapper.toProducts(products);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<DomainProduct> products = productCrudReposittory.findByIdCategoryOrderByNameAsc(categoryId);
        return Optional.of(mapper.toProducts(products));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<DomainProduct>> products = productCrudReposittory.findByQuantityStockLessThanAndState(quantity, true);
        // map of the Optional it will return an Optional with a List of Products
        return products.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productCrudReposittory.findById(productId).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        DomainProduct domainProduct = mapper.toDomainProduct(product);
        return mapper.toProduct(productCrudReposittory.save(domainProduct));
    }

    @Override
    public Product update(Product product, Product productUpdate) {
        productUpdate.setActive(product.isActive());
        productUpdate.setCategoryId(product.getCategoryId());
        productUpdate.setName(product.getName());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setStock(product.getStock());
        return save(productUpdate);
    }

    @Override
    public void delete(int productId) {
        productCrudReposittory.deleteById(productId);
    }
}
