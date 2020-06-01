import { Component, OnInit } from '@angular/core';
import {NgxSpinnerService} from "ngx-spinner"
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthenticationService } from '../services/authentication.service';
import { Customer } from '../entity/Customer';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
hide =true
  constructor(public dialogRef: MatDialogRef<RegisterComponent>,private authenticationService:AuthenticationService,
    private router:Router ,private spinner: NgxSpinnerService) { }

 customer:Customer
  
  registerCustomer(data: Customer) {
    console.log(data);
    this.customer  = new Customer(data.custId,data.custName ,data.custMobile,data.custPassword,"","");
    var user1 = this.authenticationService.registerCustomer(this.customer);
    user1.subscribe((data) => {
      console.log(data)
      alert("Register Successfully :-)\nYour Customer Id is : " + data.custId)
      this.router.navigate(['/login'])
    })
    this.dialogRef.close();
  }
  ngOnInit() {
        this.spinner.show();
        setTimeout(() => {
          this.spinner.hide();
        }, 3000);
  }

}
