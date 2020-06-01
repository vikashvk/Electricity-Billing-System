import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  customerId:any
  constructor(private service:CustomerService, private router:Router) { }
  customer1:any={}
  ngOnInit(): void {
    this.customerId=localStorage.getItem('customerId');
    this.viewCustomerProfile();
  }
  changePassword(data:any)
  {
    var change = this.service.changePassword(data.custId, data.oldPassword , data.newPassword);
    change.subscribe((data)=>
    {
      console.log(data);
      alert("Password has been changed successfully")
    },
     error => {
      console.log(error);
      let errString = '';
      for (var err of error.error.errors) {
        errString += err + ' ';
      }
      alert(errString);
    })
  }
  viewCustomerProfile()
  {
    var result = this.service.viewCustomerProfile(this.customerId)
    result.subscribe((data)=>
    {
      console.log(data);
      this.customer1= data;
    })
  }
}
