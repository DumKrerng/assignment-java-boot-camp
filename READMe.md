## User Stories
1. User: DumKrerng ทำการ Login เข้าสู่ web
2. User ค้นหาข้อมูล Product ด้วย ""
3. User เลือกดูรายละเอียด Product: 
4. User กด "ใส่ตะกร้า"
5. User กด "ชำระสินค้า"
6. User เลือก ที่อยู่การจัดส่ง
7. User เลือก Payment Method
8. User กด "ส่ั่งสินค้า"
___

## API Function
### 1. User: DumKrerng ทำการ Login เข้าสู่ web
Endpoint: 
```
[POST] /api/v1/login
```

Body: 
```
{
    Username: string
    Password: string
}
```

Response:
```
{
    HttpCode: int
    Message: string
    Data: {
        Username: string
        Name: string
    }
}
```
___

### 2. User ค้นหาข้อมูล Product ด้วย ""
Endpoint:
```
[GET] /api/v1/search/product/{searchValue}
```

Body:
```
null
```

Response:
```
{
    HttpCode: int
    Message: string
    Data: [
    	{
    	    ProductCode: string
    	    ProductName: string
    	    ProductTitle: string
    	    ProductGallery: string
    	    ProductRating: number
    	    ProductDetail: number
    	    UnitPrice: number
    	    UnitDiscount: number
    	}
    ]
}
```
___

### 4. User กด "ใส่ตะกร้า"
Endpoint:
```
[POST] /api/v1/basket/product/{productCode}
```

Body:
```
null
```

Response:
```
{
    HttpCode: int
    Message: string
    Data: [
    	{
    	    ProductCode: string
    	    ProductName: string
    	    ProductTitle: string
    	    ProductGallery: string
    	    ProductRating: number
    	    ProductDetail: number
    	    UnitPrice: number
    	    UnitDiscount: number
            Quantity: number
    	}
    ]
}
```
___

### 5. User กด "ชำระสินค้า", 6. User เลือก ที่อยู่การจัดส่ง
#### Show Basket detail
Endpoint:
```
[GET] /api/v1/basket/
```

Body:
```
null
```

Response:
```
{
    HttpCode: int
    Message: string
    Data: [
    	{
    	    ProductCode: string
    	    ProductName: string
    	    ProductTitle: string
    	    ProductGallery: string
    	    ProductRating: number
    	    ProductDetail: number
    	    UnitPrice: number
    	    UnitDiscount: number
    	    QuantityOrder: number
    	}
    ]
}
```

#### Show Shipping detail
Endpoint:
```
[GET] /api/v1/shippingdetail
```

Body:
```
null
```

Response:
```
{
    HttpCode: int
    Message: string
    Data: {
	    UserEmail: string
	    UserFullName: string
	    AddessDetail: string
	    Postcode: string
	    Province: string
	    District: string
	    SubDistrict: string
	    PhoneNumber: string
    }
}
```
___

### 8. User กด "ส่ั่งสินค้า"
Endpoint:
```
[POST] /api/v1/order
```

Body:
```
{
    ShippingDetail: {
        UserEmail: string
        UserFullName: string
        AddessDetail: string
        Postcode: string
        Province: string
        District: string
        SubDistrict: string
        PhoneNumber: string
    },
    InvoiceDetail: {
        UserCardID: string
        UserFullName: string
        AddessDetail: string
        Postcode: string
        Province: string
        District: string
        SubDistrict: string
        PhoneNumber: string
    },
    PaymentDetail: {
        PaymentMethodCode: string
        CreditCard: {
            CreditCardNumber: string
            CreditCardName: string
            CreditCardExpire: string
            CreditCardCCV: string
        }
        Bank: {
            SwiftCode: string
            BankAccountNumber: string
            BankAccountName: string
        },
        PayPal: {
            UserEmail: 
        },
        LINEPay: {
            AccountNumber:
        }
    }
}
```

Response:
```
{
    HttpCode: int
    Message: string
    Data: {
        OrderNumber: string
        InvoiceNumber: string
        Payer: string
        TransactionDate: datetime
    }
}
```
___




## Assignments for Java Boot Camp
* [Week 1 :: Design and Develop RESTful API with Spring Boot](https://github.com/up1/assignment-java-boot-camp/wiki/Week-01)



## Resources
* [Spring Boot Reference](https://spring.io/projects/spring-boot)
* https://www.baeldung.com/ 
* https://start.spring.io/
* [Git commit message](https://www.conventionalcommits.org/en/v1.0.0/)

