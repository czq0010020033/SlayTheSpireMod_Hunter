package com.czq.mod.pet.cards.pet;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.StringConstant;
import com.czq.mod.pet.monsters.pets.CultistPet;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class CultistPetCall
  extends PetCall
{
  public static final String ID = "CultistPetCall";
 // private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");

   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("CultistPetCall");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

  public CultistPetCall()
  {
    //super("CultistPetCall", NAME, "colorless/skill/finesse", "colorless/skill/finesse", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, 0);
    super(ID, NAME, DESCRIPTION, 1, CardColor.COLORLESS, CardRarity.COMMON);
   // this.baseBlock = 5;
    this.exhaust = false;
    this.baseMagicNumber = 3;
  	this.magicNumber = this.baseMagicNumber;
  }
  
	public void use(AbstractPlayer p, AbstractMonster m) {
		 AbstractMonster pet = 	CallPet();
			if(pet != null && this.upgraded)
				pet.damage.get(0).base += 2;
		}
  
  public AbstractCard makeCopy()
  {
    return makePetCopy(new CultistPetCall());
  }
  public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(2);
			refreshDescription();
		}
	}
  
  public void refreshDescription() {
		if (exhaust && isEthereal) {
			if(this.upgraded)
				this.rawDescription = UPGRADE_DESCRIPTION + StringConstant.Ethereal + StringConstant.Exhaust;
			else
				this.rawDescription = DESCRIPTION + StringConstant.Ethereal + StringConstant.Exhaust;
		}
		else{
			if(this.upgraded)
				this.rawDescription = UPGRADE_DESCRIPTION;
			else
				this.rawDescription = DESCRIPTION ;
		}
		this.initializeDescription();
	}
  
 
}
