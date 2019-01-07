package com.czq.mod.pet.characters;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.abstracts.CustomPlayer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.attack.EagleFirst;
import com.czq.mod.pet.cards.attack.EagleSecond;
import com.czq.mod.pet.cards.attack.LeftHookFist;
import com.czq.mod.pet.cards.attack.PummelGiant;
import com.czq.mod.pet.cards.attack.Strike_Giant;
import com.czq.mod.pet.cards.pet.CultistPetCall;
import com.czq.mod.pet.cards.power.ComboForm;
import com.czq.mod.pet.cards.skill.BurningPactGiant;
import com.czq.mod.pet.cards.skill.DeathExplode;
import com.czq.mod.pet.cards.skill.DeathProphecy;
import com.czq.mod.pet.cards.skill.Defend_Giant;
import com.czq.mod.pet.cards.skill.DuplicateHand;
import com.czq.mod.pet.patches.LibraryTypeEnum;
import com.czq.mod.pet.patches.ModClassEnum;
import com.czq.mod.pet.relics.SoulBox;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class Giant extends CustomPlayer {
	private static final int ENERGY_PER_TURN = 3;
	public static final Logger logger = LogManager.getLogger(Giant.class
			.getName());
	/*
	 * private static final String MARISA_SHOULDER_2 =
	 * "img/char/Marisa/shoulder2.png"; private static final String
	 * MARISA_SHOULDER_1 = "img/char/Marisa/shoulder1.png"; private static final
	 * String MARISA_CORPSE = "img/char/Marisa/fallen.png"; private static final
	 * String MARISA_SKELETON_ATLAS = "img/char/Marisa/MarisaModel_v02.atlas";
	 * private static final String MARISA_SKELETON_JSON =
	 * "img/char/Marisa/MarisaModel_v02.json"; private static final String
	 * MARISA_ANIMATION = "Idle";
	 * 
	 * private static final String ORB_VFX = "img/UI/energyBlueVFX.png";
	 */
	private static final float[] LAYER_SPEED = { -40.0F, -32.0F, 20.0F, -20.0F,
			0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
	/*private static final String[] ORB_TEXTURES = {
			"images/ui/EPanel/layer5.png", "images/ui/EPanel/layer4.png",
			"images/ui/EPanel/layer3.png", "images/ui/EPanel/layer2.png",
			"images/ui/EPanel/layer1.png", "images/ui/EPanel/layer0.png",
			"images/ui/EPanel/layer5d.png", "images/ui/EPanel/layer4d.png",
			"images/ui/EPanel/layer3d.png", "images/ui/EPanel/layer2d.png",
			"images/ui/EPanel/layer1d.png" };*/
	private static final String[] ORB_TEXTURES = {
		"images/ui/topPanel/blue/1.png", "images/ui/topPanel/blue/1d.png",
		"images/ui/topPanel/blue/2.png", "images/ui/topPanel/blue/2d.png",
		"images/ui/topPanel/blue/3.png", "images/ui/topPanel/blue/3d.png",
		"images/ui/topPanel/blue/4.png", "images/ui/topPanel/blue/4d.png",
		"images/ui/topPanel/blue/5.png", "images/ui/topPanel/blue/5d.png",
		"images/ui/topPanel/blue/border.png" };
	private static final int STARTING_HP = 70;
	private static final int MAX_HP = 70;
	private static final int STARTING_GOLD = 99;
	private static final int HAND_SIZE = 5;
	private static final int ASCENSION_MAX_HP_LOSS = 5;

//	public static BeastStatus beastStatus = BeastStatus.NORMAL;

	public Giant(String name) {
		super(name, ModClassEnum.Giant, ORB_TEXTURES,
				"images/ui/topPanel/energyBlueVFX.png", LAYER_SPEED, null, null);

		this.dialogX = (this.drawX + 0.0F * Settings.scale);
		this.dialogY = (this.drawY + 220.0F * Settings.scale);

		logger.info("init Giant");

		initializeClass(null, "images/characters/ironclad/shoulder2.png",
				"images/characters/ironclad/shoulder.png",
				"images/characters/ironclad/corpse.png",

				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(
						3));

		// loadAnimation("img/char/ironclad/MarisaModel_v02.atlas",
		// "img/char/ironclad/MarisaModel_v02.json", 1.0F);
		loadAnimation("images/characters/ironclad/idle/skeleton.atlas",
				"images/characters/ironclad/idle/skeleton.json", 1.0F);
		// AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle",
		// true);
		AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
		e.setTime(e.getEndTime() * MathUtils.random());
		e.setTimeScale(1.0F);

		if ((ModHelper.enabledMods.size() > 0)
				&& ((ModHelper.isModEnabled("Diverse"))
						|| (ModHelper.isModEnabled("Chimera")) || (ModHelper
							.isModEnabled("Blue Cards")))) {
			this.masterMaxOrbs = 1;
		}
		logger.info("init finish");
	}

	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList();
		/*	for(AbstractCard card : CardLibrary.getCardList(LibraryTypeEnum.GIANT_COLOR)){
			retVal.add(card.cardID);
		}
	*/
		 retVal.add(Strike_Giant.ID);
		 retVal.add(Strike_Giant.ID);
		 retVal.add(Defend_Giant.ID);
		 retVal.add(Defend_Giant.ID);
		 retVal.add(Defend_Giant.ID);
		 retVal.add(Defend_Giant.ID);
		 retVal.add(CultistPetCall.ID);
		 retVal.add(EagleFirst.ID);
		 retVal.add(EagleFirst.ID);
		 retVal.add(EagleSecond.ID);
		// retVal.add(RightHookFist.ID);
		// retVal.add(FinalFist.ID);
		// retVal.add(RetainCombo.ID);
		// retVal.add(UnlimitedCombo.ID);
		// retVal.add(SnakeForm.ID);
		// retVal.add(CultistForm.ID);
		// retVal.add(HealWind.ID);
		// retVal.add(DiscoverCombo.ID);
		// retVal.add(InfiniteCombo.ID);

		// retVal.add(TimeTravel.ID);
	
		/*
		 * retVal.add(OneBlood.ID); retVal.add(BloodAttack.ID);
		 * retVal.add(Prayer.ID); retVal.add(RecoverHP.ID);
		 * retVal.add(RecoverHP.ID);
		 */

		// retVal.add(SeekCombo.ID);
		// .add(ComboConnect.ID);
		return retVal;
	}

	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList();
		retVal.add(SoulBox.ID);
		UnlockTracker.markRelicAsSeen(SoulBox.ID);
		return retVal;
	}

	public CharSelectInfo getLoadout() {
		String flavor;
		String title;
		if (Settings.language == Settings.GameLanguage.ZHS) {
			title = "猎人";
			flavor = "与野兽为伴，擅长射箭和召唤野兽。";
		} else {
			title = "Hunter";
			flavor = "He lives with beasts.";
		}
		return new CharSelectInfo(title, flavor, 60, 60, 0, 99, 5, this,

		getStartingRelics(), getStartingDeck(), false);
	}

	public CardColor getCardColor() {
		return AbstractCardEnum.GIANT_COLOR;
	}

	public AbstractCard getStartCardForEvent() {
		return new LeftHookFist();
	}

	public String getTitle(AbstractPlayer.PlayerClass playerClass) {
		String title;
		if (Settings.language == Settings.GameLanguage.ZHS) {
			title = "猎人";
		} else {
			title = "Hunter";
		}
		return title;
	}

	public Color getCardTrailColor() {
		return new Color(1.0F, 0.4F, 0.1F, 1.0F);
	}

	public int getAscensionMaxHPLoss() {
		return 5;
	}

	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontBlue;
	}

	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT",
				MathUtils.random(-0.2F, 0.2F));
		CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED,
				ScreenShake.ShakeDur.SHORT, false);
	}

	public String getCustomModeCharacterButtonSoundKey() {
		return "ATTACK_MAGIC_BEAM_SHORT";
	}

	public String getLocalizedCharacterName() {
		return "Hunter";
	}

	public AbstractPlayer newInstance() {
		return new Giant(this.name);
	}
	/*
	public void changeState(BeastStatus status) {
		if (status == beastStatus)
			return;
		beastStatus = status;
		AnimationState.TrackEntry e;
		switch (status) {
		case NORMAL:
			loadAnimation("images/characters/ironclad/idle/skeleton.atlas",
					"images/characters/ironclad/idle/skeleton.json", 1.0F);
			// AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle",
			// true);
			e = this.state.setAnimation(0, "Idle", true);
			e.setTime(e.getEndTime() * MathUtils.random());
			e.setTimeScale(1.0F);

			break;
		case BIRD:
			loadAnimation("images/monsters/theCity/byrd/flying.atlas",
					"images/monsters/theCity/byrd/flying.json", 1.0F);

			e = this.state.setAnimation(0, "idle_flap", true);
			e.setTime(e.getEndTime() * MathUtils.random());
			e.setTimeScale(1.0F);
			break;
		}
	}

	public static enum BeastStatus {
		NORMAL, BIRD
	}
	@Override
	public void update() {
		if (this.skeleton != null && !this.skeleton.getFlipX()
				&& beastStatus != BeastStatus.NORMAL) {
			this.skeleton.setFlipX(true);
		}
		if (!flipHorizontal && beastStatus != BeastStatus.NORMAL)
			flipHorizontal = true;
		super.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		if (this.skeleton != null && !this.skeleton.getFlipX()
				&& beastStatus != BeastStatus.NORMAL) {
			this.skeleton.setFlipX(true);
		}
		if (!flipHorizontal && beastStatus != BeastStatus.NORMAL)
			flipHorizontal = true;
		super.render(sb);
	}
*/
	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.characters.AbstractPlayer#getCardRenderColor()
	 */
	@Override
	public Color getCardRenderColor() {
		return Color.SCARLET;
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.characters.AbstractPlayer#getSlashAttackColor()
	 */
	@Override
	public Color getSlashAttackColor() {
		return Color.RED;
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.characters.AbstractPlayer#getSpireHeartSlashEffect()
	 */
	@Override
	public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
		return new AbstractGameAction.AttackEffect[] {
				AbstractGameAction.AttackEffect.SLASH_HEAVY,
				AbstractGameAction.AttackEffect.FIRE,
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
				AbstractGameAction.AttackEffect.SLASH_HEAVY,
				AbstractGameAction.AttackEffect.FIRE,
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.characters.AbstractPlayer#getSpireHeartText()
	 */
	@Override
	public String getSpireHeartText() {
		String Description;
		if (Settings.language == Settings.GameLanguage.ENG) {
			Description = "NL WOW...";
		} else {
			Description = "NL 你的精神紧绷到极限...";
		}
		return Description;
	}

	/**
	 * 描述：
	 * 
	 * @see com.megacrit.cardcrawl.characters.AbstractPlayer#getVampireText()
	 */
	@Override
	public String getVampireText() {
		return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[0];
	}
}
