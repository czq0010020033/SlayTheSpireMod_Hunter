/**      
 * 项目名称：PetMod<br> 
 */    
package com.czq.mod.pet.helpers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

/**
 * 描述: TODO(描述这个类的作用)<br><br>
 * 作者： Administrator/850530595@qq.com<br>    
 * 创建时间：2018年12月30日/下午10:17:07<br>    
 * 修改人：Administrator/850530595@qq.com<br>    
 * 修改时间：2018年12月30日/下午10:17:07<br>    
 * 修改备注：<br>
 * 版本：1.0
 */
public class StringConstant {
	 private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("StringConstant");
	  public static final String[] TEXT = uiStrings.TEXT;
	  public static final String cantUseMessage = TEXT[0];
	  public static final String Echo = TEXT[1];
	  public static final String Exhaust = TEXT[2];
	  public static final String Ethereal = TEXT[3];
	  public static final String Innate = TEXT[4];
	  public static final String Available = TEXT[5];
	  public static final String PetCallcantUse = TEXT[6];
	  
	  //monster intent
	  public static final String IntentBlock_1 =   TEXT[7];
	  public static final String IntentBlock_2 = TEXT[8];
	  public static final String IntentHeal_1 =   TEXT[9]; 
	  public static final String IntentHeal_2 = TEXT[10];
	  public static final String IntentLighting_1 =   TEXT[11]; 
	  public static final String IntentLighting_2 = TEXT[12];
	  public static final String IntentLighting_3 =   TEXT[13]; 
	  public static final String IntentLighting_4 = TEXT[14];
		  
}
