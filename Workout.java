    abstract class Workout {
        protected String name;
        protected double duration;

        public Workout(String name, double duration) {
            this.name = name;
            this.duration = duration;
        }

        public abstract double calculateCaloriesBurned();

        public void displayWorkout() {
            System.out.println(name + " - " + duration + " minutes");
        }
    }

