import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Doctor");
            System.out.println("4. View Doctors");
            System.out.println("5. Book Appointment");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> viewPatients();
                case 3 -> addDoctor();
                case 4 -> viewDoctors();
                case 5 -> bookAppointment();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addPatient() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter age: ");
            int age = sc.nextInt();
            System.out.print("Enter gender: ");
            String gender = sc.next();

            String query = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.executeUpdate();

            System.out.println("Patient added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewPatients() {
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patients");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addDoctor() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter specialization: ");
            String spec = sc.next();

            String query = "INSERT INTO doctors(name, specialization) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, spec);
            ps.executeUpdate();

            System.out.println("Doctor added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewDoctors() {
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM doctors");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void bookAppointment() {
        try (Connection con = DBConnection.getConnection()) {
            System.out.print("Enter patient ID: ");
            int pid = sc.nextInt();
            System.out.print("Enter doctor ID: ");
            int did = sc.nextInt();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = sc.next();

            String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, pid);
            ps.setInt(2, did);
            ps.setString(3, date);
            ps.executeUpdate();

            System.out.println("Appointment booked!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
