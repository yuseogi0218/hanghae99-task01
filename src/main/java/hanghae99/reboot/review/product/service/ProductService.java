package hanghae99.reboot.review.product.service;

import hanghae99.reboot.review.common.exception.CustomCommonException;
import hanghae99.reboot.review.product.domain.Product;
import hanghae99.reboot.review.product.exception.ProductErrorCode;
import hanghae99.reboot.review.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(Long productId) {
        return productRepository.findTopById(productId).orElseThrow(() -> new CustomCommonException(ProductErrorCode.NOT_FOUND_PRODUCT));
    }

    public Product getProductByIdForUpdate(Long productId) {
        return productRepository.findTopByIdForUpdate(productId).orElseThrow(() -> new CustomCommonException(ProductErrorCode.NOT_FOUND_PRODUCT));
    }
}
