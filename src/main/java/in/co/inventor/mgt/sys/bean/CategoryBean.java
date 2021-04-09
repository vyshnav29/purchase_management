package in.co.inventor.mgt.sys.bean;

public class CategoryBean extends BaseBean {

	private long brandId;
	private String brandName;
	private String name;
	private String decription;
	private String image;

	public CategoryBean() {
		// TODO Auto-generated constructor stub
	}
	
	

	public long getBrandId() {
		return brandId;
	}



	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}



	public String getBrandName() {
		return brandName;
	}



	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String description) {
		this.decription = description;
	}

	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

	
	
}
