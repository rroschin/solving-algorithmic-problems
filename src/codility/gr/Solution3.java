package codility.gr;

import java.util.LinkedList;
import java.util.Queue;

public class Solution3 {

  public int solution(int[] A, int X, int Y, int Z) {

    int fuelSum = 0;
    int maxFuelNeeded = 0;
    Queue<Integer> cars = new LinkedList<>();
    for (int a : A) {
      cars.add(a);
      fuelSum += a;
      maxFuelNeeded = Math.max(maxFuelNeeded, a);
    }
    if (fuelSum > X + Y + Z) {
      return -1; //not enough fuel
    }
    if (maxFuelNeeded > X && maxFuelNeeded > Y && maxFuelNeeded > Z) {
      return -1; //not enough fuel
    }

    int time = 0;

    int carAtX = 0;
    boolean isXBusy = false;
    int carAtY = 0;
    boolean isYBusy = false;
    int carAtZ = 0;
    boolean isZBusy = false;

    boolean isCurrCarWaiting = false;
    Integer currCar = null;
    while (!cars.isEmpty() || isCurrCarWaiting) {
      currCar = isCurrCarWaiting ? currCar : cars.poll();
      if (X < currCar && Y < currCar && Z < currCar) {
        return -1;
      }

      if (!isXBusy && X >= currCar) { //X is free and have enough fuel
        isXBusy = true;
        carAtX = currCar;
        if (isCurrCarWaiting) {
          isCurrCarWaiting = false;
        }
      } else if (!isYBusy && Y >= currCar) {  //Y is free and have enough fuel
        isYBusy = true;
        carAtY = currCar;
        if (isCurrCarWaiting) {
          isCurrCarWaiting = false;
        }
      } else if (!isZBusy && Z >= currCar) { //Z is free and have enough fuel
        isZBusy = true;
        carAtZ = currCar;
        if (isCurrCarWaiting) {
          isCurrCarWaiting = false;
        }
      } else { // All are busy
        isCurrCarWaiting = true;
        time++;

        if (isXBusy) {
          carAtX--;
          X--;
          if (carAtX == 0) {
            isXBusy = false;
          }
        }
        if (isYBusy) {
          carAtY--;
          Y--;
          if (carAtY == 0) {
            isYBusy = false;
          }
        }
        if (isZBusy) {
          carAtZ--;
          Z--;
          if (carAtZ == 0) {
            isZBusy = false;
          }
        }
      }

    }

    return time;
  }

}
