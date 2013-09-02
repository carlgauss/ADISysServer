package business.applicationservice.checker;
public class StringGenerator {

    public static String getAStringOfExactlyLength(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += "v";
        }
        return result;
    }
}
