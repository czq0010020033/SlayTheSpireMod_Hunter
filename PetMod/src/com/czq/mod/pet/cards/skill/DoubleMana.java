package com.czq.mod.pet.cards.skill;

import com.czq.mod.pet.actions.DoubleHpAction;
import com.czq.mod.pet.actions.DoubleManaAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleMana extends AbstractModCard {
	public static final String ID = "Double Mana";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	 public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;

	public DoubleMana() {
		super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST,
				DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.ENEMY);
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if(this.upgraded){
			AbstractDungeon.actionManager.addToBottom(new DoubleManaAction(m, 2));
			  AbstractDungeon.actionManager.addToBottom(new DoubleHpAction(m, 2));
		}
		else
		{
		AbstractDungeon.actionManager.addToBottom(new DoubleManaAction(m, 1));
		  AbstractDungeon.actionManager.addToBottom(new DoubleHpAction(m, 1));
		}
	}

	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if (!canUse) {
			return false;
		}
		if (m != null && m instanceof Pet)
			return true;
		else {
			this.cantUseMessage = StringConstant.cantUseMessage;
			return false;
		}
	}

	public AbstractCard makeCopy() {
		return new DoubleMana();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}


}
