import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CustomerService } from '../services/customer.service';
import { PaymentRequestModel } from '../models/payment-request-model';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-credit-card-form',
  templateUrl: './credit-card-form.component.html',
  styleUrls: ['./credit-card-form.component.css']
})
export class CreditCardFormComponent implements OnInit {
  billId: number = 0;
  amount: number = 0;
  constructor(private toastr: ToastrService,private location:Location,@Inject('API_URL') private apiUrl: string, private http: HttpClient, private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.currentbillId.subscribe(billId => { this.billId = billId; });
    this.customerService.currentamount.subscribe(amount => this.amount = amount);
  }

  chargeCreditCard() {
    let form = document.getElementsByTagName("form")[0];
    (<any>window).Stripe.card.createToken({
      number: form.cardNumber.value,
      exp_month: form.expMonth.value,
      exp_year: form.expYear.value,
      cvc: form.cvc.value
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.chargeCard(token);
      } else {
        console.log(response.error.message);
      }
    });
  }
  chargeCard(token: string) {
    let paymentRequestBody: PaymentRequestModel = { token: token, billId: this.billId }
    this.http.post(this.apiUrl + 'payment/charge', paymentRequestBody)
      .subscribe(resp => {
        this.toastr.success("Payment successful");
        this.location.back();
      })
  }
}
