package ksmybatis.manage.products.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.common.mapper.CommonMapper;
import ksmybatis.manage.products.dto.Product;
import ksmybatis.manage.products.dto.SearchProduct;
import ksmybatis.manage.products.mapper.ProductsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService{

	private final ProductsMapper productsMapper;
	private final CommonMapper commonMapper;
	
	@Override
	public List<Product> getSearchProductList(SearchProduct searchProduct) {
		
		return productsMapper.getSearchProductList(searchProduct);
	}
	
	@Override
	public void addProduct(Product product) {
		String newCode = commonMapper.getPrimaryKey("products", "prod_cd", "prod_");
		// 자동증가코드 셋팅
		//product.setProductCode(newCode);
		
		productsMapper.addProduct(product);
		log.info("상품등록후 product: {}", product);
		
	}
	
	@Override
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		return productsMapper.getProductList();
	}
}










