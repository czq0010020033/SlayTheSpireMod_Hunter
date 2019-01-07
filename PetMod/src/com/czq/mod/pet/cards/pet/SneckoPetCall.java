package com.czq.mod.pet.cards.pet;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.MathUtils;
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

public class SneckoPetCall
  extends PetCall
{
  public static final String ID = "SneckoPetCall";
 // private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");
	  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("SneckoPetCall");
	  public static final String NAME = cardStrings.NAME;
	  public static final String DESCRIPTION = cardStrings.DESCRIPTION;

  public SneckoPetCall()
  {
    //super("CultistPetCall", NAME, "colorless/skill/finesse", "colorless/skill/finesse", 1, DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, 0);
    super(ID, NAME, DESCRIPTION, 3, CardColor.COLORLESS, CardRarity.UNCOMMON);
   // this.baseBlock = 5;
  }
  
  public AbstractCard makeCopy()
  {
    return new SneckoPetCall();
  }
  public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(2);
		}
	}
  
 
}
