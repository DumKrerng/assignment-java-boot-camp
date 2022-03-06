package com.example.Shopping;

import javax.annotation.*;
import com.example.Shopping.product.*;
import com.example.Shopping.user.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class ShoppingApplication {

	@Autowired
	private UserRepository m_repoUser;

	@Autowired
	private ProductRepository m_repoProduct;

	@PostConstruct
	public void initialData() {
		UserModel user = new UserModel();
		user.setUsername("DumKrerng");
		user.setName("DumKrerng");
		user.setPassword("123456");
		m_repoUser.save(user);

		user = new UserModel();
		user.setUsername("DumKrerng-DD");
		user.setName("DumKrerng-DD");
		user.setPassword("123456");
		m_repoUser.save(user);

		ProductModel product = new ProductModel("ProductX", "ProductX");
		product.setUnitPrice(10);
		m_repoProduct.save(product);

		product = new ProductModel("ProductA", "ProductA");
		product.setUnitPrice(13);
		m_repoProduct.save(product);

		product = new ProductModel("ProductCodeX", "ProductNameX");
		product.setUnitPrice(3);
		m_repoProduct.save(product);

		product = new ProductModel("CodeX", "NameX");
		product.setUnitPrice(30);
		m_repoProduct.save(product);

		product = new ProductModel("เสื้อX", "เสื้อX");
		product.setUnitPrice(30.50);
		m_repoProduct.save(product);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

}
