package utils;

public class GameUtils {
    public GameUtils() {
    }

    // 자동차 이름의 길이가 5자 이하인지 확인하는 메서드
    public boolean isRightCarName(String userCarsName) {
        return userCarsName.length() <= 5;
    }

    // 입력된 문자열이 숫자인지 확인하는 메서드
    public boolean isNumber(String userNumber) {
        for (int i = 0; i < userNumber.length(); i++) {
            if (!Character.isDigit(userNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}