package entity.item;

import entity.character.Player;

public class BaseItem {
	protected String name;

	public BaseItem(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
