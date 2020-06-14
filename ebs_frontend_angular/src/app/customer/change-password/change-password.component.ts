import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { Router } from '@angular/router';
import { ChangePasswordModel } from 'src/app/models/change-password-model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  customerId: any;
  constructor(private toastr: ToastrService, private customerService: CustomerService, private router: Router) { }
  customer1: any = {}
  ngOnInit(): void {
  }
  changePassword(credentials: ChangePasswordModel) {//Changes the password, when successful redirects to /user
    this.customerService.changePassword(credentials)
      .subscribe((data) => {
        console.log(data);
        this.toastr.success("Password has been changed successfully.");
        this.router.navigate(['/user']);
      },
        error => {
          throw error;
        });
  }
  cancel() {//redirects to /user
    this.router.navigate(['/user']);
  }
}
