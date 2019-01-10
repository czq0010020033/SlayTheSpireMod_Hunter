package com.czq.mod.pet.relics;

import basemod.abstracts.CustomRelic;

import com.czq.mod.pet.helpers.ImageHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SummonerMedal

extends CustomRelic
{
  public static final String ID = "SummonerMedal";
  private static final int STR = 1;
  
  public SummonerMedal()
  {
    super(ID, ImageMaster.loadImage(ImageHelper.getRelicImageUrl(ID)), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.CLINK);
  }
  
  public String getUpdatedDescription()
  {
    return this.DESCRIPTIONS[0];
  }
  
  
  
  public AbstractRelic makeCopy()
  {
    return new SummonerMedal();
  }
}
