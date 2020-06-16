import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerDetails } from '../../models/customerdetail';
import { Observable } from 'rxjs';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-customerview',
  templateUrl: './customerview.component.html',
  styleUrls: ['./customerview.component.css']
})
export class CustomerviewComponent implements OnInit {
custdet: Observable<CustomerDetails[]>;

  constructor(private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    // debugger
    this.custdet = this.adminService.getCustomerList();
  }

  deleteBill(id: number) {
    this.adminService.deleteBill(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  Billdetails(id: number){
    this.router.navigate(['billdetails', id]);
  }
  addBill(custId:number){
    this.router.navigate(['addbill',custId]);
  }
}
