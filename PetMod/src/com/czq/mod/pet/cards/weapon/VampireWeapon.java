package com.czq.mod.pet.cards.weapon;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.powers.VampireWeaponPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VampireWeapon extends AbstractModCard {
	public static final String ID = "Vampire Weapon";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;

	public VampireWeapon() {
		super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST,
				DESCRIPTION, AbstractCard.CardType.POWER,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.SPECIAL,
				AbstractCard.CardTarget.NONE);
		this.baseMagicNumber = 1;
		this.magicNumber = this.baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new VampireWeaponPower(p, this.magicNumber), this.magicNumber));
	}

	public AbstractCard makeCopy() {
		return new VampireWeapon();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(0);
		}
	}
}
