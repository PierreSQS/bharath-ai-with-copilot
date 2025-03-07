import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReservationService } from '../../services/checkin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-startcheckin',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './startcheckin.component.html',
  styleUrl: './startcheckin.component.css'
})
export class StartcheckinComponent {

  reservationId: number = 0;

  constructor(private service:ReservationService,private router:Router) { }

  onClick(){

    this.service.getReservationById(this.reservationId).subscribe(
      data => {
        console.log(data);
        this.service.reservationData = data;
        this.router.navigate(['/checkin']);
      }
    );

  }


}
