
import java.util.HashMap;
import java.util.Map;

public class Registor {

    private static final Map<Integer, String> colorMap = new HashMap<>();

    static {
        colorMap.put(0, "preto");
        colorMap.put(1, "marrom");
        colorMap.put(2, "vermelho");
        colorMap.put(3, "laranja");
        colorMap.put(4, "amarelo");
        colorMap.put(5, "verde");
        colorMap.put(6, "azul");
        colorMap.put(7, "violeta");
        colorMap.put(8, "cinza");
        colorMap.put(9, "branco");
    }

    public String getResistorColors(String input) {
        String[] parts = input.split(" ");
        String value = parts[0];
        double resistorValue;
        StringBuilder colors = new StringBuilder();

        if (value.contains("k")) {
            resistorValue = Double.parseDouble(value.replace("k", "")) * 1000;
        } else if (value.contains("M")) {
            resistorValue = Double.parseDouble(value.replace("M", "")) * 1000000;
        } else {
            resistorValue = Double.parseDouble(value);
        }

        String[] digits = String.valueOf((int) resistorValue).split("");
        colors.append(colorMap.get(Integer.parseInt(digits[0]))).append(" ")
                .append(colorMap.get(Integer.parseInt(digits[1]))).append(" ")
                .append(colorMap.get(digits.length > 2 ? digits.length - 2 : 0))
                .append(" ")
                .append("dourado");

        return colors.toString();
    }

    public static void main(String[] args) {
        Registor resistor = new Registor();
        System.out.println(resistor.getResistorColors("47 ohms")); // amarelo violeta preto dourado
        System.out.println(resistor.getResistorColors("4.7k ohms")); // amarelo violeta vermelho dourado
        System.out.println(resistor.getResistorColors("1M ohms")); // marrom preto verde dourado
    }
}
