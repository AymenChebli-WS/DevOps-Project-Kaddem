// src/app/components/departement.component.ts

import { Component, OnInit } from '@angular/core';
import { Departement } from 'src/models/Departement';
import { DepartementService } from 'src/services/DepartementService';

@Component({
  selector: 'app-departement',
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.css']
})
export class DepartementComponent implements OnInit {
  departements: Departement[] = [];
  selectedDepartement: Departement | null = null; // This will hold the department being edited
  newDepartement: Departement = { nomDepart: '' }; // Initial empty model for adding
  showAddForm: boolean = false;
  isEditing: boolean = false;

  constructor(private departementService: DepartementService) {}

  ngOnInit(): void {
    this.loadDepartements();
  }

  // Load all departements from the server
  loadDepartements(): void {
    this.departementService.getDepartements().subscribe(
      (data) => {
        this.departements = data;
      },
      (error) => {
        console.error('Error retrieving departements:', error);
      }
    );
  }

  // Toggle the add form
  toggleAddForm(): void {
    this.showAddForm = !this.showAddForm;
    this.isEditing = false; // Hide edit form if it's open
    this.selectedDepartement = null; // Reset selected department
  }

  // Add a new departement
  addDepartement(): void {
    if (!this.newDepartement.nomDepart) return; // Basic validation

    this.departementService.addDepartement(this.newDepartement).subscribe(
      (departement) => {
        this.departements.push(departement); // Add to the list
        this.newDepartement = { nomDepart: '' }; // Reset the form
        this.showAddForm = false; // Hide the form
      },
      (error) => {
        console.error('Error adding departement:', error);
      }
    );
  }

  // Remove a departement by ID
  removeDepartement(departementId: number): void {
    this.departementService.removeDepartement(departementId).subscribe(
      () => {
        this.departements = this.departements.filter(d => d.idDepart !== departementId);
      },
      (error) => console.error('Error removing departement:', error)
    );
  }

  // Open the form for editing a selected departement
  editDepartement(departement: Departement): void {
    this.isEditing = true;
    this.selectedDepartement = { ...departement }; // Clone the object for editing
    this.showAddForm = false; // Hide add form if it's open
  }

  // Update an existing departement
  updateDepartement(): void {
    if (!this.selectedDepartement) return;

    this.departementService.updateDepartement(this.selectedDepartement).subscribe(
      (updatedDepartement) => {
        const index = this.departements.findIndex(d => d.idDepart === updatedDepartement.idDepart);
        if (index !== -1) {
          this.departements[index] = updatedDepartement;
        }
        this.selectedDepartement = null; // Clear the form
        this.isEditing = false; // Hide the form
      },
      (error) => {
        console.error('Error updating departement:', error);
      }
    );
  }

  // Cancel the edit operation
  cancelEdit(): void {
    this.selectedDepartement = null;
    this.isEditing = false;
  }
}
