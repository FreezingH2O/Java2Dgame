package entity.item;

import entity.ElementType;
import entity.character.Player;

public class KeyItem extends BaseItem {

	private ElementType elementType;
	
	public KeyItem(String name, ElementType elementType) {
		super(elementType + " KeyItem"); 
		this.setElementType(elementType);
	}

	@Override
	public void use(Player player) {
		// TODO Auto-generated method stub
		player.addKeyItem(this);
        System.out.println(player.getName() + " collected " + getName());  
	}
	
	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

}
