import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/entity/Customer';
import { CustomerService } from 'src/app/services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.scss']
})
export class UpdateProfileComponent implements OnInit {


  constructor(private service:CustomerService, private router:Router) { }
  customerId:any
  customer:Customer
  customer1:any={}
  ngOnInit(): void {
    this.customerId=localStorage.getItem('customerId');
    this.viewCustomerProfile();
  }
   editProfile(data:Customer)
   {
     console.log(data);
     this.customer = new Customer(0,data.custName,data.custMobile,"",data.custAddress,data.custCity);
       var user = this.service.editProfile(this.customerId, this.customer);
       user.subscribe((data)=>
       {
         console.log(data);
         alert("Profile has been updated successfully");
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
