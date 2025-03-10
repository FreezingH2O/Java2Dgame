package entity.item;

import javafx.scene.image.Image;

public class BaseItem {
	protected String name;
	protected Image pic;
	
	public BaseItem(String name) {
		super();
		this.name = name;
		this.pic = new Image("item/"+getName()+".png");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

}
