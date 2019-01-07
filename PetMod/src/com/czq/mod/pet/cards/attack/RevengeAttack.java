/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.cards.attack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2018年10月16日/下午6:58:47<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2018年10月16日/下午6:58:47<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class RevengeAttack extends AbstractModCard {
	private static final Logger logger = LogManager
			.getLogger(RevengeAttack.class.getName());
	public static final String ID = "Revenge Attack";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	public final int BASE_DAMAGE = 12;
	public RevengeAttack() {
		super(ID, NAME, "colorless/skill/finesse", "colorless/skill/finesse",
				2, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = BASE_DAMAGE;
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#makeCopy()
	 */
	@Override
	public AbstractCard makeCopy() {
		return new RevengeAttack();
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#upgrade()
	 */
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(1);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		this.baseDamage = GameActionManager.damageReceivedThisCombat + this.BASE_DAMAGE;
//		this.baseMagicNumber = AbstractDungeon.player.damagedThisCombat;
		calculateCardDamage(m);
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}

	public void applyPowers() {
		this.baseDamage = GameActionManager.damageReceivedThisCombat + this.BASE_DAMAGE;
	//	this.baseMagicNumber = AbstractDungeon.player.damagedThisCombat;
	//	this.magicNumber = this.baseMagicNumber;
		super.applyPowers();

	}

}
