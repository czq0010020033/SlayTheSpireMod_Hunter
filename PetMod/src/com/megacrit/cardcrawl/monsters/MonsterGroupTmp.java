package com.megacrit.cardcrawl.monsters;
/*import com.czq.mod.pet.helpers.GiantModHelper;

package com.megacrit.cardcrawl.monsters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.cards.attack.FinalFist;
import com.czq.mod.pet.cards.attack.LeftHookFist;
import com.czq.mod.pet.cards.attack.RightHookFist;
import com.czq.mod.pet.cards.pet.DonuPetCall;
import com.czq.mod.pet.cards.pet.PetCapture;
import com.czq.mod.pet.cards.pet.SneckoPetCall;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.cards.green.Strike_Green;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.MonsterQueueItem;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MonsterGroup {
	private static final Logger logger = LogManager
			.getLogger(MonsterGroup.class.getName());
	public ArrayList<AbstractMonster> monsters = new ArrayList();
	public AbstractMonster hoveredMonster = null;
	

	public MonsterGroup(AbstractMonster[] input) {
		Collections.addAll(this.monsters, input);
	}

	public void addMonster(AbstractMonster m) {
		this.monsters.add(m);
	}

	public void addMonster(int newIndex, AbstractMonster m) {
		this.monsters.add(newIndex, m);
	}

	public void addSpawnedMonster(AbstractMonster m) {
		this.monsters.add(0, m);
	}

	public MonsterGroup(AbstractMonster m) {
		this(new AbstractMonster[] { m });
	}

	public void showIntent() {
		for (AbstractMonster m : this.monsters) {
			m.createIntent();
		}
	}

	public void init() {

		logger.info("floorNum = " + AbstractDungeon.floorNum);
	//	if (AbstractDungeon.floorNum == 1) {

//			boolean hasPetCard = false;
			/*
			 * if (AbstractDungeon.player != null) {
			 * //添加卡牌到Cardlibrary,同时添加卡牌到Pool，使有几率选到这些牌 。其中卡的pool值为1或2。 //
			 * PetHelper.addPetCardToLibrary();
			 * 
			 * ArrayList<AbstractCard> cards =
			 * AbstractDungeon.player.masterDeck.group; for (int i = 0; i <
			 * cards.size(); i++) { if (cards.get(i) != null &&
			 * PetHelper.isPetCard(cards.get(i))) { hasPetCard = true; } } if
			 * (!hasPetCard) { int removeAttack = 0; int removeDefend = 0; if
			 * (AbstractDungeon.player.chosenClass == PlayerClass.IRONCLAD) {
			 * Iterator<AbstractCard> itr = cards.iterator(); while
			 * (itr.hasNext()) { AbstractCard c = itr.next(); if
			 * (c.cardID.equals(Defend_Red.ID) && (removeDefend < 1)) {
			 * itr.remove(); removeDefend ++; } else if
			 * (c.cardID.equals(Strike_Red.ID) && (removeAttack < 2)) {
			 * itr.remove(); removeAttack++; } }
			 * 
			 * 
			 * } else if (AbstractDungeon.player.chosenClass ==
			 * PlayerClass.THE_SILENT) { Iterator<AbstractCard> itr =
			 * cards.iterator(); while (itr.hasNext()) { AbstractCard c =
			 * itr.next(); if (c.cardID.equals(Defend_Green.ID) && (removeDefend
			 * < 1)) { itr.remove(); removeDefend++; } else if
			 * (c.cardID.equals(Strike_Green.ID) && (removeAttack < 2)) {
			 * itr.remove(); removeAttack++; } } }
			 */
			/*
			 * if (CardLibrary.cards.containsKey(CultistPetCall.ID)) {
			 * 
			 * AbstractDungeon.player.masterDeck.addToTop(CardLibrary
			 * .getCard(CultistPetCall.ID).makeCopy());
			 * 
			 * } if (CardLibrary.cards.containsKey(GremlinFatPetCall.ID)) {
			 * 
			 * AbstractDungeon.player.masterDeck.addToTop(CardLibrary
			 * .getCard(GremlinFatPetCall.ID).makeCopy());
			 * 
			 * }
			 */
			/*
			 * if (CardLibrary.cards.containsKey(SneckoPetCall.ID)) {
			 * 
			 * AbstractDungeon.player.masterDeck.addToTop(CardLibrary
			 * .getCard(SneckoPetCall.ID).makeCopy());
			 * 
			 * } if (CardLibrary.cards.containsKey(DonuPetCall.ID)) {
			 * 
			 * AbstractDungeon.player.masterDeck.addToTop(CardLibrary
			 * .getCard(DonuPetCall.ID).makeCopy());
			 * 
			 * } if (CardLibrary.cards.containsKey(PetCapture.ID)) {
			 * 
			 * AbstractDungeon.player.masterDeck.addToTop(CardLibrary
			 * .getCard(PetCapture.ID).makeCopy());
			 * 
			 * }
			 */

	/*		ArrayList<AbstractCard> cards = AbstractDungeon.player.masterDeck.group;

			Iterator<AbstractCard> itr = cards.iterator();
			while (itr.hasNext()) {
				AbstractCard c = itr.next();
				itr.remove();
			}
			AbstractDungeon.player.masterDeck.addToTop(CardLibrary.getCard(
					LeftHookFist.ID).makeCopy());
			AbstractDungeon.player.masterDeck.addToTop(CardLibrary.getCard(
					LeftHookFist.ID).makeCopy());
			AbstractDungeon.player.masterDeck.addToTop(CardLibrary.getCard(
					RightHookFist.ID).makeCopy());
			AbstractDungeon.player.masterDeck.addToTop(CardLibrary.getCard(
					RightHookFist.ID).makeCopy());
			AbstractDungeon.player.masterDeck.addToTop(CardLibrary.getCard(
					FinalFist.ID).makeCopy());
		}
		*/
		/*
		 * boolean hasPet = false; for(int i = 0; i < monsters.size(); i++){
		 * if(monsters.get(i).id.equals("Darkling")){ hasPet = true; break; }
		 * if(monsters.get(i).id.equals(CultistPet.ID)){ hasPet = true; break; }
		 * 
		 * } if(!hasPet){ addMonster( 0, new CultistPet(0, 0,true)); }
		  GiantModHelper.pets.clear();
		
		for (AbstractMonster m : this.monsters) {
			m.init();
		}
	}

	public void add(AbstractMonster m) {
		this.monsters.add(m);
	}

	public void usePreBattleAction() {
		if (AbstractDungeon.loading_post_combat) {
			return;
		}
		for (AbstractMonster m : this.monsters) {
			m.usePreBattleAction();
			m.useUniversalPreBattleAction();
		}
	}

	public boolean areMonstersDead() {
		for (AbstractMonster m : this.monsters) {
			if ((!m.isDead) && (!m.escaped) && (!isPet(m))) {
				return false;
			}
		}
		return true;
	}

	public boolean areMonstersBasicallyDead() {
		for (AbstractMonster m : this.monsters) {
			if ((!m.isDying) && (!m.isEscaping) && (!isPet(m))) {
				return false;
			}
		}
		return true;
	}

	public void applyPreTurnLogic() {
		for (AbstractMonster m : this.monsters) {
			if ((!m.isDying) && (!m.isEscaping)) {
				if (!m.hasPower("Barricade")) {
					m.loseBlock();
				}
				m.applyStartOfTurnPowers();
			}
		}
	}

	public AbstractMonster getMonster(String id) {
		for (AbstractMonster m : this.monsters) {
			if (m.id.equals(id)) {
				return m;
			}
		}
		logger.info("MONSTER GROUP getMonster(): Could not find monster: " + id);
		return null;
	}

	public void queueMonsters() {
		for (AbstractMonster m : this.monsters) {
			if ((!m.isDeadOrEscaped()) || (m.halfDead)) {
				AbstractDungeon.actionManager.monsterQueue
						.add(new MonsterQueueItem(m));
			}
		}
	}

	public boolean haveMonstersEscaped() {
		for (AbstractMonster m : this.monsters) {
			if (!m.escaped) {
				return false;
			}
		}
		return true;
	}

	public boolean isMonsterEscaping() {
		for (AbstractMonster m : this.monsters) {
			if (m.nextMove == 99) {
				return true;
			}
		}
		return false;
	}

	public boolean hasMonsterEscaped() {
		for (AbstractMonster m : this.monsters) {
			if (m.isEscaping) {
				return true;
			}
		}
		if ((CardCrawlGame.dungeon instanceof TheCity)) {
			return true;
		}
		return false;
	}

	public AbstractMonster getRandomMonster() {
		return getRandomMonster(null, false);
	}

	public AbstractMonster getRandomMonster(boolean aliveOnly) {
		return getRandomMonster(null, aliveOnly);
	}

	public AbstractMonster getRandomMonster(AbstractMonster exception,
			boolean aliveOnly, Random rng) {
		if (areMonstersBasicallyDead()) {
			return null;
		}
		if (exception == null) {
			if (aliveOnly) {
				ArrayList<AbstractMonster> tmp = new ArrayList();
				for (AbstractMonster m : this.monsters) {
					if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
							&& (!isPet(m))) {
						tmp.add(m);
					}
				}
				if (tmp.size() <= 0) {
					return null;
				}
				return (AbstractMonster) tmp.get(rng.random(0, tmp.size() - 1));
			}
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : this.monsters) {
				if (!isPet(m)) {
					tmp.add(m);
				}
			}
			if (tmp.size() <= 0) {
				return null;
			}
			return (AbstractMonster) tmp
					.get(MathUtils.random(0, tmp.size() - 1));
			// return (AbstractMonster)this.monsters.get(rng.random(0,
			// this.monsters.size() - 1));
		}
		if (this.monsters.size() == 1) {
			return (AbstractMonster) this.monsters.get(0);
		}
		if (aliveOnly) {
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : this.monsters) {
				if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
						&& (!exception.equals(m)) && (!isPet(m))) {
					tmp.add(m);
				}
			}
			if (tmp.size() == 0) {
				return null;
			}
			return (AbstractMonster) tmp.get(rng.random(0, tmp.size() - 1));
		}
		ArrayList<AbstractMonster> tmp = new ArrayList();
		for (AbstractMonster m : this.monsters) {
			if (!exception.equals(m)) {
				tmp.add(m);
			}
		}
		return (AbstractMonster) tmp.get(rng.random(0, tmp.size() - 1));
	}

	public AbstractMonster getRandomMonster(AbstractMonster exception,
			boolean aliveOnly) {
		if (areMonstersBasicallyDead()) {
			return null;
		}
		if (exception == null) {
			if (aliveOnly) {
				ArrayList<AbstractMonster> tmp = new ArrayList();
				for (AbstractMonster m : this.monsters) {
					if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
							&& (!isPet(m))) {
						tmp.add(m);
					}
				}
				if (tmp.size() <= 0) {
					return null;
				}
				return (AbstractMonster) tmp.get(MathUtils.random(0,
						tmp.size() - 1));
			}
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : this.monsters) {
				if (!isPet(m)) {
					tmp.add(m);
				}
			}
			if (tmp.size() <= 0) {
				return null;
			}
			return (AbstractMonster) tmp
					.get(MathUtils.random(0, tmp.size() - 1));
			// return (AbstractMonster)this.monsters.get(MathUtils.random(0,
			// this.monsters.size() - 1));
		}
		if (this.monsters.size() == 1) {
			return (AbstractMonster) this.monsters.get(0);
		}
		if (aliveOnly) {
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : this.monsters) {
				if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
						&& (!exception.equals(m) && (!isPet(m)))) {
					tmp.add(m);
				}
			}
			if (tmp.size() == 0) {
				return null;
			}
			return (AbstractMonster) tmp
					.get(MathUtils.random(0, tmp.size() - 1));
		}
		ArrayList<AbstractMonster> tmp = new ArrayList();
		for (AbstractMonster m : this.monsters) {
			if (!exception.equals(m) && (!isPet(m))) {
				tmp.add(m);
			}
		}
		return (AbstractMonster) tmp.get(MathUtils.random(0, tmp.size() - 1));
	}

	public static boolean isPet(AbstractMonster monster) {
		return PetHelper.isPet(monster);
	}

	public AbstractMonster getRandomMonsterByPet(AbstractMonster exception,
			boolean aliveOnly) {
		if (areMonstersBasicallyDead()) {
			return null;
		}
		if (exception == null) {
			if (aliveOnly) {
				ArrayList<AbstractMonster> tmp = new ArrayList();
				for (AbstractMonster m : this.monsters) {
					if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
							&& (!m.isDead) && (!isPet(m))) {
						tmp.add(m);
					}
				}
				if (tmp.size() <= 0) {
					return null;
				}
				return (AbstractMonster) tmp.get(MathUtils.random(0,
						tmp.size() - 1));
			}
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : this.monsters) {
				if (!isPet(m)) {
					tmp.add(m);
				}
			}
			if (tmp.size() <= 0) {
				return null;
			}
			return (AbstractMonster) tmp
					.get(MathUtils.random(0, tmp.size() - 1));
		}
		if (this.monsters.size() == 1) {
			return (AbstractMonster) this.monsters.get(0);
		}
		if (aliveOnly) {
			ArrayList<AbstractMonster> tmp = new ArrayList();
			for (AbstractMonster m : this.monsters) {
				if ((!m.halfDead) && (!m.isDying) && (!m.isEscaping)
						&& (!m.isDead) && (!exception.equals(m)) && (!isPet(m))) {
					tmp.add(m);
				}
			}
			if (tmp.size() == 0) {
				return null;
			}
			return (AbstractMonster) tmp
					.get(MathUtils.random(0, tmp.size() - 1));
		}
		ArrayList<AbstractMonster> tmp = new ArrayList();
		for (AbstractMonster m : this.monsters) {
			if (!exception.equals(m) && (!isPet(m))) {
				tmp.add(m);
			}
		}
		return (AbstractMonster) tmp.get(MathUtils.random(0, tmp.size() - 1));
	}

	public void update() {
		for (AbstractMonster m : this.monsters) {
			m.update();
		}
		if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.DEATH) {
			this.hoveredMonster = null;
			for (AbstractMonster m : this.monsters) {
				if ((!m.isDying) && (!m.isEscaping)) {
					m.hb.update();
					m.intentHb.update();
					m.healthHb.update();
					if (((m.hb.hovered) || (m.intentHb.hovered) || (m.healthHb.hovered))
							&& (!AbstractDungeon.player.isDraggingCard)) {
						this.hoveredMonster = m;
						break;
					}
				}
			}
			if (this.hoveredMonster == null) {
				AbstractDungeon.player.hoverEnemyWaitTimer = -1.0F;
			}
		} else {
			this.hoveredMonster = null;
		}
	}

	public void updateAnimations() {
		for (AbstractMonster m : this.monsters) {
			m.updatePowers();
		}
	}

	public void escape() {
		for (AbstractMonster m : this.monsters) {
			m.escape();
		}
	}

	public void unhover() {
		for (AbstractMonster m : this.monsters) {
			m.unhover();
		}
	}

	public void render(SpriteBatch sb) {
		if ((this.hoveredMonster != null) && (!this.hoveredMonster.isDead)
				&& (!this.hoveredMonster.escaped)
				&& (AbstractDungeon.player.hoverEnemyWaitTimer < 0.0F)
				&& (!AbstractDungeon.isScreenUp)) {
			this.hoveredMonster.renderTip(sb);
		}
		for (AbstractMonster m : this.monsters) {
			m.render(sb);
		}
	}

	public void applyEndOfTurnPowers() {
		for (AbstractMonster m : this.monsters) {
			if ((!m.isDying) && (!m.isEscaping)) {
				m.applyEndOfTurnTriggers();
			}
		}
		for (AbstractPower p : AbstractDungeon.player.powers) {
			p.atEndOfRound();
		}
		for (AbstractMonster m : this.monsters) {
			if ((!m.isDying) && (!m.isEscaping)) {
				for (AbstractPower p : m.powers) {
					p.atEndOfRound();
				}
			}
		}
	}

	public void renderReticle(SpriteBatch sb) {
		for (AbstractMonster m : this.monsters) {
			if ((!m.isDying) && (!m.isEscaping)) {
				m.renderReticle(sb);
			}
		}
	}

	public ArrayList<String> getMonsterNames() {
		ArrayList<String> arr = new ArrayList();
		for (AbstractMonster m : this.monsters) {
			arr.add(m.id);
		}
		return arr;
	}
}
*/