import { Component } from '@angular/core';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';
import { RegistrationModel } from '../models/registration-model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent {
  successMessage: string = "Registered successfully, Verify your mail before login.";
  hide: boolean = true;
  constructor(private toastr: ToastrService, private userAuthService: UserAuthService, private router: Router) { }
  registerCustomer(regDetails: RegistrationModel) {
    console.log(regDetails);
    this.userAuthService.registerCustomer(regDetails)
      .subscribe((data) => {
        this.toastr.success(this.successMessage);
        this.router.navigate(['/login'])
      },
        error => {
          throw error;
        });
  }
}