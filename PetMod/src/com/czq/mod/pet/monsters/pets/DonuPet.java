package com.czq.mod.pet.monsters.pets;

import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class DonuPet
  extends Pet
{
  public static final String ID = "DonuPet";
  private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Donu");
  public static final String NAME = monsterStrings.NAME;
  public static final String[] MOVES = monsterStrings.MOVES;
  public static final String[] DIALOG = monsterStrings.DIALOG;
  public static final int HP = 250;
  public static final int A_2_HP = 265;
  private static final byte BEAM = 0;
  private static final byte CIRCLE_OF_PROTECTION = 2;
  private static final int ARTIFACT_AMT = 2;
  private static final int BEAM_DMG = 10;
  private static final int BEAM_AMT = 2;
  private static final int A_2_BEAM_DMG = 12;
  private int beamDmg;
  private static final String CIRCLE_NAME = MOVES[0];
  private static final int CIRCLE_STR_AMT = 3;
  
  
  
  public DonuPet(int mana, float x)
  {
    super(NAME, "DonuPet", 25, 0.0F, -20.0F, 390.0F / 2F, 390.0F / 2F, null, 100.0F, 20.0F);
    this.drawX = (Settings.WIDTH *x);
	this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;
    loadAnimation("images/monsters/theForest/donu/skeleton.atlas", "images/monsters/theForest/donu/skeleton.json", 2.0F);
    
    AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
    e.setTime(e.getEndTime() * MathUtils.random());
    this.stateData.setMix("Hit", "Idle", 0.1F);
    this.stateData.setMix("Attack_2", "Idle", 0.1F);
    
    this.type = AbstractMonster.EnemyType.NORMAL;
    this.dialogX = (-200.0F * Settings.scale / 2F);
    this.dialogY = (10.0F * Settings.scale / 2F);
    if (AbstractDungeon.ascensionLevel >= 9) {
      setHp(25, 25);
    } else {
      setHp(25, 25);
    }
    if (AbstractDungeon.ascensionLevel >= 4) {
      this.beamDmg = 12;
    } else {
      this.beamDmg = 10;
    }
    this.damage.add(new DamageInfo(this, this.beamDmg));
  }
  
  public void changeState(String stateName)
  {
    switch (stateName)
    {
    case "ATTACK": 
      this.state.setAnimation(0, "Attack_2", false);
      this.state.addAnimation(0, "Idle", true, 0.0F);
    }
  }
  
  public void damage(DamageInfo info)
  {
    super.damage(info);
    if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.output > 0))
    {
      this.state.setAnimation(0, "Hit", false);
      this.state.addAnimation(0, "Idle", true, 0.0F);
    }
  }
  
  public void usePreBattleAction()
  {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArtifactPower(this, 2)));
  }
  
  public void takeTurn()
  {
    int i;
    switch (this.nextMove)
    {
    case 0: 
    	AbstractMonster monster =  AbstractDungeon.getCurrRoom().monsters.getRandomMonster(this, true);
    	if(monster != null)
    	{
    		AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK"));
    		AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
    		for (i = 0; i < 2; i++) {
    			AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, 
    					(DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.FIRE));
    		}
    	}
      break;
    case 2: 
      for (AbstractMonster m :GiantModHelper.pets) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new StrengthPower(m, 3), 3));
      }
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new StrengthPower(AbstractDungeon.player, 3), 3));
    }
    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
  }
  
  protected void getMove(int num)
  {
    if (GameActionManager.turn % 2 == 0) {
      setMove((byte)0, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 2, true);
    } else {
      setMove(CIRCLE_NAME, (byte)2, AbstractMonster.Intent.BUFF);
    }
  }
  
  public void die()
  {
    super.die();
  }
}
