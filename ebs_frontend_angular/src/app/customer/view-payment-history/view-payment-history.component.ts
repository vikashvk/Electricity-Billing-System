import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { Payment } from 'src/app/models/payment';

@Component({
  selector: 'app-view-payment-history',
  templateUrl: './view-payment-history.component.html',
  styleUrls: ['./view-payment-history.component.scss']
})
export class ViewPaymentHistoryComponent implements OnInit {

  payment: Payment[] = []
  customerId: any
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    // this.getAllPayments();
  }

  viewPaymentHistory() {
    console.log(this.customerId);
    var payment = this.customerService.getAllPayments();
    payment.subscribe((data) => {
      console.log(data);
      // this.payment= data;
    },
      error => {
        throw error;
      })
  }
}
