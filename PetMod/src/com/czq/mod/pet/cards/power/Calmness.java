package com.czq.mod.pet.cards.power;

import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.czq.mod.pet.powers.CalmnessPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Calmness extends AbstractModCustomCard {
	public static final String ID = "Calmness Giant";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;

	public Calmness() {
		super(ID, NAME, "blue/power/heatsinks", COST, DESCRIPTION,
				AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

		this.baseMagicNumber = 1;
		this.magicNumber = this.baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new CalmnessPower(p, this.magicNumber), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Calmness();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(1);
		}
	}
}
