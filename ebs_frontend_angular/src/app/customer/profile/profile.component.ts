import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
user:any;
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomerProfile().subscribe(data => {
      this.user = data;
      console.log(data);
    });;
  }

}
