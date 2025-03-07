import { Routes } from '@angular/router';
import { StartcheckinComponent } from './components/startcheckin/startcheckin.component';
import { CheckinComponent } from './components/checkin/checkin.component';
import { ConfirmcheckinComponent } from './components/confirmcheckin/confirmcheckin.component';

export const routes: Routes = [
    { path: 'startCheckIn', component: StartcheckinComponent },
    { path: 'checkin', component: CheckinComponent },
    { path: 'confirm', component: ConfirmcheckinComponent }
];
