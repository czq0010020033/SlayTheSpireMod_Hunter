package com.czq.core;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnPowersModifiedSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.attack.FinalFist;
import com.czq.mod.pet.cards.attack.LeftHookFist;
import com.czq.mod.pet.cards.attack.RightHookFist;
import com.czq.mod.pet.characters.Giant;
import com.czq.mod.pet.helpers.KeywordHelper;
import com.czq.mod.pet.helpers.ModCardHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.patches.ModClassEnum;
import com.czq.mod.pet.relics.SoulBox;
import com.czq.mod.pet.relics.SoulStone;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class GiantMod implements PostExhaustSubscriber, PostBattleSubscriber,
		PostDungeonInitializeSubscriber, EditCharactersSubscriber,
		PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber,
		EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber,
		OnPowersModifiedSubscriber, PostDrawSubscriber {
	public static final Logger logger = LogManager.getLogger(GiantMod.class
			.getName());
	
	public static final Color STARLIGHT = CardHelper.getColor(0.0F, 10.0F,
		125.0F);
	//public static final Color STARLIGHT = Color.RED;
	
	public GiantMod() {
		BaseMod.subscribe(this);
		logger.info("creating the color");
		// BaseMod.addColor(.MARISA_COLOR, STARLIGHT, STARLIGHT, STARLIGHT,
		// STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT,
		// "img/512/bg_attack_MRS_s.png", "img/512/bg_skill_MRS_s.png",
		// "img/512/bg_power_MRS_s.png", "img/512/cardOrb.png",
		// "img/1024/bg_attack_MRS.png", "img/1024/bg_skill_MRS.png",
		// "img/1024/bg_power_MRS.png", "img/1024/cardOrb.png");
/*		BaseMod.addColor(AbstractCardEnum.GIANT_COLOR, STARLIGHT, STARLIGHT,
				STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT,
				"images/ui/512/bg_attack_MRS_s.png",
				"images/ui/512/bg_skill_MRS_s.png",
				"images/ui/512/bg_power_MRS_s.png",
				"images/ui/512/cardOrb.png",
				"images/ui/1024/bg_attack_MRS.png",
				"images/ui/1024/bg_skill_MRS.png",
				"images/ui/1024/bg_power_MRS.png", "images/ui/1024/cardOrb.png");*/
		
		// BaseMod.addColor(AbstractCardEnum.MARISA_DERIVATIONS, STARLIGHT,
		// STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT,
		// "img/512/bg_attack_MRS_s.png", "img/512/bg_skill_MRS_s.png",
		// "img/512/bg_power_MRS_s.png", "img/512/cardOrb.png",
		// "img/1024/bg_attack_MRS.png", "img/1024/bg_skill_MRS.png",
		// "img/1024/bg_power_MRS.png", "img/1024/cardOrb.png");
	}

	public void receiveEditCharacters() {
		logger.info("begin editing characters");

		// logger.info("add " + ThModClassEnum.MARISA.toString());
		// BaseMod.addCharacter(new Marisa("Marisa"),
		// AbstractCardEnum.MARISA_COLOR, "img/charSelect/MarisaButton.png",
		// "img/charSelect/marisaPortrait.jpg", ThModClassEnum.MARISA);
		BaseMod.addCharacter(new Giant("Hunter"), "images/giant/ui/charSelect/HunterButton.png",
				"images/ui/charSelect/ironcladPortrait.jpg", ModClassEnum.Giant);
		logger.info("done editing characters");
	}

	public void receiveEditRelics() {
		logger.info("Begin editing relics.");

		BaseMod.addRelicToCustomPool(new SoulBox(), AbstractCardEnum.GIANT_COLOR);
		BaseMod.addRelicToCustomPool(new SoulStone(), AbstractCardEnum.GIANT_COLOR);
		

		logger.info("Relics editting finished.");
	}

	public void receiveEditCards() {
		logger.info("begin editting cards");

		logger.info("add cards for PetMod");

		ModCardHelper.addCardToLibrary();
		// BaseMod.addCard(new Exhaustion_MRS());
		// UnlockTracker.unlockCard("Exhaustion_MRS");

		// BaseMod.addCard(new Exhaustion_MRS());
		// UnlockTracker.unlockCard("Exhaustion_MRS");

		logger.info("done editing cards");
	}

	public static void initialize() {
		new GiantMod();
		BaseMod.addColor(AbstractCardEnum.GIANT_COLOR, STARLIGHT, STARLIGHT,
				STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT, STARLIGHT,
				"images/cardui/512/bg_attack_red.png",
				"images/cardui/512/bg_skill_red.png",
				"images/cardui/512/bg_power_red.png",
				"images/cardui/512/card_red_orb.png",
				"images/cardui/1024/bg_attack_red.png",
				"images/cardui/1024/bg_skill_red.png",
				"images/cardui/1024/bg_power_red.png", "images/cardui/1024/card_red_orb.png");
	}

	public void receivePostExhaust(AbstractCard c) {
	}

	public void receivePostBattle(AbstractRoom r) {
		logger.info("ThMod : PostBattle");
	}

	public void receiveCardUsed(AbstractCard card) {
		logger.info("ThMod : Card used : " + card.cardID);
		if (card.retain) {
			card.retain = false;
		}
	}

	public void receivePowersModified() {
	}

	public void receivePostDungeonInitialize() {
	}

	public void receivePostDraw(AbstractCard arg0) {
	}

	@Override
	public void receiveEditKeywords() {
		logger.info("Setting up custom keywords");
		KeywordHelper.InitKeyword();
		logger.info("Keywords setting finished.");
	}

	public void receiveEditStrings() {
		logger.info("start editing strings");
		
	
		if (Settings.language == Settings.GameLanguage.ZHS) {
			logger.info("lang == zhs");

			String cardStrings = Gdx.files.internal("localization/giant/zhs/cards.json")
					.readString(String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
			String powerStrings = Gdx.files
					.internal("localization/giant/zhs/powers.json").readString(
							String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
			String relicStrings = Gdx.files
					.internal("localization/giant/zhs/relics.json").readString(
							String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
			String uiStrings = Gdx.files
					.internal("localization/giant/zhs/ui.json").readString(
							String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
			/*
			 * String relicStrings =
			 * Gdx.files.internal("localization/ThMod_Fnh_relics-zh.json"
			 * ).readString(String.valueOf(StandardCharsets.UTF_8));
			 * BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
			 * 
			 * String cardStrings =
			 * Gdx.files.internal("localization/ThMod_Fnh_cards-zh.json"
			 * ).readString(String.valueOf(StandardCharsets.UTF_8));
			 * BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
			 * 
			 * String powerStrings =
			 * Gdx.files.internal("localization/ThMod_Fnh_powers-zh.json"
			 * ).readString(String.valueOf(StandardCharsets.UTF_8));
			 * BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
			 */
		} else {
			logger.info("lang == eng");
			String cardStrings = Gdx.files.internal("localization/giant/eng/cards.json")
					.readString(String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
			String powerStrings = Gdx.files
					.internal("localization/giant/eng/powers.json").readString(
							String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
			String relicStrings = Gdx.files
					.internal("localization/giant/eng/relics.json").readString(
							String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
			String uiStrings = Gdx.files
					.internal("localization/giant/eng/ui.json").readString(
							String.valueOf(StandardCharsets.UTF_8));
			BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
		}
		logger.info("done editing strings");
	}

	public void receivePostInitialize() {
	}
}
