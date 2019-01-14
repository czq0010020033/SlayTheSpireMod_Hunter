/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.cards.weapon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.cards.status.BloodGiant;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.powers.ComboPower;
import com.czq.mod.pet.powers.EaglePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
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
public class FinalShoot extends AbstractModCard {
	private static final Logger logger = LogManager.getLogger(FinalShoot.class
			.getName());
	public static final String ID = "Final Shoot";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;

	public FinalShoot() {
		super(ID, NAME, "colorless/skill/finesse", "colorless/skill/finesse",
				3, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.SPECIAL,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = 0;
		this.baseMagicNumber = 0;
		this.magicNumber = this.baseMagicNumber;
	}

	
	@Override
	public AbstractCard makeCopy() {
		return new FinalShoot();
	}


	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(1);
		}
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		this.baseDamage = GiantModHelper.weapon.damage;
		this.magicNumber = GiantModHelper.weapon.durability;
		calculateCardDamage(m);
		if (this.magicNumber <= 0)
			return;
		if (this.magicNumber > 1) {
			for (int i = 0; i < this.magicNumber - 1; i++) {
				AbstractDungeon.actionManager
						.addToBottom(new PummelDamageAction(m, new DamageInfo(
								p, this.damage, this.damageTypeForTurn)));
			}
		}

		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_HEAVY));

	}

	public void applyPowers() {
		this.baseDamage = GiantModHelper.weapon.damage;
		super.applyPowers();
	}

}
