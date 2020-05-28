import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { Payment } from 'src/app/entity/Payment';

@Component({
  selector: 'app-view-payment-history',
  templateUrl: './view-payment-history.component.html',
  styleUrls: ['./view-payment-history.component.scss']
})
export class ViewPaymentHistoryComponent implements OnInit {

  payment:Payment[]=[]
  customerId:any
  constructor(private service:CustomerService) { }

  ngOnInit(): void {
    this.customerId =localStorage.getItem('customerId')
    this.viewPaymentHistory();
  }
  
  viewPaymentHistory()
  {
    console.log(this.customerId);
    var payment= this.service.viewPaymentHistory(this.customerId);
    payment.subscribe((data)=>
    {
      console.log(data);
     // this.payment= data;
    })
  }
}
