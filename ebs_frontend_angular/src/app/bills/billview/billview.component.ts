import { Component, OnInit } from '@angular/core';
import { Bills } from '../../models/bills';
import { Observable } from 'rxjs';
import {ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-billview',
  templateUrl: './billview.component.html',
  styleUrls: ['./billview.component.css']
})
export class BillviewComponent implements OnInit {
  bill: Observable<Bills[]>;
  id: number;

  constructor(private route: ActivatedRoute,private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.reloadData();
  }

  reloadData() {
    debugger
    this.bill = this.adminService.getAllBillByCustId(this.id);
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

  updateBill(id: number){
    this.router.navigate(['updateBill', id]);
  }

}
