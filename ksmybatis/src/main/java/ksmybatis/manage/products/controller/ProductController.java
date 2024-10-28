package ksmybatis.manage.products.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ksmybatis.manage.products.dto.Product;
import ksmybatis.manage.products.dto.SearchProduct;
import ksmybatis.manage.products.service.IProductService;
import ksmybatis.manage.vendors.dto.Vendor;
import ksmybatis.manage.vendors.mapper.VendorsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

	private final IProductService productService;
	private final VendorsMapper vendorsMapper;
	
	@PostMapping("/searchPrice")
	@ResponseBody
	public List<Product> getProductListByPrice(SearchProduct searchProduct){
		log.info("searchProduct: {}", searchProduct);
		
		return productService.getSearchProductList(searchProduct);
	}
	
	@PostMapping("/addProduct")
	public String addProduct(Product product) {
		
		log.info("상품 등록전 product: {}", product);
		
		productService.addProduct(product);
		
		return "redirect:/product/productList";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		List<Vendor> sellerInfoList = vendorsMapper.getVendorInfoByParam("some");
		log.info("sellerList : {}", sellerInfoList);
		
		model.addAttribute("title", "상품등록");
		model.addAttribute("sellerList", sellerInfoList);
		
		return "/manage/product/addProduct";
	}
	
	@GetMapping("/productList")
	public String getProductList(Model model) {
		
		List<Product> productList = productService.getProductList();
		log.info("상품목록 : {}", productService.getProductList());
		
		model.addAttribute("title", "상품목록");
		model.addAttribute("productList", productList);
		
		
		return "manage/product/productList";
	}
}












