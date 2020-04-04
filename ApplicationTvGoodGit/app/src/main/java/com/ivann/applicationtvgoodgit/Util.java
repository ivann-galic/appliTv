package com.ivann.applicationtvgoodgit;

public class Util {

    public static int genreStringToInt(String text) {
        switch (text) {
            case "Action":
                return 28;

            case "Aventure":
                return 12;

            case "Animation":
                return 16;

            case "Comédie":
                return 35;

            case "Crime":
                return 80;

            case "Documentaire":
                return 99;

            case "Drame":
                return 18;

            case "Familial":
                return 10751;

            case "Fantastique":
                return 14;

            case "Histoire":
                return 36;

            case "Horreur":
                return 27;

            case "Musique":
                return 10402;

            case "Mystère":
                return 9648;

            case "Romance":
                return 10749;

            case "Science-fiction":
                return 878;

            case "Téléfilm":
                return 10770;

            case "Thriller":
                return 53;

            case "Guerre":
                return 10752;

            case "Western":
                return 37;

            default:
                return 0;
        }

    }

    /*
      Cette fonction traduit les id_genre donnés par l'api en string exploitables pour l'affichage dans le recyclerview
       */
    public static String genreToString(int number) {

        switch (number) {

            case 28:
                return "Action";

            case 12:
                return "Aventure";

            case 16:
                return "Animation";

            case 35:
                return "Comédie";

            case 80:
                return "Crime";

            case 99:
                return "Documentaire";

            case 18:
                return "Drame";

            case 10751:
                return "Familial";

            case 14:
                return "Fantastique";

            case 36:
                return "Histoire";

            case 27:
                return "Horreur";

            case 10402:
                return "Musique";

            case 9648:
                return "Mystère";

            case 10749:
                return "Romance";

            case 878:
                return "Science-fiction";

            case 10770:
                return "Téléfilm";

            case 53:
                return "Thriller";

            case 10752:
                return "Guerre";

            case 37:
                return "Western";

            default:
                return "erreur de catégorie";


        }


    }


}
