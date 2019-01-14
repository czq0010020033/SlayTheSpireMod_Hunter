package com.czq.mod.pet.cards.weapon;

import com.czq.mod.pet.actions.IncreaseWeaponDamageAction;
import com.czq.mod.pet.actions.IncreaseWeaponDurabilityAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.powers.PoisonWeaponPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WeaponUpgrade extends AbstractModCard {
	public static final String ID = "Weapon Upgrade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;

	public WeaponUpgrade() {
		super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST,
				DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.SPECIAL,
				AbstractCard.CardTarget.NONE);
		this.baseMagicNumber = 5;
		this.magicNumber = this.baseMagicNumber;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {

		AbstractDungeon.actionManager
		.addToBottom(new IncreaseWeaponDamageAction(this.magicNumber));
		AbstractCard c = this.makeStatEquivalentCopy();
		AbstractDungeon.actionManager
				.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
	}

	public AbstractCard makeCopy() {
		return new WeaponUpgrade();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
		}
	}
}
