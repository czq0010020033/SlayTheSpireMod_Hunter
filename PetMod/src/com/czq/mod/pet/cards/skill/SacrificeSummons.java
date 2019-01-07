package com.czq.mod.pet.cards.skill;

import java.util.ArrayList;

import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.StringConstant;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SacrificeSummons extends AbstractModCard {
	public static final String ID = "Sacrifice Summons";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack
			.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 2;

	public SacrificeSummons() {
		super(ID, NAME, "red/skill/secondwind", "red/skill/secondWind", COST,
				DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.GIANT_COLOR, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.NONE);

		this.baseMagicNumber = 1;
		this.magicNumber = this.baseMagicNumber;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		ArrayList<AbstractMonster> pets = GiantModHelper.pets;
		int temp = 0;
		for (AbstractMonster monster : pets) {
			if (!monster.isDead || !monster.isDying)
			{	AbstractDungeon.actionManager.addToBottom(new SuicideAction(
					monster));
			temp++;
			}
		}
		for(int i = 0; i < temp;i++)
			
		{	
		 AbstractCard c = GiantModHelper.returnTrulyRandomPetCardInCombat().makeCopy();
		 c.setCostForTurn(0);
		 c.exhaust = true;
		 c.isEthereal = true;
		 if(this.upgraded)
			 c.upgrade();
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(
				c, true));
		}
	}

	public AbstractCard makeCopy() {
		return new SacrificeSummons();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
