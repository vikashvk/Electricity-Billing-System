import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router } from '../../../../node_modules/@angular/router';
import { AdminService } from '../../services/admin.service';
import { Bills } from '../../models/bills';

@Component({
  selector: 'app-update-bill-view',
  templateUrl: './update-bill-view.component.html',
  styleUrls: ['./update-bill-view.component.css']
})
export class UpdateBillViewComponent implements OnInit {
  
  id: number;
  billr: Bills;
  submitted = false;

  constructor(private route: ActivatedRoute,private router: Router, private adminService: AdminService) { }
  
  ngOnInit(): void {
    this.billr = new Bills();

    this.id = this.route.snapshot.params['id'];
    
    this.adminService.getBillByid(this.id)
      .subscribe(data => {
        console.log(data)
        this.billr = data;
      }, error => console.log(error));
  }

  updateBill() {
    this.adminService.editBill(this.id, this.billr)
      .subscribe(data => console.log(data), error => console.log(error));
    this.billr = new Bills();
  }

  onSubmit() {
    this.submitted = true;
    this.updateBill();    
  }


}
