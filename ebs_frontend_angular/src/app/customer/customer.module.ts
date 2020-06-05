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



@NgModule({
  declarations: [CustomerHomeComponent,ViewBillHistoryComponent,ViewPaymentHistoryComponent,ChangePasswordComponent,UpdateProfileComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    FormsModule
  ]
})
export class CustomerModule { }
