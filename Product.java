package GSI;

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
	public Product(String name, double price, int stock, String category, String description)
	        throws NegativeInt, Nulability, MaxLength {

	    // Error validation
		validarCampos(name, price, stock, category, description);
	    // If the validation is OK it create the object
	    this.name = name.trim();
	    this.price = price;
	    this.stock = stock;
	    this.category = category.trim();
	    this.description = description.trim();
	}

	
//	Constructor with all the parameters
	public	Product (int id, String name, double price, int stock, String category, String description) throws NegativeInt, Nulability, MaxLength
	{
		//Error validation
		NegativeInt.verificar(id, "id");
		validarCampos(name, price, stock, category, description);
		// If the validation is ok it create the object
		this.id			=	id;
		this.name		=	name;
		this.price		=	price;
		this.stock		=	stock;
		this.category	=	category;
		this.description=	description;
	}
	
// Validar Excepciones
	
	private void validarCampos(String name, double price, int stock, String category, String description)
            throws NegativeInt, Nulability, MaxLength {

        // Comprobaciones de nulabilidad
        Nulability.verifyNulability(name, "nombre");
        Nulability.verifyNulability(category, "categoria");
        Nulability.verifyNulability(description, "descripcion");

        // Trim y nueva verificación
        Nulability.verifyNulability(name.trim(), "nombre");
        Nulability.verifyNulability(category.trim(), "categoria");
        Nulability.verifyNulability(description.trim(), "descripcion");

        // Verificar longitudes máximas
        MaxLength.verificar(name, "nombre", 100);
        MaxLength.verificar(category, "categoria", 100);
        MaxLength.verificar(description, "descripcion", 300);

        // Verificar números negativos
        NegativeInt.verificar(price, "precio");
        NegativeInt.verificar(stock, "stock");
    }

//	Getters & Setters
    public int getId() {
        return id;
    }

    // ✅ Este setter es necesario para asignar el ID autogenerado desde la BD
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Nulability, MaxLength {
        Nulability.verifyNulability(name, "nombre");
        Nulability.verifyNulability(name.trim(), "nombre");
        MaxLength.verificar(name, "nombre", 100);
        this.name = name.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws NegativeInt {
        NegativeInt.verificar(price, "precio");
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) throws NegativeInt {
        NegativeInt.verificar(stock, "cantidad");
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) throws Nulability, MaxLength {
        Nulability.verifyNulability(category, "categoria");
        Nulability.verifyNulability(category.trim(), "categoria");
        MaxLength.verificar(category, "categoria", 100);
        this.category = category.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws Nulability, MaxLength {
        Nulability.verifyNulability(description, "descripcion");
        Nulability.verifyNulability(description.trim(), "descripcion");
        MaxLength.verificar(description, "descripcion", 300);
        this.description = description.trim();
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
