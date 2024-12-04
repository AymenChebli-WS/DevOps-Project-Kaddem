// src/app/services/departement.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Departement } from 'src/models/Departement';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {
  private baseUrl = 'http://192.168.33.10:8089/kaddem/departement'; // Update with your actual base URL

  constructor(private http: HttpClient) { }

  // Retrieve all departements
  getDepartements(): Observable<Departement[]> {
    return this.http.get<Departement[]>(`${this.baseUrl}/retrieve-all-departements`);
  }

  // Retrieve a specific departement by ID
  getDepartement(departementId: number): Observable<Departement> {
    return this.http.get<Departement>(`${this.baseUrl}/retrieve-departement/${departementId}`);
  }

  // Add a new departement
  addDepartement(departement: Departement): Observable<Departement> {
    return this.http.post<Departement>(`${this.baseUrl}/add-departement`, departement, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // Update an existing departement
  updateDepartement(departement: Departement): Observable<Departement> {
    return this.http.put<Departement>(`${this.baseUrl}/update-departement`, departement, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // Remove a departement by ID
  removeDepartement(departementId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-departement/${departementId}`);
  }
}
