import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = 'http://localhost:8080/flightreservation/reservations';

  reservationData: any;

  constructor(private http: HttpClient) { }

  getReservationById(id: number): Observable<any> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get(url);
  }

  updateReservation(reservation: any): Observable<any> {
    const url = `${this.apiUrl}`;
    return this.http.post(url, reservation);
  }
}