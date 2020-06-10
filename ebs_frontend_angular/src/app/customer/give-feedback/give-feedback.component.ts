import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { feedback } from 'src/app/models/feedback';
import { CustomerService } from 'src/app/services/customer.service';
import { CustomerDetail } from 'src/app/models/customer-detail';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-give-feedback',
  templateUrl: './give-feedback.component.html',
  styleUrls: ['./give-feedback.component.css']
})
export class GiveFeedbackComponent implements OnInit {

  constructor(private toastr: ToastrService,private router:Router, private customerService:CustomerService) { }
  customerDetails: CustomerDetail = <CustomerDetail>{};
  ngOnInit(): void {
    this.getProfileDetails();
  }
  submitFeedbackForm(feed:feedback)
  {
   
    feed.firstName = this.customerDetails.firstName;
    feed.lastName = this.customerDetails.lastName;
    console.log(feed);
    this.customerService.giveFeedback(feed)
    .subscribe((data)=>
     {
       console.log(data);
       this.toastr.success("Feedback has been send successfully.");
       this.router.navigate(['/user']);
     },
      error => {throw error;
     });
  }
  getProfileDetails() {
    this.customerService.getProfileDetails()
      .subscribe((data) => {
        console.log(data);
        this.customerDetails.firstName = data.firstName;
        this.customerDetails.lastName = data.lastName;
      
      }, error => {
        throw error;
      })
  }

  cancel()
  {
    this.router.navigate(['/user'])
  }
}
