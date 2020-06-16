import { Component, OnInit } from '@angular/core';
import { Bills } from '../../models/bills';
import { Observable } from 'rxjs';
import {ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-viewallbills',
  templateUrl: './viewallbills.component.html',
  styleUrls: ['./viewallbills.component.css']
})
export class ViewallbillsComponent implements OnInit {
  bill: Observable<Bills[]>;
  

  constructor(private route: ActivatedRoute,private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.reloadData();
  }


  reloadData() {
    debugger
    this.bill = this.adminService.getBillList();
  }

}
