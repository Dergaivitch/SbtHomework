package ru.sbt.test.refactoring;

public class Tractor {

	private Position position = new Position(0, 0);
	private Field field = new Field(5, 5);
	private Orientation orientation = Orientation.NORTH;

	public void move(String command) {
        if (command.equals("F")) {
			moveForwards();
		} else if (command.equals("T")) {
			turnClockwise();
		}
	}

    public void moveForwards() {
		position = orientation.moveForwards(position);

		if (isOutOfField()) throw new TractorInDitchException();

	}

	private boolean isOutOfField() {
		return position.getX() > field.getX() || position.getY() > field.getY();
	}

    public void turnClockwise() {
		orientation=orientation.turnClockwise();
	}

	public int getPositionX() {
		return position.getX();
	}

	public int getPositionY() {
		return position.getY();
	}

	public Orientation getOrientation() {
		return orientation;
	}

}

