package com.czq.mod.pet.cards;

import com.czq.mod.pet.cards.AbstractModCard;
import com.czq.mod.pet.cards.skill.EchoDefend;
import com.czq.mod.pet.cards.status.BloodGiant;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractEchoCard extends AbstractModCard {
	
	public AbstractEchoCard(String id, String name, String jokeUrl, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target) {
		super(id, name, jokeUrl, imgUrl, cost, rawDescription, type,  AbstractCardEnum.GIANT_COLOR,
				rarity, target);

	}
	public AbstractEchoCard(String id, String name, String imgUrl,
			int cost, String rawDescription, CardType type, CardColor color,
			CardRarity rarity, CardTarget target) {
		super(id, name, imgUrl,  cost, rawDescription, type,  AbstractCardEnum.GIANT_COLOR,
				rarity, target);

	}
	public void use(AbstractPlayer p, AbstractMonster m) {
		makeEchoCard();
	}
	
	public void makeEchoCard(){
		AbstractCard c = this.makeStatEquivalentCopy();
		c.exhaust = true;
		c.isEthereal = true;
		AbstractCard temp = CardLibrary.getCard(c.cardID).makeCopy();
		c.cost =temp.cost;
		c.costForTurn = c.cost;
		c.isCostModified = false;
		c.isCostModifiedForTurn = false;
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(
				c));
	}
	
	public abstract void refreshDescription();
	
	public AbstractCard makeEchoCopy(AbstractCard c){
		if (exhaust && isEthereal) {
			c.isEthereal = true;
			c.exhaust = true;
			if(c instanceof AbstractEchoCard)
			{
				((AbstractEchoCard)c).refreshDescription();
			}
			
		}
		return c;
	}

	public AbstractCard makeStatEquivalentCopy() {

		AbstractCard card = makeCopy();
		for (int i = 0; i < this.timesUpgraded; i++) {
			card.upgrade();
		}
		card.exhaust = this.exhaust;
		card.isEthereal = this.isEthereal;
		card.name = this.name;
		card.target = this.target;
		card.upgraded = this.upgraded;
		card.timesUpgraded = this.timesUpgraded;
		card.baseDamage = this.baseDamage;
		card.baseBlock = this.baseBlock;
		card.baseMagicNumber = this.baseMagicNumber;
		card.cost = this.cost;
		card.costForTurn = this.costForTurn;
		card.isCostModified = this.isCostModified;
		card.isCostModifiedForTurn = this.isCostModifiedForTurn;
		card.inBottleLightning = this.inBottleLightning;
		card.inBottleFlame = this.inBottleFlame;
		card.inBottleTornado = this.inBottleTornado;
		card.isSeen = this.isSeen;
		card.isLocked = this.isLocked;
		card.misc = this.misc;
		card.freeToPlayOnce = this.freeToPlayOnce;
		if(card instanceof AbstractEchoCard)
		{
			((AbstractEchoCard)card).refreshDescription();
		}
		
		return card;
	}

}
