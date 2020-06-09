import { Component, OnInit } from '@angular/core';
import { Bills } from '../../models/bills';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { AdminService } from '../../services/admin.service';


@Component({
  selector: 'app-add-bill-view',
  templateUrl: './add-bill-view.component.html',
  styleUrls: ['./add-bill-view.component.css']
})
export class AddBillViewComponent implements OnInit {
  billr: Bills = new Bills();
  submitted = false;


  constructor(private route: ActivatedRoute, private router: Router, private adminService: AdminService) { }
  custId: string;
  ngOnInit(): void {
    this.custId = this.route.snapshot.paramMap.get('custId');
  }

  newBill(data: Bills): void {
    this.submitted = false;
    this.billr = new Bills();
    
  }

  save() {
    console.log(this.billr);
    this.billr.customerid = Number(this.custId);
    this.adminService.createBill(this.billr)
      .subscribe(data => console.log(data), error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }




}
