import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { Router } from '@angular/router';
import {ChangePasswordModel } from 'src/app/models/change-password-model';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  customerId:any
  constructor(private customerService:CustomerService) { }
  customer1:any={}
  ngOnInit(): void {
  }
  changePassword(credentials:ChangePasswordModel)
  {
    this.customerService.changePassword(credentials)
   .subscribe((data)=>
    {
      console.log(data);
      alert("Password has been changed successfully.")
    },
     error => {throw error;
    });
  }
}
