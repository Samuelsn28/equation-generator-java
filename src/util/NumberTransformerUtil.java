package util;

import java.math.BigDecimal;

public class NumberTransformerUtil {
    public static String toExponentCharacters(BigDecimal exponent,
                                                  boolean onlyOneInvisible){
        String exponentCharacteres = exponent.toString();
        StringBuilder power = new StringBuilder();

        for (int i = 0; i < exponentCharacteres.length(); i++){
            switch (exponentCharacteres.charAt(i)){
                case '1':
                    power.append("¹");
                    break;
                case '2':
                    power.append("²");
                    break;
                case '3':
                    power.append("³");
                    break;
                case '4':
                    power.append("⁴");
                    break;
                case '5':
                    power.append("⁵");
                    break;
                case '6':
                    power.append("⁶");
                    break;
                case '7':
                    power.append("⁷");
                    break;
                case '8':
                    power.append("⁸");
                    break;
                case '9':
                    power.append("⁹");
                    break;
                case '0':
                    power.append("⁰");
                    break;
                case '.':
                    power.append("·");
                    break;
                case '-':
                    power.append("⁻");
                    break;
                default:
                    throw new Error(
                            "That: '" + exponentCharacteres.charAt(i) + "' is not something from a number!");
            }
        }

        if (onlyOneInvisible && power.toString().equals("¹")){
            return "";
        }
        return power.toString();
    }
}
