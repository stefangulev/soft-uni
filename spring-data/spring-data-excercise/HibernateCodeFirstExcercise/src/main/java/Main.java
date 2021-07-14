import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni_ex");
        EntityManager em = factory.createEntityManager();
//        doctorInterface(em);


    }

    public static void doctorInterface(EntityManager em) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello doctor! Please choose an option from the menu: \n 1 - Review data for patient \n 2 - Add new patient \n 3 - Add new visitation \n 0 - exit");
        int firstChoice = Integer.parseInt(scan.nextLine());
        em.getTransaction().begin();
        switch (firstChoice) {
            case 1:
                getData(scan, em);
                break;
            case 2: addPatient(scan, em);
            break;
            case 3: addVisitation(scan, em);
            case 0 :
            break;
        }
        em.getTransaction().commit();

    }

    public static void addVisitation(Scanner scan, EntityManager em) {
        Visitation visitation = new Visitation();
        visitation.setDate(LocalDate.now());
        System.out.println("Please enter validation comments if you have any");
        String comments = "";
        String commentsInput = scan.nextLine();
        comments += commentsInput;
        visitation.setComments(comments);

        System.out.println("Please enter patient ID");
        long id = Long.parseLong(scan.nextLine());
        Patient patient = em.find(Patient.class, id);
        if (patient == null) {
            System.out.println("No such patient exists. Try again or create patient!");
            return;
        }
        visitation.setPatient(patient);
        System.out.println("Please enter the diagnosis:");
        String diagnosisInput = scan.nextLine();
        Diagnose diagnose = new Diagnose();
        diagnose.setName(diagnosisInput.equals("") ? "defaukt" : diagnosisInput);
        System.out.println("Please enter a medication name for this diagnosis:");
        String medicationName = scan.nextLine();
        Medicament medicament = new Medicament();
        medicament.setName(medicationName);
        em.persist(medicament);
        diagnose.getMedicamentSet().add(medicament);
        em.persist(diagnose);
        visitation.setDiagnosis(diagnose);
        em.persist(visitation);
        System.out.println("Visitation added!");

    }
    public static void addPatient(Scanner scan, EntityManager em) {
        System.out.println("Please add patient details");
        System.out.println("First name:");
        String firstName = scan.nextLine();
        System.out.println("Last name:");
        String lastName = scan.nextLine();
        Patient patient = new Patient(firstName, lastName);
        em.persist(patient);
        System.out.println("Patient added!");
    }

    public static void getData(Scanner scan, EntityManager em) {
        System.out.println("Please enter the id of the patient you'd like to examine:");
        long id = Long.parseLong(scan.nextLine());
        Patient patient = em.find(Patient.class, id);
        if (patient == null) {
            System.out.println("Patient not found! Try again or create patient!");
        }
        System.out.println("Patient details:");
        String name = patient.getFirstName();
        String lastName = patient.getLastName();
        System.out.println("Firstname: " + name + "\n" + "Lastname: " + lastName);
        System.out.println("Do you want to see the visitations of this patient? (Type Yes to continue)");
        String userResponse = scan.nextLine();
        if (!userResponse.equals("Yes")) {
            System.out.println("Thank you for using our application! Return again anytime!");
            return;
        }
        Set<Visitation> visitationSetSet = patient.getVisitationSetSet();
        visitationSetSet.forEach(v -> System.out.println("Visitation ID/Date:" + "\n" + v.getId() + " - " + v.getDate() + "\n" + "Diagnosis - " + v.getDiagnosis().getName()));
        System.out.println("Do you want to the prescribed medicines per visitation?(Type Yes to continue)");
        String userResponse2 = scan.nextLine();
        if (!userResponse2.equals("Yes")) {
            System.out.println("Thank you for using our application! Return again anytime!");
            return;
        }
        visitationSetSet.forEach(v -> System.out.println(String.format("%s - %s",v.getDiagnosis().getName(), v.getDiagnosis().getMedicamentSet().stream().map(Medicament::getName).collect(Collectors.joining(", ")))));
    }
}
