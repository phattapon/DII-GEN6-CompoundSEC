interface Trackable {
        void trackProgress();
    }

    class Running extends Workout implements Trackable {
        private double weight;

        public Running(double duration, double weight) {
            super("Running", duration);
            this.weight = weight;
        }

        @Override
        public double calculateCaloriesBurned() {
            return 0.0175 * 9.8 * weight * duration;
        }

        @Override
        public void trackProgress() {
            System.out.println("Tracking Running Progress...");
        }
    }

