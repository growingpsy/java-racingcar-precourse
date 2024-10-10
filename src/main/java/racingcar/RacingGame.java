package racingcar;

import java.util.Scanner;
import java.util.ArrayList;
import utils.GameUtils;
import java.util.Random;

public class RacingGame {
    private Scanner scanner;
    private ArrayList<Car> cars;
    private GameUtils gameUtils;
    private int numberOfGames;

    public RacingGame(Scanner scanner) {
        this.scanner = scanner;
        this.gameUtils = new GameUtils();
        this.cars = new ArrayList<>();
    }

    public void playGame() {
        try {
            inputCars();
            inputNumberOfGames();
            for (int i = 0; i < this.numberOfGames; i++) {
                moveCars();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void inputCars() {
        final String INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
        final String ERROR_MESSAGE = "[ERROR] 자동차의 이름은 5자 이하여야 합니다.";
        System.out.println(INPUT_MESSAGE);
        String userCarsName = scanner.nextLine();
        String[] nameList = userCarsName.split(",");

        for (String name : nameList) {
            if (gameUtils.isRightCarName(name)) {
                registerCar(name);
            } else {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
        }
    }

    public void registerCar(String carName) {
        Car car = new Car(carName);
        this.cars.add(car);
    }

    public void inputNumberOfGames() {
        final String INPUT_MESSAGE = "시도할 회수는 몇회인가요?";
        final String ERROR_MESSAGE = "[ERROR] 시도 횟수는 숫자여야 한다";
        System.out.println(INPUT_MESSAGE);
        String userNumber = scanner.nextLine();
        if(!gameUtils.isNumber(userNumber))
            throw new IllegalArgumentException(ERROR_MESSAGE);
        this.numberOfGames = Integer.parseInt(userNumber);
    }

    public void getCarinfo() {
    }
    public void moveCars() {
        Random random = new Random();
        for (int i = 0; i < this.cars.size(); i++) {  // 'Cars' -> 'cars'로 수정
            Car nowCar = cars.get(i);
            int randNum = random.nextInt(10);  // RandomUtils 대체
            if (isGo(randNum)) nowCar.movePosition();
        }
    }
    public boolean isGo(int num) {
        if (num >= 4) return true;
        return false;
    }
    public void printResult() {
    }
    public void printWinner() {
    }
}