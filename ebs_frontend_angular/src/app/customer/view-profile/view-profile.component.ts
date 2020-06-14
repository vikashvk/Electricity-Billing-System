import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { CustomerDetail } from 'src/app/models/customer-detail';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.scss']
})
export class ViewProfileComponent implements OnInit {

 
  customer: CustomerDetail = <CustomerDetail>{};
  constructor(private service:CustomerService) { }

  ngOnInit(): void {
   
    this.viewCustomerProfile();
  }
 
  viewCustomerProfile()//fetches customer detail from customer service
  {
    var profile = this.service.getProfileDetails();
    profile.subscribe((data)=>
    {
      console.log(data);
      this.customer.firstName = data.firstName;
      this.customer.lastName = data.lastName;
      this.customer.mobile = data.mobile;
      this.customer.line1 = data.address.line1;
        this.customer.line2 = data.address.line2;
        this.customer.city = data.address.city;
        this.customer.state = data.address.state;
        this.customer.country = data.address.country;
        this.customer.pincode = data.address.pincode;
    })
  }
}
