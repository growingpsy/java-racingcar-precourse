package racingcar;

import utils.GameUtils;
import java.util.*;

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
            final String OUTPUT_MESSAGE = "실행 결과";
            System.out.println(OUTPUT_MESSAGE);
            for (int i = 0; i < this.numberOfGames; i++) {
                moveCars();
                printResult();
            }
            printWinner();
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
        for (int i = 0; i < this.cars.size(); i++) {
            Car nowCar = cars.get(i);
            String positionBar = makePositionBar(nowCar.getCarPosition());
            System.out.println(nowCar.getCarName() + " : " + positionBar);
        }
        System.out.println();
    }
    public String makePositionBar(int position) {
        String bar = "";
        for (int i = 0; i < position; i++) {
            bar += "-";
        }
        return bar;
    }
    public void printWinner() {
        final String OUTPUT_MESSAGE = "최종 우승자: ";
        ArrayList<String> winnerList = getWinnerList();
        System.out.println(OUTPUT_MESSAGE + Arrays.toString(winnerList.toArray()).replaceAll("[\\[\\]]", ""));
    }
    public ArrayList<String> getWinnerList() {
        Collections.sort(this.cars, new UserComparator());
        final int winnerPosition = this.cars.get(0).getCarPosition(); //우승자가 맨 처음에 있음
        ArrayList<String> winnerList = new ArrayList<String>();
        for (int i = 0; i < this.cars.size(); i++) {
            Car nowCar = this.cars.get(i);
            if (winnerPosition == nowCar.getCarPosition()) {
                winnerList.add(nowCar.getCarName());
            }
        }
        return winnerList;
    }
}

class UserComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        if (c1.getCarPosition() < c2.getCarPosition()) return 1;
        if (c1.getCarPosition() > c2.getCarPosition()) return -1;
        return 0;
    }
}