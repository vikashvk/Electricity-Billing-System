import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.scss']
})
export class ViewProfileComponent implements OnInit {

  customerId:any
  customer:any={}
  constructor(private service:CustomerService) { }

  ngOnInit(): void {
    this.customerId=localStorage.getItem('customerId');
    this.viewCustomerProfile();
  }
 
  viewCustomerProfile()
  {
    var profile = this.service.viewCustomerProfile(this.customerId);
    profile.subscribe((data)=>
    {
      console.log(data);
      this.customer = data;
    })
  }
}
