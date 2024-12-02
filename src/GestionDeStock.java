
import java.util.Scanner;

public class GestionDeStock {
    private static final int MAX_PRODUCTS = 100;
    private static int[] codesProduits = new int[MAX_PRODUCTS];
    private static String[] nomsProduits = new String[MAX_PRODUCTS];
    private static int[] quantites = new int[MAX_PRODUCTS];
    private static double[] prix = new double[MAX_PRODUCTS];
    private static int productCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            System.out.print("Choisissez une option : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> ajouterProduit(scanner);
                case 2 -> modifierProduit(scanner);
                case 3 -> supprimerProduit(scanner);
                case 4 -> afficherProduits();
                case 5 -> rechercherProduit(scanner);
                case 6 -> calculerValeurStock();
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("----- Gestion de Stock -----");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
    }

    public static void ajouterProduit(Scanner scanner) {
        if (productCount >= MAX_PRODUCTS) {
            System.out.println("Erreur : Inventaire plein !");
            return;
        }

        System.out.print("Code du produit : ");
        int code = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nom du produit : ");
        String nom = scanner.nextLine();

        System.out.print("Quantité : ");
        int quantite = scanner.nextInt();

        System.out.print("Prix unitaire : ");
        double prixUnitaire = scanner.nextDouble();

        codesProduits[productCount] = code;
        nomsProduits[productCount] = nom;
        quantites[productCount] = quantite;
        prix[productCount] = prixUnitaire;

        productCount++;
        System.out.println("Produit ajouté avec succès !");
    }

    public static void modifierProduit(Scanner scanner) {
        System.out.print("Code du produit à modifier : ");
        int code = scanner.nextInt();
        scanner.nextLine();

        int index = findProductIndexByCode(code);
        if (index == -1) {
            System.out.println("Produit introuvable !");
            return;
        }

        System.out.print("Nouveau nom : ");
        String nouveauNom = scanner.nextLine();

        System.out.print("Nouvelle quantité : ");
        int nouvelleQuantite = scanner.nextInt();

        System.out.print("Nouveau prix unitaire : ");
        double nouveauPrix = scanner.nextDouble();

        nomsProduits[index] = nouveauNom;
        quantites[index] = nouvelleQuantite;
        prix[index] = nouveauPrix;

        System.out.println("Produit modifié avec succès !");
    }

    public static void supprimerProduit(Scanner scanner) {
        System.out.print("Code du produit à supprimer : ");
        int code = scanner.nextInt();

        int index = findProductIndexByCode(code);
        if (index == -1) {
            System.out.println("Produit introuvable !");
            return;
        }

        for (int i = index; i < productCount - 1; i++) {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantites[i] = quantites[i + 1];
            prix[i] = prix[i + 1];
        }

        productCount--;
        System.out.println("Produit supprimé avec succès !");
    }

    public static void afficherProduits() {
        if (productCount == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }

        System.out.println("----- Liste des produits -----");
        for (int i = 0; i < productCount; i++) {
            System.out.println("Code: " + codesProduits[i] +
                    ", Nom: " + nomsProduits[i] +
                    ", Quantité: " + quantites[i] +
                    ", Prix: " + prix[i]);
        }
    }

    public static void rechercherProduit(Scanner scanner) {
        System.out.print("Nom du produit à rechercher : ");
        String nom = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.println("Code: " + codesProduits[i] +
                        ", Nom: " + nomsProduits[i] +
                        ", Quantité: " + quantites[i] +
                        ", Prix: " + prix[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Produit introuvable !");
        }
    }

    public static void calculerValeurStock() {
        double valeurTotale = 0;

        for (int i = 0; i < productCount; i++) {
            valeurTotale += quantites[i] * prix[i];
        }

        System.out.println("Valeur totale du stock : " + valeurTotale + " MAD");
    }

    private static int findProductIndexByCode(int code) {
        for (int i = 0; i < productCount; i++) {
            if (codesProduits[i] == code) {
                return i;
            }
        }
        return -1;
    }
}
