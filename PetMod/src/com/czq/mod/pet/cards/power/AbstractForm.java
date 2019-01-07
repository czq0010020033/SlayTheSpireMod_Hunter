/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月25日/上午8:22:08<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月25日/上午8:22:08<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public abstract class AbstractForm extends AbstractModCustomCard{

	/**
	* <p>描述: </p>
	* @param id
	* @param name
	* @param jokeUrl
	* @param imgUrl
	* @param cost
	* @param rawDescription
	* @param type
	* @param color
	* @param rarity
	* @param target
	* @param dType
	*/
	/*public AbstractForm(String id, String name, String jokeUrl, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target, DamageType dType) {
		super(id, name, , imgUrl, cost, rawDescription, type, color, rarity,
				target, dType);
		
	}
*/
	/**
	* <p>描述: </p>
	* @param id
	* @param name
	* @param object
	* @param string
	* @param i
	* @param description
	* @param power
	* @param blue
	* @param uncommon
	* @param self
	*/
	public AbstractForm(String id, String name, String object, String string,
			int i, String description, CardType power, CardColor blue,
			CardRarity uncommon, CardTarget self) {
		super(id, name, object, string, i, description, power,  AbstractCardEnum.GIANT_COLOR, uncommon, self);
		
	}

	

}
