import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ViewBillHistoryComponent } from './view-bill-history/view-bill-history.component';
import { ViewPaymentHistoryComponent } from './view-payment-history/view-payment-history.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { GiveFeedbackComponent } from './give-feedback/give-feedback.component';
import { ViewBillDetailsComponent } from './view-bill-details/view-bill-details.component';



@NgModule({
  declarations: [CustomerHomeComponent,ViewBillHistoryComponent,ViewPaymentHistoryComponent,ChangePasswordComponent,UpdateProfileComponent, GiveFeedbackComponent, ViewBillDetailsComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    FormsModule
  ]
})
export class CustomerModule { }
