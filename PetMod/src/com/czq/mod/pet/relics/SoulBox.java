package com.czq.mod.pet.relics;

import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

/*
 * 等级1：每场战斗结束回复2点生命；
 * 等级2：力量加1
 * 等级3：敏捷加1
 * 等级4：最大生命值加10
 * 等级5：连击牌伤害加50%
 * 等级6：召唤兽伤害提升50%
 * 等级7：
 */
public class SoulBox extends CustomRelic {
	public static final String ID = "GiantSoulBox";
	private static final int HEALTH_AMT = 6;
	public static int level = 1;
	public static int exp = 0;
	public static int requreExp[] = { 2, 6, 6, 8, 10, 14, 14, 14, 14, 14 };

	public SoulBox() {
		super(ID, ImageMaster.loadImage("images/giant/relics/SoulBox.png"),
				AbstractRelic.RelicTier.STARTER,
				AbstractRelic.LandingSound.MAGICAL);
		this.counter = -100;
	}

	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0] + getLevel() + this.DESCRIPTIONS[1]
				+ getNeedExp() + this.DESCRIPTIONS[2];
	}

	public void updateDescription(AbstractPlayer.PlayerClass c) {
		this.description = getUpdatedDescription();
		this.tips.clear();
		this.tips.add(new PowerTip(this.name, this.description));
		initializeTips();
	}

	public void onVictory() {
		AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(
				AbstractDungeon.player, this));
		/*
		 * AbstractPlayer p = AbstractDungeon.player; if (p.currentHealth > 0) {
		 * p.heal(6); }
		 */
		int exp = 0;
		if ((AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
			exp = 10;
		} else if ((AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite)) {
			exp = 4;
		} else if ((AbstractDungeon.getCurrRoom() instanceof MonsterRoom)) {
			exp = 2;
		}
		if (exp > 0) {
			earnExp(exp);
		}

	}

	public AbstractRelic makeCopy() {
		return new SoulBox();
	}

	public void onEquip() {
		this.counter = -100;
	}

	@Override
	public void onMonsterDeath(AbstractMonster m) {

	}

	public void earnExp(int amount) {
		int level = getLevel();
		this.counter -= amount;
		if ( level < 10 && this.counter <= -(level * 100 + requreExp[level - 1]) ) {
			this.counter = -(level + 1) * 100;
			levelUp();
		}
		updateDescription(null);
	}

	public void levelUp() {
		flash();
		AbstractDungeon.player.increaseMaxHp(5, true);
		AbstractDungeon.player.gainGold(30);

		if (Settings.language == Settings.GameLanguage.ZHS) {
			AbstractDungeon.effectList.add(new ThoughtBubble(
					AbstractDungeon.player.dialogX,
					AbstractDungeon.player.dialogY, 3.0F, "升到" +

					getLevel() + "级了！", true));
		} else {
			AbstractDungeon.effectList.add(new ThoughtBubble(
					AbstractDungeon.player.dialogX,
					AbstractDungeon.player.dialogY, 3.0F, "Rise to Level " +

					getLevel() + " !", true));
		}
		if (getLevel() == 5) {
			if (!AbstractDungeon.player.hasRelic(SoulStone.ID)) {
				AbstractDungeon.getCurrRoom()
						.addRelicToRewards(new SoulStone());
			}
		}
	}

	public int getLevel() {
		int level = Math.abs(counter) / 100;
		if (level <= 0)
			level = 1;
		else if (level >= 10)
			level = 10;
		return level;
	}

	public int getNeedExp() {
		if (level >= 10)
			return 0;
		int exp = Math.abs(counter) % 100;
		return (requreExp[getLevel() - 1] - exp);
	}
}
