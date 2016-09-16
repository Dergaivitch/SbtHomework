package ru.sbt.test.refactoring;



	public enum Orientation {
		NORTH {
			@Override
		public Position moveForwards(Position currentPosition) {
				return new Position(currentPosition.getX(), currentPosition.getY() + 1);
			}

			@Override
			public Orientation turnClockwise() {
				return EAST;
			}
		},
		EAST {
			@Override
			public Position moveForwards(Position currentPosition) {
				return new Position(currentPosition.getX() + 1, currentPosition.getY());
			}

			@Override
			public Orientation turnClockwise() {
				return SOUTH;
			}
		},
		SOUTH {
			@Override
			public Position moveForwards(Position currentPosition) {
				return new Position(currentPosition.getX(), currentPosition.getY() - 1);
			}

			@Override
			public Orientation turnClockwise() {
				return WEST;
			}
		},
		WEST {
			@Override
			public Position moveForwards(Position currentPosition) {
				return new Position(currentPosition.getX() - 1, currentPosition.getY());
			}

			@Override
			public Orientation turnClockwise() {
				return NORTH;
			}
		};

		public abstract Position moveForwards(Position currentPosition);
		public abstract Orientation turnClockwise();
	}


