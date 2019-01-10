/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.helpers;

import com.megacrit.cardcrawl.core.Settings;

import basemod.BaseMod;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2019年1月1日/下午2:16:07<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2019年1月1日/下午2:16:07<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public class KeywordHelper {

	public static void InitKeyword(){

		if (Settings.language == Settings.GameLanguage.ZHS) {		
		//zhs
		BaseMod.addKeyword(
				new String[] { "连击" },
				"连击牌有固定层数，当其层数等于人物已拥有层数的下一层时，可以触发额外效果。连击-N表示人物层数不为0时可触发。 人物连击层数在回合结束时消失。");
		BaseMod.addKeyword(
				new String[] { "连击-N" },
				"当人物连击层数不为0时，可以触发额外效果。");
		BaseMod.addKeyword(
				new String[] { "触发" },
				"连击牌的额外效果现在可以被触发。");
		BaseMod.addKeyword(
				new String[] { "熟练" },
				"熟练 可以提高随从的能力。");
		BaseMod.addKeyword(
				new String[] { "邪教徒" },
				"邪教徒每回合会涨一定的力量，每一点熟练 可以提高邪教徒一点仪式能力。");
		BaseMod.addKeyword(
				new String[] { "牧师" },
				"牧师行动时可以回复人物的生命，每一点熟练 可以额外提高 2 点生命。");
		BaseMod.addKeyword(
				new String[] { "持盾地精" },
				"持盾地精行动时可以给人物添加 格挡，每一点熟练 可以额外提高 3 点格挡。");
		BaseMod.addKeyword(
				new String[] { "异鸟" },
				"异鸟拥有多次攻击次数，每一点熟练 可以提高异鸟 一次出手次数。");
		BaseMod.addKeyword(
				new String[] { "亡语" },
				"随从死亡时可以触发的额外效果。");
		BaseMod.addKeyword(
				new String[] { "回响" },
				"回响牌在使用后会复制一张相同的牌到手牌中，该牌为消耗、虚无，费用为初始费用。");
		BaseMod.addKeyword(
				new String[] { "灵力" },
				"灵力代表随从的存活回合数，灵力每回合减一，当为0时，随从死亡。");
		BaseMod.addKeyword(
				new String[] { "随从" },
				"你最多只能召唤三个随从，当拥有三个随从时，再召唤时左边的随从会被替代。敌人的群体BUFF随从也会享受。");
		BaseMod.addKeyword(
				new String[] { "爆炸" },
				"随从死亡时对所有敌人造成获得该亡语时最大生命值的伤害。");
		BaseMod.addKeyword(
				new String[] { "奉献" },
				"该随从死亡时，你将回复生命。");
		BaseMod.addKeyword(
				new String[] { "激怒" },
				"该随从死亡时，你将获得力量。");	
		BaseMod.addKeyword(
						new String[] { "先手打击" },
						"造成5点伤害，获得一层连击。0费消耗。");
		BaseMod.addKeyword(
				new String[] { "鲜血" },
				"不能被打出。消耗。");
		BaseMod.addKeyword(
				new String[] { "鲜血" },
				"失去 1 点生命，造成 7 点伤害。将一张 鲜血 放入你的抽牌堆中。 消耗。");
		}else{
			BaseMod.addKeyword(
					new String[] {"combo" },
					"Combo card can trigger extra effcts when the card's level is the next. Combo-N can trigger when player combo isn't 0. Player combo level will disppear at the end of turn.");
			BaseMod.addKeyword(
					new String[] { "echo" },
					"After use, give the same card to your hand. the same card is ethereal, exhuast and original cost.");
			BaseMod.addKeyword(
					new String[] { "trigger" },
					"Now the combo card extra effects can be triggered.");
			BaseMod.addKeyword(
					new String[] { "spirit" },
					"Spirit can enhance minions capicity.");
			BaseMod.addKeyword(
					new String[] { "cultist" },
					"Cultist can increase Strength each turn.  1 spirit can increase 1 this capacity.");
			BaseMod.addKeyword(
					new String[] { "healer" },
					"Healer can heal your HP. 1 spirit can increase 2 heal amount.");
			BaseMod.addKeyword(
					new String[] { "gremlintsundere" },
					"GremlinTsundere can give you block. 1 spirit can increase 3 block amount.");
			BaseMod.addKeyword(
					new String[] { "byrd" },
					"Byrd attack multiple times. 1 spirit can increase 1 time.");
			BaseMod.addKeyword(
					new String[] { "deathrattle" },
					"It will be triggered when minion die.");
			BaseMod.addKeyword(
					new String[] { "mana" },
					" Minion will die when mana is 0. At the end of each turn, mana decrease 1.");
			BaseMod.addKeyword(
					new String[] { "minion","minions" },
					"You can only summon 3 minions. when you have 3 minions, next minion will replace the left minion.");
			BaseMod.addKeyword(
					new String[] { "explode" },
					"When this minion die, it will deal the minion's max HP(the number is when minion receive this deathrattle) to all enimies.");
			BaseMod.addKeyword(
					new String[] { "sacrifice" },
					"When this minion die, you will heal HP.");
			BaseMod.addKeyword(
					new String[] { "revenge" },
					"when this minion die, you will gain strength.");
			BaseMod.addKeyword(
					new String[] { "blood" },
					"It can't be play. Exhuast.");
			BaseMod.addKeyword(
					new String[] { "firstcombo" },
					"Deal 5 damage. Gain combo-1. Exhaust.");
			BaseMod.addKeyword(
					new String[] { "bloodattack" },
					"0 Cost. Deal 7 damage. Lose 1 HP. Exhaust.");
			
		}
	}
}
