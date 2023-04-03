package application;

import cartes.Carte;
import cartes.PaquetDeCartes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Application de jeu de Aces Up Solitaire
 *
 * @author Robert Aubé (MAJ - 2023)
 * @version 1.0 (Date de création: 2023-03-25)
 * @see <a href="https://www.solitaire-play.com/aces-up/">https://www.solitaire-play.com/aces-up</a>
 */
public class AcesUpSolitaire extends JFrame {
    /**
     * Le paquet de carte utilisé pour le jeu
     */
    private final PaquetDeCartes paquetDeCartes;
    /**
     * Indique de fonctionner ou non en mode test.
     * si l'on veut être en mode test alors le paquet ne sera jamais mélangé. (même entre chaque ronde)
     * si cet attribut est à false, le paquet sera mélangé à la première ronde et pour les rondes suivantes.
     */
    private final boolean modeTest;

    /**
     * Fait référence à la logique du jeu. Elle gère l'ensemble des colonnes de cartes.
     */
    private LogiqueAcesUp logiqueAcesUp;

    // Les menus
    private JMenuBar menuBar = new JMenuBar();

    // Menu jeu
    private JMenu jeu = new JMenu("Jeu");
    private JMenuItem nouveau = new JMenuItem("Nouveau jeu");
    private JMenuItem enregister = new JMenuItem("Enregistrer le jeu");
    private JMenuItem reprendre = new JMenuItem("Reprendre un jeu");
    private JMenuItem fermer = new JMenuItem("Fermer");
    private ActionMenu ecouteurMenu = new ActionMenu();

    // Les colonnes de cartes
    private JPanel paneListes = new JPanel(new GridLayout(1, 4));
    private JPanel[] paneListeCartes = new JPanel[LogiqueAcesUp.NB_COLONNES_DE_CARTES];
    private JButton[] btnDeplacerListe = new JButton[LogiqueAcesUp.NB_COLONNES_DE_CARTES];
    private JButton[] btnEnleverListe = new JButton[LogiqueAcesUp.NB_COLONNES_DE_CARTES];
    private JEditorPane[] txtListeCartes = new JEditorPane[LogiqueAcesUp.NB_COLONNES_DE_CARTES];
    private ActionSouris ecouteurSouris = new ActionSouris();
    private ActionBouton ecouteurBtn = new ActionBouton();

    // La pioche graphique
    private JPanel panePioche = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 5, 5));
    private JButton btnPiger = new JButton("Piger");
    private JEditorPane txtPioche = new JEditorPane();

    /**
     * Constructeur de l'application Aces Up Solitaire. Il met en place une
     * interface simple.
     *
     * @param paquetDeCartes le paquet de cartes utilisé pour jouer.
     * @param modeTest       true si l'on veut être en mode test
     *                       et que nous ne souhaitons jamais mélanger les paquets avant chaque ronde
     *                       false si l'on est en mode jeu. Le paquet sera alors mélangé à chaque fois.
     */
    public AcesUpSolitaire(PaquetDeCartes paquetDeCartes, boolean modeTest) {
        this.paquetDeCartes = paquetDeCartes;
        this.modeTest = modeTest;

        initInterfaceJeu();

        gestionNouveauJeu();
    }


    /**
     * Dessine uniquement les colonnes qui ont été mis à jour
     */
    private void dessinerLesColonneDeCartes() {
        for (int idxColonne = 0; idxColonne < (LogiqueAcesUp.NB_COLONNES_DE_CARTES - 1); idxColonne++) {
            if (logiqueAcesUp.colonneEstMAJ(idxColonne)) {
                dessinerColonneCarte(idxColonne);
            }
        }
    }

    /**
     * Permet de dessiner (d'afficher) les cartes dans les zones texte de cartes
     * selon la structure de données associée
     *
     * @param noColonne le numéro de la colonne de carte à dessiner
     */
    private void dessinerColonneCarte(int noColonne) {
        Carte carte;
        ArrayList<Carte> listeCartes = logiqueAcesUp.getColonneCartes(noColonne);
        String txtHtml;

        txtListeCartes[noColonne].setText("");

        txtHtml = "<center>";
        for (int i = listeCartes.size() - 1; i >= 0; i--) {
            carte = listeCartes.get(i);
            txtHtml += htmlCarte(carte);
        }
        txtHtml += "</center>";

        txtListeCartes[noColonne].setText(txtHtml);

        logiqueAcesUp.setColonneCommeDessine(noColonne);
    }

    private String htmlCarte(Carte carte) {
        String txtHtml;
        String couleur;

        couleur = carte.getSorte().getCouleur() == Color.RED ? "red" : "black";
        txtHtml = "<font face='Tahoma' size='5' color='" + couleur + "'>" + carte + "</font><br/>";
        return txtHtml;
    }


    /**
     * Permet d'afficher le nombre de cartes restantes dans la pioche.
     */
    private void dessinerPioche() {
        String txtHtml = "<br/><center><font face='Tahoma' size='38' color='#000c33'><b>";

        if (!logiqueAcesUp.piocheEstVide()) {
            txtHtml += logiqueAcesUp.nbCartesPioche() + "<br/>";
        }

        txtHtml += "</b></font></center>";

        txtPioche.setBackground(logiqueAcesUp.nbCartesPioche() > 0 ? Color.GRAY : Color.WHITE);

        txtPioche.setText(txtHtml);
    }

    /**
     * Permet de commencer une nouvelle partie ...
     * 1- en copiant le paquet de carte initial dans le paquetPourCetteRonde
     * 2- en brassant le paquet selon que nous sommes en mode test ou non
     * 3- en initialisant la logique de jeu
     * 4- en dessinant la pioche et les colonnes
     */
    private void gestionNouveauJeu() {
        PaquetDeCartes paquetPourCetteRonde = (PaquetDeCartes) paquetDeCartes.clone();

        if (!modeTest) {
            paquetPourCetteRonde.melanger();
        }
        logiqueAcesUp = new LogiqueAcesUp(paquetPourCetteRonde);
        dessinerPioche();
        dessinerLesColonneDeCartes();
    }

    /**
     * Permet de faire la gestion de l'action piger, dans la pioche et
     * pour chaque colonne de cartes.
     * <p>
     * Les colonnes sont affichées en fonction du changement résultant de la pige.
     */
    private void gestionPiger() {
        logiqueAcesUp.piger();

        dessinerLesColonneDeCartes();
        dessinerPioche();
    }

    /**
     * Permet d'enregistrer une partie en cours. L'enregistrement des données
     * pourrait être fait par sérialisation. Cette fonction fait de l'affichage
     * de messages
     */
    private void gestionEnregistrerJeu() {
        JFileChooser jFC = new JFileChooser(GestionFicherObjet.PATH);
        int ok = jFC.showSaveDialog(this);

        if (ok == JFileChooser.APPROVE_OPTION) {
         //   try {
                //todo enregistrer le jeu à l'aide de la classe GestionFicherObjet

                //todo décommenter le try catch pour que ce soit opérationnel
                showMessage("Enregistrement réussi", "Enregistrement réussi dans \n" + jFC.getSelectedFile(), JOptionPane.INFORMATION_MESSAGE);
                /*
            } catch (IOException e) {
                System.out.println("Erreur : " + e.getMessage());
                showMessage("Erreur d'enregistrement",
                        "Erreur: Il n'est pas possible d'enregistrer les données du jeu dans ce fichier\n" + jFC.getSelectedFile(),
                        JOptionPane.ERROR_MESSAGE);
            }
                 */
        }
    }

    /**
     * Permet de lire un fichier qui contient les informations au sujet d'une
     * partie. En plus de lire les données, elle remplie et dessine les composants.
     */
    private void gestionReprendreJeu() {
        JFileChooser jFC = new JFileChooser(GestionFicherObjet.PATH);
        int ok = jFC.showOpenDialog(this);

        if (ok == JFileChooser.APPROVE_OPTION) {
            // try {
                //todo lire le fichier du jeu à l'aide de la classe GestionFicherObjet

                logiqueAcesUp.setAllColonneADessiner();
                dessinerLesColonneDeCartes();
                dessinerPioche();
                //todo décommenter le try catch pour que ce soit opérationnel
                /*
            } catch (IOException e) {
                System.out.println("Erreur : " + e.getMessage());
                showMessage("Erreur de lecture",
                        "Erreur: Il n'est pas possible de lire ce fichier:\n" + jFC.getSelectedFile(),
                        JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
                System.out.println("Erreur : " + e.getMessage());
                showMessage("Erreur de lecture",
                        "Ce fichier ne peut être chargé dans la classe du jeu:\n" + jFC.getSelectedFile(),
                        JOptionPane.ERROR_MESSAGE);
            }

                 */
        }
    }

    /**
     * Permet de déplacer, selon les règles du jeu, une carte de la colonne de
     * cartes dont l'index de la colonne de carte est reçu en entrée.
     *
     * @param noColonne le numéro de la colonne d'où on veut déplacer la carte.
     */
    private void gestionDeplacerCarte(int noColonne) {
        logiqueAcesUp.deplacerCarte(noColonne);
        dessinerLesColonneDeCartes();
    }

    /**
     * Permet d'enlever, selon les règles du jeu, une carte de la colonne de
     * cartes dont l'index de la colonne de carte est reçu en entrée.
     *
     * @param noColonne le numéro de la colonne d'où on veut enlever la carte.
     */
    private void gestionEnleverCarteDUneColonne(int noColonne) {
        logiqueAcesUp.enleverColonne(noColonne);
        dessinerLesColonneDeCartes();
    }

    private void gestionFinPartie() {
        if (logiqueAcesUp.piocheEstVide()) {
            if (logiqueAcesUp.partieEstGagne()) {
                showMessage("Information", "Bravo! vous avez gagné!!!", JOptionPane.INFORMATION_MESSAGE);
                gestionNouveauJeu(); // on recommence automatiquement
            } else {
                if (logiqueAcesUp.partieEstTermine()) {
                    showMessage("Information", "Désolé, vous aves perdu...", JOptionPane.INFORMATION_MESSAGE);
                    gestionNouveauJeu(); // on recommence automatiquement
                }
            }
        }
    }

    /**
     * Permet de faire la gestion de l'option fermer du menu
     */
    private void gestionFermer() {
        System.exit(0);
    }

    private void initInterfaceJeu() {
        initFenetre();
        initMenu();
        initColonne();
        initPioche();
        ajouterEcouteur();

        this.setVisible(true);
    }

    private void initFenetre() {
        // Init de la fenêtre
        this.setTitle("Aces Up Solitaire");
        this.setSize(450, 560);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void initMenu() {
        // Init des menus
        this.jeu.add(nouveau);
        this.jeu.add(enregister);
        this.jeu.add(reprendre);
        this.jeu.addSeparator();
        this.jeu.add(fermer);
        this.menuBar.add(jeu);
        // Ajout du menu jeu à la barre de menus
        this.setJMenuBar(menuBar);
    }


    private void initColonne() {
        // Mise en place des composants pour les colonnes de cartes (4 colonnes)
        // et les boutons (2 boutons sur chaque colonne)
        for (int i = 0; i < LogiqueAcesUp.NB_COLONNES_DE_CARTES; i++) {
            txtListeCartes[i] = new JEditorPane();
            txtListeCartes[i].setPreferredSize(new Dimension(80, 325));
            txtListeCartes[i].setEditable(false);
            txtListeCartes[i].setContentType("text/html");
            txtListeCartes[i].addMouseListener(ecouteurSouris);

            btnEnleverListe[i] = new JButton("Enlever");
            btnEnleverListe[i].addActionListener(ecouteurBtn);
            btnDeplacerListe[i] = new JButton("Déplacer");
            btnDeplacerListe[i].addActionListener(ecouteurBtn);

            paneListeCartes[i] = new JPanel(
                    new FlowLayout(FlowLayout.CENTER, 5, 5));
            paneListeCartes[i].add(txtListeCartes[i]);
            paneListeCartes[i].add(btnEnleverListe[i]);
            paneListeCartes[i].add(btnDeplacerListe[i]);

            paneListes.add(paneListeCartes[i]);
        }

        this.add(paneListes, BorderLayout.CENTER);
    }

    private void initPioche() {
        // La pioche
        btnPiger.addActionListener(ecouteurBtn);
        panePioche.add(btnPiger);
        txtPioche.setPreferredSize(new Dimension(60, 90));
        txtPioche.setEditable(false);
        txtPioche.setContentType("text/html");
        panePioche.add(txtPioche);

        this.add(panePioche, BorderLayout.SOUTH);
    }

    private void ajouterEcouteur() {
        // Ajout des écouteurs au menu jeu
        nouveau.addActionListener(ecouteurMenu);
        enregister.addActionListener(ecouteurMenu);
        reprendre.addActionListener(ecouteurMenu);
        fermer.addActionListener(ecouteurMenu);
    }


    /**
     * Classe interne qui permet de faire l'écoute des options de menu
     */
    private class ActionMenu implements ActionListener {
        /**
         * Méthode invoquée lorsqu'une option de menu est cliquée
         *
         * @param pAE , pointeur d'événement
         */
        public void actionPerformed(ActionEvent pAE) {
            if (pAE.getSource() == nouveau) {
                gestionNouveauJeu();
            } else if (pAE.getSource() == enregister) {
                gestionEnregistrerJeu();
            } else if (pAE.getSource() == reprendre) {
                gestionReprendreJeu();
            } else if (pAE.getSource() == fermer) {
                gestionFermer();
            }
        }
    }

    /**
     * Classe interne qui permet de faire l'écoute des boutons
     */
    private class ActionBouton implements ActionListener {
        /**
         * Méthode invoquée lorsqu'un bouton est cliqué
         *
         * @param actionButtonEvent un pointeur d'événement
         */
        public void actionPerformed(ActionEvent actionButtonEvent) {
            JButton btn = (JButton) actionButtonEvent.getSource();

            if (btn == btnPiger) {
                gestionPiger();
            } else {
                // Boutons enlever
                for (int i = 0; i < btnEnleverListe.length; i++) {
                    if (btn == btnEnleverListe[i]) {
                        gestionEnleverCarteDUneColonne(i);
                    }
                }

                // Bouton déplacer
                for (int i = 0; i < btnDeplacerListe.length; i++) {
                    if (btn == btnDeplacerListe[i]) {
                        gestionDeplacerCarte(i);
                    }
                }
            }

            gestionFinPartie();
        }
    }

    /**
     * Classe interne qui permet de faire l'écoute des évènements de la souris
     */
    private class ActionSouris extends MouseAdapter {
        /**
         * Méthode invoquée lorsqu'un évènement souris arrive
         *
         * @param mouseEvent pointeur d'événement
         */
        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getSource() instanceof JEditorPane) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    for (int i = 0; i < txtListeCartes.length; i++) {
                        if (mouseEvent.getSource() == txtListeCartes[i]) {
                            gestionEnleverCarteDUneColonne(i);
                            gestionDeplacerCarte(i);
                        }
                    }
                } else {
                    if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                        for (int i = 0; i < txtListeCartes.length; i++) {
                            if (mouseEvent.getSource() == txtListeCartes[i]) {
                                gestionDeplacerCarte(i);
                            }
                        }
                    }
                }
            }

            if (mouseEvent.getButton() == MouseEvent.BUTTON2) {
                gestionPiger();
            }

            gestionFinPartie();
        }
    }

    /**
     * Afficher une fenêtre de dialogue
     *
     * @param titre       tire de la fenêtre
     * @param message     teste principale de la fenêtre
     * @param typeMessage type de message
     */
    private void showMessage(String titre, String message, int typeMessage) {
        JOptionPane.showMessageDialog(AcesUpSolitaire.this, message, titre, typeMessage);
    }
}
