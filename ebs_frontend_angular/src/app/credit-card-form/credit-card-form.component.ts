import { Component, OnInit, Inject } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { CustomerService } from '../services/customer.service';


@Component({
  selector: 'app-credit-card-form',
  templateUrl: './credit-card-form.component.html',
  styleUrls: ['./credit-card-form.component.css']
})
export class CreditCardFormComponent implements OnInit {
amount:number = 0;
  constructor(@Inject('API_URL') private apiUrl: string,private http: HttpClient,private customerService:CustomerService) { }

  ngOnInit(): void {
    this.customerService.currentAmount.subscribe(amount=> {this.amount = amount;console.log(amount);});
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
    console.log(this.amount);
    const headers = new HttpHeaders({'token': token
    , 'amount': this.amount.toString()
  });
    this.http.post(this.apiUrl + 'payment/charge', {}, {headers: headers})
      .subscribe(resp => {
        console.log(resp);
      })
  }
}
