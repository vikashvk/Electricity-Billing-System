import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-interaction',
  templateUrl: './customer-interaction.component.html',
  styleUrls: ['./customer-interaction.component.scss']
})
export class CustomerInteractionComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  navigateToUpdateProfile()
{
  console.log("...................");
  this.router.navigate(['/app-update-profile'])
}

navigateToChangePassword()
{
  console.log("...................");
  this.router.navigate(['/app-change-password'])
}
navigateToViewBillHistory()
{
  console.log("...................");
  this.router.navigate(['/app-view-bill-history'])
}

navigateToViewProfile()
{
  console.log("...................");
  this.router.navigate(['/app-view-profile'])
}
navigateToViewPaymentHistory()
{
  console.log("...................");
  this.router.navigate(['/app-view-payment-history'])
}

}
