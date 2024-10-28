package ksmybatis.manage.products.service;

import java.util.List;

import ksmybatis.manage.products.dto.Product;
import ksmybatis.manage.products.dto.SearchProduct;

public interface IProductService {
	// 상품목록조회 검색
	List<Product> getSearchProductList(SearchProduct searchProduct);
	
	// 상품등록
	void addProduct(Product product);
	
	// 상품목록조회
	List<Product> getProductList();
}
