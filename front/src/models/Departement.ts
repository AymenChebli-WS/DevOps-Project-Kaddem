import { Etudiant } from "./Etudiant";

export class Departement {
    idDepart?: number; // Optional because it may not be set when creating a new departement
    nomDepart: string;
    etudiants?: Etudiant[]; // Assuming you have an Etudiant model defined

    constructor(nomDepart: string, idDepart?: number, etudiants?: Etudiant[]) {
        this.nomDepart = nomDepart;
        if (idDepart) {
            this.idDepart = idDepart;
        }
        if (etudiants) {
            this.etudiants = etudiants;
        }
    }
}