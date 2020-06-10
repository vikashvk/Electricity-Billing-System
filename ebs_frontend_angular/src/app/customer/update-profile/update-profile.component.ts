import { Component, OnInit } from '@angular/core';
import { CustomerDetail } from 'src/app/models/customer-detail';
import { CustomerService } from 'src/app/services/customer.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.scss']
})
export class UpdateProfileComponent implements OnInit {


  constructor(private toastr: ToastrService,private customerService: CustomerService, private router: Router) { }
  customerDetails: CustomerDetail = <CustomerDetail>{};
  
  ngOnInit(): void {
    this.getProfileDetails();
  }
  updateProfileDetails(newCustomerDetails: CustomerDetail) {
    console.log(newCustomerDetails);
    this.customerService.updateProfileDetails(newCustomerDetails)
      .subscribe((data) => {
        console.log(data);
        this.toastr.success("Profile has been updated successfully");
      }, error => {
        throw error;
      })
  }
  getProfileDetails() {
    this.customerService.getProfileDetails()
      .subscribe((data) => {
        console.log(data);
        this.customerDetails.firstName = data.firstName;
        
        this.customerDetails.lastName = data.lastName;
        
        this.customerDetails.mobile = data.mobile;
        this.customerDetails.line1 = data.address.line1;
        this.customerDetails.line2 = data.address.line2;
        this.customerDetails.city = data.address.city;
        this.customerDetails.state = data.address.state;
        this.customerDetails.country = data.address.country;
        this.customerDetails.pincode = data.address.pincode;
      }, error => {
        throw error;
      })
  }
  cancel()
  {
    this.router.navigate(['/user'])
  }
}
