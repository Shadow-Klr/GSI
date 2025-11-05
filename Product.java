package Gs_Inventario;

public class Product {
	private int		id;
	private String	name;
	private double	price;
	private int		stock;
	private String	category;
	private String	description;

//	Null constructor
	public	Product()
	{
		
	}

//	Constructor without id
public Product(String name, double price, int stock, String category, String description) throws NegativeInt, nulability
{
    // Validate if the price or the stock is negative
    NegativeInt.verificar(price, "precio");
    NegativeInt.verificar(stock, "stock");
	nulability.verifyNulability(name.trim(), "nombre");
	nulability.verifyNulability(price, "precio");
	nulability.verifyNulability(stock, "stock");
	nulability.verifyNulability(category.trim(), "categoria");
	nulability.verifyNulability(description.trim(), "descripcion");

    // If the validation is ok it create the object
    this.name 		=	name;
    this.price 		=	price;
    this.stock 		=	stock;
    this.category 	=	category;
    this.description=	description;
}

	
//	Constructor with all the parameters
	public	Product (int id, String name, double price, int stock, String category, String description)
	{
		// Validate if the price or the stock is negative
    	NegativeInt.verificar(price, "precio");
    	NegativeInt.verificar(stock, "stock");
		nulability.verifyNulability(id, "id")
		nulability.verifyNulability(name.trim(), "nombre");
		nulability.verifyNulability(price, "precio");
		nulability.verifyNulability(stock, "stock");
		nulability.verifyNulability(category.trim(), "categoria");
		nulability.verifyNulability(description.trim(), "descripcion");
		
		// If the validation is ok it create the object
		this.id			=	id;
		this.name		=	name;
		this.price		=	price;
		this.stock		=	stock;
		this.category	=	category;
		this.description=	description;
	}

//	Getters & Setters
	public	int getId() {
		return id;
	}

	public	String getName() {
		return name;
	}

	public	void setName(String name) throws nulability {
		nulability.verifyNulability(name.trim(), "nombre");
		this.name = name;
	}

	public	double getPrice() {
		return price;
	}

	public	void setPrice(double price) throws negativeInt, nulability {
		NegativeInt.verificar(price, "precio");
		nulability.verifyNulability(price, "precio");
		this.price = price;
	}

	public	int getStock() {
		return stock;
	}

	public	void setStock(int stock) throws negativeInt, nulability {
		NegativeInt.verificar(stock, "cantidad")
		nulability.verifyNulability(stock, "stock");
		this.stock = stock;
	}

	public	String getCategory() {
		return category;
	}

	public	void setCategory(String category) throws nulability {
		nulability.verifyNulability(category.trim(), "categoria");
		this.category = category;
	}

	public	String getDescription() {
		return description;
	}

	public	void setDescription(String description) throws nulability {
		nulability.verifyNulability(description.trim(), "descripcion");
		this.description = description;
	}
	
//	ToString method
	@Override
	public	String toString()
	{
		return "Product\n{" +
                "\n  id = " + id +
                "\nname = " + name +
                "\nprice = " + price +
                "\nstock = " + stock +
                "\ncategory = " + category +
                "\ndescription = " + description +
                "\n}";
	}
}
