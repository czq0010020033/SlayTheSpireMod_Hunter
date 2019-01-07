package com.czq.mod.pet.cards.pet;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.czq.mod.pet.actions.pet.SpawnPetAction;
import com.czq.mod.pet.cards.AbstractCardEnum;
import com.czq.mod.pet.cards.AbstractModCustomCard;
import com.czq.mod.pet.helpers.GiantModHelper;
import com.czq.mod.pet.helpers.ImageHelper;
import com.czq.mod.pet.helpers.PetHelper;
import com.czq.mod.pet.monsters.pets.Pet;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class PetCall extends AbstractModCustomCard {
	private static final Logger logger = LogManager
			.getLogger(PetCall.class.getName());
	
//	public String petName;

/*	public PetCall(String id, String name, String petName, int cost , AbstractCard.CardColor color,	AbstractCard.CardRarity rarity) {

		super(id, name, "colorless/skill/finesse", "colorless/skill/finesse",
				cost, "召唤" + petName + ",最多两只宠物。再使用则所有宠物加3力量。 NL 消耗", 
				AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED,
				rarity, AbstractCard.CardTarget.NONE);
		this.petName = petName;
		 this.exhaust = true;
	}
*/	
	public PetCall(String id, String name, String description, int cost , AbstractCard.CardColor color,	AbstractCard.CardRarity rarity) {
		super(id, name,  ImageHelper.getCardImageUrl(id, AbstractCard.CardType.SKILL), 
				cost, description, 
				AbstractCard.CardType.SKILL, AbstractCardEnum.GIANT_COLOR,
				rarity, AbstractCard.CardTarget.NONE);
		
		this.baseMagicNumber = 4;
		this.magicNumber = this.baseMagicNumber;
		 this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		logger.info("use card");
		
	    
		CallPet();
//	 else if (pets.size() >= 2) {
		/*	if (pets.get(0) != null
					&& (pets.get(0).drawX < Settings.WIDTH * 0.25F))
			{
				//   AbstractDungeon.actionManager.addToBottom(new CannotLoseAction());
				      AbstractDungeon.actionManager.addToBottom(new AnimateShakeAction(pets.get(0), 1.0F, 0.1F));
				      AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(pets.get(0)));
				      AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0F));
				      AbstractDungeon.actionManager.addToBottom(new SuicideAction(pets.get(0), false));
				      pets.remove(0);
				      createPet(this.cardID, Pet.LEFT_X);
			}
			else if(pets.get(1) != null && (pets.get(1).drawX < Settings.WIDTH * 0.25F))
			{
			//	 AbstractDungeon.actionManager.addToBottom(new CannotLoseAction());
			      AbstractDungeon.actionManager.addToBottom(new AnimateShakeAction(pets.get(1), 1.0F, 0.1F));
			      AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(pets.get(1)));
			      AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0F));
			      AbstractDungeon.actionManager.addToBottom(new SuicideAction(pets.get(1), false));
			      pets.remove(1);
			      createPet(this.cardID, Pet.LEFT_X);
			}
		*/	
		/*	for (AbstractMonster pet_tmp : AbstractDungeon.getCurrRoom().monsters.pets) {
				if (pet_tmp != null && (!pet_tmp.isDead) && (!pet_tmp.isDying)) {
					AbstractDungeon.actionManager.addToBottom(new TalkAction(
							pet_tmp, "ka ka!!!", 1F, 1.5F));
					AbstractDungeon.actionManager
							.addToBottom(new ApplyPowerAction(pet_tmp, pet_tmp,
									new StrengthPower(pet_tmp, 3), 3));
				}
			}
			*/
	//	}
		
	}
	
/*	 public boolean canUse(AbstractPlayer p, AbstractMonster m)
	  {
		  boolean canUse = super.canUse(p, m);
		    if (!canUse) {
		      return false;
		    }
		    
		    ArrayList<AbstractMonster> pets = GiantModHelper.pets;
		    if(pets.size() >=2 )
		    {
		    	for(AbstractMonster pet : pets)
		    	{
		    		if ((pet == null)
							|| (pet != null && ((pet.isDead) || (pet.isDying)))) {
		    			return true;
					}
		    	}
		    }
		    else
		    	return true;
		    this.cantUseMessage = StringConstant.PetCallcantUse;
		    return false;
	  }
	  
*/
/*	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(3);
			this.exhaust = false;
			//this.rawDescription = "召唤一只" + petName + ",最多两只宠物。再使用变成所有宠物加3力量。";
			initializeDescription();
		}
	}
*/
	 
	 
	 public AbstractMonster CallPet(){
			ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
			if(monsters == null)
				return null;
			ArrayList<AbstractMonster> pets = GiantModHelper.pets;
				AbstractMonster pet;
			//	logger.info("after judge Darkling");
			Iterator<AbstractMonster> itr = pets.iterator();
			while (itr.hasNext()) {
				pet = itr.next();
				if ((pet == null)
						|| (pet != null && ((pet.isDead) || (pet.isDying)))) {
					itr.remove();
				}
			}
			logger.info("after remove pet");
			if (pets.size() == 0) {
				return createPet(this.cardID, Pet.LEFT_X);
					
			} else if (pets.size() == 1) {
			
				if (pets.get(0) != null
						&& (pets.get(0).drawX < Settings.WIDTH * 0.25F))
				 return	createPet(this.cardID, Pet.RIGHT_X);
				else
				return 	createPet(this.cardID, Pet.LEFT_X);
			}	  else if (pets.size() >= 2) {
					if (pets.get(0) != null
				&& (pets.get(0).drawX < Settings.WIDTH * 0.25F))
		{
			//   AbstractDungeon.actionManager.addToBottom(new CannotLoseAction());
			      AbstractDungeon.actionManager.addToBottom(new AnimateShakeAction(pets.get(0), 1.0F, 0.1F));
			      AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(pets.get(0)));
			      AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0F));
			      AbstractDungeon.actionManager.addToBottom(new SuicideAction(pets.get(0)));
			      pets.remove(0);
			 return     createPet(this.cardID, Pet.LEFT_X);
		}
		else if(pets.get(1) != null && (pets.get(1).drawX < Settings.WIDTH * 0.25F))
		{
		//	 AbstractDungeon.actionManager.addToBottom(new CannotLoseAction());
		      AbstractDungeon.actionManager.addToBottom(new AnimateShakeAction(pets.get(1), 1.0F, 0.1F));
		      AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(pets.get(1)));
		      AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0F));
		      AbstractDungeon.actionManager.addToBottom(new SuicideAction(pets.get(1)));
		      pets.remove(1);
		return      createPet(this.cardID, Pet.LEFT_X);
		}
	
			}	
			return null;
	 }
	private AbstractMonster createPet(String CardId, float pos_x) {
		
		    
		ArrayList<AbstractMonster> pets = GiantModHelper.pets;
		String petId = CardId.replace("Call", "");
		
		AbstractMonster pet = PetHelper.getPetInstance(petId, 0, pos_x);
		
		if(pet == null){
			logger.info("pet == null");
		}
		/*if (CultistPetCall.ID.equals(CardId)) {
			pet = new CultistPet(pos_x, 0, true);
			logger.info("after create pet");
			if(this.upgraded){
				if(pet.damage.size() >= 1)
				{
					pet.damage.get(0).base = 8;
				}
			}
		} else if (GremlinNobPetCall.ID.equals(CardId)) {
			pet = new GremlinNobPet(pos_x);
			logger.info("after create pet");
			if(this.upgraded){
				if(pet.damage.size() >= 2)
				{
				pet.damage.get(0).base = 3;
				pet.damage.get(1).base = 5;
				}
			}
		}  else if(GremlinFatPetCall.ID.equals(CardId))
		{
			pet = new GremlinFatPet(pos_x, 0);
			logger.info("after create pet");
			if(this.upgraded){
				if(pet.damage.size() >= 1)
				{
					pet.damage.get(0).base = 4;
				}
			}
		}else if(ChampPetCall.ID.equals(CardId))
		{
			pet = new ChampPet(pos_x);
			logger.info("after create pet");
		}*/

		if (pet != null && pet instanceof Pet) {
			pets.add(pet);
			
			beforeSpawn(pet);
			AbstractDungeon.actionManager.addToBottom(new SpawnPetAction((Pet) pet,this.magicNumber,false));
		}
		logger.info("after SpawnPetAction");
		
		return pet;
	}

	
	public void beforeSpawn(AbstractMonster pet){
		
	}
	
	public AbstractCard makePetCopy(AbstractCard c){
		if (exhaust && isEthereal) {
			c.isEthereal = true;
			c.exhaust = true;
			refreshDescription();
		}
		return c;
	}
	
	public void refreshDescription() {
	}
}
