public class User {
        private String name; // ชื่อผู้ใช้
        private PermissionCard card; // บัตรสิทธิ์ของผู้ใช้

        public User(String name) {
            this.name = name;
            this.card = new PermissionCard();
        }

        public String getName() {
            return name;
        }

        public PermissionCard getCard() {
            return card;
        }

        @Override
        public String toString() {
            return "ชื่อผู้ใช้: " + name + ", " + card;
        }
    }

