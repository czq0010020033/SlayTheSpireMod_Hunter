/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月26日/下午12:58:12<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月26日/下午12:58:12<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public abstract class AbstractModCard extends AbstractModCustomCard {
	
	
	public AbstractModCard(String id, String name, String jokeUrl, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target) {
		super(id, name, jokeUrl, imgUrl, cost, rawDescription, type,  AbstractCardEnum.GIANT_COLOR,
				rarity, target);

	}
	
	public AbstractModCard(String id, String name, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target) {
		super(id, name, imgUrl,  cost, rawDescription, type,  AbstractCardEnum.GIANT_COLOR,
				rarity, target);

	}
}
