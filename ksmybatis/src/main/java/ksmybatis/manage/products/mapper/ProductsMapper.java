package ksmybatis.manage.products.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.manage.products.dto.Product;
import ksmybatis.manage.products.dto.SearchProduct;

@Mapper
public interface ProductsMapper {
	
	// 상품목록조회 검색
	List<Product> getSearchProductList(SearchProduct searchProduct);
	
	// 상품등록
	int addProduct(Product product);

	// 상품목록조회
	List<Product> getProductList();

	// 판매자가 등록한 상품삭제
	int removeProductsById(String sellerId);

}
