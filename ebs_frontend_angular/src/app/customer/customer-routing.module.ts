import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ViewBillHistoryComponent } from './view-bill-history/view-bill-history.component';
import { ViewPaymentHistoryComponent } from './view-payment-history/view-payment-history.component';
import { ViewProfileComponent } from './view-profile/view-profile.component';
import { GiveFeedbackComponent } from './give-feedback/give-feedback.component';
import { ViewBillDetailsComponent } from './view-bill-details/view-bill-details.component';


const routes: Routes = [
  { path: '', component: CustomerHomeComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { path: 'update-profile', component: UpdateProfileComponent },
  { path: 'bills', component: ViewBillHistoryComponent },
  { path: 'bills/:id', component: ViewBillDetailsComponent },
  { path: 'payments', component: ViewPaymentHistoryComponent },
  { path: 'view-profile', component: ViewProfileComponent },
  { path: 'give-feedback', component: GiveFeedbackComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
