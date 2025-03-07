import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReservationService } from '../../services/checkin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkin',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './checkin.component.html',
  styleUrl: './checkin.component.css'
})
export class CheckinComponent {

  noOfbags: number = 0;
  data: any;

  constructor(private service:ReservationService,private router:Router) { }

  ngOnInit(){
    this.data = this.service.reservationData;
  }

  checkIn(){

    let request = {
      id : this.data.id,
      checkedIn : true,
      numberOfBags : this.noOfbags
    }

    this.service.updateReservation(request).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/confirm']);
      }
    );
  
  }


}
