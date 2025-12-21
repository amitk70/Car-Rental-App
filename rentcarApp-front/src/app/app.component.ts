import { Component } from '@angular/core';
import { DatabaseService } from './auth/services/database.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'rentcarApp-front';

isAdminLoggedIn:boolean = DatabaseService.isAdminLoggedIn();
isCustomerLoggedIn:boolean = DatabaseService.isCustomerLoggedIn();

}
