class Test3 {
  public static void main(String[] args) {
    CS2030STest we = new CS2030STest();
    we.expectPrint(
        "Successfully completing road trip with bike",
        () -> {
          Bike b = new Bike();
          RoadTrip r = new RoadTrip(b, 10);
          r.complete();
        },
        "");
    we.expect(
        "After a road trip, Bike's distance is updated",
        () -> {
          Bike b = new Bike();
          RoadTrip r = new RoadTrip(b, 10);
          r.complete();
          return b.toString();
        },
        "Bike distance: 10.00 folded: false");

    we.expectPrint(
        "Cannot complete road trip with folded bike",
        () -> {
          Bike b = new Bike();
          RoadTrip r = new RoadTrip(b, 10);
          b.fold();
          r.complete();
        },
        "This trip cannot be completed.\n");

    we.expectPrint(
        "Successfully completing road trip with car",
        () -> {
          Car c = new Car(10);
          RoadTrip r = new RoadTrip(c, 10);
          r.complete();
        },
        "");

    we.expect(
        "After a road trip, Car's fuel level is updated",
        () -> {
          Car c = new Car(10);
          RoadTrip r = new RoadTrip(c, 10);
          r.complete();
          return c.toString();
        },
        "Car fuelLevel: 8.00");

    we.expectPrint(
        "Cannot completing road trip when fuel is not enough",
        () -> {
          Car c = new Car(10);
          RoadTrip r = new RoadTrip(c, 10);
          r.complete();
          try {
            c.move(40);
          } catch (CannotMoveException e) {
            // do nothing
          }
          r.complete();
        },
        "This trip cannot be completed.\n");
  }
}
