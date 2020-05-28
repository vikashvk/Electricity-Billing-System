import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-view-bill-history',
  templateUrl: './view-bill-history.component.html',
  styleUrls: ['./view-bill-history.component.scss']
})
export class ViewBillHistoryComponent implements OnInit {

  constructor(private service:CustomerService) { }

  customerId:any
  ngOnInit(): void {
    this.customerId =localStorage.getItem('customerId')
    this.viewBillHistory();
  }
 viewBillHistory()
 {
  console.log(this.customerId);
  var bill= this.service.viewBillHistory(this.customerId);
  bill.subscribe((data)=>
  {
    console.log(data);
   // this.payment= data;
  })
 }
}
