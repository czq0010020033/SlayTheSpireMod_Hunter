package com.czq.mod.pet.cards.weapon;

import java.util.ArrayList;

import com.czq.mod.pet.actions.EquipWeaponAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.WeaponHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HunterBow extends AbstractModCard {
	public static final String ID = "Hunter Bow";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 3;

	public HunterBow() {
		super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST,
				DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.NONE);
		this.baseBlock = 5;
		this.baseMagicNumber = 5;
		this.magicNumber = this.baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p,
				this.block));
		AbstractDungeon.actionManager.addToBottom(new EquipWeaponAction(2, 4));
		ArrayList<AbstractCard> cards = WeaponHelper.getRandomWeaponCards(5);
		for (AbstractCard c : cards) {
			if (this.upgraded)
				c.upgrade();
			AbstractDungeon.actionManager
					.addToBottom(new MakeTempCardInHandAction(c));
		}

	}

	public AbstractCard makeCopy() {
		return new HunterBow();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(3);
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
