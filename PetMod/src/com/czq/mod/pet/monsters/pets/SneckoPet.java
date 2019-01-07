package com.czq.mod.pet.monsters.pets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateFastAttackAction;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;

public class SneckoPet
  extends Pet
{
  public static final String ID = "SneckoPet";
  private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Snecko");
  public static final String NAME = monsterStrings.NAME;
  public static final String[] MOVES = monsterStrings.MOVES;
  public static final String[] DIALOG = monsterStrings.DIALOG;
  private static final byte GLARE = 1;
  private static final byte BITE = 2;
  private static final byte TAIL = 3;
  private static final int BITE_DAMAGE = 15;
  private static final int TAIL_DAMAGE = 8;
  private static final int A_2_BITE_DAMAGE = 18;
  private static final int A_2_TAIL_DAMAGE = 10;
  private int biteDmg;
  private int tailDmg;
  private static final int VULNERABLE_AMT = 2;
  private static final int HP_MIN = 114;
  private static final int HP_MAX = 120;
  private static final int A_2_HP_MIN = 120;
  private static final int A_2_HP_MAX = 125;
  private boolean firstTurn = true;
  
  public SneckoPet(int mana, float x)
  {
    super(NAME, "SneckoPet", 15, -30.0F, -20.0F, 310.0F * 2F / 3F, 305.0F  * 2F / 3F, null);
    this.drawX = (Settings.WIDTH *x);
	this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;
    loadAnimation("images/monsters/theCity/reptile/skeleton.atlas", "images/monsters/theCity/reptile/skeleton.json",  3F/ 2F);
    
    AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
    e.setTime(e.getEndTime() * MathUtils.random());
    this.stateData.setMix("Hit", "Idle", 0.1F);
    e.setTimeScale(0.8F);
    if (AbstractDungeon.ascensionLevel >= 7) {
      setHp(15, 15);
    } else {
      setHp(15, 15);
    }
    if (AbstractDungeon.ascensionLevel >= 2)
    {
      this.biteDmg = 18;
      this.tailDmg = 10;
    }
    else
    {
      this.biteDmg = 15;
      this.tailDmg = 8;
    }
    this.damage.add(new DamageInfo(this, this.biteDmg));
    this.damage.add(new DamageInfo(this, this.tailDmg));
  }
  
  public void takeTurn()
  {
	  AbstractMonster monster;

    switch (this.nextMove)
    {
    case 1: 
      AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK"));
      AbstractDungeon.actionManager.addToBottom(new VFXAction(this, new IntimidateEffect(this.hb.cX, this.hb.cY), 0.5F));
      
      AbstractDungeon.actionManager.addToBottom(new FastShakeAction(AbstractDungeon.player, 1.0F, 1.0F));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new ConfusionPower(AbstractDungeon.player)));
      
      break;
    case 2: 
    	monster =  AbstractDungeon.getCurrRoom().monsters.getRandomMonster(this, true);
    	if(monster != null){
      AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK_2"));
      AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new BiteEffect(monster.hb.cX + 
      
        MathUtils.random(-50.0F, 50.0F) * Settings.scale, monster.hb.cY + 
        MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE
        .cpy()), 0.3F));
      
      AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, 
        (DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.NONE));
    	}
      break;
    case 3: 
    	monster =  AbstractDungeon.getCurrRoom().monsters.getRandomMonster(this, true);
    	if(monster != null){
      AbstractDungeon.actionManager.addToBottom(new AnimateFastAttackAction(this));
      AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, 
      
        (DamageInfo)this.damage.get(1), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
      
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this, new VulnerablePower(monster, 2, true), 2));
    	}
      break;
    }
    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
  }
  
  public void changeState(String stateName)
  {
    switch (stateName)
    {
    case "ATTACK": 
      this.state.setAnimation(0, "Attack", false);
      this.state.addAnimation(0, "Idle", true, 0.0F);
      break;
    case "ATTACK_2": 
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
  
  protected void getMove(int num)
  {
    if (this.firstTurn)
    {
      this.firstTurn = false;
      setMove(MOVES[0], (byte)1, AbstractMonster.Intent.STRONG_DEBUFF);
      return;
    }
    if (num < 40)
    {
      setMove(MOVES[1], (byte)3, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
      return;
    }
    if (lastTwoMoves((byte)2)) {
      setMove(MOVES[1], (byte)3, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
    } else {
      setMove(MOVES[2], (byte)2, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
    }
  }
  
  public void die()
  {
    super.die();
    CardCrawlGame.sound.play("SNECKO_DEATH");
  }
}
