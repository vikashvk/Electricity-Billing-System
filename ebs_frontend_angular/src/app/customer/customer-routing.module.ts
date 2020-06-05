import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ViewBillHistoryComponent } from './view-bill-history/view-bill-history.component';
import { ViewPaymentHistoryComponent } from './view-payment-history/view-payment-history.component';


const routes: Routes = [
  { path: '', component: CustomerHomeComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { path: 'update-profile', component: UpdateProfileComponent },
  { path: 'bills', component: ViewBillHistoryComponent },
  { path: 'payments', component: ViewPaymentHistoryComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
