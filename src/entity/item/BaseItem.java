package entity.item;

import entity.character.Player;

public abstract class BaseItem {
	protected String name;

	public BaseItem(String name) {
		super();
		this.name = name;
	}

	public abstract void use(Player player);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
