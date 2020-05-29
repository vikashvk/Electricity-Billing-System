import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Customer } from '../entity/Customer';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  customer: Customer

  constructor(public dialogRef: MatDialogRef<LoginComponent>,
    private authenticationService: AuthenticationService,
    private spinner: NgxSpinnerService, private router: Router) { }
    hide = true;
  loginCustomer(check: any) {

    this.customer = new Customer(check.custId, "", "", check.custPassword,"","");
    var sameer = this.authenticationService.loginCustomer(this.customer);
    sameer.subscribe((data) => {
       localStorage.setItem('customerId',data);

      console.log(data)
      if (data == check.custId) {
        this.authenticationService.sendToken(data);

        alert("Login successful for id " + data);
       // this.router.navigate(['home'])
      }
      else { alert("Login denied for id " + check.custId); }
    }, error => {
      console.log(error);
      let errString = '';
      for (var err of error.error.errors) {
        errString += err + ' ';
      }
      alert(errString);
    })
    this.dialogRef.close();
  }

  ngOnInit(): void {

    this.spinner.show();
    setTimeout(() => {
      this.spinner.hide();
    }, 1000);
  }


}
