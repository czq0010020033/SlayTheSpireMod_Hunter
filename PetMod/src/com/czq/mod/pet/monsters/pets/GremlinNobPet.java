package com.czq.mod.pet.monsters.pets;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AngerPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class GremlinNobPet
  extends Pet
{
  public static final String ID = "GremlinNobPet";
  private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("GremlinNob");
  public static final String NAME = monsterStrings.NAME;
  public static final String[] MOVES = monsterStrings.MOVES;
  public static final String[] DIALOG = monsterStrings.DIALOG;
  private static final int HP_MIN = 82;
  private static final int HP_MAX = 86;
  private static final int A_2_HP_MIN = 85;
  private static final int A_2_HP_MAX = 90;
  private static final int BASH_DMG = 6;
  private static final int RUSH_DMG = 14;
  private static final int A_2_BASH_DMG = 8;
  private static final int A_2_RUSH_DMG = 16;
  private static final int DEBUFF_AMT = 2;
  private int bashDmg;
  private int rushDmg;
  private static final byte BULL_RUSH = 1;
  private static final byte SKULL_BASH = 2;
  private static final byte BELLOW = 3;
  private static final int ANGRY_LEVEL = 2;
  private boolean usedBellow = false;
  
  public GremlinNobPet(int mana, float x)
  {
    super(NAME, "GremlinNobPet", 10, -70.0F, -10.0F,270.0F * 2F / 3F, 380.0F * 2F / 3F , null);
    if(x > 0.25F)
    {
    	x += 0.01F;
    }
    else{
    	x -= 0.01F;
    }
    this.drawX = (Settings.WIDTH *x);
	this.drawY = AbstractDungeon.floorY - 20F * Settings.scale;
	  this.intentOffsetX = (-30.0F * 2F / 3F  * Settings.scale);
    this.type = AbstractMonster.EnemyType.NORMAL;
    this.dialogX = (-60.0F  * 2F / 3F* Settings.scale );
	this.dialogY = (50.0F  * 2F / 3F* Settings.scale  );
    if (AbstractDungeon.ascensionLevel >= 8) {
      setHp(15, 15);
    } else {
      setHp(15, 15);
    }
    if (AbstractDungeon.ascensionLevel >= 3)
    {
      this.bashDmg = 2;
      this.rushDmg = 4;
    }
    else
    {
      this.bashDmg = 2;
      this.rushDmg = 4;
    }
    this.damage.add(new DamageInfo(this, this.rushDmg));
    this.damage.add(new DamageInfo(this, this.bashDmg));
    
    loadAnimation("images/monsters/theBottom/nobGremlin/skeleton.atlas", "images/monsters/theBottom/nobGremlin/skeleton.json", 3F / 2F);
    
    AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
    e.setTime(e.getEndTime() * MathUtils.random());
  }
  

  
  public void takeTurn()
  {
	  AbstractMonster monster;
    switch (this.nextMove)
    {
    case 3: 
      playSfx();
      AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[0], 1.0F, 3.0F));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new AngerPower(this, 1), 1));
      
      break;
    case 2: 
		 monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(this, true);
		if (monster != null){
			AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, 
      
        (DamageInfo)this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
      
      	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this, new VulnerablePower(monster, 1, true), 1));
		}
      break;
    case 1: 
    	 monster = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(this, true);
		if (monster != null){
      AbstractDungeon.actionManager.addToBottom(new AnimateSlowAttackAction(this));
      AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, 
      
        (DamageInfo)this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
      break;
    }
    AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
  }
  
  private void playSfx()
  {
    int roll = MathUtils.random(2);
    if (roll == 0) {
      AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_GREMLINNOB_1A"));
    } else if (roll == 1) {
      AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_GREMLINNOB_1B"));
    } else {
      AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_GREMLINNOB_1C"));
    }
  }
  
  protected void getMove(int num)
  {
    if (!this.usedBellow)
    {
      this.usedBellow = true;
      setMove((byte)3, AbstractMonster.Intent.BUFF);
      return;
    }
    if (num < 33)
    {
      setMove(MOVES[0], (byte)2, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
      return;
    }
    if (lastTwoMoves((byte)1)) {
      setMove(MOVES[0], (byte)2, AbstractMonster.Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(1)).base);
    } else {
      setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
    }
  }
  
  public void die()
  {
    super.die();
   
  }
}
