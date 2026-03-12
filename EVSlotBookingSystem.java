import java.util.*;

// Custom Class to store Booking Details
class Booking {
    int slotNum;
    String name, phone, car, charger, time;

    Booking(int slotNum, String name, String phone, String car, String charger, String time) {
        this.slotNum = slotNum;
        this.name = name;
        this.phone = phone;
        this.car = car;
        this.charger = charger;
        this.time = time;
    }
}

public class EVSlotBookingSystem {
    // DSA Implementation: HashMap for O(1) Search, Queue for FIFO Booking
    private static final Map<String, String> userDb = new HashMap<>();
    private static final Map<String, String> phoneDb = new HashMap<>();
    private static final Queue<Booking> adminQueue = new LinkedList<>();
    private static final int MAX_SLOTS = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Initial Admin Setup
        userDb.put("S@itej", "sai@2007");
        phoneDb.put("S@itej", "9876543210");

        while (true) {
            System.out.println("\n⚡ INDIA EV SLOT BOOKING SYSTEM (DSA 2026) ⚡");
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1) {
                handleLogin(sc);
            } else if (choice == 2) {
                handleSignup(sc);
            } else if (choice == 3) {
                handleForgot(sc);
            } else if (choice == 4) {
                System.out.println("Exiting System...");
                break;
            } else {
                System.out.println("Invalid Choice!");
            }
        }
        sc.close();
    }

    public static void handleSignup(Scanner sc) {
        System.out.print("Enter Mobile Number: ");
        String ph = sc.nextLine();
        System.out.print("Create Username: ");
        String u = sc.nextLine();
        System.out.print("Create Password: ");
        String p = sc.nextLine();

        userDb.put(u, p);
        phoneDb.put(u, ph);
        System.out.println("✅ Account Created Successfully! You can now Login.");
    }

    public static void handleLogin(Scanner sc) {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        if (userDb.containsKey(u) && userDb.get(u).equals(p)) {
            System.out.println("\n✅ Login Success! Welcome " + u);
            if (u.equals("S@itej")) {
                showAdminDashboard();
            } else {
                showUserBooking(u, sc);
            }
        } else {
            System.out.println("❌ Invalid Username or Password.");
        }
    }

    public static void handleForgot(Scanner sc) {
        System.out.print("Username: ");
        String u = sc.nextLine();
        if (userDb.containsKey(u)) {
            System.out.print("Verify full 10-digit mobile number: ");
            String ph = sc.nextLine();
            if (phoneDb.get(u).equals(ph)) {
                System.out.println("✅ Identity Verified! Password: " + userDb.get(u));
            } else {
                System.out.println("❌ Mobile number mismatch.");
            }
        } else {
            System.out.println("❌ User not found.");
        }
    }

    public static void showUserBooking(String user, Scanner sc) {
        if (adminQueue.size() >= MAX_SLOTS) {
            System.out.println("⚠️ Station Full! No slots available.");
            return;
        }

        System.out.println("\n--- NEW BOOKING FORM ---");
        System.out.print("Enter Driver Name: ");
        String name = sc.nextLine();
        
        System.out.println("Select Car Model:");
        System.out.println("1. Maruti Suzuki e Vitara | 2. Tesla Model Y | 3. BMW iX | 4. Mercedes EQS | 5. Tata Nexon EV");
        System.out.print("Choice (Enter Name): ");
        String car = sc.nextLine();

        System.out.println("Select Charger: [Fast Charging / Normal Charging]");
        String charger = sc.nextLine();

        System.out.print("Enter Start Time (e.g., 10:30 AM): ");
        String time = sc.nextLine();

        int slotNum = adminQueue.size() + 1;
        adminQueue.add(new Booking(slotNum, name, phoneDb.get(user), car, charger, time));
        System.out.println("✅ Slot " + slotNum + " Reserved Successfully!");
    }

    public static void showAdminDashboard() {
        System.out.println("\n===== ADMIN DASHBOARD: RECENT LOGS =====");
        System.out.println("Slot | Name | Phone | Car | Type | Time");
        System.out.println("-------------------------------------------");
        
        if (adminQueue.isEmpty()) {
            System.out.println("No active bookings in the Queue.");
        } else {
            for (Booking b : adminQueue) {
                System.out.println(b.slotNum + " | " + b.name + " | " + b.phone + " | " + b.car + " | " + b.charger + " | " + b.time);
            }
        }
        System.out.println("===========================================");
    }
}