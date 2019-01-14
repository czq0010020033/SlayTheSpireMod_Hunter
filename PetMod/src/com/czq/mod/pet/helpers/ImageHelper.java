/**      
 * 项目名称：PetMod<br> 
 */
package com.czq.mod.pet.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 描述: TODO(描述这个类的作用)<br>
 * <br>
 * 作者： Administrator/850530595@qq.com<br>
 * 创建时间：2019年1月1日/下午8:04:56<br>
 * 修改人：Administrator/850530595@qq.com<br>
 * 修改时间：2019年1月1日/下午8:04:56<br>
 * 修改备注：<br>
 * 版本：1.0
 */
public class ImageHelper {

	public static final String CardPrefix = "images/giant/cards/";
	private static final String PowerPrefix = "images/giant/powers/";
	private static final String RelicPrefix = "images/giant/relics/";

	public static String getCardImageUrl(String id, AbstractCard.CardType type) {
		String tmp = id.replace(" ", "");
		StringBuilder str = new StringBuilder();
		str.append(CardPrefix);
		switch (type) {
		case ATTACK:
			str.append("attack/");
			break;
		case CURSE:
			str.append("curse/");
			break;
		case POWER:
			str.append("power/");
			break;
		case SKILL:
			str.append("skill/");
			break;
		case STATUS:
			str.append("status/");
			break;
		default:
			break;
		}
		str.append(tmp + ".png");
		if (!fileExist(str.toString())) {
			switch (type) {
			case ATTACK:
				return CardPrefix + "attack/Strike_Giant.png";
			case CURSE:
			case POWER:
				return CardPrefix + "power/Power.png";
			case SKILL:
				return CardPrefix + "skill/Skill.png";
			case STATUS:
			default:
				break;
			}
			return CardPrefix + "attack/Strike_Giant.png";
		}
		return str.toString();
	}

	public static String getPowerImageUrl(String id) {
		String tmp = id.replace(" ", "");
		tmp = PowerPrefix + tmp + ".png";
		if (!fileExist(tmp)) {
			if (tmp.contains("128"))
				tmp = PowerPrefix + "BloodyPower_128.png";
			else
				tmp = PowerPrefix + "BloodyPower_48.png";
		}
		return tmp;
	}

	public static String getRelicImageUrl(String id) {
		String tmp = id.replace(" ", "");
		return RelicPrefix + tmp + ".png";
	}

	private static boolean fileExist(String path) {
		if (ImageHelper.class.getResource("/" + path) != null)
			return true;
		return false;
	}

	public static void main(String args[]) {
		if (ImageHelper.class.getResource("/" + CardPrefix
				+ "attack/EagleThird.png") != null) {
			System.out.println("true");
		} else
			System.out.println("false");
	}
}
