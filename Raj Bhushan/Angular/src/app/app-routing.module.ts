import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CustomerInteractionComponent } from './customer-interaction/customer-interaction.component';
import { ChangePasswordComponent } from './customer/change-password/change-password.component';
import { UpdateProfileComponent } from './customer/update-profile/update-profile.component';
import { ViewBillHistoryComponent } from './customer/view-bill-history/view-bill-history.component';
import { LogoutComponent } from './logout/logout.component';
import { ViewProfileComponent } from './customer/view-profile/view-profile.component';
import { ViewPaymentHistoryComponent } from './customer/view-payment-history/view-payment-history.component';


const routes: Routes = [
  {
    path:'' , redirectTo:'home' ,pathMatch:'full'
  },
  {
  path:'home',
  component:HomeComponent
  },

  {
    path:'contact',
    component:ContactComponent
  },
  {
    path:'about',
    component:AboutComponent
  },
  {
    path:'logout',
    component:LogoutComponent
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path:'customer-interaction',
    component:CustomerInteractionComponent
  },
  {
    path:'app-change-password',
    component:ChangePasswordComponent
  },
  {
    path:'app-update-profile',
    component:UpdateProfileComponent
  },
  {
    path:'app-view-bill-history',
    component:ViewBillHistoryComponent
  },
  {
    path:'app-view-profile',
    component:ViewProfileComponent
  },
  {
    path:'app-view-payment-history',
    component:ViewPaymentHistoryComponent
  }

 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
