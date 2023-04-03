package application;

import java.io.*;

/**
 * Gestionnaire de fichiers objets.
 * Il permet de sauver et lire des fichiers d'objets
 */
public class GestionFicherObjet {

    /**
     * Séparateur de chemin de fichier
     */
    public static char FILE_PATH_SEPARATOR = File.separatorChar;
    /**
     * Répertoire par défaut ou les fichiers sont sauvés.
     */
    public static String PATH = System.getProperty("user.dir") + FILE_PATH_SEPARATOR + "_fichiers" + FILE_PATH_SEPARATOR;
    /**
     * Permet de faire l'enregistrement d'objets Serializable.
     *
     * @param nomFichier nom du fichier à sauvegarder
     * @param objet      objet à sauvegarder
     * @throws IOException se déclenche s'il a une erreur dans l'opération d'écriture
     */
    public static void enregistrerObjet(File nomFichier, Object objet) throws IOException {
        // TODO Complétez le code de la méthode : enregistrerObjet(File nomFichier, Object objet)
    }

    /**
     * Permet de récupérer un objet à partir d'un fichier dont le nom est reçu en paramètre.
     *
     * @param nomFichier Nom du fichier à lire
     * @return Objet lu dans le fichier
     * @throws IOException            Se déclenche s'il a une erreur dans l'opération de lecture
     * @throws ClassNotFoundException Levée lorsqu'une application essaie de se charger dans une classe, mais aucune
     *                                définition pour la classe avec le nom spécifié n'a pu être trouvée.
     */
    public static Object lireObjet(File nomFichier) throws IOException, ClassNotFoundException {
        // TODO  Complétez le code de la méthode : Object lireObjet(File nomFichier)
        return null;
    }
}
