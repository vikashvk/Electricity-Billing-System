import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Location } from '@angular/common';
@Component({
  selector: 'app-view-bill-details',
  templateUrl: './view-bill-details.component.html',
  styleUrls: ['./view-bill-details.component.css']
})
export class ViewBillDetailsComponent implements OnInit {
  bill: any;
  constructor(private location: Location, private route: ActivatedRoute, private customerService: CustomerService) { }
  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => { return this.customerService.getBillDetails(params['id']); }))
      .subscribe((data) => {
        console.log(data);
        this.bill = data
      }, error => {
        this.location.back();
        throw error;
      });

  }
}
