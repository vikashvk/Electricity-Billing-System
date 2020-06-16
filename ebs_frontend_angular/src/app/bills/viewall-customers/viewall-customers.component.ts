import { Component, OnInit } from '@angular/core';
import { CustomerDetails } from '../../models/customerdetail';
import { Observable } from 'rxjs';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewall-customers',
  templateUrl: './viewall-customers.component.html',
  styleUrls: ['./viewall-customers.component.css']
})
export class ViewallCustomersComponent implements OnInit {

  custdet: Observable<CustomerDetails[]>;
  constructor(private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    debugger
    this.custdet = this.adminService.getCustomerList();
  }

}
